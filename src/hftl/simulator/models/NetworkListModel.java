package hftl.simulator.models;

import javax.swing.*;

/**
 * Created by nickcariss on 08.06.16.
 */
import javax.swing.DefaultListModel;
import java.util.ArrayList;


public class NetworkListModel extends DefaultListModel {

    public String getElementAt(int index) {
        Network network = (Network) super.getElementAt(index);
        return network.getName();
    }

    public Network getObjectAt(int index)
    {
        Network network = (Network) super.getElementAt(index);
        return network;
    }

    public int[] getSelectedIndices()
    {
        ArrayList<Integer> arrSelection = new ArrayList<Integer>;

        for (int i=0; i<this.size();i++)
        {
            if (this.getObjectAt(i).getSelected())
            {
                arrSelection.add(i);
            }
        }

        return arrSelection.stream().mapToInt(i -> i).toArray();

    }

    public void loadNetworks () {
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

}