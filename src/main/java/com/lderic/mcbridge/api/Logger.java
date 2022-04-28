package com.lderic.mcbridge.api;

public interface Logger {
    void info(Object msg);

    void warn(Object msg);

    void error(Object msg);

    void debug(Object msg);
}
