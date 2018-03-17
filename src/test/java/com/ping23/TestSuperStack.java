package com.ping23;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ping23.ch_test.SuperStack;

public class TestSuperStack {
    
    private SuperStack classUnderTest;
    
    @Before
    public void setUp() throws Exception {
        classUnderTest = new SuperStack();
    }

    @Test
    public void testSuperStack() throws FileNotFoundException {
        String filename = "/test/java/com/ping23/input006.txt";
        URL url = SuperStack.class.getResource(filename);
        String absoluteFullPathFilename = url.getPath();

        // System.out.println(absoluteFullPathFilename);

        File file = new File(absoluteFullPathFilename);
        Scanner fileScanner = new Scanner(file);
        String inputString = "";
        while (fileScanner.hasNextLine()) {
            String aLine = fileScanner.nextLine();
            inputString += aLine + "\n";
            // System.out.println(aLine);
        }
        fileScanner.close();

        // String[] _operations = SuperStack.getOperations(inputString);
        // String[] _operations = SuperStack.getOperations(INPUT_1);
        String[] _operations = classUnderTest.getOperations(INPUT_2);

        String actualOutput = classUnderTest.superStack(_operations);

        System.out.println(EXPECTED_OUTPUT_2);
        System.out.println("===");
        System.out.println(actualOutput);
        System.out.println("===");

        Assert.assertEquals(EXPECTED_OUTPUT_2, actualOutput);

    } // end testSuperStack()

    private static final String INPUT_1 = "12\r\n" + "push 4\r\n" + "pop\r\n"
        + "push 3\r\n" + "push 5\r\n" + "push 2\r\n" + "inc 3 1\r\n" + "pop\r\n"
        + "push 1\r\n" + "inc 2 2\r\n" + "push 4\r\n" + "pop\r\n" + "pop";

    private static final String INPUT_2 =
        "8\r\n" + "push -36\r\n" + "push 20\r\n" + "pop \r\n" + "push -9\r\n"
            + "pop \r\n" + "push -53\r\n" + "pop \r\n" + "inc 1 -17";

    private static final String EXPECTED_OUTPUT_2 = "-36\r\n" + "20\r\n"
        + "-36\r\n" + "-9\r\n" + "-36\r\n" + "-53\r\n" + "-36\r\n" + "-53\r\n";

}
