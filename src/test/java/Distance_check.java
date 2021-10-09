import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Distance_check {
    @Test
    void test_calculation(){
        assertEquals(29, Object_coordinates.dist_from_0(20, 21));
    }
}