package com.lderic.mcbridge.logging

import com.lderic.mcbridge.console.Console
import com.lderic.mcbridge.text.Text
import com.lderic.mcbridge.text.TextChain


internal abstract class AbstractLogger(private val name: String) : Logger {
    private val console: Console = Console.getInstance()

    override fun info(msg: Text?) {
        log(msg, Logger.Level.INFO)
    }

    override fun info(msg: TextChain?) {
        log(msg, Logger.Level.INFO)
    }

    override fun info(msg: String?) {
        log(msg, Logger.Level.INFO)
    }

    override fun info(msg: Any?) {
        log(msg.toString(), Logger.Level.INFO)
    }

    override fun warn(msg: Text?) {
        log(msg, Logger.Level.WARN)
    }

    override fun warn(msg: TextChain?) {
        log(msg, Logger.Level.WARN)
    }

    override fun warn(msg: String?) {
        log(msg, Logger.Level.WARN)
    }

    override fun warn(msg: Any?) {
        log(msg.toString(), Logger.Level.WARN)
    }

    override fun error(msg: Text?) {
        log(msg, Logger.Level.ERROR)
    }

    override fun error(msg: TextChain?) {
        log(msg, Logger.Level.ERROR)
    }

    override fun error(msg: String?) {
        log(msg, Logger.Level.ERROR)
    }

    override fun error(msg: Any?) {
        log(msg.toString(), Logger.Level.ERROR)
    }

    /**
     * Returns pattern before message.
     */
    abstract fun pattern(logType: String): String

    private fun log(msg: String?, level: Logger.Level) {
        console.write(Text.of("[$name] ${pattern(level.name)} ${msg ?: "null"}").setColor(level.color))
    }

    private fun log(msg: Text?, level: Logger.Level) {
        if (msg?.hasColor() == true) {
            console.write(TextChain.of(Text("[$name] ${pattern(level.name)} ").setColor(level.color), msg))
        } else {
            console.write(Text("[$name] ${pattern(level.name)} ${msg?.toString() ?: "null"}").setColor(level.color))
        }
    }

    private fun log(msg: TextChain?, level: Logger.Level) {
        if (msg == null) {
            console.write(Text("[$name] ${pattern(level.name)} null").setColor(level.color))
        } else {
            msg.forEach {
                if (!it.hasColor()) {
                    it.color = level.color
                }
            }
            console.write(
                TextChain()
                    .append(Text("[$name] ${pattern(level.name)} ").setColor(level.color))
                    .append(msg)
            )
        }
    }
}