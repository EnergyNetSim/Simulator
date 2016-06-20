package hftl.simulator.models;


/**
 * Created by nickcariss on 08.06.16.
 */
import javax.swing.DefaultListModel;


public class NetworkListModel extends DefaultListModel {



    public void load()
    {
        //TODO Datenbankabfrage
        //abgleich mit networks (hier ist auch hinterlegt, was schon selected ist)
        //--> aus der DB aktualisierte Liste und Beibehaltung der Auswahl des Users
        //Rückgabe als Array von Networks



        Network n1 = new Network("Netz 1", 1, false);
        Network n2 = new Network("Netz 3", 22, true);
        Network n3 = new Network("Netz 24",2, true);

        //Wir fügen unserem ListModel Network-Objekte hinzu
        this.addElement(n1);
        this.addElement(n2);
        this.addElement(n3);
    }

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