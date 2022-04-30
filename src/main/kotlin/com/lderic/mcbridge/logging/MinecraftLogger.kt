package com.lderic.mcbridge.logging

object MinecraftLogger : Logger {
    override fun info(message: Any) {
        SystemIO.out.println("[Minecraft] $message")
    }

    override fun warn(message: Any) {
        SystemIO.out.println("[Minecraft] $message")
    }

    override fun error(message: Any) {
        SystemIO.out.println("[Minecraft] $message")
    }
}