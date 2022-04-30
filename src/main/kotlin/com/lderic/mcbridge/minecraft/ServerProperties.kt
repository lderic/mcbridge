package com.lderic.mcbridge.minecraft

import com.lderic.mcbridge.MCBridgeProperties
import com.lderic.mcbridge.util.PropertiesHandler
import java.io.File

object ServerProperties {
    private val handler = PropertiesHandler(File(MCBridgeProperties.serverPath + "/server.properties"))

    operator fun get(key: String): String? {
        return handler.getProperty(key)
    }
}