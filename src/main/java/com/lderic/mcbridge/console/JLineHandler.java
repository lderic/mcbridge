package com.lderic.mcbridge.console;

import com.lderic.mcbridge.MCBridge;
import com.lderic.mcbridge.text.Text;
import com.lderic.mcbridge.text.TextChain;
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
        lineReader.setOpt(LineReader.Option.ERASE_LINE_ON_FINISH);
        lineReader.setOpt(LineReader.Option.CASE_INSENSITIVE_SEARCH);
        lineReader.setOpt(LineReader.Option.DISABLE_EVENT_EXPANSION);
        lineReader.unsetOpt(LineReader.Option.INSERT_TAB);
        lineReader.unsetOpt(LineReader.Option.MOUSE);
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
    public void write(String msg) {
        lineReader.printAbove(msg);
    }

    @Override
    public void write(Text text) {
        lineReader.printAbove(ConsoleTextHandler.handleConsoleText(text));
    }

    @Override
    public void write(TextChain textChain) {
        lineReader.printAbove(ConsoleTextHandler.handleConsoleText(textChain));
    }
}
