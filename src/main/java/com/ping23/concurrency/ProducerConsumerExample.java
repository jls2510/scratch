package com.ping23.concurrency;

public class ProducerConsumerExample {

    private ClockTicker clockTicker;

    public static void main(String[] args) {
        new ProducerConsumerExample().startTheParty();
    }

    public void startTheParty() {
        Drop drop = new Drop();
        (new Thread(new Producer(drop))).start();
        (new Thread(new Consumer(drop, this))).start();

        clockTicker = new ClockTicker();
        (new Thread(clockTicker)).start();

    }

    public void stopTheClockTicker () {
        clockTicker.stop();
    }
}