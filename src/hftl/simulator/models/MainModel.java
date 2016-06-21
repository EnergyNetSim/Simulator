package hftl.simulator.models;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

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
        dsCost = simulate();
        dsPowerConsumption = simulate();
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
    private XYDataset simulate ()
    {
        XYSeriesCollection dataset;
        XYSeries dataseries;
        Network network;
        dataset = new XYSeriesCollection();

        for (int i = 0; i < networks.size(); i++)
        {
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