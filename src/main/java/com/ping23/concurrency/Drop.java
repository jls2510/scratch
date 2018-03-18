package com.ping23.concurrency;

public class Drop {
    // Message sent from producer
    // to consumer.
    private String message;
    // True if consumer should wait
    // for producer to send message,
    // false if producer should wait for
    // consumer to retrieve message.
    private boolean empty = true;

    public synchronized String take() {
        System.out.println("Drop.take() called");
        // Wait until message is
        // available.
        while (empty) {
            System.out.println("Drop.take() EMPTY, can't give you what I don't have!");
            try {
                System.out.println("Drop.take() starting wait");
                wait();
            } catch (InterruptedException e) {
                System.out.println("Drop.take() wait interrupted");
            }
        }
        // Toggle status.
        empty = true;
        // Notify producer that
        // status has changed.
        System.out.println("Drop.take() notifying and returning message: " + message);
        notifyAll();
        return message;
    }

    public synchronized void put(String message) {
        System.out.println("\nDrop.put() called with String: " + message);
        // Wait until message has
        // been retrieved.
        while (!empty) {
            System.out.println("Drop.put() NOT EMPTY, can't accept message: " + message);
            try {
                System.out.println("Drop.put() starting wait");
                wait();
            } catch (InterruptedException e) {
                System.out.println("Drop.put() wait interrupted"); // this never happens
            }
        }
        // Toggle status.
        empty = false;
        // Store message.
        this.message = message;
        // Notify consumer that status
        // has changed.
        System.out.println("Drop.put() accepting message: " + message + ", notifying and returning");
        notifyAll();
    }
}
