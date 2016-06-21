package hftl.simulator.views;

import hftl.simulator.models.Setting;
import hftl.simulator.models.Settings;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by nickcariss on 07.06.16.
 */
public class SettingsView extends JDialog
{
    JPanel panButtons;
    JScrollPane scrPane;
    JList list;
    JButton btnOk;
    JLabel lblKey;
    JTextField txtValue;
    Setting activeSetting;

    public SettingsView(JFrame owner, Settings settings)
    {
        super(owner, "Einstellungen", true);
        initialize(settings);
    }

    private void initialize(Settings settings)
    {

        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(400,400);

        //Create list panel:
        list = new JList(settings);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent le) {
                int idx = list.getSelectedIndex();
                if (idx != -1) {
                    btnOk.setEnabled(true);
                    activeSetting = (Setting) list.getModel().getElementAt(idx);
                    System.out.println("Current selection: " + list.getModel().getElementAt(idx));
                    lblKey.setText(activeSetting.getKey());
                    txtValue.setText(activeSetting.getValue());
                }
                else
                {
                    refresh();
                }
            }
        });






        scrPane = new JScrollPane(list);
        scrPane.setBorder(new EmptyBorder(10,10,10,10));
        this.add(scrPane, BorderLayout.CENTER);

        //Create button panel
        panButtons = new JPanel(new FlowLayout());
        panButtons.setBorder(new EmptyBorder(10,10,10,10));

        this.add(panButtons, BorderLayout.SOUTH);

        //Create buttons:
        lblKey = new JLabel("Test");
        panButtons.add(lblKey);
        txtValue = new JTextField();
        txtValue.setPreferredSize(new Dimension(150,20));
        //txtValue.setSize(200,20);
        panButtons.add(txtValue);
        btnOk = createButton("Save");
        btnOk.setEnabled(false);
        panButtons.add(btnOk);

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
        btnOk.addActionListener(l);
    }

    public int getActiveIndex()
    {
        return list.getSelectedIndex();
    }

    public String getCurrentValue()
    {
        return txtValue.getText();
    }

    public void refresh()
    {
        txtValue.setText("");
        lblKey.setText("Einstellung");
        list.clearSelection();
        btnOk.setEnabled(false);
        this.repaint();

    }
}
