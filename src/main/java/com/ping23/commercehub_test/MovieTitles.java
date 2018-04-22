package com.ping23.commercehub_test;

import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.net.*;
import java.net.HttpURLConnection;

import com.google.gson.*;

public class MovieTitles
{

    /*
     * Complete the function below.
     */

    static String[] getMovieTitles(String substr) throws IOException
    {
        // construct the query string
        String baseQueryString =
            "https://jsonmock.hackerrank.com/api/movies/search/?Title=";
        String queryString = baseQueryString + substr;

        // get the ResponsePage
        ResponsePage responsePage = getResponsePage(queryString);

        // structure to hold movie titles
        ArrayList<String> unsortedMovieTitles = new ArrayList<String>();

        // iterate over response pages
        int numberOfPages = responsePage.getTotal_pages();
        for (int pageNumber = 1; pageNumber <= numberOfPages; pageNumber++)
        {
            // we already have the first response page
            if (pageNumber > 1)
            {
                // get the next page
                queryString = baseQueryString + substr + "&page=" + pageNumber;
                responsePage = getResponsePage(queryString);
            }
            // get the movies
            Movie[] movieArray = responsePage.getMovies();
            buildMovieTitlesArray(movieArray, unsortedMovieTitles);
        }

        String[] movieTitles = new String[unsortedMovieTitles.size()];
        unsortedMovieTitles.toArray(movieTitles);

        // create Comparator - just calls String.compareTo()
        Comparator<String> titleComparator = new Comparator<String>()
        {
            public int compare(String string1, String string2)
            {
                return string1.compareTo(string2);
            }
        };

        // sort the array
        Arrays.sort(movieTitles, titleComparator);

        return movieTitles;

    } // end getMovieTitles()

    /**
     * get the ResponsePage object for the given queryString
     * NOTE I consulted the Java and Gson API documentation extensively while
     * writing this code
     * @param queryString
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    private static ResponsePage getResponsePage(String queryString)
        throws MalformedURLException, IOException
    {
        URL url = new URL(queryString);
        HttpURLConnection urlConnection =
            (HttpURLConnection) url.openConnection();
        InputStream in = urlConnection.getInputStream();
        BufferedReader bufferedReader =
            new BufferedReader(new InputStreamReader(in));
        String responseContent = "";
        String thisLine = "";

        while (true)
        {
            thisLine = bufferedReader.readLine();
            if (thisLine == null)
            {
                break;
            }
            responseContent += thisLine;
        }

        Gson gson = new Gson();
        ResponsePage responsePage =
            gson.fromJson(responseContent, ResponsePage.class);

        return responsePage;

    } // end getResponsePage()

    /**
     * accumulate movie title Strings in the unsortedMovieTitles ArrayList
     * @param movieArray
     * @param unsortedMovieTitles
     */
    private static void buildMovieTitlesArray(Movie[] movieArray,
        ArrayList<String> unsortedMovieTitles)
    {
        for (int movieIndex = 0; movieIndex < movieArray.length; movieIndex++)
        {

            unsortedMovieTitles.add(movieArray[movieIndex].getTitle());
        }
    } // end buildMovieTitles()

    /**
     * a class to hold the page response
     */
    private class ResponsePage
    {
        private int page = 0;
        private int per_page = 0;
        private int total = 0;
        private int total_pages = 0;
        private Movie[] data = null;

        public int getPage()
        {
            return page;
        }

        public int getPer_page()
        {
            return per_page;
        }

        public int getTotal()
        {
            return total;
        }

        public int getTotal_pages()
        {
            return total_pages;
        }

        public Movie[] getMovies()
        {
            return data;
        }
    } // end class ResponsePage

    /**
     * a class to hold movie info
     */
    private class Movie
    {
        private String Title = "";

        public String getTitle()
        {
            return Title;
        }
    } // end class Movie

    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(INPUT_1);
        final String fileName = System.getenv("OUTPUT_PATH");
        BufferedWriter bw = null;
        if (fileName != null)
        {
            bw = new BufferedWriter(new FileWriter(fileName));
        }
        else
        {
            bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        String[] res;
        String _substr;
        try
        {
            _substr = in.nextLine();
        }
        catch (Exception e)
        {
            _substr = null;
        }

        res = getMovieTitles(_substr);
        for (int res_i = 0; res_i < res.length; res_i++)
        {
            bw.write(String.valueOf(res[res_i]));
            bw.newLine();
        }

        bw.close();
    }

    private static final String INPUT_1 = "spiderman";

}
