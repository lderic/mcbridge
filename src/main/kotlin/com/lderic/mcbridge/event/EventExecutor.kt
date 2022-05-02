package com.lderic.mcbridge.event

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

object EventExecutor {
    private val eventExecutorScope = CoroutineScope(EmptyCoroutineContext)

    @JvmStatic
    fun <E : Events.Event> on(eventHandler: EventHandler<E>, event: E) {
        eventExecutorScope.launch {
            eventHandler.onEvent(event)
        }
    }
}