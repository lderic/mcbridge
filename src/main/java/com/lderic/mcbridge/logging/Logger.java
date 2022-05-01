package com.lderic.mcbridge.logging;

import com.lderic.mcbridge.text.Color;
import com.lderic.mcbridge.text.Text;
import com.lderic.mcbridge.text.TextChain;

public interface Logger {
    void info(Text msg);

    void info(TextChain msg);

    void info(String msg);

    void info(Object msg);

    void warn(Text msg);

    void warn(TextChain msg);

    void warn(String msg);

    void warn(Object msg);

    void error(Text msg);

    void error(TextChain msg);

    void error(String msg);

    void error(Object msg);

    enum Level {
        INFO("INFO", new Color(0x32CD32)),
        WARN("WARN", new Color(0xFFD700)),
        ERROR("ERROR", new Color(0xFF0000));

        private final String name;
        private final Color color;

        Level(String name, Color color) {
            this.name = name;
            this.color = color;
        }

        public static Level fromString(String name) {
            switch (name.toUpperCase()) {
                case "WARN" -> {
                    return WARN;
                }
                case "ERROR" -> {
                    return ERROR;
                }
                default -> {
                    return INFO;
                }
            }
        }

        public String getName() {
            return name;
        }

        public Color getColor() {
            return color;
        }
    }
}
