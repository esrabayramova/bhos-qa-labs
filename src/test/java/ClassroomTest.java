import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClassroomTest {
    @Test
    void test_volume(){
        assertEquals(4032, Classroom.volume_of_room(21, 12, 16));
    }

    @Test
    void test_area(){
        assertEquals(192, Classroom.area_of_room(12, 16));
    }

    @Test
    void test_diagonal() { assertEquals(29, Classroom.diagonal_of_room(16, 12, 21));}

    @Test
    void  test_student_per_m_squared() { assertEquals(1, Classroom.student_per_m_square(12, 16, 192));}

    @Test
    void test_course_name() { assertEquals("Class of Psychology", Classroom.getClass_name("Psychology"));}
}