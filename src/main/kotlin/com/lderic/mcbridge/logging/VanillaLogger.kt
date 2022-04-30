package com.lderic.mcbridge.logging

import java.util.*

internal class VanillaLogger constructor(name: String) : AbstractLogger(name) {
    override fun pattern(logType: String): String {
        return "[%tT] [${Thread.currentThread().name}/${logType}]:".format(Date())
    }
}