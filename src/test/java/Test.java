import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        Terminal terminal = TerminalBuilder.builder().system(true).build();
        LineReader reader = LineReaderBuilder.builder().terminal(terminal).build();
        new Thread(() -> {
            try {
                reader.printAbove("hello\n");
                Thread.sleep(1000);
                reader.printAbove("hel\nlo");
                Thread.sleep(1000);
                reader.printAbove("\n");
                Thread.sleep(1000);
                reader.printAbove("world");
            }catch (Exception ignored){}
        }).start();
        reader.readLine("Enter your name: ");

    }
}
