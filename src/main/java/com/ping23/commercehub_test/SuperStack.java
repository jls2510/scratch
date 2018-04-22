package com.ping23.commercehub_test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SuperStack {
    
    public SuperStack() {
        System.out.println("SuperStack constructor");
    }

    public static void main(String[] args)
        throws FileNotFoundException, IOException {

        SuperStack superStack = new SuperStack();

        String filename = "input006.txt";
        URL url = SuperStack.class.getResource(filename);
        String absoluteFullPathFilename = url.getPath();

        // System.out.println(absoluteFullPathFilename);

        File file = new File(absoluteFullPathFilename);

        //Scanner fileScanner = new Scanner(file);
        Scanner fileScanner = new Scanner(file);
        String inputString = "";
        while (fileScanner.hasNextLine()) {
            String aLine = fileScanner.nextLine();
            inputString += aLine + "\n";
            // System.out.println(aLine);
        }
        fileScanner.close();

        //System.out.println(inputString);

        String[] _operations = superStack.getOperations(inputString);

        String actualOutput = superStack.superStack(_operations);
        
        System.out.println(actualOutput);

    }

    /**
     * get (read) the operations from the input data
     */
    public String[] getOperations(String inputSource) {
        
        Scanner in = new Scanner(inputSource);

        // Scanner in = new Scanner(INPUT_2);
        int _operations_size = 0;
        _operations_size = Integer.parseInt(in.nextLine().trim());
        String[] _operations = new String[_operations_size];
        String _operations_item;
        for (int _operations_i =
            0; _operations_i < _operations_size; _operations_i++) {
            try {
                _operations_item = in.nextLine();
            }
            catch (Exception e) {
                _operations_item = null;
            }
            _operations[_operations_i] = _operations_item;
        }

        return _operations;

    } // end getOperations()

    /*
     * Complete the function below.
     */

    public String superStack(String[] operations) {
        String resultsString = "";

        // initialize stackArray to 25 elements
        int[] stackArray = new int[25];
        int currentTopIndex = -1;

        // for each operation
        for (int opIndex = 0; opIndex < operations.length; opIndex++) {
            // parse the operation string
            String operation = operations[opIndex];
            String regex = "^(\\w+)\\s*(-?\\d*)\\s*(-?\\d*)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(operation);
            matcher.find(); // this assumes there will be only one operation per
                            // line

            // the verb is assumed to be in the first position
            String verb = matcher.group(1);

            // POP ---------------------------------
            if (verb.equals("pop")) {
                stackArray[currentTopIndex] = 0;
                --currentTopIndex;
                if (currentTopIndex < 0) {
                    String result = "EMPTY";
                    //System.out.println(result);
                    resultsString += result + "\r\n";
                }
                else {
                    int result = stackArray[currentTopIndex];
                    //System.out.println(result);
                    resultsString += result + "\r\n";
                }
                continue;
            }

            // PUSH -------------------------------------------------
            if (verb.equals("push")) {
                // if we've reached the length of the array then double the
                // length
                // depending on usage and size I might try to optimize this by
                // shrinking
                // it back down at some point if there's a lot of unused
                // capacity
                if (stackArray.length < currentTopIndex + 2) {
                    stackArray =
                        Arrays.copyOf(stackArray, stackArray.length * 2);
                }

                // get first int argument
                // for production code I would check for valid input here
                int firstArg = 0;
                if (matcher.group(2) != null && !matcher.group(2).equals("")) {
                    firstArg = Integer.parseInt(matcher.group(2));

                }

                // do the push
                ++currentTopIndex;
                stackArray[currentTopIndex] = firstArg;

                int result = stackArray[currentTopIndex];
                //System.out.println(result);
                resultsString += result + "\r\n";
                continue;
            }

            // INC -------------------------------------------------------------
            if (verb.equals("inc")) {
                // get first int argument
                // for production code I would check for valid input here
                int firstArg = 0;
                if (matcher.group(2) != null && !matcher.group(2).equals("")) {
                    firstArg = Integer.parseInt(matcher.group(2));

                }
                // get second int argument
                // for production code I would check for valid input here
                int secondArg = 0;
                if (matcher.group(3) != null && !matcher.group(3).equals("")) {
                    secondArg = Integer.parseInt(matcher.group(3));
                }

                // first arg is "e", the number of elements to increment
                // second arg is "k", the value to increment by
                for (int incIndex = 0; incIndex < firstArg; incIndex++) {
                    stackArray[incIndex] = stackArray[incIndex] + secondArg;
                }

                int result = stackArray[currentTopIndex];
                //System.out.println(result);
                resultsString += result + "\r\n";
                continue;
            }

        }

        return resultsString;

    } // end superStack()

    private static final String INPUT_1 = "12\r\n" + "push 4\r\n" + "pop\r\n"
        + "push 3\r\n" + "push 5\r\n" + "push 2\r\n" + "inc 3 1\r\n" + "pop\r\n"
        + "push 1\r\n" + "inc 2 2\r\n" + "push 4\r\n" + "pop\r\n" + "pop";

    private static final String INPUT_2 =
        "8\r\n" + "push -36\r\n" + "push 20\r\n" + "pop \r\n" + "push -9\r\n"
            + "pop \r\n" + "push -53\r\n" + "pop \r\n" + "inc 1 -17";

}
