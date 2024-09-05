package com.week2.synchronization;

public class SynchronizedMethod {
    private int count = 0;
    private final Object lock = new Object();

    // Synchronized method
    public synchronized void incrementMethod() {
        System.out.println(Thread.currentThread().getName() + " is incrementing. Current count: " + count);
        count++;
        System.out.println(Thread.currentThread().getName() + " has incremented. New count: " + count);
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        SynchronizedMethod demo = new SynchronizedMethod();
        Runnable task = () -> {
            for (int i = 0; i < 5; i++) {
                demo.incrementMethod();
            }
        };
        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final count: " + demo.getCount());
    }
}
