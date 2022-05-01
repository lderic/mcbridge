package com.lderic.mcbridge.event;

import com.lderic.mcbridge.minecraft.Server;

public class Events {
    public final static EventHandler<PlayerEvent> PLAYER_JOIN = new EventHandler<>();
    public final static EventHandler<PlayerEvent> PLAYER_LEAVE = new EventHandler<>();
    public final static EventHandler<PlayerChatEvent> PLAYER_CHAT = new EventHandler<>();
    public final static EventHandler<ServerLogEvent> SERVER_INFO = new EventHandler<>();
    public final static EventHandler<ServerLogEvent> SERVER_WARNING = new EventHandler<>();
    public final static EventHandler<Event> SERVER_INITIALIZING = new EventHandler<>();
    public final static EventHandler<ServerRuntimeEvent> SERVER_STARTED = new EventHandler<>();
    public final static EventHandler<Event> SERVER_STOPPED = new EventHandler<>();
    public final static EventHandler<Event> SERVER_STOPPING = new EventHandler<>();

    public static class Event {
        private final long time;

        public Event(long time) {
            this.time = time;
        }

        public long getTime() {
            return time;
        }
    }

    public static class ServerRuntimeEvent extends Event {
        private final Server server;
        ServerRuntimeEvent(Server server, long time) {
            super(time);
            this.server = server;
        }

        public Server getServer() {
            return server;
        }
    }

    public static class PlayerEvent extends ServerRuntimeEvent {
        private final String player;
        PlayerEvent(Server server, String player, long time) {
            super(server, time);
            this.player = player;
        }

        public String getPlayer() {
            return player;
        }
    }

    public static class PlayerChatEvent extends PlayerEvent {
        private final String message;
        PlayerChatEvent(Server server, String player, String message, long time) {
            super(server, player, time);
            this.message = message;
        }

        public String getPlayerChat() {
            return message;
        }
    }

    public static class ServerLogEvent extends ServerRuntimeEvent {
        private final String message;
        ServerLogEvent(Server server, String message, long time) {
            super(server, time);
            this.message = message;
        }

        public String getLogMessage() {
            return message;
        }
    }
}
