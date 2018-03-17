package com.ping23.concurrency;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class LessonEighteen {

    public static void main(String[] args) {

        addThreadsToPool();

    }


    public static void addThreadsToPool() {

        ScheduledThreadPoolExecutor eventPool = new ScheduledThreadPoolExecutor(5);

        eventPool.scheduleAtFixedRate(new GetSystemTime(), 0, 5, TimeUnit.SECONDS);
        eventPool.scheduleAtFixedRate(new PerformSystemCheck("Calendar"), 5, 1, TimeUnit.SECONDS);
        eventPool.scheduleAtFixedRate(new PerformSystemCheck("Mail"), 4, 2, TimeUnit.SECONDS);
        eventPool.scheduleAtFixedRate(new PerformSystemCheck("Preferences"), 3, 3, TimeUnit.SECONDS);
        eventPool.scheduleAtFixedRate(new PerformSystemCheck("Schedule"), 2, 4, TimeUnit.SECONDS);
        eventPool.scheduleAtFixedRate(new PerformSystemCheck("Memory"), 1, 5, TimeUnit.SECONDS);

        System.out.println("Number of Threads: " + Thread.activeCount());

        Thread[] activeThreads = new Thread[Thread.activeCount()];
        Thread.enumerate(activeThreads);

        System.out.println("Active Threads:");
        for (Thread aThread : activeThreads) {
            System.out.println("Thread name: " + aThread.getName());
            System.out.println("Thread priority: " + aThread.getPriority());
        }
    }
}
