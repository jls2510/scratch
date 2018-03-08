package main.java.com.ping23;

import java.math.BigDecimal;
import java.util.*;

class BigDecimalStringSort
{

    public static void main(String[] args)
    {
        // Input
        Scanner sc = new Scanner(INPUT);
        int n = sc.nextInt();
        String[] s = new String[n + 2];
        for (int i = 0; i < n; i++)
        {
            s[i] = sc.next();
        }
        sc.close();

        // Write your code here

        boolean swapped = true;
        String tmp = "";
        int stopper = 0;

        while (swapped)
        {
            swapped = false;
            stopper++;
            //System.out.println("s.length = " + s.length);
            for (int index = 0; index < s.length - stopper; index++)
            {
                if (s[index + 1] == null) {
                    break;
                }
                //System.out.println("index = " + index);
                //System.out.println("s[index] = " + s[index]);
                BigDecimal a = new BigDecimal(s[index]);
                //System.out.println("a = " + a.toString());
                //System.out.println("s[index + 1] = " + s[index + 1]);
                BigDecimal b = new BigDecimal(s[index + 1]);
                //System.out.println("b = " + b.toString());
                if (a.compareTo(b) < 0)
                {
                    tmp = new String(s[index]);
                    s[index] = new String(s[index + 1]);
                    s[index + 1] = tmp;
                    swapped = true;
                } // if
            } // for
        } // while
        
        
          // Output
        for (int i = 0; i < n; i++)
        {
            System.out.println(s[i]);
        }
    } // main
    
    public static final String INPUT = "9\r\n" + 
        "-100\r\n" + 
        "50\r\n" + 
        "0\r\n" + 
        "56.6\r\n" + 
        "90\r\n" + 
        "0.12\r\n" + 
        ".12\r\n" + 
        "02.34\r\n" + 
        "000.000";

}
