package hftl.simulator.models;

/**
 * Created by Student on 05.05.2016.
 */
import java.sql.*;
/**
 * Created by Student on 05.05.2016.
 */
public class DatabaseBasics {

    private Connection con;
    private Statement st;
    private ResultSet rs;

    public DatabaseBasics() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("DB on");

        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }

}