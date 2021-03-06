package com.lderic.mcbridge.logging

import com.lderic.mcbridge.console.Console
import com.lderic.mcbridge.text.Text
import com.lderic.mcbridge.text.TextChain

object MinecraftLogger : Logger {
    private val console: Console = Console.getInstance()
    override fun info(msg: Text?) {
        console.write(msg)
    }

    override fun info(msg: TextChain?) {
        console.write(msg)
    }

    override fun info(msg: String?) {
        info(Text("[Minecraft] $msg").use(Logger.Level.INFO.text))
    }

    override fun info(msg: Any?) {
        info(msg.toString())
    }

    override fun warn(msg: Text?) {
        console.write(msg)
    }

    override fun warn(msg: TextChain?) {
        console.write(msg)
    }

    override fun warn(msg: String?) {
        warn(Text("[Minecraft] $msg").use(Logger.Level.WARN.text))
    }

    override fun warn(msg: Any?) {
        warn(msg.toString())
    }

    override fun error(msg: Text?) {
        console.write(msg)
    }

    override fun error(msg: TextChain?) {
        console.write(msg)
    }

    override fun error(msg: String?) {
        error(Text("[Minecraft] $msg").use(Logger.Level.ERROR.text))
    }

    override fun error(msg: Any?) {
        error(msg.toString())
    }
}