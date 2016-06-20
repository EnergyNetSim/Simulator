package hftl.simulator.models;

import java.sql.*;

/**
 * Created by Christian on 20.06.2016.
 */
public class DatabaseQueries
{
    DatabaseConnection dbCon;

    public DatabaseQueries(DatabaseConnection dbCon)
    {
        this.dbCon = dbCon;
    }

    public ResultSet selectNetworks()
    {
        Statement stmt = dbCon.getStmt();
        try {
            return stmt.executeQuery("SELECT * FROM networks");
        } catch (Exception e) {
            return null;
        }
    }

    public ResultSet selectSettings()
    {
        Statement stmt = dbCon.getStmt();
        try {
            return stmt.executeQuery("SELECT * FROM settings");
        } catch (Exception e) {
            return null;
        }
    }

    public ResultSet updateSetting(String key, String value)
    {
        Statement stmt = dbCon.getStmt();
        try {
            stmt.executeUpdate("UPDATE settings SET settingValue = '" + value + "' WHERE settingKey = '" + key + "';");
            return stmt.executeQuery("SELECT * FROM settings WHERE settingKey = '" + key + "';");
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}