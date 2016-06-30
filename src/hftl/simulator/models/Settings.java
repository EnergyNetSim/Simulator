package hftl.simulator.models;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * A list of all the settings.
 *
 */
public class Settings extends DefaultListModel{

    DatabaseQueries dbQuery;

    /**
     * Constructor sets DatabaseQuery object.
     *
     * @param   dbQuery     Used to execute SQL statements.
     */
    public Settings(DatabaseQueries dbQuery)
    {
        this.dbQuery = dbQuery;
    }

    /**
     * Queries DB for the settings, creates Setting objects and adds to list.
     *
     */
    void load()
    {
        ResultSet rsSettings = dbQuery.selectSettings();

        if (rsSettings!= null)
        {
            try
            {
                while (rsSettings.next())
                {
                    Setting setting = new Setting(
                            rsSettings.getString("settingKey"),
                            rsSettings.getString("settingValue"));
                    this.addElement(setting);
                }
            }
            catch (SQLException e)
            {
                System.out.println(e);
            }
        }
    }

    /**
     * Searches an settings object in the ListModel.
     *
     * @param   index   Index of element in the ListModel.
     * @return  Setting Setting object with the corresponding index.
     */
    @Override
    public Setting getElementAt(int index) {
        Setting setting = (Setting) super.getElementAt(index);
        return setting;
    }

    /**
     * Retrieve Setting object for given key.
     *
     * @param   key   Key of element in the ListModel.
     * @return  Setting Setting object with the corresponding key.
     */
    public Setting getElement(String key)
    {
        Setting setting;

        for (int i = 0; i < this.size(); i++)
        {
            setting = getElementAt(i);
            if (Objects.equals(setting.getKey(), key))
            {
                return setting;
            }
        }
        return null;
    }

    /**
     * Saves an updated value in the database.
     *
     * @param   index   Index of Setting object.
     * @param   value   Updated value.
     */
    void save(int index, String value)
    {
        if (dbQuery.updateSetting(getElementAt(index).getKey(), value))
        {
            getElementAt(index).setValue(value);
        }
    }
}