package hftl.simulator.models;

import java.sql.*;

/**
 * Establishes database connection.
 * All config params are kept as hard-coded values.
 *
 */
public class DatabaseConnection
{

    private final String driver;
    private String dBase;
    private String user;
    private String pw;
    private Connection con;
    private Statement stmt;

    /**
     *  Constructor sets database config params.
     *
     */
    public DatabaseConnection()
    {
        driver = "com.mysql.jdbc.Driver";
        dBase = "jdbc:mysql://localhost/";
        user = "root";
        pw = "root";
    }

    /**
     * Opens database connection.
     *
     * @return Returns true if connection is successfully established.
     */
    public boolean openConnection()
    {
        try
        {
            //Load DB driver
            Class.forName(driver);
            //Establish connection
            con = DriverManager.getConnection(dBase, user, pw);
            //Create DB object
            stmt = con.createStatement();
            stmt.execute("USE energynetsimdb;");
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            return false;
        }
    }

    /**
     * Gets the DB object's Statement object to allow creation of SQL statements.
     *
     * @return  Statement   The DB object's statement object.
     */
    public Statement getStmt()
    {
        return stmt;
    }

    /**
     * Closes the DB connection.
     *
     */
    public void closeConnection()
    {
        try
        {
            stmt.close();
            con.close();
        }
        catch (SQLException sqle)
        {
            System.out.println(sqle.toString());
        }
    }
}