import com.lderic.mcbridge.text.Color;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {
        Terminal terminal = TerminalBuilder.builder().system(true).build();
        LineReader reader = LineReaderBuilder.builder().terminal(terminal).build();
        AttributedStyle style = AttributedStyle.DEFAULT.foregroundRgb(Color.RED.getRgb()).faint();
        AttributedStyle style2 = AttributedStyle.DEFAULT.foregroundRgb(Color.BLUE.getRgb()).blink();
        AttributedStringBuilder builder = new AttributedStringBuilder();
        ;
        reader.printAbove(
                builder.append(new AttributedString("Hello", style))
                        .append(new AttributedString(" World", style2))
                        .toAttributedString()
        );
        Thread.sleep(5000);
    }
}
