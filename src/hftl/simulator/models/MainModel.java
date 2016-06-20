package hftl.simulator.models;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Created by nickcariss on 07.06.16.
 */
public class MainModel {

    NetworkLayout[] selectedNetworks;
    XYDataset dsCost;
    XYDataset dsPowerConsumption;
    NetworkLoad networkLoad;
    Settings settings;
    NetworkListModel networks;
    DatabaseQueries dbQuery;

    public MainModel (DatabaseConnection dbCon) {

        dbQuery = new DatabaseQueries(dbCon);
        networks = new NetworkListModel(dbQuery);
        networks.load();
        settings = new Settings(dbQuery);
        settings.load();
        networkLoad = new NetworkLoad();

    }

    /**
     * Main business logic
     */
    public void calculate ()
    {
        dsCost = simulate();
        dsPowerConsumption = simulate();
    }

    public XYDataset getDsCost()
    {
        return dsCost;
    }

    public XYDataset getDsPowerConsumption()
    {
        return dsPowerConsumption;
    }

    public XYDataset getDsNetworkLoad()
    {
        return networkLoad.getSummary();
    }

    private XYDataset simulate () {

        XYSeriesCollection dataset;
        XYSeries dataseries;
        Network network;

        dataset = new XYSeriesCollection();

        for (int i = 0; i < networks.size(); i++) {
            network = (Network)networks.getElementAt(i);
            if (network.getSelected())
            {
                dataseries = new XYSeries(network.getName());
                for(int j=0; j<24;j++)
                {
                    dataseries.add(j, j*2+i);
                }

                dataset.addSeries(dataseries);
            }
        }

        return dataset;

    }

    public NetworkListModel getNetworks() {

        return networks;

    }

    public Settings getSettings() {

        return settings;

    }

    public void setSelectedNetworks(int[] indices){

        networks.setSelectedIndices(indices);

    }

    public boolean hasSelectedNetworks() {

        return networks.hasSelectedNetworks();

    }

    public void saveSetting(int index, String value) {
        settings.save(index, value);
    }
}