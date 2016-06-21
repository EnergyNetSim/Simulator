package hftl.simulator.models;

/**
 * This class holds information about a single setting.
 *
 */
public class Setting {

    String key;
    String value;

    /**
     * Constructor.
     *
     * @param key      Description of the setting.
     * @param value    Value of the setting.
     */
    public Setting(String key, String value)
    {
        this.key = key;
        this.value = value;
    }

    /**
     * Get settings key.
     *
     * @return  String  Settings key.
     */
    public String getKey()
    {
        return this.key;
    }

    /**
     * Get settings value.
     *
     * @return  String  Settings value.
     */
    public String getValue()
    {
        return this.value;
    }

    /**
     * Set settings value.
     *
     * @param   value   Settings value.
     */
    public void setValue(String value)
    {
        this.value = value;
    }

    /**
     * Overrides system method toString().
     *
     * @return  String  Key and value.
     */
    @Override
    public String toString()
    {
        return key + ": " + value;
    }
}