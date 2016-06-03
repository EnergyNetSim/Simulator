package hftl.simulator.models.helper;

/**
 * Created by Christian on 31.05.2016.
 */
public class DataPoint
{
    private double x;
    private double y;

    public DataPoint(double x, double y)
    {
        this.x = x;
        this.y = y;
    }


    public double X() {
        return x;
    }

    public double Y() {
        return y;
    }
}
