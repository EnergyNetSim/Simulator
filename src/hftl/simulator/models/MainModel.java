package hftl.simulator.models;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.Objects;

/**
 * Main model of the MVC structure.
 * Holds all other models.
 * Single point of contact for the controllers.
 *
 */
public class MainModel
{
    XYDataset dsCost;
    XYDataset dsPowerConsumption;
    NetworkLoad networkLoad;
    Settings settings;
    Networks networks;
    DatabaseQueries dbQuery;

    /**
     * Constructor initializes all sub-models and the dbQuery object which is then passed to the sub-models as needed.
     *
     * @param   dbCon   DB connection used for executing SQL statements in the sub-models.
     */
    public MainModel (DatabaseConnection dbCon)
    {
        dbQuery = new DatabaseQueries(dbCon);
        networks = new Networks(dbQuery);
        networks.load();
        settings = new Settings(dbQuery);
        settings.load();
        networkLoad = new NetworkLoad();
    }

    /**
     * This is where the energy usage algorithm calculation has to be implemented.
     * At the moment, hard coded estimation values are used.
     *
     */
    public void calculate ()
    {
        dsCost = simulate(true);
        dsPowerConsumption = simulate(false);
    }

    /**
     * Used by the controller to access simulation results for cost values.
     *
     * @return  XYDataset   Used by charts in the MainView.
     */
    public XYDataset getDsCost()
    {
        return dsCost;
    }

    /**
     * Used by the controller to access simulation results for consumption values.
     *
     * @return  XYDataset   Used by charts in the MainView.
     */
    public XYDataset getDsPowerConsumption()
    {
        return dsPowerConsumption;
    }

    /**
     * Used by the controller to access summarized network load values.
     *
     * @return  XYDataset   Used by charts in the MainView.
     */
    public XYDataset getDsNetworkLoad()
    {
        return networkLoad.getSummary();
    }

    /**
     * Simulates values while the algorithm is still being implemented by S.L.
     *
     * @return  XYDataset   This dataset contains two Dataseries with mock values.
     */
    private XYDataset simulate (boolean boolCost)
    {
        XYSeriesCollection dataset;
        XYSeries dataseries;
        Network network;
        dataset = new XYSeriesCollection();
        Double cost = 1.0; // EUR/MWh, 29.06.2016

        if (boolCost)
        {
            cost = Double.valueOf(settings.getElement("Price(EUR)/MWh").getValue());
        }

        for (int i = 0; i < networks.size(); i++)
        {
            network = (Network)networks.getElementAt(i);
            if(network.getSelected())
            {
                if (Objects.equals(network.getName(), "Static Network"))
                {
                    dataseries = new XYSeries(network.getName());

                    for (int j = 0; j < 24; j++) {
                        dataseries.add(j, cost * 1.24);
                    }

                    dataset.addSeries(dataseries);
                }
                else if (Objects.equals(network.getName(), "Dynamic Network"))
                {
                    dataseries = new XYSeries(network.getName());

                    dataseries.add(0, cost * 0.95);
                    dataseries.add(1, cost * 0.95);
                    dataseries.add(2, cost * 0.95);
                    dataseries.add(3, cost * 0.95);
                    dataseries.add(4, cost * 0.95);
                    dataseries.add(5, cost * 0.95);
                    dataseries.add(6, cost * 0.95);
                    dataseries.add(7, cost * 0.95);
                    dataseries.add(8, cost * 1.09);
                    dataseries.add(9, cost * 1.13);
                    dataseries.add(10, cost * 1.15);
                    dataseries.add(11, cost * 1.18);
                    dataseries.add(12, cost * 1.22);
                    dataseries.add(13, cost * 1.24);
                    dataseries.add(14, cost * 1.24);
                    dataseries.add(15, cost * 1.24);
                    dataseries.add(16, cost * 1.24);
                    dataseries.add(17, cost * 1.21);
                    dataseries.add(18, cost * 1.16);
                    dataseries.add(19, cost * 1.14);
                    dataseries.add(20, cost * 1.13);
                    dataseries.add(21, cost * 0.96);
                    dataseries.add(22, cost * 0.95);
                    dataseries.add(23, cost * 0.95);

                    dataset.addSeries(dataseries);
                }
            }
        }
        return dataset;
    }

    /**
     * Used by the controller to access the list of networks.
     *
     * @return  Networks    List of all networks.
     */
    public Networks getNetworks()
    {
        return networks;
    }

    /**
     * Used by the controller to access the list of settings.
     *
     * @return  Settings    List of all settings.
     */
    public Settings getSettings()
    {
        return settings;
    }

    /**
     * Networks selected by the user are forwarded to the networks model.
     *
     * @param   indices     Array of indices of the networks selected by the user.
     */
    public void setSelectedNetworks(int[] indices)
    {
        networks.setSelectedIndices(indices);
    }

    /**
     * Check if there are any selected networks.
     * (Used for deactivating the calculation button.)
     *
     * @return  boolean     True if at least one network is selected.
     */
    public boolean hasSelectedNetworks()
    {
        return networks.hasSelectedNetworks();
    }

    /**
     * Ask settings to save a setting value.
     *
     * @param   index   List index of the setting to change.
     * @param   value   New value.
     */
    public void saveSetting(int index, String value)
    {
        settings.save(index, value);
    }
}