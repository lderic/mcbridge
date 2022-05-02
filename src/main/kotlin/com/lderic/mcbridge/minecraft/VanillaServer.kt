package com.lderic.mcbridge.minecraft

import com.lderic.mcbridge.MCBridge
import com.lderic.mcbridge.logging.Logger
import com.lderic.mcbridge.logging.LoggerFactory
import com.lderic.mcbridge.text.Text
import com.lderic.mcbridge.text.TextChain

internal object VanillaServer : Server {
    private val serverLogger = LoggerFactory.getMinecraftLogger()

    override fun getLogger(): Logger = serverLogger

    override fun broadcastMessage(message: String?) {
        broadcastMessage(Text(message))
    }

    override fun broadcastMessage(message: Text?) {
        message ?: return
        MCBridge.getCurrentProcess().writeln("tellraw @a ${message.handleMinecraftText()}".let {
            if (MCBridge.getCurrentProcess().getVersion() >= Version.MC1_13_0) {
                it.addExecute()
            } else {
                it
            }
        })
    }

    override fun broadcastMessage(message: TextChain?) {
        message ?: return
        MCBridge.getCurrentProcess().writeln("tellraw @a ${message.handleMinecraftText()}".let {
            if (MCBridge.getCurrentProcess().getVersion() >= Version.MC1_13_0) {
                it.addExecute()
            } else {
                it
            }
        })
    }

    override fun sendMessage(message: String?, player: String?) {
        message ?: return
        sendMessage(Text(message), player)
    }

    override fun sendMessage(message: Text?, player: String?) {
        message ?: return
        player ?: return
        MCBridge.getCurrentProcess().writeln("tellraw @a ${message.handleMinecraftText()}".let {
            if (MCBridge.getCurrentProcess().getVersion() >= Version.MC1_13_0) {
                it.addExecute()
            } else {
                it
            }
        })
    }

    override fun sendMessage(message: TextChain?, player: String?) {
        message ?: return
        player ?: return
        MCBridge.getCurrentProcess().writeln("tellraw @a ${message.handleMinecraftText()}".let {
            if (MCBridge.getCurrentProcess().getVersion() >= Version.MC1_13_0) {
                it.addExecute()
            } else {
                it
            }
        })
    }

    override fun send(command: String?) {
        command ?: return
        MCBridge.getCurrentProcess().writeln(command)
    }

    override fun getCurrentPlayers(): Int {
        TODO()
    }

    override fun getMaxPlayers(): Int {
        TODO("Not yet implemented")
    }

    override fun kickPlayer(playerName: String?, reason: String?) {
        playerName ?: return
        MCBridge.getCurrentProcess().writeln("kick $playerName".let { if (reason != null) "$it $reason" else it })
    }

    override fun banPlayer(playerName: String?, reason: String?) {
        playerName ?: return
        MCBridge.getCurrentProcess().writeln("ban $playerName".let { if (reason != null) "$it $reason" else it })
    }

    override fun pardonPlayer(playerName: String?) {
        playerName ?: return
        MCBridge.getCurrentProcess().writeln("pardon $playerName")
    }

    override fun start() {
        TODO("Not yet implemented")
    }

    override fun restart() {
        TODO("Not yet implemented")
    }

    override fun stop() {
        TODO("Not yet implemented")
    }

    override fun getMotd(): String {
        TODO("Not yet implemented")
    }

    override fun op(playerName: String?) {
        playerName ?: return
        MCBridge.getCurrentProcess().writeln("op $playerName")
    }

    override fun deop(playerName: String?) {
        playerName ?: return
        MCBridge.getCurrentProcess().writeln("deop $playerName")
    }

    override fun isOp(playerName: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun isWhitelist(playerName: String?): Boolean {
        TODO("Not yet implemented")
    }

    private fun String.addExecute(): String {
        return "execute at @p run $this"
    }
}