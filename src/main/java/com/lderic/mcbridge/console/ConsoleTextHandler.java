package com.lderic.mcbridge.console;

import com.lderic.mcbridge.text.Text;
import com.lderic.mcbridge.text.TextChain;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;

public class ConsoleTextHandler {
    public static AttributedString handleConsoleText(Text text) {
        AttributedStyle style = AttributedStyle.DEFAULT;
        if (text.isBold()) {
            style = style.bold();
        }
        if (text.isItalic()) {
            style = style.italic();
        }
        if (text.isUnderlined()) {
            style = style.underline();
        }
        if (text.isStrikethrough()) {
            style = style.crossedOut();
        }
        if (text.isInverse()) {
            style = style.inverse();
        }
        if (text.hasColor()) {
            style = style.foregroundRgb(text.getColor().getRgb());
        }
        return new AttributedString(text.toString(), style);
    }

    public static AttributedString handleConsoleText(TextChain textChain) {
        AttributedStringBuilder sb = new AttributedStringBuilder();
        for (Text text : textChain) {
            sb.append(handleConsoleText(text));
        }
        return sb.toAttributedString();
    }
}
