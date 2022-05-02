package com.lderic.mcbridge.process

import com.lderic.mcbridge.MCBridgeProperties
import com.lderic.mcbridge.MCBridgeProperties.pluginPath
import com.lderic.mcbridge.logging.InitLogger
import com.lderic.mcbridge.plugin.pluginCompiledDir
import java.io.File


object ProcessFactory {
    @JvmStatic
    fun compileProcess(name: String): Process {
        return ProcessBuilder(
            "javac",
            "-cp",
            System.getProperty("java.class.path"),
            "-d",
            pluginCompiledDir,
            name
        )
            .directory(File(pluginPath))
            .redirectErrorStream(true)
            .start()
    }

    @JvmStatic
    fun launchMinecraft(): MCProcess {
        val commands = try {
            MCBridgeProperties.startCommand!!.split(" ")
        } catch (e: Exception) {
            InitLogger.error("Minecraft starting command does not correct")
            throw e
        }
        return MCProcess(ProcessBuilder(commands).directory(File(MCBridgeProperties.serverPath)))
    }
}