package hftl.simulator.models;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nickcariss on 20.06.16.
 */
public class Settings extends DefaultListModel{

    DatabaseQueries dbQuery;

    public Settings(DatabaseQueries dbQuery)
    {
        this.dbQuery = dbQuery;
    }

    public void load()
    {
        ResultSet rsSettings = dbQuery.selectSettings();

        if (rsSettings!= null)
        {
            try {
                while (rsSettings.next()) {
                    Setting setting = new Setting(
                            rsSettings.getString("settingKey"),
                            rsSettings.getString("settingValue"));
                    this.addElement(setting);
                }
            } catch (SQLException e)
            {
                System.out.println(e);
            }
        }
    }

    public Object getElementAt(int index) {
        Setting setting = (Setting) super.getElementAt(index);
        return setting;
    }

    public void save(int index, String value)
    {
        if (dbQuery.updateSetting(((Setting) getElementAt(index)).getKey(), value))
        {
            ((Setting) getElementAt(index)).setValue(value);
        }
    }
}