package com.ping23.commercehub_test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class SentenceWordOrder
{

    /*
     * Complete the function below.
     */
    static String arrange(String sentence)
    {
        String returnSentence = "";

        // lowercase and remove period
        sentence = sentence.toLowerCase();
        sentence = sentence.replaceAll("\\.$", "");
        // split the string into words
        String[] words = sentence.split(" ");

        // sort the words from shortest to longest
        // SORT ----------------------------------------
        String tmp = "";
        int stopper = 0;
        
        // iterate until the sort is done vs iterating over all positions
        // this shortens the process in some cases
        boolean stillSwapping = true;
        
        // iterate over array segments shrinking from end toward beginning
        while (stillSwapping)
        {
            stillSwapping = false;
            stopper++;
            for (int index = 0; index < words.length - stopper; index++)
            {
                if (words[index].length() > words[index + 1].length())
                {
                    tmp = words[index];
                    words[index] = words[index + 1];
                    words[index + 1] = tmp;
                    stillSwapping = true;
                }
            }
        }
        // end SORT ------------------------------------
        
        // reconstruct sentence
        
        // capitalize first letter
        String firstWord = words[0];
        String fl = firstWord.substring(0, 1);
        fl = fl.toUpperCase();
        firstWord = fl + firstWord.substring(1);
        words[0] = firstWord;
        
        // assemble the sentence
        for (int wordIndex = 0; wordIndex < words.length; wordIndex++) {
            returnSentence += words[wordIndex];
            if (!(wordIndex == words.length -1)) {
                returnSentence += " ";
            }
        }
        
        // don't forget to add the period!
        returnSentence += ".";

        //System.out.println(returnSentence);
        return returnSentence;
    }

    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(INPUT_1);
        final String fileName = System.getenv("OUTPUT_PATH");
        BufferedWriter bw = null;
        if (fileName != null)
        {
            bw = new BufferedWriter(new FileWriter(fileName));
        }
        else
        {
            bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        String res;
        String sentence;
        try
        {
            sentence = in.nextLine();
        }
        catch (Exception e)
        {
            sentence = null;
        }

        res = arrange(sentence);
        bw.write(res);
        bw.newLine();

        bw.close();
    }

    private static final String INPUT_1 =
        "The lines are printed in reverse order.";

}
