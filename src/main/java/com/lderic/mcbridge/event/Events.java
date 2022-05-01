package com.lderic.mcbridge.event;

import com.lderic.mcbridge.minecraft.Server;

public class Events {
    public final static EventHandler<PlayerJoinEvent> PLAYER_JOIN = PlayerJoinEventHandler.INSTANCE;
    public final static EventHandler<PlayerLeaveEvent> PLAYER_LEAVE = PlayerLeaveEventHandler.INSTANCE;

    public record PlayerJoinEvent(Server server, String player, long time) implements Event {}

    public record PlayerLeaveEvent(Server server, String player, long time) implements Event {}
}
