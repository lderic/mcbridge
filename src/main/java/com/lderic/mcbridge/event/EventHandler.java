package com.lderic.mcbridge.event;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EventHandler<E extends Events.Event> {
    private final Map<String, EventListener<E>> listeners = new HashMap<>();

    protected EventHandler() {
    }

    public EventController<E> registerListener(@NotNull EventListener<E> listener) {
        String uuid = UUID.randomUUID().toString();
        listeners.put(uuid, listener);
        return new EventController<>(this, uuid);
    }

    protected void removeListener(@NotNull String uuid) {
        listeners.remove(uuid);
    }

    protected void clearListeners() {
        listeners.clear();
    }

    protected void onEvent(@NotNull E event) {
        listeners.values().forEach(listener -> listener.onEvent(event));
    }
}
