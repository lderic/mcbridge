package com.lderic.mcbridge.text;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TextChain implements Iterable<Text> {
    private final List<Text> chain = new ArrayList<>();

    public TextChain append(Object obj) {
        if (obj != null) {
            chain.add(Text.of(obj.toString()));
        }
        return this;
    }

    public TextChain append(Text text) {
        if (text != null) {
            chain.add(text);
        }
        return this;
    }

    public TextChain append(TextChain textChain) {
        for (Text text : textChain) {
            append(text);
        }
        return this;
    }

    public static TextChain of(String... texts) {
        TextChain chain = new TextChain();
        for (String text : texts) {
            chain.append(Text.of(text));
        }
        return chain;
    }

    public static TextChain of(Text... texts) {
        TextChain chain = new TextChain();
        for (Text text : texts) {
            chain.append(text);
        }
        return chain;
    }

    @NotNull
    @Override
    public Iterator<Text> iterator() {
        return chain.iterator();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Text text : chain) {
            builder.append(text.toString());
        }
        return builder.toString();
    }
}
