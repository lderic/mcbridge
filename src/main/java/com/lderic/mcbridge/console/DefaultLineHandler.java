package com.lderic.mcbridge.console;

import com.lderic.mcbridge.logging.SystemIO;
import com.lderic.mcbridge.text.Text;
import com.lderic.mcbridge.text.TextChain;

import java.util.Scanner;

public class DefaultLineHandler implements LineHandler {
    private final Scanner scanner = new Scanner(System.in);

    DefaultLineHandler() {
    }

    @Override
    public String readLine() {
        return scanner.nextLine();
    }

    @Override
    public void write(String msg) {
        SystemIO.out.println(msg);
    }

    @Override
    public void write(Text text) {
        SystemIO.out.println(text.toString());
    }

    @Override
    public void write(TextChain textChain) {
        SystemIO.out.println(textChain.toString());
    }
}
