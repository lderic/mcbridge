package com.lderic.mcbridge.process

import org.jline.terminal.TerminalBuilder
import java.io.BufferedReader
import java.io.BufferedWriter
import kotlin.concurrent.thread

class MCProcess constructor(
    private val processBuilder: ProcessBuilder
) {
    init {
        TerminalBuilder.builder().system(true).build()
    }

    private lateinit var process: Process

    fun start() {
        process = processBuilder.start()

    }

    private lateinit var stdin: BufferedWriter
    private lateinit var stdout: BufferedReader
    private lateinit var stderr: BufferedReader

    init {
        thread(name = "Minecraft STDOUT", start = false) {
            stdout = process.inputStream.bufferedReader()
            var line: String?
            while (stdout.readLine().also { line = it } != null) {
                println(line)
            }
        }
    }
}