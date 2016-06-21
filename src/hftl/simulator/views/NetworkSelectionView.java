package hftl.simulator.views;

import hftl.simulator.models.Networks;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Network selection view. Is opened from MainController when Button "Network selection" is clicked.
 *
 */
public class NetworkSelectionView extends JDialog
{
    private JList list;
    private JButton btnOk;
    private JButton btnCancel;

    /**
     * Constructor sets JDialog's owner and gets the network list.
     *
     * @param   owner       JFrame (MainView)
     * @param   networks    Network list
     */
    public NetworkSelectionView(JFrame owner, Networks networks)
    {
        super(owner, "Select networks", true);
        initialize(networks);
    }

    /**
     * Creates all Java-Swing-components and adds them to the JDialog.
     * Adds the network objects to the list.
     *
     * @param networks  Network list
     */
    private void initialize(Networks networks)
    {
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(300,400);

        //Create list panel:
        list = new JList(networks);
        list.setSelectedIndices(networks.getSelectedIndices());
        JScrollPane scrPane = new JScrollPane(list);
        scrPane.setBorder(new EmptyBorder(10,10,10,10));
        this.add(scrPane, BorderLayout.CENTER);

        //Create button panel:
        JPanel panButtons = new JPanel(new FlowLayout());
        panButtons.setBorder(new EmptyBorder(10,10,10,10));
        this.add(panButtons, BorderLayout.SOUTH);

        //Create buttons:
        btnOk = createButton("OK");
        panButtons.add(btnOk);
        btnCancel = createButton("Cancel");
        panButtons.add(btnCancel);
    }

    /**
     * Creates a JButton-object and returns it.
     *
     * @param   title   Caption of the new button.
     * @return  JButton
     */
    private JButton createButton(String title)
    {
        JButton button;

        button = new JButton(title);
        button.setSize(50, 20);
        return button;
    }

    /**
     * Adds an ActionListener object to all buttons.
     *
     * @param   listener   ActionListener in NetworkSelectionController.
     */
    public void setListener(ActionListener listener)
    {
        btnOk.setActionCommand("btnOk");
        btnCancel.setActionCommand("btnCancel");

        btnOk.addActionListener(listener);
        btnCancel.addActionListener(listener);
    }

    /**
     * Returns all selected indices to the controller.
     *
     * @return int[]    Array of all selected inidices.
     */
    public int[] getSelectedIndices()
    {
        return list.getSelectedIndices();
    }
}
