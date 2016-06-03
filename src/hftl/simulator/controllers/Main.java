package hftl.simulator.controllers;

import hftl.simulator.views.helper.TestChart;

/**
 * Created by Student on 05.05.2016.
 */
public class Main {

    public static void main(final String[] args) {
        try {
            //hftl.simulator.views.MainWindow windowMain = new hftl.simulator.views.MainWindow();
            TestChart test = new TestChart();
            System.out.println("Drücken Sie Enter um das Spiel nun zu beenden.");
            //System.in.read();
        }
        catch (Exception ex)
        {
            System.out.println("Spielfehler: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}