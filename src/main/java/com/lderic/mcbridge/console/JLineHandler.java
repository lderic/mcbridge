package com.lderic.mcbridge.console;

import com.lderic.mcbridge.MCBridge;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;

public class JLineHandler implements LineHandler {
    private final Terminal terminal;
    private final LineReader lineReader;

    JLineHandler(Terminal terminal) {
        this.terminal = terminal;
        // TODO: Add line completer and highlighter
        lineReader = LineReaderBuilder.builder()
                .terminal(terminal)
                .appName(MCBridge.NAME)
                .build();
    }

    @Override
    public String readLine() {
        return lineReader.readLine("> ");
    }

    @Override
    public void println(String line) {
        lineReader.printAbove(line);
    }
}
