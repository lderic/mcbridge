package com.lderic.mcbridge.logging

import com.lderic.mcbridge.api.Logger


internal class LoggerImpl(private val name: String) : Logger {
    override fun info(message: Any) {
        println("[$name/INFO] $message")
    }

    override fun warn(message: Any) {
        println("[$name/WARN] $message")
    }

    override fun error(message: Any) {
        println("[$name/ERROR] $message")
    }

    override fun debug(message: Any) {
        println("[$name/DEBUG] $message")
    }
}