package com.lderic.mcbridge;

import com.lderic.mcbridge.console.Console;
import com.lderic.mcbridge.event.Events;
import com.lderic.mcbridge.logging.Logger;
import com.lderic.mcbridge.logging.LoggerFactory;
import com.lderic.mcbridge.plugin.Plugins;
import com.lderic.mcbridge.process.MCProcess;
import com.lderic.mcbridge.process.ProcessFactory;

public class MCBridge {
    public static final Logger logger = LoggerFactory.getLogger(MCBridge.class);
    public static final String VERSION = "1.0_SNAPSHOT";
    public static final String NAME = "MCBridge";
    public static final String AUTHOR = "lderic";
    public static final String GITHUB = "https://github.com/lderic/mcbridge";

    public static final String COMMAND_PREFIX = "!!";

    private static MCProcess currentProcess;

    public static void main(String[] args) {
        logger.info("Starting " + NAME + " v" + VERSION);
        logger.info("Author: " + AUTHOR);
        logger.info("GitHub: " + GITHUB);
        logger.info("Loading plugins...");
        Plugins.reload();
        logger.info("Starting server...");
        currentProcess = ProcessFactory.launchMinecraft();
        if (currentProcess == null) {
            logger.error("Failed to start Minecraft server!");
            return;
        }
        Thread reader = new Thread(() -> {
            Console console = Console.getInstance();
            String line;
            while (true) {
                line = console.readLine();
                if (line == null) {
                    continue;
                }
                if (line.startsWith(COMMAND_PREFIX)) {
                    handleCommand(line.substring(2));
                }else  {
                    currentProcess.writeln(line);
                }

            }
        });
        reader.start();
        currentProcess.start();
    }

    public static void handleCommand(String command) {
        switch (command) {
            case "exit" -> {
                System.exit(0);
            }
            case "reload" -> {
                Plugins.reload();
            }
        }
    }

    public static MCProcess getCurrentProcess() {
        return currentProcess;
    }
}
