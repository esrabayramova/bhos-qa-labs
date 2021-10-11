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
}