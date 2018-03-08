package main.java.com.ping23;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.java.com.ping23.util.FileUtilities;

public class FindTaggedContentSolution
{
    public static void main(String[] args)
    {

        String pattern = "<([^>]+)>([^<>]+)</\\1>";
        Pattern r = Pattern.compile(pattern);

        // Scanner in = new Scanner(System.in);

        try
        {
            // Scanner in = new Scanner(INPUT2);
            Scanner in = new Scanner(FileUtilities.getFileFromRelativeFullPath(
                "/main/java/com/ping23/input.txt"));

            int testCases = Integer.parseInt(in.nextLine());
            while (in.hasNextLine())
            {
                String line = in.nextLine();

                Matcher m = r.matcher(line);
                boolean findMatch = true;
                while (m.find())
                {
                    System.out.println(m.group(2));
                    findMatch = false;
                }
                if (findMatch)
                {
                    System.out.println("None");
                }
                testCases--;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

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
