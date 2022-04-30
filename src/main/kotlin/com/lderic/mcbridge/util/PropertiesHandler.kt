package com.lderic.mcbridge.util

import java.io.File
import java.util.*

class PropertiesHandler constructor(
    val propertiesFile: File
) {
    private val properties = Properties()

    init {
//        logger.info("Loading properties from file: ${propertiesFile.absolutePath}")
        if (propertiesFile.exists()) {
            load()
        } else {
//            logger.warn("Properties file does not exist: ${propertiesFile.absolutePath}")
        }
        Runtime.getRuntime().addShutdownHook(Thread {
//            logger.info("Saving properties to ${propertiesFile.absolutePath}")
            store()
        })
    }

    fun load() {
        properties.load(propertiesFile.reader())
    }

    fun store() {
        properties.store(propertiesFile.writer(), null)
    }

    fun contains(key: String): Boolean = properties.containsKey(key)

    fun setProperty(key: String, value: String) {
        properties.setProperty(key, value)
    }

    fun getProperty(key: String): String? = properties.getProperty(key)

    fun requireAllExist(vararg keys: String) {
        for (key in keys) {
            properties[key] ?: throw MissingPropertyException(key, propertiesFile)
        }
    }


    companion object {
        //private val logger: Logger = LoggerFactory.getLogger(PropertiesHandler::class.java)
    }
}

class MissingPropertyException : Exception {
    constructor(message: String) : super(message)
    constructor(property: String, file: File) : this("Property $property is missing in file ${file.absolutePath}")
}