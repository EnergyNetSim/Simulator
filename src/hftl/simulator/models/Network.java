package hftl.simulator.models;

/**
 * Created by nickcariss on 07.06.16.
 */
public class Network {

    private String name;
    private int id;
    private boolean selected;

    public Network(String name, int id, boolean selected){
        this.name = name;
        this.id = id;
        this.selected = selected;
    }

    public String getName(){
        return this.name;
    }

    public int getId(){
        return this.id;
    }

    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }

    public boolean getSelected()
    {
        return selected;
    }



}
