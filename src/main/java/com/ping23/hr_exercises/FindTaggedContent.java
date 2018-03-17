package com.ping23.hr_exercises;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

import main.java.com.ping23.util.FileUtilities;

public class FindTaggedContent
{

    private static String regex = "<(.+)>(.*)</\\1>";
    private static Pattern pattern = Pattern.compile(regex);
    private static String anyTagRegex = "[<|>]";
    private static Pattern anyTagPattern = Pattern.compile(anyTagRegex);
    private static String badString = "Bad";
    private static boolean foundContent = false;

    public static void main(String[] args)
    {

        try
        {
            // Scanner in = new Scanner(INPUT2);
            Scanner in = new Scanner(FileUtilities.getFileFromRelativeFullPath(
                "/main/java/com/ping23/input.txt"));
            int testCases = Integer.parseInt(in.nextLine());

            while (in.hasNextLine())
            {
                String line = in.nextLine();
                Matcher matcher = pattern.matcher(line);

                foundContent = false;

                // if there's no match at all then catch it here
                if (matcher.find())
                {
                    // dive into the string
                    // if we don't find anything then print "None"
                    if (findAndPrintContent(line) == null && !foundContent)
                    {
                        System.out.println("None");
                    }
                }
                else
                {
                    System.out.println("None");
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * @param line
     * @return
     */
    private static String findAndPrintContent(String line)
    {
        if (line == null)
        {
            return null;
        }

        // System.out.println("findAndPrintContent()" + line);

        Matcher matcher = pattern.matcher(line);
        String content = line;
        if (matcher.find())
        {
            // System.out.println("if(matcher.find()): true");
            matcher.reset();
            while (matcher.find())
            {
                // System.out.println("while(matcher.find())");
                content = findAndPrintContent(matcher.group(2));

            }

        }
        else
        {
            // no matching tags in the string so it's a final nested string
            // it's either good or bad

            // check for empty content
            if (content.equals(""))
            {
                content = badString;
            }

            // check for non-matching tags which invalidate content
            Matcher anyTagMatcher = anyTagPattern.matcher(content);
            if (anyTagMatcher.find())
            {
                content = badString;
            }

            if (content.equals(badString))
            {
                return null;
            }

            System.out.println(content);
            foundContent = true;

        }

        // System.out.println("findAndPrintContent() returning: " + content);
        return content;
    } // findAndPrintContent()

    private static final String INPUT = "4\r\n"
        + "<h1>Nayeem loves counseling</h1>\r\n"
        + "<h1><h1>Sanjay has no watch</h1></h1><par>So wait for a while<par>\r\n"
        + "<Amee>safat codes like a ninja</amee>\r\n"
        + "<SA premium>Imtiaz has a secret crash</SA premium>";

    private static final String INPUT2 = "10\r\n" + "<h1>some</h1>\r\n"
        + "<h1>had<h1>public</h1></h1>\r\n"
        + "<h1>had<h1>public</h1515></h1>\r\n" + "<h1><h1></h1></h1>\r\n"
        + "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\r\n"
        + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\r\n"
        + "<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>\r\n"
        + "<>hello</>\r\n" + "<>hello</><h>dim</h>\r\n"
        + "<>hello</><h>dim</h>>>>>";

}
