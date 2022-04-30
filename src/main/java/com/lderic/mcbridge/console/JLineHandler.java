package com.lderic.mcbridge.console;

import com.lderic.mcbridge.MCBridge;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.Terminal;

public class JLineHandler implements LineHandler {
    private final Terminal terminal;
    private final LineReader lineReader;

    JLineHandler(Terminal terminal) {
        this.terminal = terminal;
        // TODO: Add line completer and highlighter
        lineReader = LineReaderBuilder.builder()
                .terminal(terminal)
                .completer(new StringsCompleter("say", "tell", "help", "stop", "exit"))
                .appName(MCBridge.NAME)
                .build();
    }

    @Override
    public String readLine() {
        String line = null;
        try {
            line = lineReader.readLine("> ");
        } catch (UserInterruptException e) {
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return line;
    }

    @Override
    public void write(Object msg) {
        lineReader.printAbove(msg.toString());
    }
}
