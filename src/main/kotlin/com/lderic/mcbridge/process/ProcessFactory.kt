package com.lderic.mcbridge.process

import com.lderic.mcbridge.plugin.pluginCompiledDir
import com.lderic.mcbridge.plugin.pluginDir
import java.io.File

fun compileProcess(name: String): Process {
    return ProcessBuilder(
        "javac",
        "-cp",
        System.getProperty("java.class.path"),
        "-d",
        pluginCompiledDir,
        name
    )
        .directory(File(pluginDir))
        .redirectErrorStream(true)
        .start()
}