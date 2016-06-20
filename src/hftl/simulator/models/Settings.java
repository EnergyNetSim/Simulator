package hftl.simulator.models;

import javax.swing.*;

/**
 * Created by nickcariss on 20.06.16.
 */
public class Settings extends DefaultListModel{

    public void load()
    {
        //TODO Datenbankabfrage
        //--> aus der DB aktualisierte Liste

        Setting s1 = new Setting("Annahme", "Wert");
        Setting s2 = new Setting("Annahme2", "Wert2");
        Setting s3 = new Setting("Annahme3", "Wert3");

        //Wir fügen unserem ListModel Network-Objekte hinzu
        this.addElement(s1);
        this.addElement(s2);
        this.addElement(s3);
    }

    public Object getElementAt(int index) {
        Setting setting = (Setting) super.getElementAt(index);
        return setting;
    }

}
