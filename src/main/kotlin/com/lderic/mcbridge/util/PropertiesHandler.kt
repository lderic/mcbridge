package com.lderic.mcbridge.util

import com.lderic.mcbridge.logging.InitLogger
import java.io.File
import java.util.*

class PropertiesHandler constructor(
    val propertiesFile: File
) {
    private val properties = Properties()

    init {
        if (propertiesFile.exists()) {
            load()
            InitLogger.info("Loading properties ${propertiesFile.name}")
        }else {
            InitLogger.warn("Properties file does not exist")
        }
        Runtime.getRuntime().addShutdownHook(Thread {
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
}

class MissingPropertyException : Exception {
    constructor(message: String) : super(message)
    constructor(property: String, file: File) : this("Property $property is missing in file ${file.absolutePath}")
}