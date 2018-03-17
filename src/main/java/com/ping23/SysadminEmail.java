package com.ping23;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SysadminEmail {

    public static String[] getSysadminEmails() {

        String[] sysadmin_emails = new String[3];
        Properties prop = new Properties();
        InputStream input = null;

        try {

            String filename = "sysadmin_email.properties";
            input = SysadminEmail.class.getResourceAsStream(filename);
            prop.load(input);

            // get the email addresses
            sysadmin_emails[0] = prop.getProperty("mailer.sysadmin_email.1");
            sysadmin_emails[1] = prop.getProperty("mailer.sysadmin_email.2");
            sysadmin_emails[2] = prop.getProperty("mailer.sysadmin_email.3");


        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sysadmin_emails;

    }
}
