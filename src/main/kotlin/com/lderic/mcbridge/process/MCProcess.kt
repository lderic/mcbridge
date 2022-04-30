package com.lderic.mcbridge.process

import com.lderic.mcbridge.logging.MinecraftLogger
import java.io.BufferedReader
import java.io.BufferedWriter
import kotlin.concurrent.thread

class MCProcess constructor(
    private val processBuilder: ProcessBuilder
) {
    private val logger = MinecraftLogger

    private lateinit var process: Process

    init {
        Runtime.getRuntime().addShutdownHook(Thread {
            process.destroy()
        })
    }

    private lateinit var stdin: BufferedWriter
    private lateinit var stdout: BufferedReader
    private lateinit var stderr: BufferedReader

    private val stdoutThread: Thread = thread(name = "stdout", start = false) {
        stdout = process.inputReader()
        var line: String?
        while (stdout.readLine().also { line = it } != null) {
            logger.info(line)
        }
        logger.info("Process ended")
        //TODO: handle process exit
    }

    private val stderrThread: Thread = thread (name = "stderr", start = false) {
        stderr = process.inputReader()
        var line: String?
        while (stderr.readLine().also { line = it } != null) {
            logger.info(line)
        }
    }

    fun start() {
        process = processBuilder.start()
        stdoutThread.start()
        stderrThread.start()
        stdin = process.outputWriter()
    }

    fun writeln(line: String) {
        stdin.write(line)
        stdin.newLine()
        stdin.flush()
    }

    fun destroy() {
        process.destroy()
    }

    fun waitFor() {
        process.waitFor()
    }

    fun exitValue() = process.exitValue()
}