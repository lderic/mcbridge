package com.lderic.mcbridge.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.util.*
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.reflect.KProperty

object MCBridgeProperty {
    private val properties: Properties by PropertiesHandler(
        File("${System.getProperty("user.dir")}/mcbridge.properties")
    ) { mcbridgeProperties, mcbridgePropertiesFile ->
        println("working on thread ${Thread.currentThread().name}")
        if (!mcbridgePropertiesFile.exists()) {
            mcbridgePropertiesFile.createNewFile()
            mcbridgeProperties.clear()
            mcbridgeProperties.setProperty(SERVER_PATH, "server/")
            mcbridgeProperties.setProperty(PLUGIN_PATH, "plugins/")
            mcbridgeProperties.setProperty(START_COMMAND, "java -Xms1G -Xmx2G -jar server.jar")
            mcbridgeProperties.setProperty(LOGGER_PATTERN_TYPE, "vanilla")
            launch(Dispatchers.IO) {
                mcbridgeProperties.store(mcbridgePropertiesFile.bufferedWriter(), null)
            }
        } else {
            mcbridgeProperties.load(mcbridgePropertiesFile.bufferedReader())
            fun checkPropertyExist(property: String) {
                mcbridgeProperties[property] ?: throw MissingPropertyException(property, mcbridgePropertiesFile)
            }
            checkPropertyExist(SERVER_PATH)
            checkPropertyExist(PLUGIN_PATH)
            checkPropertyExist(START_COMMAND)
            checkPropertyExist(LOGGER_PATTERN_TYPE)
        }
    }

    val serverPath: String
        get() = properties.getProperty(SERVER_PATH)
    val pluginPath: String
        get() = properties.getProperty(PLUGIN_PATH)
    val startCommand: String
        get() = properties.getProperty(START_COMMAND)
    val loggerPatternType: String
        get() = properties.getProperty(LOGGER_PATTERN_TYPE)
    val userDir: String
        get() = System.getProperty("user.dir")

    operator fun get(key: String): String = properties.getProperty(key)
}

private const val SERVER_PATH = "server_path"
private const val PLUGIN_PATH = "plugin_path"
private const val START_COMMAND = "start_command"
private const val LOGGER_PATTERN_TYPE = "logger_pattern"

class PropertiesHandler constructor(
    private val propertiesFile: File,
    initConfig: CoroutineScope.(Properties, File) -> Unit = { _, _ -> }
) {
    val properties = Properties()
    private val coroutineScope = CoroutineScope(EmptyCoroutineContext)

    init {
        coroutineScope.launch(Dispatchers.IO) {
            this.initConfig(properties, propertiesFile)
        }
    }

    operator fun getValue(any: Any, property: KProperty<*>): Properties {
        return properties
    }
}

class MissingPropertyException : Exception {
    constructor(message: String) : super(message)
    constructor(property: String, file: File) : this("Property $property is missing in file ${file.absolutePath}")
}