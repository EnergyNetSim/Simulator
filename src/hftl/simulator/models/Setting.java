package hftl.simulator.models;

/**
 * Created by nickcariss on 07.06.16.
 */
public class Setting {

    String key;
    String value;

    public Setting(String key, String value)
    {
        this.key = key;
        this.value = value;
    }

    public String getKey()
    {
        return this.key;
    }

    public String getValue()
    {
        return this.value;
    }

    public void setValue(String value)
    {
        this.value = value;
        System.out.println("Wert '" + value + "' zu Key '" + key + "' gespeichert.");
    }

    public String toString()
    {
        return key + ": " + value;
    }

}