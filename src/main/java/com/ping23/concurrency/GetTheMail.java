package com.ping23.concurrency;

public class GetTheMail implements Runnable {

    private int startTime;

    public GetTheMail(int startTime) {

        this.startTime = startTime;
    }

    public void run() {

        while (true) {
            System.out.println("Checking the Mail every " + startTime + "seconds.");

            try {
                Thread.sleep(startTime * 1000);
            } catch (InterruptedException e) {
            }

        }

    }

}
