package com.lderic.mcbridge.api;

public interface Server {
    void broadcastMessage(String message);

    void sendMessage(String message, String player);

    void sendCommand(String command);

    int getCurrentPlayers();

    int getMaxPlayers();

    void setMaxPlayers(int maxPlayers);

    void kickPlayer(String playerName, String reason);

    void banPlayer(String playerName, String reason);

    void pardonPlayer(String playerName);

    void start();

    void restart();

    void stop();

    void setMotd(String motd);

    String getMotd();

    void op(String playerName);

    void deop(String playerName);

    boolean isOp(String playerName);

    void setWhitelist(String playerName);

    boolean isWhitelist(String playerName);
}
