package hftl.simulator.models;

import hftl.simulator.models.helper.DataPoint;
import hftl.simulator.models.helper.DataSeries;

import javax.swing.*;

/**
 * Created by nickcariss on 07.06.16.
 */
public class MainModel {

    NetworkLayout[] selectedNetworks;
    DataSeries[] dsCost;
    DataSeries[] dsPowerConsumption;
    DataSeries[] dsNetworkLoad;
    Network[] networks;
    Setting[] settings;
    NetworkListModel networks;


    public MainModel () {


        networks = new NetworkListModel();

    }

    public void calculate ()
    {
        dsCost = simulate();
        dsPowerConsumption = simulate();
        dsNetworkLoad = simulate();
    }

    public DataSeries[] getDsCost()
    {
        return dsCost;
    }

    public DataSeries[] getDsPowerConsumption()
    {
        return dsPowerConsumption;
    }

    public DataSeries[] getDsNetworkLoad()
    {
        return dsNetworkLoad;
    }

    private DataSeries[] simulate () {

        DataSeries[] dataseries;

        dataseries = new DataSeries[2];
        dataseries[0] = new DataSeries("Netz 1", 24);
        dataseries[1] = new DataSeries("Netz 2", 24);

        try {
            dataseries[0].insertValue(new DataPoint(1, 5));
            dataseries[0].insertValue(new DataPoint(2, 6));
            dataseries[0].insertValue(new DataPoint(3, 5));
            dataseries[0].insertValue(new DataPoint(4, 4));
            dataseries[0].insertValue(new DataPoint(5, 2));
            dataseries[0].insertValue(new DataPoint(6, 4));
            dataseries[0].insertValue(new DataPoint(7, 7));
            dataseries[0].insertValue(new DataPoint(8, 9));
            dataseries[0].insertValue(new DataPoint(9, 11));
            dataseries[0].insertValue(new DataPoint(10, 13));
            dataseries[0].insertValue(new DataPoint(11, 12));
            dataseries[0].insertValue(new DataPoint(12, 13));
            dataseries[0].insertValue(new DataPoint(13, 14));
            dataseries[0].insertValue(new DataPoint(14, 15));
            dataseries[0].insertValue(new DataPoint(15, 15));
            dataseries[0].insertValue(new DataPoint(16, 12));
            dataseries[0].insertValue(new DataPoint(17, 10));
            dataseries[0].insertValue(new DataPoint(18, 12));
            dataseries[0].insertValue(new DataPoint(19, 14));
            dataseries[0].insertValue(new DataPoint(20, 14));
            dataseries[0].insertValue(new DataPoint(21, 11));
            dataseries[0].insertValue(new DataPoint(22, 9));
            dataseries[0].insertValue(new DataPoint(23, 7));
            dataseries[0].insertValue(new DataPoint(24, 6));

            dataseries[1].insertValue(new DataPoint(1, 2));
            dataseries[1].insertValue(new DataPoint(2, 4));
            dataseries[1].insertValue(new DataPoint(3, 3));
            dataseries[1].insertValue(new DataPoint(4, 2));
            dataseries[1].insertValue(new DataPoint(5, 1));
            dataseries[1].insertValue(new DataPoint(6, 2));
            dataseries[1].insertValue(new DataPoint(7, 5));
            dataseries[1].insertValue(new DataPoint(8, 7));
            dataseries[1].insertValue(new DataPoint(9, 9));
            dataseries[1].insertValue(new DataPoint(10, 11));
            dataseries[1].insertValue(new DataPoint(11, 10));
            dataseries[1].insertValue(new DataPoint(12, 11));
            dataseries[1].insertValue(new DataPoint(13, 12));
            dataseries[1].insertValue(new DataPoint(14, 13));
            dataseries[1].insertValue(new DataPoint(15, 13));
            dataseries[1].insertValue(new DataPoint(16, 10));
            dataseries[1].insertValue(new DataPoint(17, 8));
            dataseries[1].insertValue(new DataPoint(18, 10));
            dataseries[1].insertValue(new DataPoint(19, 12));
            dataseries[1].insertValue(new DataPoint(20, 12));
            dataseries[1].insertValue(new DataPoint(21, 9));
            dataseries[1].insertValue(new DataPoint(22, 7));
            dataseries[1].insertValue(new DataPoint(23, 5));
            dataseries[1].insertValue(new DataPoint(24, 4));

        } catch (Exception e) {
            System.out.println(e);
        }

        return dataseries;

    }

    public NetworkListModel getNetworks() {



        return networks;

    }

    public void setSelectedNetworks(Network[] networks){

        //TODO!
        //selectedNetworks =  NetworkLayouts.getById(intNetworkIds);

    }

    public boolean hasSelectedNetworks() {
        //TODO: Check if any selected networks in networks array
        return true;
    }

    public void setSettings(Setting[] settings) {
        //TODO: Save settings to DB
    }
}
