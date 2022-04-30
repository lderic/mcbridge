package com.lderic.mcbridge.logging


internal abstract class AbstractLogger(private val name: String) : Logger {
    override fun info(message: Any?) {
        SystemIO.out.println("[$name] ${pattern("INFO")} $message")
    }

    override fun warn(message: Any?) {
        SystemIO.out.println("[$name] ${pattern("WARN")} $message")
    }

    override fun error(message: Any?) {
        SystemIO.err.println("[$name] ${pattern("ERROR")} $message")
    }

    /**
     * Returns pattern before message.
     */
    abstract fun pattern(logType: String): String
}