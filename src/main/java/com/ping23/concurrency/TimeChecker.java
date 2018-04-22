package com.ping23.concurrency;

import java.time.LocalTime;

public class TimeChecker implements Runnable {

    private LocalTime startTime = LocalTime.now();
    private String stillTickingMessage = "Still Ticking";
    private String doneMessage = "Done.";

    public static void main(String[] args) {
        TimeChecker tc = new TimeChecker();
        tc.run();

    }

    public void run() {

        while (LocalTime.now().isBefore(startTime.plusSeconds(10))) {
            stillTickingMessage += ".";
            System.out.println(stillTickingMessage);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {

            }
        }
        System.out.println(doneMessage);

    }
}
