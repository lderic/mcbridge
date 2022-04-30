package com.lderic.mcbridge.console;

import com.lderic.mcbridge.MCBridge;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

class Console {
    private static final Console INSTANCE = new Console();
    private LineHandler lineHandler;

    private Console() {
        try {
            Terminal terminal = TerminalBuilder.builder()
                    .name(MCBridge.NAME)
                    .system(true)
                    .build();
            lineHandler = new JLineHandler(terminal);
        } catch (Throwable e) {
            lineHandler = new DefaultLineHandler();
        }
    }

    public static Console getInstance() {
        return INSTANCE;
    }

    public void println(String msg) {
        lineHandler.println(msg);
    }

    public void readLine() {
        lineHandler.readLine();
    }
}
