package com.lderic.mcbridge.console;

import com.lderic.mcbridge.text.Text;
import com.lderic.mcbridge.text.TextChain;

public interface LineHandler {
    String readLine();

    void write(String line);

    void write(Text text);

    void write(TextChain textChain);
}
