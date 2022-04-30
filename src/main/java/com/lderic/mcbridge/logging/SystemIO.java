package com.lderic.mcbridge.logging;

import java.io.InputStream;
import java.io.PrintStream;

public class SystemIO {
    public static final PrintStream out = System.out;
    public static final PrintStream err = System.err;
    public static final InputStream in = System.in;

    public static void redirectStream() {
        System.setOut(LoggingOutputStream.out);
        System.setErr(LoggingErrorStream.err);
    }
}
