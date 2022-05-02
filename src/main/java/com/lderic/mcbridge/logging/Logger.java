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
        INFO(new Text("INFO").setColor(new Color(0x48BB31))),
        WARN(new Text("WARN").setColor(new Color(0xBBBB23))),
        ERROR(new Text("ERROR").setColor(new Color(0xFF0006)));

        private final Text text;

        Level(Text text) {
            this.text = text;
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
            return text.toString();
        }

        public Text getText() {
            return text;
        }
    }
}
