package com.ping23;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.ping23.util.FileUtilities;

public class FileWritingPractice {

    public static void main(String[] args)
        throws IOException, FileNotFoundException {
        
        // get some content
        String[] alice = FileUtilities.getFirstFiveLinesOfAliceInWonderland();

        String filename = "output_log.txt";

        String user_home = System.getProperties().getProperty("user.home");
        String fullpath_filename = user_home + "/" + filename;

        // open the file for reading
        BufferedWriter bufferedWriter = null;

        // OPTIONS FOR READING ---------------------------------
        int option = 5;
        switch (option) {
            case 1: {
                FileOutputStream fileOutputStream =
                    new FileOutputStream(fullpath_filename);
                OutputStreamWriter outputStreamWriter =
                    new OutputStreamWriter(fileOutputStream);
                bufferedWriter = new BufferedWriter(outputStreamWriter);
                break;
            }

            case 2: {
                FileWriter fileWriter = new FileWriter(fullpath_filename);
                bufferedWriter = new BufferedWriter(fileWriter);
                break;
            }

            case 3: {
                Path path = Paths.get(fullpath_filename);
                bufferedWriter = Files.newBufferedWriter(path);
                break;
            }

            case 5:
            default: {
                File file = new File(fullpath_filename);
                FileWriter fileWriter = new FileWriter(file);
                bufferedWriter = new BufferedWriter(fileWriter);
                break;
            }

        } // end switch

        // write lines to file
        if (bufferedWriter != null) {
            bufferedWriter.write("write option: " + option);
            bufferedWriter.newLine();
            for (String line : alice) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        }

    }

}
