package com.ping23.concurrency;

public class SharedStaticQueueTest {

    public static void main(String[] args) {

        for (int index = 0; index < 10; ++index) {
            final String hello = "Hello " + index;
            System.out.println("adding to queue: " + hello);
            (new Thread(new Runnable() {
                public void run() {
                    // addToQueue("Hello");
                    SharedStaticQueue.myQueue.add(hello);
                }
            })).start();
        }

        try {
            Thread.sleep(3000);
        }
        catch (Exception e) {
        }

        while (SharedStaticQueue.myQueue.peek() != null) {
            System.out.println(SharedStaticQueue.myQueue.poll());
        }
    }

}
