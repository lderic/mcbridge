package com.lderic.mcbridge.plugin

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.concurrent.thread

val pluginDir = "${System.getProperty("user.dir")}/plugins"
const val pluginCompiledDir = ".plugin_classes"
internal val pluginLoaderScope = CoroutineScope(Dispatchers.IO)
internal val pluginScope = CoroutineScope(Dispatchers.IO)

internal object Plugins {
    private val loader = PluginLoader
    private val preloader = PluginPreloader


    fun load() {
        thread(name = "Plugin Load Thread", isDaemon = true) {
            val entrypoints = loader.loadPlugins(preloader.preload())
            entrypoints.forEach {
                it.onLoad()
            }
        }
    }
}
