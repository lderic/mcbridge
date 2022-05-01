package com.lderic.mcbridge.console;

import com.lderic.mcbridge.MCBridge;
import com.lderic.mcbridge.text.Text;
import com.lderic.mcbridge.text.TextChain;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class Console {
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

    public void write(String msg) {
        lineHandler.write(msg);
    }

    public void write(Text text) {
        lineHandler.write(text);
    }

    public void write(TextChain textChain) {
        lineHandler.write(textChain);
    }

    public String readLine() {
        return lineHandler.readLine();
    }
}
