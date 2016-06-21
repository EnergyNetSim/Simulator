package hftl.simulator.controllers;

import hftl.simulator.models.DatabaseConnection;
import hftl.simulator.models.MainModel;
import hftl.simulator.models.Network;
import hftl.simulator.models.Setting;
import hftl.simulator.views.MainView;
import hftl.simulator.views.NetworkSelectionView;
import hftl.simulator.views.SettingsView;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Heart of the MVC pattern.
 * Contains sub-controllers. Initializes the model and the view.
 *
 * @author youtube.com/NickCariss
 */
public class MainController implements ActionListener{


    private MainView mainView;
    private MainModel model;
    private DatabaseConnection dbCon;

    /**
     * MainController-Constructor.
     * Tries to open DatabaseConnection. Opens the view on success.
     */
    public MainController ()
    {
        dbCon = new DatabaseConnection();

        if(dbCon.openConnection())
        {
            model = new MainModel(dbCon);
            mainView = new MainView("EnergyNetSim");
            mainView.setListener(this);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Could not establish database connection!");
        }
    }

    /**
     * Event handler for MainView.
     * Handles all MainView button click events.
     *
     * @param e The event emitted by the MainView upon clicking a button.
     */
    public void actionPerformed(ActionEvent e)
    {
        String strActionCommand = e.getActionCommand();

        // Determine which button has been pressed.
        if (strActionCommand.equals("btnNetworkSelection"))
        {
            NetworkSelectionView networkSelectionView = new NetworkSelectionView(mainView, model.getNetworks());
            networkSelectionView.setListener(new NetworkSelectionController(networkSelectionView));
            networkSelectionView.setVisible( true );
        }
        else if (strActionCommand.equals("btnSettings"))
        {
            SettingsView settingsView = new SettingsView(mainView, model.getSettings());
            settingsView.setListener(new SettingsController(settingsView));
            settingsView.setVisible( true );
        }
        else if (strActionCommand.equals("btnCalculate"))
        {
            model.calculate();
            mainView.showCostDiagram(model.getDsCost());
            mainView.showNetworkLoadDiagram(model.getDsNetworkLoad());
            mainView.showPowerConsumptionDiagram(model.getDsPowerConsumption());
        }
    }

    //Subklassen implementieren und Views für Settings und SelectNetworks aktualisieren.
    //Die Subklasse ruft beim Schließen des Views die Methode view.enableCalculation(true/false) auf.

    /**
     * Sub-Controller for the network selection dialog.
     * Opens the network selection view and handles all events inside.
     *
     */
    class NetworkSelectionController implements ActionListener
    {
        NetworkSelectionView view;

        public NetworkSelectionController(NetworkSelectionView view)
        {
            this.view = view;
        }

        public void actionPerformed(ActionEvent e)
        {
            String strActionCommand = e.getActionCommand();

            if (strActionCommand.equals("btnOk"))
            {
                model.setSelectedNetworks(view.getSelectedIndices());
                mainView.enableCalculating(model.hasSelectedNetworks());
                view.dispose();
            }
            else if (strActionCommand.equals("btnCancel"))
            {
                view.dispose();
            }
        }
    }

    /**
     * Sub-Controller for the settings dialog.
     * Opens the settings view and handles all events inside.
     *
     */
    class SettingsController implements ActionListener
    {
        SettingsView view;

        public SettingsController(SettingsView view)
        {
            this.view = view;
        }

        public void actionPerformed(ActionEvent e)
        {
            String strActionCommand = e.getActionCommand();

            if (strActionCommand.equals("btnOk"))
            {
                model.saveSetting(view.getActiveIndex(), view.getCurrentValue());
                view.refresh();
            }
        }
    }
}