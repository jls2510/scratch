package com.ping23.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DBAccess {

    public static void main(String[] args) {

        Connection conn;
        try {
            conn = DriverManager.getConnection(
                "jdbc:ucanaccess://D:/jls/Projects/M-100/m-100_legacy_project/db/snitz_forums_2000_20171220.mdb");
            Statement s = conn.createStatement();

            String query;
            // query = "SELECT [T_MESSAGE] FROM [FORUM_TOPICS] WHERE [T_AUTHOR]
            // = 12 LIMIT 10";
            query =
                "SELECT * FROM FORUM_TOPICS WHERE T_AUTHOR = 12 LIMIT 10";
            ResultSet rs = s.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int index = 1; index <= columnsNumber; index++) {
                    System.out.print(rs.getString(index) + "; ");
                }
                System.out.println();
            }
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    } // main()

}
