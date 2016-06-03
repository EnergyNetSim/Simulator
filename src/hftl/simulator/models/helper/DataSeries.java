package hftl.simulator.models.helper;

/**
 * Created by Christian on 31.05.2016.
 */
public class DataSeries
{
    private String title;
    private DataPoint[] values;
    private int valueLength;

    public DataSeries(String title, int size)
    {
        this.title = title;
        this.valueLength = size;
        values = new DataPoint[size];
    }

    public void insertValue(DataPoint value) throws Exception
    {
        for(int i=0;i<values.length;i++)
        {
            if(values[i] == null)
            {
                values[i] = value;
                return;
            }
        }
        Exception e = new Exception("Datenreihe bereits voll!");
        throw e;
    }

    public DataPoint getValue(int position)
    {
        return values[position];
    }

    public String Title()
    {
        return title;
    }

    public int NumValues()
    {
        return valueLength;
    }

    public void printValues()
    {
        System.out.print(Title() + ": ");
        for(int i=0; i<NumValues();i++)
        {
            System.out.print("{" + values[i].X() + "; " + values[i].Y() + "}; ");
        }
        System.out.println();
    }
}
