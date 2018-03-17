package com.ping23.concurrency;

import java.util.Random;

public class Consumer implements Runnable {
    private Drop drop;
    private ProducerConsumerExample parent;

    public Consumer(Drop drop, ProducerConsumerExample parent) {
        this.parent = parent;
        this.drop = drop;
    }

    public void run() {
        Random random = new Random();
        for (String message = drop.take(); !message.equals("DONE"); message = drop.take()) {
            System.out.format("MESSAGE RECEIVED: %s%n", message);
            try {
                System.out.println("Consumer going to sleep...");
                Thread.sleep(random.nextInt(10000));
            } catch (InterruptedException e) {
                System.out.println("Consumer sleep interrupted");
            }
            System.out.println("\nConsumer calling drop.take()");
        }

        parent.stopTheClockTicker();
    }
}