package com.lderic.mcbridge.console;

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
    public void println(String line) {
        System.out.println(line);
    }
}
