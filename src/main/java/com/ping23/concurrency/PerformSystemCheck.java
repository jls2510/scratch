package com.ping23.concurrency;

import java.util.concurrent.locks.ReentrantLock;

public class PerformSystemCheck implements Runnable {

    private String checkWhat;

    private ReentrantLock lock = new ReentrantLock();

    public PerformSystemCheck(String checkWhat) {
        this.checkWhat = checkWhat;
    }

    public void run() {

        lock.lock();

        System.out.println("Checking: " + checkWhat);

        lock.unlock();
    }

}
