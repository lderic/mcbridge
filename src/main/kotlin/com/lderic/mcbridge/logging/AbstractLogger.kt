package com.lderic.mcbridge.logging

import com.lderic.mcbridge.console.Console


internal abstract class AbstractLogger(private val name: String) : Logger {
    override fun info(message: Any?) {
        Console.getInstance().write("[$name] ${pattern("INFO")} $message")
    }

    override fun warn(message: Any?) {
        Console.getInstance().write("[$name] ${pattern("WARN")} $message")
    }

    override fun error(message: Any?) {
        Console.getInstance().write("[$name] ${pattern("ERROR")} $message")
    }

    /**
     * Returns pattern before message.
     */
    abstract fun pattern(logType: String): String
}