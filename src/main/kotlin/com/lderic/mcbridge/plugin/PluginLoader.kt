package com.lderic.mcbridge.plugin

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File

class PluginLoader : ClassLoader() {
    fun loadPlugins(entrypoints: List<File>): Map<File, PluginEntryPoint> {
        val result = mutableMapOf<File, PluginEntryPoint>()
        val job = pluginLoaderScope.launch {
            entrypoints.forEach { file ->
                launch(Dispatchers.IO) {
                    try {
                        val bytes = file.readBytes()
                        val clazz = defineClass(file.nameWithoutExtension, bytes, 0, bytes.size)
                        (clazz.getConstructor().newInstance() as? PluginEntryPoint)?.also {
                            result[file] = it
                            Plugins.logger.info("Loaded plugin ${file.nameWithoutExtension}")
                        }
                            ?: Plugins.logger.error("Plugin ${file.nameWithoutExtension} does not implement PluginEntryPoint")
                    } catch (t: Throwable) {
                        Plugins.logger.error("Failed to load plugin ${file.nameWithoutExtension}")
                    }
                }
            }
        }
        runBlocking { job.join() }
        return result
    }
}