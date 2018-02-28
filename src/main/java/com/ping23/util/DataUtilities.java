package main.java.com.ping23.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * @author James L. Stevenson
 */
public class DataUtilities
{

    /**
     * non-instantiable
     */
    private DataUtilities()
    {
    }

    /*
     * get a datasource
     */
    public static DataSource getDataSource()
    {

        DataSource datasource = null;
        try
        {
            // Look up the JNDI data source only once at init time
            // this stuff is defined in META-INF/context.xml
            Context envCtx = (Context) new InitialContext();
            datasource = (DataSource) envCtx.lookup("java:comp/env/jdbc/ple");
        }
        catch (NamingException e)
        {
            e.printStackTrace();
        }

        return datasource;
    }

    /**
     * get a Connection from the DataSource instance !!!Remember to CLOSE it
     * when
     * you're done!!!
     * @return the Connection instance
     * @throws SQLException
     */
    public static Connection getConnection(String url, String username,
        String password) throws SQLException
    {
        Connection connection = null;

        connection = DriverManager.getConnection(url, username, password);

        return connection;
    } // getConnection()

} // class
