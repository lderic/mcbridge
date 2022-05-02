package com.lderic.mcbridge.plugin

import com.lderic.mcbridge.event.Events
import com.lderic.mcbridge.logging.Logger
import com.lderic.mcbridge.logging.LoggerFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import kotlin.concurrent.thread

const val pluginCompiledDir = ".plugin_classes"
internal val pluginLoaderScope = CoroutineScope(Dispatchers.IO)
internal val pluginScope = CoroutineScope(Dispatchers.IO)

object Plugins {
    @JvmStatic
    val logger: Logger = LoggerFactory.getLogger("Plugin Loader")

    @JvmStatic
    var isLoading = false

    @JvmStatic
    var loader: PluginLoader? = null

    @JvmStatic
    var preloader: PluginPreloader? = null

    @JvmStatic
    fun reload() {
        if (isLoading) {
            logger.warn("Plugin reload already in progress")
            return
        }
        isLoading = true
        Events.clearListeners()
        loader = null
        thread(name = "Plugin Load Thread", isDaemon = true) {
            loader = PluginLoader()
            preloader = PluginPreloader()
            val entrypoints: Map<File, PluginEntryPoint> = loader!!.loadPlugins(preloader!!.preload())
            entrypoints.forEach { (file, entrypoint) ->
                pluginScope.launch {
                    try {
                        entrypoint.onLoad()
                    } catch (e: Exception) {
                        logger.error("Exception in loading plugin ${file.absolutePath}")
                    } catch (e: Error) {
                        logger.error("Error in loading plugin ${file.absolutePath}")
                    }
                }
            }
            isLoading = false
        }
    }
}
