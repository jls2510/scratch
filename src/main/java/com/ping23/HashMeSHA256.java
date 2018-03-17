package com.ping23;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.security.*;
import java.nio.charset.StandardCharsets;

public class HashMeSHA256
{

    public static void main(String[] args) throws NoSuchAlgorithmException
    {
        String messageString = "My Very Important Super Secret Message";
        
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();        
        digest.update(messageString.getBytes(StandardCharsets.UTF_8));
        byte[] hashBytes = digest.digest();

        String hashCode = "";
        for (byte b : hashBytes)
        {
            hashCode += String.format("%02x", b);
        }

        System.out.println(hashCode);

    }
}
