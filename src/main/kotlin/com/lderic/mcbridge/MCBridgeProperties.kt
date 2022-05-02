package com.lderic.mcbridge

import com.lderic.mcbridge.logging.InitLogger
import com.lderic.mcbridge.util.MissingPropertyException
import com.lderic.mcbridge.util.PropertiesHandler
import java.io.File
import kotlin.system.exitProcess

object MCBridgeProperties {
    const val FILE_NAME = "mcbridge.properties"
    const val SERVER_DIR = "server_path"
    const val PLUGIN_DIR = "plugin_path"
    const val START_COMMAND = "start_command"
    const val SERVER_TYPE = "logger_pattern"

    private val handler = PropertiesHandler(File("${System.getProperty("user.dir")}/${FILE_NAME}"))

    init {
        if (!handler.propertiesFile.exists()) {
            InitLogger.warn("$FILE_NAME not found, creating new one")
            handler.propertiesFile.createNewFile()
            handler.setProperty(SERVER_DIR, "server/")
            handler.setProperty(PLUGIN_DIR, "plugins/")
            handler.setProperty(START_COMMAND, "java -Xmx1024M -Xms1024M -jar server.jar nogui")
            handler.setProperty(SERVER_TYPE, "vanilla")
            handler.store()
            File(serverPath).also {
                if (!it.exists()) {
                    it.mkdirs()
                }
            }
            File(pluginPath).also {
                if (!it.exists()) {
                    it.mkdirs()
                }
            }
            InitLogger.info("Environment initialization completed, please add server file")
            exitProcess(0)
        } else {
            requireExist()
        }
    }

    fun requireExist() {
        for (key in arrayOf(SERVER_DIR, PLUGIN_DIR, START_COMMAND, SERVER_TYPE)) {
            handler.getProperty(key) ?: throw MissingPropertyException(key, handler.propertiesFile)
        }
        if (!File(serverPath).exists()) {
            throw Exception("Server directory not found in $serverPath")
        }
        if (!File(pluginPath).exists()) {
            throw Exception("Plugin directory not found in $pluginPath")
        }
    }

    val serverDir: String?
        get() = handler.getProperty(SERVER_DIR)
    val serverPath
        get() = "${System.getProperty("user.dir")}/${serverDir}"
    val pluginDir: String?
        get() = handler.getProperty(PLUGIN_DIR)
    val pluginPath
        get() = "${System.getProperty("user.dir")}/${pluginDir}"
    val startCommand: String?
        get() = handler.getProperty(START_COMMAND)
    val serverType: String?
        get() = handler.getProperty(SERVER_TYPE)
}