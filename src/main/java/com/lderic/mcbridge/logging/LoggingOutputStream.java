package com.lderic.mcbridge.logging;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.PrintStream;

public class LoggingOutputStream extends PrintStream {
    public static final PrintStream out = new LoggingOutputStream();
    public static final String STDOUT = "[STDOUT] ";
    private LoggingOutputStream() {
        super(SystemIO.out);
    }

    @Override
    public void print(boolean b) {
        super.print(STDOUT + b);
    }

    @Override
    public void print(char c) {
        super.print(STDOUT + c);
    }

    @Override
    public void print(int i) {
        super.print(STDOUT + i);
    }

    @Override
    public void print(long l) {
        super.print(STDOUT + l);
    }

    @Override
    public void print(float f) {
        super.print(STDOUT + f);
    }

    @Override
    public void print(double d) {
        super.print(STDOUT + d);
    }

    @Override
    public void print(@NotNull char[] s) {
        super.print(STDOUT + s);
    }

    @Override
    public void print(@Nullable String s) {
        super.print(STDOUT + s);
    }

    @Override
    public void print(@Nullable Object obj) {
        super.print(STDOUT + obj);
    }
}
