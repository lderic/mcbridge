package com.lderic.mcbridge.minecraft

import com.lderic.mcbridge.logging.Logger
import com.lderic.mcbridge.logging.LoggerFactory
import com.lderic.mcbridge.text.Text
import com.lderic.mcbridge.text.TextChain

internal object VanillaServer : Server {
    private val serverLogger = LoggerFactory.getMinecraftLogger()

    override fun getLogger(): Logger = serverLogger

    override fun broadcastMessage(message: String?) {
        TODO("Not yet implemented")
    }

    override fun broadcastMessage(message: Text?) {
        TODO("Not yet implemented")
    }

    override fun broadcastMessage(message: TextChain?) {
        TODO("Not yet implemented")
    }

    override fun sendMessage(message: String?, player: String?) {
        TODO("Not yet implemented")
    }

    override fun sendMessage(message: Text?, player: String?) {
        TODO("Not yet implemented")
    }

    override fun sendMessage(message: TextChain?, player: String?) {
        TODO("Not yet implemented")
    }

    override fun send(command: String?) {
        TODO("Not yet implemented")
    }

    override fun getCurrentPlayers(): Int {
        TODO("Not yet implemented")
    }

    override fun getMaxPlayers(): Int {
        TODO("Not yet implemented")
    }

    override fun kickPlayer(playerName: String?, reason: String?) {
        TODO("Not yet implemented")
    }

    override fun banPlayer(playerName: String?, reason: String?) {
        TODO("Not yet implemented")
    }

    override fun pardonPlayer(playerName: String?) {
        TODO("Not yet implemented")
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
        TODO("Not yet implemented")
    }

    override fun deop(playerName: String?) {
        TODO("Not yet implemented")
    }

    override fun isOp(playerName: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun isWhitelist(playerName: String?): Boolean {
        TODO("Not yet implemented")
    }
}