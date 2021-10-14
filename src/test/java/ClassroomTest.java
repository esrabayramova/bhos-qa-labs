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
    void test_class_name() { assertEquals("Class of Psychology", Classroom.getClass_name("Psychology"));}

//    Classroom new_class = new Classroom(21, 12, 16, 192, "Science");
//
//    @Test
//    void test_height() { assertEquals(21, new_class.getHeight_of_room());}
//
//    @Test
//    void test_length() { assertEquals(12, new_class.getLength_of_room());}
//
//    @Test
//    void test_width() { assertEquals(16, new_class.getWidth_of_room());}
//
//    @Test
//    void test_student_num() { assertEquals(192, new_class.getNum_of_students());}
//
//    @Test
//    void test_course_name() { assertEquals("Science", new_class.getCourse_name());}
}