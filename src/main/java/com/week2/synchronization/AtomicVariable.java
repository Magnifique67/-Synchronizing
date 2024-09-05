package com.week2.synchronization;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicVariable {
    private final AtomicInteger counter = new AtomicInteger(0);

    public void increment() {
        int value = counter.incrementAndGet();
        System.out.println("Counter: " + value);
    }

    public static void main(String[] args) {
        AtomicVariable demo = new AtomicVariable();

        Thread t1 = new Thread(demo::increment);
        Thread t2 = new Thread(demo::increment);

        t1.start();
        t2.start();
    }
}
