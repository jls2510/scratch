package com.ping23;

import java.util.Calendar;
import java.text.DateFormat;

public class Scratch
{
    
    public static void main(String[] args)
    {
        //System.out.println(getDay("05","08","2015"));
        
        String s = "He is a very very good boy, isn't he?";
        //s = "                 a";
        s = "                      ";
        s = s.trim();
        
        System.out.println(">>" + s + "<<");
        printTokens(s);

    }

    public static void printTokens(String s) {
        
        String regex = "[ !,?._'@]+";
        //String regex = "[^a-zA-Z]+";   
        
        String[] tokens = s.split(regex);
        System.out.println(tokens.length);
        System.out.println("----------");
        for (int index = 0; index < tokens.length; index++) {
            System.out.println(tokens[index]);
        }
        System.out.println("----------");

    }
    

    public static String getDay(String day, String month, String year)
    {
        /*
         * Write your code here.
         */
        Calendar cal = Calendar.getInstance();
        cal.set(Integer.parseInt(year), Integer.parseInt(month)-1,
            Integer.parseInt(day));

        System.out.println("" + Integer.parseInt(year) + ": " + Integer.parseInt(month) + ": " + Integer.parseInt(day));

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        String dayName = "";
        String[] dayNames = { "SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY",
            "THURSDAY", "FRIDAY", "SATURDAY" };
        
        DateFormat df = DateFormat.getDateInstance();
        String formattedDate = df.format(cal.getTime());

        //return dayNames[dayOfWeek - 1];
        return formattedDate;

    }

}
