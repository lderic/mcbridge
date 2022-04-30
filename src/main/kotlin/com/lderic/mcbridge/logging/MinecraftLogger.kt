package com.lderic.mcbridge.logging

import com.lderic.mcbridge.console.Console

object MinecraftLogger : Logger {
    override fun info(message: Any?) {
        Console.getInstance().write("[Minecraft] $message")
    }

    override fun warn(message: Any?) {
        Console.getInstance().write("[Minecraft] $message")
    }

    override fun error(message: Any?) {
        Console.getInstance().write("[Minecraft] $message")
    }
}