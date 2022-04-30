package com.lderic.mcbridge.logging;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.PrintStream;

public class LoggingErrorStream extends PrintStream {
    public static final PrintStream err = new LoggingErrorStream();
    public static final String STDERR = "[STDERR] ";
    private LoggingErrorStream() {
        super(SystemIO.err);
    }

    @Override
    public void print(boolean b) {
        super.print(STDERR + b);
    }

    @Override
    public void print(char c) {
        super.print(STDERR + c);
    }

    @Override
    public void print(int i) {
        super.print(STDERR + i);
    }

    @Override
    public void print(long l) {
        super.print(STDERR + l);
    }

    @Override
    public void print(float f) {
        super.print(STDERR + f);
    }

    @Override
    public void print(double d) {
        super.print(STDERR + d);
    }

    @Override
    public void print(@NotNull char[] s) {
        super.print(STDERR + s);
    }

    @Override
    public void print(@Nullable String s) {
        super.print(STDERR + s);
    }

    @Override
    public void print(@Nullable Object obj) {
        super.print(STDERR + obj);
    }
}
