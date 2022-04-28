package com.lderic.mcbridge.minecraft

import com.lderic.mcbridge.util.MCBridgeProperty
import com.lderic.mcbridge.util.PropertiesHandler
import java.io.File

object ServerProperties {
    private val properties by PropertiesHandler(File(MCBridgeProperty.serverPath + "/server.properties"))

    operator fun get(key: String): String? {
        return properties.getProperty(key)
    }
}