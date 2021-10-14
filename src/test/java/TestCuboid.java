import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCuboid {
    @Test
    void test_volume(){
        assertEquals(224, Cuboid.volume(4, 7, 8));
    }

    @Test
    void test_surface_area(){
        assertEquals(232, Cuboid.surface_area(4, 7, 8));
    }

    Cuboid new_cuboid = new Cuboid(2, 3, 4);

    @Test
    void test_height() { assertEquals(2, new_cuboid.getHeight());}

    @Test
    void test_length() { assertEquals(3, new_cuboid.getLength());}

    @Test
    void test_width() { assertEquals(4, new_cuboid.getWidth());}
}