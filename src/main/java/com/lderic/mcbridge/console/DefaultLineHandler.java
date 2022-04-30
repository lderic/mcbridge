package com.lderic.mcbridge.console;

import com.lderic.mcbridge.logging.SystemIO;

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
    public void write(Object msg) {
        SystemIO.out.println(msg);
    }
}
