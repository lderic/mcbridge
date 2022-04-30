package com.lderic.mcbridge.logging;

import com.lderic.mcbridge.console.Console;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.PrintStream;
import java.util.Arrays;

public class LoggingOutputStream extends PrintStream {
    public static final PrintStream out = new LoggingOutputStream();
    public static final String STDOUT = "[STDOUT] ";

    private LoggingOutputStream() {
        super(SystemIO.out);
    }

    private final Console console = Console.getInstance();

    @Override
    public void print(boolean b) {
        console.write(STDOUT + b);
    }

    @Override
    public void print(char c) {
        console.write(STDOUT + c);
    }

    @Override
    public void print(int i) {
        console.write(STDOUT + i);
    }

    @Override
    public void print(long l) {
        console.write(STDOUT + l);
    }

    @Override
    public void print(float f) {
        console.write(STDOUT + f);
    }

    @Override
    public void print(double d) {
        console.write(STDOUT + d);
    }

    @Override
    public void print(@NotNull char[] s) {
        console.write(STDOUT + Arrays.toString(s));
    }

    @Override
    public void print(@Nullable String s) {
        console.write(STDOUT + s);
    }

    @Override
    public void print(@Nullable Object obj) {
        console.write(STDOUT + obj);
    }

    @Override
    public void println() {
        console.write("\n");
    }

    @Override
    public void println(boolean x) {
        console.write(STDOUT + x);
    }

    @Override
    public void println(char x) {
        console.write(STDOUT + x);
    }

    @Override
    public void println(int x) {
        console.write(STDOUT + x);
    }

    @Override
    public void println(long x) {
        console.write(STDOUT + x);
    }

    @Override
    public void println(float x) {
        console.write(STDOUT + x);
    }

    @Override
    public void println(double x) {
        console.write(STDOUT + x);
    }

    @Override
    public void println(@NotNull char[] x) {
        console.write(STDOUT + Arrays.toString(x));
    }

    @Override
    public void println(@Nullable String x) {
        console.write(STDOUT + x);
    }

    @Override
    public void println(@Nullable Object x) {
        console.write(STDOUT + x);
    }
}
