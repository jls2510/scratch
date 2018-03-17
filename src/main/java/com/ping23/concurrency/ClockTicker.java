package com.ping23.concurrency;

public class ClockTicker implements Runnable {

    private boolean timeToStop = false;

    public void run() {

        while (!timeToStop) {
            System.out.print("+");

            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {

            }

        }
    }

    public void stop() {
        timeToStop = true;
    }
}
