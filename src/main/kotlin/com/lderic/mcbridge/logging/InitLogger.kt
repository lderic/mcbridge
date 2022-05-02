package com.lderic.mcbridge.logging

internal object InitLogger : AbstractLogger("Init") {
    override fun pattern(logType: String): String = "[$logType]"

    override fun getMessage(log: String): String {
        throw Exception("Not available")
    }

    override fun getLogLevel(log: String): String {
        throw Exception("Not available")
    }
}