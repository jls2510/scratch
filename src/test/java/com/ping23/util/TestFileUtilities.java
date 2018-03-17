package com.ping23.util;

import java.io.File;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.JUnit4;

import com.ping23.util.FileUtilities;

@RunWith(JUnit4.class)
public class TestFileUtilities
{

    @Test
    public void testWriteReadToUserHome()
    {
        System.out.println("TestFileUtilities.testWriteReadToUserHome()");

        String testFilename = "test.txt";

        // make sure the file does not already exist
        File testFile = FileUtilities.getFileInUserHome(testFilename);
        testFile.delete();

        // write then read
        doTheWriteThing(testFile);
        String[] readLines = doTheReadThing(testFile);

        // clean up
        testFile.delete();

        String[] writtenLines =
            FileUtilities.getFirstFiveLinesOfAliceInWonderland();

        System.out.println(
            Arrays.deepEquals(writtenLines, readLines) ? "Equal" : "Not Equal");

        Assert.assertArrayEquals(writtenLines, readLines);

    } // testWriteReadToUserHome()

    @Test
    public void testWriteReadToRelativeFullPath()
    {
        System.out
            .println("/TestFileUtilities.testWriteReadToRelativeFullPath()");

        // NOTE that the file must already exist in this scenario
        String relativeFullPath = "/main/java/com/ping23/util/test.txt";

        File testFile = null;

        try
        {
            testFile =
                FileUtilities.getFileFromRelativeFullPath(relativeFullPath);
        }
        catch (Exception e)
        {
            System.out.println("fail");
            return;
            //e.printStackTrace();
        }

        doTheWriteThing(testFile);

        String[] readLines = doTheReadThing(testFile);
        String[] writtenLines =
            FileUtilities.getFirstFiveLinesOfAliceInWonderland();

        System.out.println(
            Arrays.deepEquals(writtenLines, readLines) ? "Equal" : "Not Equal");

        Assert.assertArrayEquals(writtenLines, readLines);

    } // testWriteReadToRelativeFullPath()

    @Test
    public void testWriteReadToAbsoluteFullPath()
    {
        System.out
            .println("TestFileUtilities.testWriteReadToAbsoluteFullPath()");

        String absoluteFullPath = "C:/Users/jls/TestFileUtilities/test.txt";

        // note that the file must already exist
        File testFile =
            FileUtilities.getFileFromAbsoluteFullPath(absoluteFullPath);
        testFile.delete();

        // write then read
        doTheWriteThing(testFile);
        String[] readLines = doTheReadThing(testFile);

        // clean up
        testFile.delete();

        String[] writtenLines =
            FileUtilities.getFirstFiveLinesOfAliceInWonderland();
        System.out.println(
            Arrays.deepEquals(writtenLines, readLines) ? "Equal" : "Not Equal");

        Assert.assertArrayEquals(writtenLines, readLines);

    } // testWriteReadToAbsoluteFullPath()

    /**
     * do the write thing
     */
    public static boolean doTheWriteThing(File file)
    {
        boolean successValue = false;

        boolean shouldAppend = false;

        successValue = FileUtilities.writeLinesToTextFile(file,
            FileUtilities.getFirstFiveLinesOfAliceInWonderland(), shouldAppend);

        return successValue;
    }

    /**
     * do the read thing
     */
    public static String[] doTheReadThing(File file)
    {

        String[] fileLines = FileUtilities.readTextFile(file);

        return fileLines;

    }

    public static void main(String[] args)
    {
        Result result = JUnitCore.runClasses(TestFileUtilities.class);

        for (Failure failure : result.getFailures())
        {
            System.out.println(failure.toString());
        }

    }

}
