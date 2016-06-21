package hftl.simulator.models;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Will hold all information about network traffic.
 * Contains only mock values at the moment.
 *
 */
public class NetworkLoad {

    /**
     * Default constructor.
     *
     */
    public NetworkLoad() {}

    /**
     * Returns a mock XYDataset of aggregated network load for a 24h period.
     *
     * @return  XYDataset   A dataset with one dataseries for 24h of network load.
     */
    public XYDataset getSummary()
    {
        XYSeriesCollection dataset;
        XYSeries dataseries;
        dataset = new XYSeriesCollection();
        dataseries = new XYSeries("Netzlast");

        for(int j=0; j<24;j++)
        {
            dataseries.add(j, Math.sin(j));
        }

        dataset.addSeries(dataseries);
        return dataset;
    }
}
