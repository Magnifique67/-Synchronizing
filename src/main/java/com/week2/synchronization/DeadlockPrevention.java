package com.week2.synchronization;

class DeadLockPrevention {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public void method1() {
        System.out.println(Thread.currentThread().getName() + " is trying to acquire lock1 in method1");
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName() + " acquired lock1 in method1");
            try { Thread.sleep(50); } catch (InterruptedException e) {}

            System.out.println(Thread.currentThread().getName() + " is trying to acquire lock2 in method1");
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + " acquired lock2 in method1");

            }
        }
    }

    public void method2() {
        System.out.println(Thread.currentThread().getName() + " is trying to acquire lock1 in method2");
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName() + " acquired lock1 in method2");
            try { Thread.sleep(50); } catch (InterruptedException e) {}

            System.out.println(Thread.currentThread().getName() + " is trying to acquire lock2 in method2");
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + " acquired lock2 in method2");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DeadLockPrevention demo = new DeadLockPrevention();

        Thread t1 = new Thread(demo::method1, "Thread-1");
        Thread t2 = new Thread(demo::method2, "Thread-2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}