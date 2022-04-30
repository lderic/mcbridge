package com.lderic.mcbridge;

import com.lderic.mcbridge.console.Console;
import com.lderic.mcbridge.logging.Logger;
import com.lderic.mcbridge.logging.LoggerFactory;
import com.lderic.mcbridge.process.MCProcess;
import com.lderic.mcbridge.process.ProcessFactoryKt;

public class MCBridge {
    MCBridgeProperties properties = MCBridgeProperties.INSTANCE;
    public static final Logger logger = LoggerFactory.getLogger(MCBridge.class);
    public static final String VERSION = "1.0_SNAPSHOT";
    public static final String NAME = "MCBridge";
    public static final String AUTHOR = "lderic";
    public static final String GITHUB = "https://github.com/lderic/mcbridge";

    public static void main(String[] args) {
        logger.info("Starting " + NAME + " v" + VERSION);
        logger.info("Author: " + AUTHOR);
        logger.info("GitHub: " + GITHUB);
        logger.info("");
        logger.info("Starting server...");
        final MCProcess mc = ProcessFactoryKt.launchMinecraft();
        if (mc == null) {
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
                if (line.equalsIgnoreCase("exit")) {
                    System.exit(0);
                    break;
                }
                mc.writeln(line);
            }
        });
        reader.start();
        mc.start();
    }
}
