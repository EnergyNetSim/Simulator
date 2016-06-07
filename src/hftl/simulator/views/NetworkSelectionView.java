package hftl.simulator.views;

import hftl.simulator.models.Network;

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
    JList listNetworks;
    JButton btnOk;
    JButton btnCancel;

    public NetworkSelectionView(JFrame owner)
    {
        super(owner, "Netzwerke wählen", true);
        initialize();
    }

    private void initialize()
    {
        String[] eintraege = {"1","2","3"}; //TODO: Was braucht die Liste? ListModel?

        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(200,400);

        //Create list panel:
        listNetworks = new JList(eintraege);
        scrPane = new JScrollPane();
        scrPane.setBorder(new EmptyBorder(10,10,10,10));
        scrPane.add(listNetworks);
        this.add(scrPane, BorderLayout.CENTER);

        //Create button panel
        panButtons = new JPanel(new FlowLayout());
        panButtons.setBorder(new EmptyBorder(10,10,10,10));

        this.add(panButtons, BorderLayout.SOUTH);

        //Create buttons:
        btnOk = createButton("OK");
        panButtons.add(btnOk);
        btnCancel = createButton("Abbrechen");
        panButtons.add(btnCancel);

        this.setVisible(true);

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

    public Network[] getSelectedNetworks() {

        //TODO: Return selected network ids as array (obviously)
        return null;
    }

    public void setNetworks(Network[] networks) {

        //TODO: Liste des Views mit den Netzwerken füllen, so dass Namen angezeigt und die Indizes hinterlegt sind.
        //wenn Network.selected, dann muss das Netzwerk in der Liste als ausgewählt angezeigt werden.



    }
}
