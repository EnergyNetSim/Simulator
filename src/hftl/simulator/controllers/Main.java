package hftl.simulator.controllers;

/**
 * Created by Student on 05.05.2016.
 */
public class Main {

    public static void main(String[] args) {
        try {
            hftl.simulator.views.MainWindow windowMain = new hftl.simulator.views.MainWindow();

            hftl.simulator.models.DatabaseBasics connect = new  hftl.simulator.models.DatabaseBasics();

            System.out.println("Drücken Sie Enter um das Spiel nun zu beenden.");
            System.in.read();
        }
        catch (Exception ex)
        {
            System.out.println("Spielfehler: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}