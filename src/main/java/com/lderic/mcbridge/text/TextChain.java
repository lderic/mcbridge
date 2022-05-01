package com.lderic.mcbridge.text;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TextChain implements Iterable<Text> {
    private final List<Text> chain = new ArrayList<>();

    public void append(Text text) {
        chain.add(text);
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
}
