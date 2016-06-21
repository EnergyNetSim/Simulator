package hftl.simulator.models;

import java.sql.*;

/**
 * Contains all SQL Statements.
 * Is called by the models and executes the SQL statements against the DB.
 *
 */
public class DatabaseQueries
{
    DatabaseConnection dbCon;

    /**
     * Constructor.
     *
     * @param   dbCon     DB Connection that is passed here by the MainController.
     */
    public DatabaseQueries(DatabaseConnection dbCon)
    {
        this.dbCon = dbCon;
    }

    /**
     * Queries the database for a list of all networks.
     *
     * @return  ResultSet   Contains all networks found in the database table NETWORKS.
     */
    public ResultSet selectNetworks()
    {
        Statement stmt = dbCon.getStmt();
        try
        {
            return stmt.executeQuery("SELECT * FROM networks");
        }
        catch (Exception e)
        {
            return null;
        }
    }

    /**
     * Queries the database for a list of all settings.
     *
     * @return  ResultSet   Contains all settings found in the database table SETTINGS.
     */
    public ResultSet selectSettings()
    {
        Statement stmt = dbCon.getStmt();
        try
        {
            return stmt.executeQuery("SELECT * FROM settings");
        }
        catch (Exception e)
        {
            return null;
        }
    }

    /**
     * Updates a single setting in the database.
     *
     * @param   key         The unique settingKey in the database table SETTINGS.
     * @param   value       The corresponding value which is updated in the database.
     * @return  boolean     Returns true if update was successful. Returns false otherwise.
     */
    public boolean updateSetting(String key, String value)
    {
        Statement stmt = dbCon.getStmt();
        try
        {
            stmt.executeUpdate("UPDATE settings SET settingValue = '" + value + "' WHERE settingKey = '" + key + "';");
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }
}