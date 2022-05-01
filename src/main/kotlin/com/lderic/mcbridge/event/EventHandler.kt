package com.lderic.mcbridge.event

interface EventHandler<E : Events.Event> {
    fun onEvent(event: E);
    fun registerListener(listener: EventListener<E>);
}

interface EventListener<E : Events.Event> {
    fun onEvent(event: E)
}