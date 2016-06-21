package hftl.simulator.models;

/**
 * This class holds information about a single network.
 *
 */
public class Network {

    private String name;
    private int id;
    private boolean selected;

    /**
     * Constructor.
     *
     * @param name      Name of this network.
     * @param id        Database ID for network.
     * @param selected  True if network selected by user in view.
     */
    public Network(String name, int id, boolean selected)
    {
        this.name = name;
        this.id = id;
        this.selected = selected;
    }

    /**
     * Getter for network name
     *
     * @return String Network name.
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Getter for network ID.
     *
     * @return  int     Network ID.
     */
    public int getId()
    {
        return this.id;
    }

    /**
     * Sets selected to true if network is selected in the view's network list.
     *
     * @param   selected    True if selected.
     */
    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }

    /**
     * Getter for selection indicator.
     *
     * @return boolean True if selected.
     */
    public boolean getSelected()
    {
        return selected;
    }

    /**
     * Overrides default toString method.
     *
     * @return  String  This network's name.
     */
    @Override
    public String toString()
    {
        return name;
    }



}
