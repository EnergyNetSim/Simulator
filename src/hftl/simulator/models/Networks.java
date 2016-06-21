package hftl.simulator.models;

import javax.swing.DefaultListModel;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A list of all the networks.
 *
 */
public class Networks extends DefaultListModel
{
    DatabaseQueries dbQuery;

    /**
     * Constructor sets DatabaseQuery object.
     *
     * @param   dbQuery     Used to execute SQL statements.
     */
    public Networks(DatabaseQueries dbQuery)
    {
        this.dbQuery = dbQuery;
    }

    /**
     * Queries DB for the networks, creates Network objects and adds to list.
     *
     */
    void load()
    {
        ResultSet rsNetworks = dbQuery.selectNetworks();

        if (rsNetworks!= null)
        {
            try
            {
                while (rsNetworks.next())
                {
                    Network network = new Network(
                            rsNetworks.getString("networkName"),
                            rsNetworks.getInt("networkId"),
                            false);
                    this.addElement(network);
                }
            }
            catch (SQLException e)
            {
                System.out.println(e);
            }
        }
    }

    /**
     * Searches an network object in the ListModel.
     *
     * @param   index   Index of element in the ListModel.
     * @return  Network Network object with the corresponding index.
     */
    @Override
    public Network getElementAt(int index)
    {
        Network network;
        network = (Network) super.getElementAt(index);
        return network;
    }

    /**
     * Returns an array of indices from all selected objects.
     *
     * @return  int[]   An int-array of indices.
     */
    public int[] getSelectedIndices()
    {
        int[] arrSelection;
        int arraySize = 0;
        int j;

        for (int i=0; i<this.size();i++)
        {
            if (this.getElementAt(i).getSelected())
            {
                arraySize++;
            }
        }

        arrSelection = new int[arraySize];

        j = 0;
        for (int i=0; i<this.size();i++)
        {
            if (this.getElementAt(i).getSelected())
            {
                arrSelection[j] = i;
                j++;
            }
        }

        return arrSelection;
    }

    /**
     * Saves the current selection state in each network object.
     *
     * @param   indices Array of selected indices.
     */
    void setSelectedIndices(int[] indices)
    {
        for(int i=0; i<this.size(); i++)
        {
            this.getElementAt(i).setSelected(false);
        }
        for(int i=0;i<indices.length;i++)
        {
            this.getElementAt(indices[i]).setSelected(true);
        }

    }

    /**
     * Identify the number of selected networks.
     *
     * @return  boolean True if at least one network is selected.
     */
    boolean hasSelectedNetworks()
    {
        for (int i = 0; i < this.size(); i++)
        {
            if (this.getElementAt(i).getSelected())
            {
                return true;
            }
        }
        return false;
    }
}