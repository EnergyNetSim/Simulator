package hftl.simulator.views;

import hftl.simulator.models.Networks;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by nickcariss on 07.06.16.
 */
public class NetworkSelectionView extends JDialog
{
    JPanel panButtons;
    JScrollPane scrPane;
    JList list;
    JButton btnOk;
    JButton btnCancel;

    public NetworkSelectionView(JFrame owner, Networks networks)
    {
        super(owner, "Select networks", true);
        initialize(networks);
    }

    private void initialize(Networks networks)
    {
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(300,400);

        //Create list panel:
        list = new JList(networks);
        list.setSelectedIndices(networks.getSelectedIndices());
        scrPane = new JScrollPane(list);
        scrPane.setBorder(new EmptyBorder(10,10,10,10));
        //scrPane.add(list);
        //JLabel testlabel = new JLabel("Testlabel");
        //testlabel.setSize(20,20);
        //scrPane.add(testlabel);
        this.add(scrPane, BorderLayout.CENTER);

        //Create button panel
        panButtons = new JPanel(new FlowLayout());
        panButtons.setBorder(new EmptyBorder(10,10,10,10));

        this.add(panButtons, BorderLayout.SOUTH);

        //Create buttons:
        btnOk = createButton("OK");
        panButtons.add(btnOk);
        btnCancel = createButton("Cancel");
        panButtons.add(btnCancel);

    }

    private JButton createButton(String title) {
        JButton button;

        button = new JButton(title);
        button.setSize(50, 20);
        return button;
    }

    public void setListener(ActionListener l)
    {
        btnOk.setActionCommand("btnOk");
        btnCancel.setActionCommand("btnCancel");

        btnOk.addActionListener(l);
        btnCancel.addActionListener(l);
    }

    /**
     *
     * @return
     */
    public int[] getSelectedIndices()
    {

        return list.getSelectedIndices();
    }
}
