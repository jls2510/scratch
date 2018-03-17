package com.ping23.hr_exercises;

import java.math.BigDecimal;
import java.util.*;

public class BigDecimalStringSort {

    public static void main(String[] args) {

        String[] sortedStringItems = getSortedStringItems(TEST_INPUT);

        String actualOutput = "";
        // Output
        for (int index = 0; index < sortedStringItems.length; index++) {
            System.out.println(sortedStringItems[index]);
            actualOutput += sortedStringItems[index];
        }

        System.out.println(actualOutput);

    } // main

    /**
     * get sorted string items
     * this method reads in an input string consisting of:
     * an integer, representing the number of items to follow;
     * a number of items which are string representations of decimal numbers,
     * separated by newlines.
     * The method returns a string array containing the same items
     * sorted by their decimal values
     */
    public static String[] getSortedStringItems(String input) {

        // Input
        Scanner scanner = new Scanner(input);
        int numberOfItems = scanner.nextInt();
        String[] stringItems = new String[numberOfItems];
        for (int index = 0; index < numberOfItems; index++) {
            stringItems[index] = scanner.next();
        }
        scanner.close();

        boolean swapped = true;
        String tmp = "";
        int stopper = 0;

        while (swapped) {
            swapped = false;
            stopper++;
            // System.out.println("s.length = " + s.length);
            for (int index = 0; index < stringItems.length - stopper; index++) {
                if (stringItems[index + 1] == null) {
                    break;
                }
                // System.out.println("index = " + index);
                // System.out.println("s[index] = " + s[index]);
                BigDecimal a = new BigDecimal(stringItems[index]);
                // System.out.println("a = " + a.toString());
                // System.out.println("s[index + 1] = " + s[index + 1]);
                BigDecimal b = new BigDecimal(stringItems[index + 1]);
                // System.out.println("b = " + b.toString());
                if (a.compareTo(b) < 0) {
                    tmp = new String(stringItems[index]);
                    stringItems[index] = new String(stringItems[index + 1]);
                    stringItems[index + 1] = tmp;
                    swapped = true;
                } // if
            } // for
        } // while

        return stringItems;

    } // end getSortedStringItems()

    public static final String TEST_INPUT =
        "9\r\n" + "-100\r\n" + "50\r\n" + "0\r\n" + "56.6\r\n" + "90\r\n"
            + "0.12\r\n" + ".12\r\n" + "02.34\r\n" + "000.000";

    public static final String EXPECTED_OUTPUT =
        "9056.65002.340.12.120000.000-100";

}
