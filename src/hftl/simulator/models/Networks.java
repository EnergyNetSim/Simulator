package hftl.simulator.models;


/**
 * Created by nickcariss on 08.06.16.
 */
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
    public void load()
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
     *
     * @param index
     * @return
     */
    public Object getElementAt(int index) {
        Network network = (Network) super.getElementAt(index);
        return network;
    }

    public Network getObjectAt(int index)
    {
        Network network = (Network) super.getElementAt(index);
        return network;
    }

    public int[] getSelectedIndices()
    {
        int[] arrSelection;
        int arraySize = 0;
        int j;

        for (int i=0; i<this.size();i++)
        {
            if (this.getObjectAt(i).getSelected())
            {
                arraySize++;
            }
        }

        arrSelection = new int[arraySize];
        j = 0;
        for (int i=0; i<this.size();i++)
        {
            if (this.getObjectAt(i).getSelected())
            {
                arrSelection[j] = i;
                j++;
            }
        }

        return arrSelection;

    }

    public void setSelectedIndices(int[] indices)
    {
        for(int i=0; i<this.size(); i++)
        {
            this.getObjectAt(i).setSelected(false);
        }
        for(int i=0;i<indices.length;i++)
        {
            this.getObjectAt(indices[i]).setSelected(true);
        }

    }

    public boolean hasSelectedNetworks()
    {
        for (int i = 0; i < this.size(); i++) {
            if (this.getObjectAt(i).getSelected()) return true;
        }
        return false;
    }
}