package com.lderic.mcbridge.event;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class EventHandlerImpl<E extends Events.Event> implements EventHandler<E> {

    private final List<EventListener<E>> listeners = new ArrayList<>();

    protected EventHandlerImpl() {
    }

    public void registerListener(@NotNull EventListener<E> listener) {
        listeners.add(listener);
    }

    @Override
    public void onEvent(@NotNull E event) {
        listeners.forEach(listener -> listener.onEvent(event));
    }
}
