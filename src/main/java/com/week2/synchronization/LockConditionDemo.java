package com.week2.synchronization;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockConditionDemo {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private boolean ready = false;

    public void producer() {
        lock.lock();
        try {
            while (ready) {
                condition.await();
            }
            ready = true;
            System.out.println("Produced!");
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void consumer() {
        lock.lock();
        try {
            while (!ready) {
                condition.await();
            }
            System.out.println("Consumed!");
            ready = false;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        LockConditionDemo demo = new LockConditionDemo();

        Thread producer = new Thread(demo::producer);
        Thread consumer = new Thread(demo::consumer);

        producer.start();
        consumer.start();
    }
}

