package com.lderic.mcbridge.event
interface EventListener<E : Events.Event> {
    fun onEvent(event: E)
}