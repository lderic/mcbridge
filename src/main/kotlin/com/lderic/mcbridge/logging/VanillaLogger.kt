package com.lderic.mcbridge.logging

import java.util.*

internal class VanillaLogger constructor(name: String) : AbstractLogger(name) {
    override fun pattern(logType: String): String {
        return "[%tT] [${Thread.currentThread().name}/${logType}]:".format(Date())
    }

    override fun getMessage(log: String): String {
        return log.split("]: ")[1]
    }

    override fun getLogLevel(log: String): String {
        return try {
            log.split("]: ")[0].split("/")[1]
        }catch (e:Exception) {
            "INFO"
        }
    }
}