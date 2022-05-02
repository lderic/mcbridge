package com.lderic.mcbridge.process

import com.lderic.mcbridge.MCBridgeProperties
import com.lderic.mcbridge.MCBridgeProperties.pluginPath
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
    fun launchMinecraft(): MCProcess? {
        val commands = MCBridgeProperties.startCommand?.split(" ") ?: return null
        return MCProcess(ProcessBuilder(commands).directory(File(MCBridgeProperties.serverPath)))
    }
}