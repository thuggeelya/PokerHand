package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class MutableInt {

    private final AtomicInteger value = new AtomicInteger(0);

    public int get() {
        return value.get();
    }

    public void increment() {
        value.incrementAndGet();
    }
}