package hftl.simulator.views.helper;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import hftl.simulator.models.helper.*;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Student on 24.05.2016.
 */
public class ChartingHistogram implements Charting
{
    private String title;
    private DataSeries[] inputData;
    private String captionX;
    private String captionY;

    public ChartingHistogram(String title, DataSeries[] inputData, String captionX, String captionY)
    {
        this.title = title;
        this.inputData = inputData;
        this.captionX = captionX;
        this.captionY = captionY;
        printInputData();
    }

    public void printInputData()
    {
        System.out.println("Eingabedaten:");
        for(int i=0; i<inputData.length;i++)
        {
            inputData[i].printValues();
        }
    }

    public JPanel DoChart() throws Exception
    {
        ChartPanel chartPanel;
        if (inputData != null)
        {
            XYDataset dataset = createDataset();
            JFreeChart chart = createChart(dataset);
            chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
            return chartPanel;
        }
        else
        {
            Exception e = new Exception("Keine Datenreihen vorhanden! Diagramm kann nicht erzeugt werden.");
            throw e;
        }
    }

    private XYDataset createDataset()
    {
        XYSeriesCollection dataset;
        XYSeries dataseries;
        DataPoint point;

        if(inputData.length == 0)
        {
            System.out.println("Keine Daten da!");
            return null;
        }
        else
        {
            dataset = new XYSeriesCollection();

            System.out.println("Daten einlesen...");
            for (int i = 0; i<inputData.length; i++)
            {
                dataseries = new XYSeries(inputData[i].Title());
                System.out.print(dataseries.getDescription() + ":  ");
                for(int j=0; j<inputData[i].NumValues();j++)
                {
                    point = inputData[i].getValue(j);
                    dataseries.add(point.X(), point.Y());
                    System.out.println(j + ". Datensatz eingefügt.");
                }

                dataset.addSeries(dataseries);
            }
            return dataset;
        }
    }

    private JFreeChart createChart(final XYDataset dataset) {

        // create the chart...
        final JFreeChart chart = ChartFactory.createXYLineChart(
                title,      // chart title
                captionX,                      // x axis label
                captionY,                      // y axis label
                dataset,                  // data
                PlotOrientation.VERTICAL,
                true,                     // include legend
                true,                     // tooltips
                false                     // urls
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);

//        final StandardLegend legend = (StandardLegend) chart.getLegend();
        //      legend.setDisplaySeriesShapes(true);

        // get a reference to the plot for further customisation...
        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
        //    plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesShapesVisible(1, false);
        plot.setRenderer(renderer);

        // change the auto tick unit selection to integer units only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        // OPTIONAL CUSTOMISATION COMPLETED.

        return chart;

    }
}