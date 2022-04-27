package com.lderic.mcbridge.minecraft

import com.lderic.mcbridge.api.Server

class ServerImpl : Server {
    override fun broadcastMessage(message: String?) {
        TODO("Not yet implemented")
    }

    override fun sendMessage(message: String?, player: String?) {
        TODO("Not yet implemented")
    }

    override fun sendCommand(command: String?) {
        TODO("Not yet implemented")
    }

    override fun getCurrentPlayers(): Int {
        TODO("Not yet implemented")
    }

    override fun getMaxPlayers(): Int {
        TODO("Not yet implemented")
    }

    override fun setMaxPlayers(maxPlayers: Int) {
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

    override fun setMotd(motd: String?) {
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

    override fun setWhitelist(playerName: String?) {
        TODO("Not yet implemented")
    }

    override fun isWhitelist(playerName: String?): Boolean {
        TODO("Not yet implemented")
    }
}