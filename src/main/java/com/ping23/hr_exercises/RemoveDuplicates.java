package com.ping23.hr_exercises;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RemoveDuplicates
{

    public static void main(String[] args)
    {

        String regex = "(?i)\\b(\\w+)\\b\\s+\\b\\1\\b";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

        Scanner in = new Scanner(System.in);
        int numSentences = Integer.parseInt(in.nextLine());

        while (numSentences-- > 0)
        {
            String input = in.nextLine();

            Matcher m = p.matcher(input);

            // Check for subsequences of input that match the compiled pattern
            while (m.find())
            {
                
                input = input.replaceAll("(?i)\\b(\\w+)\\b(?:\\s+\\b\\1\\b)+",
                    "$1");
            }

            // Prints the modified sentence.
            System.out.println(input);
            
            // (?i) case insensitive
            // \\b word boundary
            // (\\w+) word character, one or more (parens = capture group)
            // (?: forward lookahead
            // \\s+\\b\\1\\b spaces, word boundary, 1st capture group, word boundary
            // )+ one or more > captures any number of multiples
            
        }

        in.close();
    }
    

    private static final String INPUT = "";

    private static final String EXPECTED = "";


}

