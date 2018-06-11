package com.ping23.concurrency;


import java.util.concurrent.ConcurrentLinkedQueue;

public class SharedStaticQueueTest {

    public static ConcurrentLinkedQueue<String> myQueue = new ConcurrentLinkedQueue<String>();

    public static void main(String[] args) {


        for (int index = 0; index < 10; ++index) {
            final String hello = "Hello " + index;
            (new Thread(new Runnable() {
                public void run() {
                    //addToQueue("Hello");
                    myQueue.add(hello);
                }
            })).start();
        }

        while (myQueue.peek() != null) {
            System.out.println(myQueue.poll());
        }
    }

}
