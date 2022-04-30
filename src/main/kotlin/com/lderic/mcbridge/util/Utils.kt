package com.lderic.mcbridge.util

import com.lderic.mcbridge.logging.Logger
import com.lderic.mcbridge.logging.VanillaLogger
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

public inline fun <T> Iterable<T>.forEachLaunch(
    coroutineScope: CoroutineScope,
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    crossinline action: (T) -> Unit
): List<Job> {
    return this.map {
        coroutineScope.launch(context, start) { action(it) }
    }
}

public inline fun <T, R> Iterable<T>.forEachAsync(
    coroutineScope: CoroutineScope,
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    crossinline action: (T) -> R
): List<Deferred<R>> {
    return this.map {
        coroutineScope.async(context, start) { action(it) }
    }
}
