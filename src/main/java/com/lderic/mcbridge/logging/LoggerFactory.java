package com.lderic.mcbridge.logging;

import com.lderic.mcbridge.MCBridgeProperties;

public class LoggerFactory {
    static {
        SystemIO.redirectStream();
    }

    /**
     * Notes that the logger might be null if logger type in mcbridge.properties is not set.
     */
    public static Logger getLogger(String name) {
        return selectLogger(name);
    }

    public static Logger getLogger(Class<?> clazz) {
        return selectLogger(clazz.getSimpleName());
    }

    public static Logger getMinecraftLogger() {
        return MinecraftLogger.INSTANCE;
    }

    private static Logger selectLogger(String name) {
        if (name.equals("Minecraft")) {
            return MinecraftLogger.INSTANCE;
        }
        String type = MCBridgeProperties.INSTANCE.getLoggerPatternType();

        if (type == null) {
            return null;
        }
        switch (type) {
            default -> {
                return new VanillaLogger(name);
            }
        }
    }
}
