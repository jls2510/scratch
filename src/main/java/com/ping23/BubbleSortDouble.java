package com.ping23;

import java.util.Random;

public class BubbleSortDouble
{
    private static int swapCounter = 0;
    private static int unswappedCounter = 0;
    
    public static void main(String[] args)
    {
        double[] arrayToSort = new double[100];
        Random randGen = new Random();
        for (int index = 0; index < arrayToSort.length; index++)
        {
            arrayToSort[index] = randGen.nextDouble() * 100.0;
        }
        
        System.out.println("*** arrayToSort prior to sort");
        for (int index = 0; index < arrayToSort.length; index++) {
            System.out.print(arrayToSort[index] + " ");
        }
        System.out.println();
        
        swapCounter = 0;
        unswappedCounter = 0;
        bubbleSort(arrayToSort);
        
        System.out.println("*** arrayToSort after sort");
        for (int index = 0; index < arrayToSort.length; index++) {
            System.out.print(arrayToSort[index] + " ");
        }
        System.out.println();

        System.out.println("swapCounter = " + swapCounter);
        System.out.println("unswappedCounter = " + unswappedCounter);
        
        

    }
    
    public static double[] bubbleSort(double[] array) {
        
        boolean swapped = true;
        int stopper = 0;
        double tmp;
        
        while (swapped) {
            swapped = false;
            stopper++;
            for (int index = 0; index < array.length - stopper; index++) {
                if (array[index] > array[index + 1]) {
                    tmp = array[index];
                    array[index] = array[index + 1];
                    array[index + 1] = tmp;
                    swapped = true;
                    swapCounter++;
                } // if
                else {
                    unswappedCounter++;
                }
            } // for
        } // while
        
        return array;
    } // bubbleSort()

}
