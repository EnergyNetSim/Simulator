package hftl.simulator.models;

/**
 * Created by nickcariss on 07.06.16.
 */
public class Network {

    private int id;
    private String name;
    private boolean selected;

    public String Name () { //Darf er das?
     return name;

    }
    public void Name (String name) { //Wär cool, Man!
        this.name = name;
    }

    public int Id () { //Darf er das?
        return id;

    }
    public void Id (int id) { //Wär cool, Man!
        this.id = id;
    }

    public boolean Selected () { //Darf er das?
        return selected;

    }
    public void Selected (boolean selected) { //Wär cool, Man!
        this.selected = selected;
    }


}
