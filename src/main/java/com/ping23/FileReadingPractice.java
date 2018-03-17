package com.ping23;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReadingPractice {

    public static void main(String[] args)
        throws IOException, FileNotFoundException {

        String filename = "input_log.txt";

        String user_home = System.getProperties().getProperty("user.home");
        String fullpath_filename = user_home + "/" + filename;

        // open the file for reading
        BufferedReader bufferedReader = null;
        Scanner fileScanner = null;

        // OPTIONS FOR READING ---------------------------------
        int option = 4;
        switch (option) {
            case 1: {
                FileInputStream fis = new FileInputStream(fullpath_filename);
                InputStreamReader isr = new InputStreamReader(fis);
                bufferedReader = new BufferedReader(isr);
                break;
            }

            case 2: {
                FileReader fileReader = new FileReader(fullpath_filename);
                bufferedReader = new BufferedReader(fileReader);
                break;
            }

            case 3: {
                Path path = Paths.get(fullpath_filename);
                bufferedReader = Files.newBufferedReader(path);
                break;
            }

            case 4: {
                Path path = Paths.get(fullpath_filename);
                fileScanner = new Scanner(path);
                break;

            }

            case 5:
            default: {
                File file = new File(fullpath_filename);
                FileReader fileReader = new FileReader(file);
                bufferedReader = new BufferedReader(fileReader);
                break;
            }

        } // end switch

        // read file lines into data structure
        ArrayList<String> fileLinesArrayList = new ArrayList<String>();
        if (bufferedReader != null) {
            while (bufferedReader.ready()) {
                String aLine = bufferedReader.readLine();
                fileLinesArrayList.add(aLine);
                System.out.println(aLine);
            }
            bufferedReader.close();
        }

        if (fileScanner != null) {
            while (fileScanner.hasNextLine()) {
                String aLine = fileScanner.nextLine();
                fileLinesArrayList.add(aLine);
                System.out.println(aLine);
            }
            fileScanner.close();
        }

    }

}
