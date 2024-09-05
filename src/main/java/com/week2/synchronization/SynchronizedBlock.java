package com.week2.synchronization;

public class SynchronizedBlock {
    private int count = 0;
    private final Object lock = new Object(); // Lock object for synchronized block

    // Method with synchronized block
    public void incrementBlock() {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + " is incrementing using synchronized block. Current count: " + count);
            count++;
            System.out.println(Thread.currentThread().getName() + " has incremented using synchronized block. New count: " + count);
        }
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        SynchronizedBlock demo = new SynchronizedBlock();

        Runnable task = () -> {
            for (int i = 0; i < 5; i++) {
                demo.incrementBlock();
            }
        };
        Thread t1 = new Thread(task, "Thread-Block-1");
        Thread t2 = new Thread(task, "Thread-Block-2");
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
