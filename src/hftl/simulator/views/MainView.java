package hftl.simulator.views;

import hftl.simulator.views.helper.ChartingHistogram;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Student on 04.05.2016.
 */
public class MainView extends JFrame {

    JPanel panHeader;
    JPanel panBody;
    JPanel panNetworkLoad;
    JPanel panPowerConsumption;
    JPanel panCost;
    JButton btnSettings;
    JButton btnCalculate;
    JButton btnSelectNetworks;

    public MainView(String strTitle) {
        super(strTitle);

        initialize();

    }

    private void initialize() {
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);

        //Create header panel:
        panHeader = new JPanel(new FlowLayout(FlowLayout.CENTER));
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
        btnSelectNetworks = createButton("Netzwerk-Auswahl");
        panHeader.add(btnSelectNetworks);

        btnSettings = createButton("Einstellungen");
        panHeader.add(btnSettings);

        btnCalculate = createButton("Berechnen");
        btnCalculate.setEnabled(false);
        panHeader.add(btnCalculate);

        this.setVisible(true);

    }

    private JButton createButton(String title) {
        JButton button;

        button = new JButton(title);
        button.setSize(50, 20);
        return button;
    }

    public void showCostDiagram(XYDataset dsCost) {

        ChartingHistogram chart = new ChartingHistogram("Kosten", dsCost, "Dauer", "Euro");
        panBody.remove(panCost);

        try {
            panCost = chart.DoChart();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("In ShowCostDiagramm.");
        panBody.add(panCost);
        panBody.validate();
        panBody.repaint();
    }

    public void showPowerConsumptionDiagram(XYDataset dsCost) {

        ChartingHistogram chart = new ChartingHistogram("Stromverbrauch", dsCost, "Dauer", "kWh");
        panBody.remove(panPowerConsumption);

        try {
            panPowerConsumption = chart.DoChart();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("In showPowerConsumptionDiagram.");

        panBody.add(panPowerConsumption);
        panBody.validate();
        panBody.repaint();

    }

    public void showNetworkLoadDiagram(XYDataset dsCost)
    {

        ChartingHistogram chart = new ChartingHistogram("Netzlast", dsCost, "Dauer", "Byte");
        panBody.remove(panNetworkLoad);

        try {
            panNetworkLoad = chart.DoChart();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("In showNetworkLoadDiagramm.");

        panBody.add(panNetworkLoad);
        panBody.validate();
        panBody.repaint();

    }

    public void enableCalculating(boolean value)
    {
        btnCalculate.setEnabled(value);
    }

    public void setListener(ActionListener l)
    {
        btnSelectNetworks.setActionCommand("btnNetworkSelection");
        btnSettings.setActionCommand("btnSettings");
        btnCalculate.setActionCommand("btnCalculate");

        btnSelectNetworks.addActionListener(l);
        btnSettings.addActionListener(l);
        btnCalculate.addActionListener(l);
    }
}