package com.lderic.mcbridge.minecraft.server;

import com.lderic.mcbridge.logging.Logger;
import com.lderic.mcbridge.minecraft.text.Text;
import com.lderic.mcbridge.minecraft.text.TextChain;

public interface Server {
    Logger getLogger();

    /**
     * Send a message to all players
     */
    void broadcastMessage(String message);

    void broadcastMessage(Text message);

    void broadcastMessage(TextChain message);

    /**
     * Sends a message to the specific player
     */
    void sendMessage(String message, String player);

    void sendMessage(Text message, String player);

    void sendMessage(TextChain message, String player);

    /**
     * Sends command to the server
     *
     * @param command sent to the server
     */
    void send(String command);

    int getCurrentPlayers();

    void kickPlayer(String playerName, String reason);

    void banPlayer(String playerName, String reason);

    void pardonPlayer(String playerName);

    void start();

    void restart();

    void stop();

    int getMaxPlayers();

    String getMotd();

    void op(String playerName);

    void deop(String playerName);

    boolean isOp(String playerName);

    boolean isWhitelist(String playerName);
}
