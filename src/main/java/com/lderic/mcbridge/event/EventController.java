package com.lderic.mcbridge.event;

public class EventController<E extends Events.Event> {
    private final EventHandler<E> handler;
    private final String uuid;

    protected EventController(EventHandler<E> eventHandler, String uuid) {
        this.handler = eventHandler;
        this.uuid = uuid;
    }

    public void cancel() {
        handler.removeListener(uuid);
    }
}
