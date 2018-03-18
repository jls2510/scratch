package com.ping23.concurrency;

import java.util.Random;

public class Producer implements Runnable {
    private Drop drop;

    public Producer(Drop drop) {
        this.drop = drop;
    }

    public void run() {
        String importantInfo[] = {
                "Mares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy too"
        };
        Random random = new Random();

        for (int i = 0;
             i < importantInfo.length;
             i++) {
            System.out.println("\nProducer putting line: " + importantInfo[i]);
            drop.put(importantInfo[i]);
            try {
                System.out.println("Producer going to sleep...");
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {
                System.out.println("Producer sleep interrupted"); // this never happens

            }
        }
        drop.put("DONE");
    }
}