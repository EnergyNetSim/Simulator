package hftl.simulator.views;

import hftl.simulator.views.helper.ChartingHistogram;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Main view. Is opened by the MainController at the beginning of program execution.
 *
 */
public class MainView extends JFrame {

    private JPanel panBody;
    private JPanel panNetworkLoad;
    private JPanel panPowerConsumption;
    private JPanel panCost;
    private JButton btnSettings;
    private JButton btnCalculate;
    private JButton btnSelectNetworks;

    /**
     * Constructor. Sets title. Initializes the JFrame.
     *
     * @param title
     */
    public MainView(String title)
    {
        super(title);
        initialize();
    }

    /**
     * Creates all Java-Swing-components and adds them to JFrame.
     *
     */
    private void initialize()
    {
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);

        //Create header panel:
        JPanel panHeader = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.add(panHeader, BorderLayout.NORTH);

        //Create body panel
        panBody = new JPanel(new GridLayout(3, 1, 30, 10));
        panBody.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.add(panBody, BorderLayout.CENTER);

        //Create 3 chart panels:
        panNetworkLoad = new JPanel();
        panNetworkLoad.setBackground(Color.darkGray);
        panBody.add(panNetworkLoad);
        panPowerConsumption = new JPanel();
        panPowerConsumption.setBackground(Color.darkGray);
        panBody.add(panPowerConsumption);
        panCost = new JPanel();
        panCost.setBackground(Color.darkGray);
        panBody.add(panCost);

        //Create buttons:
        btnSelectNetworks = createButton("Network selection");
        panHeader.add(btnSelectNetworks);

        btnSettings = createButton("Settings");
        panHeader.add(btnSettings);

        btnCalculate = createButton("Calculate");
        btnCalculate.setEnabled(false);
        panHeader.add(btnCalculate);

        this.setVisible(true);
    }

    /**
     * Creates a JButton-object and returns it.
     *
     * @param   title   Caption of the new button.
     * @return  JButton
     */
    private JButton createButton(String title) {
        JButton button;

        button = new JButton(title);
        button.setSize(50, 20);
        return button;
    }

    /**
     * Replaces the panCost-Panel with the cost chart.
     *
     * @param   dsCost  XYDataset to create a new chart.
     */
    public void showCostDiagram(XYDataset dsCost)
    {
        ChartingHistogram chart = new ChartingHistogram("Costs", dsCost, "Time", "Euro");
        panBody.remove(panCost);

        try
        {
            panCost = chart.DoChart();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        panBody.add(panCost);
        panBody.validate();
        panBody.repaint();
    }

    /**
     * Replaces the panPowerConsumption-Panel with the powerConsumption chart.
     *
     * @param   dsPowerConsumption  XYDataset to create a new chart.
     */
    public void showPowerConsumptionDiagram(XYDataset dsPowerConsumption)
    {
        ChartingHistogram chart = new ChartingHistogram("Power consumption", dsPowerConsumption, "Time", "MWh");
        panBody.remove(panPowerConsumption);

        try
        {
            panPowerConsumption = chart.DoChart();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        panBody.add(panPowerConsumption);
        panBody.validate();
        panBody.repaint();
    }

    /**
     * Replaces the panNetworkLoad-Panel with the networkLoad chart.
     *
     * @param dsNetworkLoad XYDataset to create a new chart.
     */
    public void showNetworkLoadDiagram(XYDataset dsNetworkLoad)
    {
        ChartingHistogram chart = new ChartingHistogram("Normalized network load", dsNetworkLoad, "Time", "Network load");
        panBody.remove(panNetworkLoad);

        try
        {
            panNetworkLoad = chart.DoChart();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        panBody.add(panNetworkLoad);
        panBody.validate();
        panBody.repaint();
    }

    /**
     * Sets the button "Calculate" enabled or disabled.
     * @param   value   True if enabled.
     */
    public void enableCalculating(boolean value)
    {
        btnCalculate.setEnabled(value);
    }

    /**
     * Adds an ActionListener object to all buttons.
     *
     * @param   listener   ActionListener in MainController.
     */
    public void setListener(ActionListener listener)
    {
        btnSelectNetworks.setActionCommand("btnNetworkSelection");
        btnSettings.setActionCommand("btnSettings");
        btnCalculate.setActionCommand("btnCalculate");

        btnSelectNetworks.addActionListener(listener);
        btnSettings.addActionListener(listener);
        btnCalculate.addActionListener(listener);
    }
}