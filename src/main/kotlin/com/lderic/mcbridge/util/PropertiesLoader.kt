package com.lderic.mcbridge.util

import java.io.File
import java.util.*

object Property {
    val serverPath: String
        get() = PropertiesLoader.mcbridgeProperties.getProperty(SERVER_PATH)
    val pluginPath: String
        get() = PropertiesLoader.mcbridgeProperties.getProperty(PLUGIN_PATH)
    val startCommand: String
        get() = PropertiesLoader.mcbridgeProperties.getProperty(START_COMMAND)

    operator fun get(key: String): String = PropertiesLoader.mcbridgeProperties.getProperty(key)
}

private const val SERVER_PATH = "server_path"
private const val PLUGIN_PATH = "plugin_path"
private const val START_COMMAND = "start_command"

object PropertiesLoader {
    private val mcbridgePropertiesFile = File("${System.getProperty("user.dir")}/mcbridge.properties")

    val mcbridgeProperties = Properties()

    init {
        fun defaultProperties() {
            mcbridgeProperties.clear()
            mcbridgeProperties.setProperty(SERVER_PATH, "server/")
            mcbridgeProperties.setProperty(PLUGIN_PATH, "plugins/")
            mcbridgeProperties.setProperty(START_COMMAND, "java -Xms1G -Xmx2G -jar server.jar")
            mcbridgeProperties.store(mcbridgePropertiesFile.bufferedWriter(), null)
        }
        if (!mcbridgePropertiesFile.exists()) {
            mcbridgePropertiesFile.createNewFile()
            defaultProperties()
        } else {
            mcbridgeProperties.load(mcbridgePropertiesFile.bufferedReader())
            fun checkPropertyExist(property: String) {
                mcbridgeProperties[property] ?: throw MissingPropertyException(property, mcbridgePropertiesFile)
            }
            checkPropertyExist(SERVER_PATH)
            checkPropertyExist(PLUGIN_PATH)
            checkPropertyExist(START_COMMAND)
        }
    }

    fun load() {

    }
}

class MissingPropertyException : Exception {
    constructor(message: String) : super(message)
    constructor(property: String, file:File) : this("Property $property is missing in file ${file.absolutePath}")
}