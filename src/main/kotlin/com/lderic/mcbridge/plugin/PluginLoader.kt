package com.lderic.mcbridge.plugin

import com.lderic.mcbridge.api.PluginEntryPoint
import com.lderic.mcbridge.util.forEachLaunch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

internal object PluginLoader : ClassLoader() {
    fun loadPlugins(entrypoints: List<File>): List<PluginEntryPoint> {
        val result = mutableListOf<PluginEntryPoint>()
        pluginLoaderScope.launch {
            entrypoints.forEachLaunch(this, Dispatchers.IO) {
                val bytes = it.readBytes()
                val clazz = defineClass(it.nameWithoutExtension, bytes, 0, bytes.size)
                try {
                    (clazz.getConstructor().newInstance() as? PluginEntryPoint)?.also { result.add(it) }
                        ?: throw IllegalStateException("Plugin ${it.name} does not implement PluginEntryPoint")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return result
    }
}