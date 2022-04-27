package com.lderic.mcbridge.plugin

import com.lderic.mcbridge.process.compileProcess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

/**
 * This class is for preloading plugins. It compiles the plugin class.
 */
internal object PluginPreloader {
    private val prepareToCompile = mutableListOf<File>()

    /**
     * Preloads the plugin.
     * @return the compiled plugin entrypoint.
     */
    fun preload(): List<File> {
        val entrypoints = mutableListOf<File>()
        pluginLoaderScope.launch(Dispatchers.IO) {
            loadSourceFiles()

            for (plugin in prepareToCompile) {
                launch(Dispatchers.IO) {
                    val process = compileProcess(plugin.name)
                    println("Compiling ${plugin.name} in thread ${Thread.currentThread().name}")

                    var temp: String?
                    while (process.inputReader().readLine().also { temp = it } != null) {
                        println(temp)
                    }

                    process.waitFor()

                    if (process.exitValue() != 0) {
                        println("Plugin ${plugin.name} compiled failed")
                    } else {
                        println("Plugin ${plugin.name} compiled successfully")
                        entrypoints.add(File("$pluginDir/$pluginCompiledDir/${plugin.nameWithoutExtension}.class"))
                    }
                }
            }
        }
        return entrypoints
    }

    private fun loadSourceFiles() {
        println("Loading plugins in thread ${Thread.currentThread().name}")
        File(pluginDir).listFiles()?.forEach {
            if (it.isDirectory) {
                loadMultipleFilePlugin(it)
            } else if (it.extension == "java") {
                loadSingleFilePlugin(it)
            }
        } ?: throw Exception("No access to plugin directory")
    }

    private fun loadSingleFilePlugin(file: File) {
        prepareToCompile.add(file)
    }

    private fun loadMultipleFilePlugin(dir: File) {
    }
}