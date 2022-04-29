package com.lderic.mcbridge.event

import com.lderic.mcbridge.minecraft.server.VanillaServer
import java.util.function.Consumer

interface EventHandler<E : Event> {
    fun onEvent(source: String)

    fun registerListener(listener: Consumer<E>)
}

abstract class AbstractEventHandler<T : Event> : EventHandler<T> {
    internal val listeners = mutableListOf<Consumer<T>>()

    abstract override fun onEvent(source: String)

    override fun registerListener(listener: Consumer<T>) {
        listeners.add(listener)
    }
}

object PlayerJoinEventHandler : AbstractEventHandler<Events.PlayerJoinEvent>() {
    override fun onEvent(source: String) {
        listeners.forEach { it.accept(Events.PlayerJoinEvent(VanillaServer, source, System.currentTimeMillis())) }
    }
}

object PlayerLeaveEventHandler : AbstractEventHandler<Events.PlayerLeaveEvent>() {
    override fun onEvent(source: String) {
        listeners.forEach { it.accept(Events.PlayerLeaveEvent(VanillaServer, source, System.currentTimeMillis())) }
    }
}