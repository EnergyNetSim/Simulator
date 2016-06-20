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
 * Created by nickcariss on 07.06.16.
 */
public class MainController implements ActionListener{


    private MainView mainView;
    private NetworkSelectionView networkSelectionView;
    private SettingsView settingsView;
    private MainModel model;
    private DatabaseConnection dbCon;

    public MainController () {


        DatabaseConnection dbCon = new DatabaseConnection();
        if(dbCon.openConnection())
        {
            mainView = new MainView("EnergyNetSim");
            model = new MainModel(dbCon);

            mainView.setListener(this);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Verbindung zur Datenbank konnte nicht hergestellt werden!");
        }



    }

    public void showView()
    {
        mainView.setVisible(true);
    }


    public void actionPerformed(ActionEvent e)
    {
        String strActionCommand = e.getActionCommand();

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

    class NetworkSelectionController implements ActionListener {

        NetworkSelectionView view;

        public NetworkSelectionController(NetworkSelectionView view) {
            this.view = view;
            //view.setNetworks(model.getNetworks()); //is done in view constructor
            System.out.println("In NetworkSelectionController Constructor");
        }

        public void actionPerformed(ActionEvent e) {

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

    class SettingsController implements ActionListener {

        SettingsView view;

        public SettingsController(SettingsView view) {
            this.view = view;
        }

        public void actionPerformed(ActionEvent e) {

            String strActionCommand = e.getActionCommand();

            if (strActionCommand.equals("btnOk"))
            {
                model.saveSetting(view.getActiveIndex(), view.getCurrentValue());
                view.refresh();
            }

        }

    }
}