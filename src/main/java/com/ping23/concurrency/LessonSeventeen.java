package com.ping23.concurrency;

import com.ping23.concurrency.GetTheMail;
import com.ping23.concurrency.GetTime20;

public class LessonSeventeen {

    public static void main(String[] args) {

        Thread getTime = new GetTime20();

        Runnable getMail = new GetTheMail(5);
        Runnable getMailAgain = new GetTheMail(3);

        getTime.start();

        new Thread(getMail).start();
        new Thread(getMailAgain).start();

        System.out.println("Number of Threads: " + Thread.activeCount());

    }
}
