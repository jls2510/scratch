package com.ping23;

import com.ping23.commercehub_test.SuperStack;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class FilePathExploration {

    public static void main(String[] args) throws IOException, URISyntaxException {

        final String filename = "/input006.txt";
        URI uri = new URI(filename);
        String absoluteFullPathFilename = uri.getPath();
        System.out.println(absoluteFullPathFilename);

        final String path = new File("").getPath();
        System.out.println("getPath() yields: " + path);

        final String rootPath = new File("").getAbsolutePath();
        System.out.println("getAbsolutePath() for rootPath yields: " + rootPath);

        //String aPath = new File()

        final String absolutePath = new File("/com/ping23/x.txt").getAbsolutePath();
        System.out.println("getAbsolutePath() yields: " + absolutePath);

        final String canonicalPath = new File("/com/ping23/x.txt").getCanonicalPath();
        System.out.println("getCanonicalPath() yields: " + canonicalPath);

        final String anotherFilename = "input006.txt";
        //anotherFilename = "";
        final URL url = SuperStack.class.getResource(anotherFilename);
        final String absoluteFullPathAnotherFilename = url.getPath();
        System.out.println("absoluteFullPathAnotherFilename = " + absoluteFullPathAnotherFilename);

    }
}
