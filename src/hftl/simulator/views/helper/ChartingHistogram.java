package hftl.simulator.views.helper;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;

/**
 * Creates a diagram within a JPanel.
 *
 */
public class ChartingHistogram
{
    private String title;
    private XYDataset inputData;
    private String captionX;
    private String captionY;

    /**
     * Constructor sets title, raw data and captions for x- and y-axes.
     *
     * @param title
     * @param inputData
     * @param captionX
     * @param captionY
     */
    public ChartingHistogram(String title, XYDataset inputData, String captionX, String captionY)
    {
        this.title = title;
        this.inputData = inputData;
        this.captionX = captionX;
        this.captionY = captionY;
    }

    /**
     * Creates a JPanel containing a JFreeChart.
     *
     * @return  JPanel  JPanel for further use in view.
     * @throws Exception
     */
    public JPanel DoChart() throws Exception
    {
        ChartPanel chartPanel;
        if (inputData != null)
        {
            JFreeChart chart = createChart(inputData);
            chartPanel = new ChartPanel(chart);
            return chartPanel;
        }
        else
        {
            Exception e = new Exception("Keine Datenreihen vorhanden! Diagramm kann nicht erzeugt werden.");
            throw e;
        }
    }

    /**
     * Creates a JFreeChart out of the XYDataset.
     *
     * @param   dataset XYDataset including dataseries.
     * @return  JFreeChart Created chart.
     */
    private JFreeChart createChart(final XYDataset dataset)
    {

        // create the chart...
        final JFreeChart chart = ChartFactory.createXYLineChart(
                title,                      // chart title
                captionX,                   // x axis label
                captionY,                   // y axis label
                dataset,                    // data
                PlotOrientation.VERTICAL,
                true,                       // include legend
                true,                       // tooltips
                false                       // urls
        );

        chart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesShapesVisible(1, false);
        plot.setRenderer(renderer);

        // change the auto tick unit selection to integer units only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        return chart;
    }
}