package com.lderic.mcbridge.plugin

import com.lderic.mcbridge.MCBridgeProperties
import com.lderic.mcbridge.process.ProcessFactory.compileProcess
import com.lderic.mcbridge.text.Color
import com.lderic.mcbridge.text.Text
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File

/**
 * This class is for preloading plugins. It compiles the plugin class.
 */
class PluginPreloader {
    private val prepareToCompile = mutableListOf<File>()

    /**
     * Preloads the plugin.
     * @return the compiled plugin entrypoint.
     */
    fun preload(): List<File> {
        val entrypoints = mutableListOf<File>()
        val job = pluginLoaderScope.launch(Dispatchers.IO) {
            loadSourceFiles()

            for (plugin in prepareToCompile) {
                launch(Dispatchers.IO) {
                    val process = compileProcess(plugin.name)
                    Plugins.logger.info("Compiling ${plugin.name}")

                    var temp: String?
                    while (process.inputReader().readLine().also { temp = it } != null) {
                        Plugins.logger.info(temp)
                    }

                    process.waitFor()

                    if (process.exitValue() != 0) {
                        Plugins.logger.error("Plugin ${plugin.name} compiled failed")
                    } else {
                        Plugins.logger.info("Plugin ${plugin.name} compiled successfully")
                        entrypoints.add(File("${MCBridgeProperties.pluginPath}/$pluginCompiledDir/${plugin.nameWithoutExtension}.class"))
                    }
                }
            }
        }
        runBlocking { job.join() }
        prepareToCompile.clear()
        return entrypoints
    }

    private fun loadSourceFiles() {
        Plugins.logger.info("Loading Files in thread ${Thread.currentThread().name}")
        MCBridgeProperties.pluginDir?.let {
            File(it).listFiles()?.forEach {
                if (it.isDirectory) {
                    loadMultipleFilePlugin(it)
                } else if (it.extension == "java") {
                    loadSingleFilePlugin(it)
                }
            }
            Plugins.logger.info(Text("Find ${prepareToCompile.size} plugins").setColor(Color.BLUE))
        } ?: throw Exception("No access to plugin directory")
    }

    private fun loadSingleFilePlugin(file: File) {
        prepareToCompile.add(file)
    }

    private fun loadMultipleFilePlugin(dir: File) {
    }
}