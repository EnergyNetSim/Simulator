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
 * Settings view. Is opened from MainController when Button "Settings" is clicked.
 *
 */
public class SettingsView extends JDialog
{
    private JList list;
    private JButton btnOk;
    private JLabel lblKey;
    private JTextField txtValue;
    private Setting activeSetting;

    /**
     * Constructor sets JDialog's owner and gets the settings list.
     *
     * @param   owner       JFrame (MainView)
     * @param   settings    Settings list
     */
    public SettingsView(JFrame owner, Settings settings)
    {
        super(owner, "Settings", true);
        initialize(settings);
    }

    /**
     * Creates all Java-Swing-components and adds them to the JDialog.
     * Adds the setting objects to the list. Creates an action listener for list selection events.
     *
     * @param settings  Settings list
     */
    private void initialize(Settings settings)
    {

        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(400,300);

        //Create list panel:
        list = new JList(settings);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        list.addListSelectionListener(new ListSelectionListener()
        {
            public void valueChanged(ListSelectionEvent le)
            {
                int idx = list.getSelectedIndex();
                if (idx != -1)
                {
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

        JScrollPane scrPane = new JScrollPane(list);
        scrPane.setBorder(new EmptyBorder(10,10,10,10));
        this.add(scrPane, BorderLayout.CENTER);

        //Create button panel
        JPanel panButtons = new JPanel(new FlowLayout());
        panButtons.setBorder(new EmptyBorder(10,10,10,10));
        this.add(panButtons, BorderLayout.SOUTH);

        //Create buttons and labels:
        lblKey = new JLabel("Test");
        panButtons.add(lblKey);
        txtValue = new JTextField();
        txtValue.setPreferredSize(new Dimension(100,20));
        panButtons.add(txtValue);
        btnOk = createButton("Save");
        btnOk.setEnabled(false);
        panButtons.add(btnOk);

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
     * Adds an ActionListener object to all buttons.
     *
     * @param   listener   ActionListener in SettingsController.
     */
    public void setListener(ActionListener listener)
    {
        btnOk.setActionCommand("btnOk");
        btnOk.addActionListener(listener);
    }

    /**
     * Returns index of selected setting.
     *
     * @return  int     Selected index.
     */
    public int getActiveIndex()
    {
        return list.getSelectedIndex();
    }

    /**
     * Returns text in textfield.
     *
     * @return  String  Text from textfield.
     */
    public String getCurrentValue()
    {
        return txtValue.getText();
    }

    /**
     * Updates the view, clears selection and textfield.
     *
     */
    public void refresh()
    {
        txtValue.setText("");
        lblKey.setText("Choose a setting");
        list.clearSelection();
        btnOk.setEnabled(false);
        this.repaint();
    }
}
