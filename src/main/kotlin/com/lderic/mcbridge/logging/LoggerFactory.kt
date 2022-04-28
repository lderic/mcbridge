package com.lderic.mcbridge.logging

import com.lderic.mcbridge.api.Logger

internal interface LoggerFactory {
    fun getLogger(name: String): Logger

    fun getLogger(clazz: Class<*>): Logger

    companion object : LoggerFactory {
        override fun getLogger(name: String): Logger {
            return LoggerImpl(name)
        }

        override fun getLogger(clazz: Class<*>): Logger {
            return LoggerImpl(clazz.simpleName)
        }
    }
}