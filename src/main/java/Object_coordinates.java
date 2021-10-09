import java.lang.Math;

public class Object_coordinates
{
    int x;
    int y;
    String obj;

    public Object_coordinates(int x, int y,
                              String obj)
    {
        this.x = x;
        this.y = y;
        this.obj = obj;
    }

    public String getObj()
    {
        return obj;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public static double dist_from_0(int x, int y)
    {
        double d = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        //System.out.println("Distance of " +obj+ " from the origin: " + d);
        return d;
    }

    public static void main(String[] args)
    {
        Object_coordinates car = new Object_coordinates(6,8, "car");
        Object_coordinates tree = new Object_coordinates(5,12, "tree");
        //dist_from_0(car.getX(), car.getY(), car.getObj());
        System.out.println("The distance from the " + car.getObj() + " to the origin is " + dist_from_0(car.getX(), car.getY()));
        System.out.println("The distance from the " + tree.getObj() + " to the origin is " + dist_from_0(tree.getX(), tree.getY()));
    }
}
