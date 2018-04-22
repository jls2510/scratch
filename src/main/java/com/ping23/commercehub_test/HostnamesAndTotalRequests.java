package com.ping23.commercehub_test;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class HostnamesAndTotalRequests
{
    private static final String INPUT_1 = "input_log.txt";

    public static void main(String[] args) throws IOException
    {

        // Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(INPUT_1);

        // read the string filename
        String filename;
        filename = scanner.nextLine();
        scanner.close();

        String user_home = System.getProperties().getProperty("user.home");
        String fullpath_filename = user_home + "/" + filename;

        // open the file for reading
        File file = new File(fullpath_filename);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);

        // read file lines into data structure
        ArrayList<String> fileLinesArrayList = new ArrayList<String>();
        while (reader.ready())
        {
            fileLinesArrayList.add(reader.readLine());
        }
        reader.close();

        // parse each line -----------------------------------------------
        // regex to parse the lines
        String regex = "^(\\S+)\\s";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = null;

        // HashMap will enforce unique hostnames and allow us to accumulate
        // occurrances
        HashMap<String, Integer> hostnames = new HashMap<String, Integer>();

        // iterate over the lines
        for (String aLine : fileLinesArrayList)
        {
            // get the hostname
            matcher = pattern.matcher(aLine);
            matcher.find();
            String hostname = matcher.group(1);

            // accumulate the count
            int count = 1;
            Integer currentCount = hostnames.get(hostname);
            if (currentCount != null)
            {
                count += currentCount.intValue();
            }

            // store the result
            hostnames.put(hostname, count);
        }

        // open the output file
        String outputFilename = user_home + "/" + "records_" + filename;
        File outputFile = new File(outputFilename);
        BufferedWriter writer =
            new BufferedWriter(new FileWriter(outputFile));

        // write a line to the file for each hostname / count pair
        for (String hostname : hostnames.keySet())
        {
            String outLine = hostname + " " + hostnames.get(hostname);
            System.out.println(outLine);
            writer.write(outLine);
            writer.newLine();
        }

        // close the writer
        writer.close();
    }

}
