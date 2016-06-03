package hftl.simulator.views.helper;

import hftl.simulator.models.helper.DataPoint;
import hftl.simulator.models.helper.DataSeries;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;

/**
 * Created by Christian on 31.05.2016.
 */
public class TestChart extends ApplicationFrame
{
    public TestChart(){

        super("Test!!!");
        ChartingHistogram chart = new ChartingHistogram("Stromverbrauch", data(), "Stunde", "mW");
        try
        {
            JPanel panel = chart.DoChart();
            setContentPane(panel);
            this.pack();
            RefineryUtilities.centerFrameOnScreen(this);
            this.setVisible(true);
        }
        catch (Exception e){}
    }

    private DataSeries[] data() {
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
}
