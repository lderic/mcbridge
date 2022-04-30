package com.lderic.mcbridge

import com.lderic.mcbridge.util.PropertiesHandler
import java.io.File

object MCBridgeProperties {
    const val FILE_NAME = "mcbridge.properties"
    const val SERVER_DIR = "server_path"
    const val PLUGIN_DIR = "plugin_path"
    const val START_COMMAND = "start_command"
    const val LOGGER_PATTERN_TYPE = "logger_pattern"

    private val handler = PropertiesHandler(File("${System.getProperty("user.dir")}/${FILE_NAME}"))

    init {
        if (!handler.propertiesFile.exists()) {
            handler.propertiesFile.createNewFile()
            handler.setProperty(SERVER_DIR, "server/")
            handler.setProperty(PLUGIN_DIR, "plugins/")
            handler.setProperty(START_COMMAND, "java -Xms1G -Xmx2G -jar server.jar nogui")
            handler.setProperty(LOGGER_PATTERN_TYPE, "vanilla")
            handler.store()
        }else {
            handler.requireAllExist(SERVER_DIR, PLUGIN_DIR, START_COMMAND, LOGGER_PATTERN_TYPE)
        }
    }

    fun init() {
        // Load this class
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
    val loggerPatternType: String?
        get() = handler.getProperty(LOGGER_PATTERN_TYPE)
}