package com.lderic.mcbridge.logging;

import com.lderic.mcbridge.util.MCBridgeProperty;

public class LoggerFactory {
    static {
        SystemIO.redirectStream();
    }

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
        switch (MCBridgeProperty.INSTANCE.getLoggerPatternType().toLowerCase()) {
            default -> {
                return new VanillaLogger(name);
            }
        }
    }
}
