package hftl.simulator.models;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Created by Student on 05.05.2016.
 */
public class NetworkLoad {

    //TODO: Get data from Database
    //TODO:

    /**
     *
     *
     */
    public NetworkLoad()
    {

    }

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
