package hftl.simulator.models;

import java.sql.*;

/**
 * Created by Student on 05.05.2016.
 */
public class DatabaseConnection
{

    private final String treiber = "com.mysql.jdbc.Driver";
    private String dBase = "jdbc:mysql://localhost/";
    private String benutzer = "root";
    private String passwort = "root";
    private Connection con;
    private Statement stmt;

    /** Konstruktor stellt die Daten zum Verbindungsaufbau zusammen.*/
    public DatabaseConnection()
    {}

    public boolean openConnection()
    {
        try {
            //Laedt den Datenbanktreiber
            Class.forName(treiber);
            //Stellt die Verbindung her
            con = DriverManager.getConnection(dBase, benutzer, passwort);
            //Erzeugt ein Objekt fuer Abfragen und Aenderungen der Datenbank
            stmt = con.createStatement();
            stmt.execute("USE energynetsimdb;");
            return true;
        }
        catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe.toString());
            return false;
        }
        catch (SQLException sqle) {
            System.out.println(sqle.toString());
            return false;
        }
    }

    public Statement getStmt()
    {
        return stmt;
    }

    public void closeConnection()
    {
        try {
            stmt.close();
            con.close();
        }
        catch (SQLException sqle) {
            System.out.println(sqle.toString());
        }
    }

    public void insert(String table, String[] columns, String[][] values)
    {

    }

    public void getData()
    {
        try {
            ResultSet results = stmt.executeQuery("SELECT * FROM networks");
            System.out.println("Folgende Netzwerke sind vorhanden:");


        }
        catch (SQLException sqle)
        {
            System.out.println(sqle.toString());
        }
    }
}