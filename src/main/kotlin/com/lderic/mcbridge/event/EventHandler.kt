package com.lderic.mcbridge.event

import java.util.function.Consumer

internal class EventHandler<E : Event> {
    private val listeners = mutableListOf<Consumer<E>>()

    fun onEvent(event: E) {
        listeners.forEach { it.accept(event) }
    }

    fun registerListener(listener: Consumer<E>) {
        listeners.add(listener)
    }
}
