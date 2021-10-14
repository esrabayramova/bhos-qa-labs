public class Cuboid
{
    int height;
    int length;
    int width;

    public Cuboid(int h, int l, int w)
    {
        this.height = h;
        this.length = l;
        this.width = w;
    }


    public int getHeight()
    {
        return height;
    }

    public int getLength()
    {
        return length;
    }

    public int getWidth()
    {
        return width;
    }

    public static int volume(int h, int l, int w)
    {
        int v = h * l * w;
        //System.out.println("Volume of the cuboid " + v);
        return v;
    }

    public static int surface_area(int h, int l, int w)
    {
        int a = 2*(h*l + l*w + w*h);
        //System.out.println("Surface area of the cuboid " + a);
        return a;
    }

    public static void main(String[] args)
    {
        Cuboid cuboid_1 = new Cuboid(3, 5, 6);
        System.out.println("Volume of the given cuboid: " + volume(cuboid_1.getHeight(), cuboid_1.getLength(), cuboid_1.getWidth()));
        System.out.println("Surface area of the given cuboid: " + surface_area(cuboid_1.getHeight(), cuboid_1.getLength(), cuboid_1.getWidth()));
    }
}