package com.lderic.mcbridge.process

import com.lderic.mcbridge.MCBridge
import com.lderic.mcbridge.event.EventExecutor
import com.lderic.mcbridge.event.Events
import com.lderic.mcbridge.logging.AbstractLogger
import com.lderic.mcbridge.logging.MinecraftLogger
import com.lderic.mcbridge.minecraft.Server
import com.lderic.mcbridge.minecraft.Server.State
import com.lderic.mcbridge.minecraft.Version
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.BufferedWriter
import kotlin.concurrent.thread
import kotlin.coroutines.EmptyCoroutineContext

class MCProcess constructor(
    private val processBuilder: ProcessBuilder
) {
    private val logger = MinecraftLogger
    private val server: Server = Server.selectServer()

    private val MCProcessScope = CoroutineScope(EmptyCoroutineContext)
    private lateinit var process: Process
    private var state = State.STOPPED

    private lateinit var version: Version

    init {
        Runtime.getRuntime().addShutdownHook(Thread {
            when (state) {
                State.INITIALIZING -> {
                    destroy()
                }
                State.RUNNING -> {
                    writeln("stop")
                    val job = MCProcessScope.launch {
                        delay(10000)
                        destroy()
                    }
                    waitFor()
                    job.cancel()
                }
                State.STOPPING -> {
                    val job = MCProcessScope.launch {
                        delay(10000)
                        destroy()
                    }
                    waitFor()
                    job.cancel()
                }
                else -> {}
            }
        })
    }

    private lateinit var stdin: BufferedWriter
    private lateinit var stdout: BufferedReader
    private lateinit var stderr: BufferedReader

    private val stdoutThread: Thread = thread(name = "stdout", start = false) {
        state = State.INITIALIZING
        stdout = process.inputReader()
        var line: String?
        while (stdout.readLine().also { line = it } != null) {
            logGame(line!!)
            val rawLog = line!!.split("]: ", limit = 2)[1]
            if (rawLog.startsWith("Starting minecraft server version")) {
                val version = rawLog.split(" ").last()
                this.version = Version(version)
            }
            if (rawLog.startsWith("Done ")) {
                break
            }
        }
        state = State.RUNNING
        EventExecutor.on(Events.SERVER_STARTED, Events.ServerRuntimeEvent(server, System.currentTimeMillis()))
        while (stdout.readLine().also { line = it } != null) {
            handleRunningLog(line!!)
        }
        MCBridge.logger.info("Process ended")
        state = State.STOPPED
        EventExecutor.on(Events.SERVER_STOPPED, Events.Event(System.currentTimeMillis()))
    }

    private val stderrThread: Thread = thread(name = "stderr", start = false) {
        stderr = process.errorReader()
        var line: String?
        while (stderr.readLine().also { line = it } != null) {
            logger.error(line)
        }
    }

    private fun logGame(log: String):Long {
        val time = System.currentTimeMillis()
        MCBridge.logger as AbstractLogger
        val type = MCBridge.logger.getLogLevel(log)
        if (type == "WARN") {
            logger.warn(log)
            EventExecutor.on(Events.SERVER_WARNING, Events.ServerLogEvent(server, log, time))
        } else {
            logger.info(log)
            EventExecutor.on(Events.SERVER_INFO, Events.ServerLogEvent(server, log, time))
        }
        return time;
    }

    private fun handleRunningLog(log: String) {
        val time = logGame(log)
        MCProcessScope.launch {
            val rawLog = log.split("]: ", limit = 2)[1]
            if (rawLog == "Stopping the server") {
                state = State.STOPPING
                EventExecutor.on(Events.SERVER_STOPPING, Events.Event(time))
            } else if (rawLog.startsWith("<") && rawLog.contains(">")) {
                // Player message
                val arr = rawLog.substring(1).split("> ")
                if (arr[1].startsWith(MCBridge.COMMAND_PREFIX)) {
                    MCBridge.handleCommand(arr[1].substring(MCBridge.COMMAND_PREFIX.length))
                } else {
                    EventExecutor.on(Events.PLAYER_CHAT, Events.PlayerChatEvent(server, arr[0], arr[1], time))
                }
            } else {
                if (rawLog.endsWith("joined the game")) {
                    EventExecutor.on(Events.PLAYER_JOIN, Events.PlayerEvent(server, rawLog.split(" ")[0], time))
                } else if (rawLog.endsWith("left the game")) {
                    EventExecutor.on(Events.PLAYER_LEAVE, Events.PlayerEvent(server, rawLog.split(" ")[0], time))
                }
            }
        }
    }

    fun start() {
        process = processBuilder.start()
        stdoutThread.start()
        stderrThread.start()
        stdin = process.outputWriter()
        MCProcessScope.launch {
            EventExecutor.on(Events.SERVER_INITIALIZING, Events.Event(System.currentTimeMillis()))
        }
    }

    fun writeln(line: String) {
        if (state == State.RUNNING) {
            synchronized(stdin) {
                stdin.write(line)
                stdin.newLine()
                stdin.flush()
            }
        }
    }

    fun destroy() {
        process.destroy()
    }

    fun waitFor() = process.waitFor()
}