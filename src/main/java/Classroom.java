import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Classroom{
    private static String course_name;
    int height_of_room;
    int length_of_room;
    int width_of_room;
    int num_of_students;
    //String course_name;

    public Classroom(int h, int l, int w, int num, String c){
        this.height_of_room = h;
        this.length_of_room = l;
        this.width_of_room = w;
        this.num_of_students = num;
        this.course_name = c;
    }

    public int getHeight_of_room(){
        return height_of_room;
    }

    public int getWidth_of_room(){
        return width_of_room;
    }

    public int getLength_of_room(){
        return length_of_room;
    }

    public int getNum_of_students(){
        return num_of_students;
    }

    public String getCourse_name(){
        return course_name;
    }

    public static String getClass_name(String coursename){
        return "Class of " + coursename;
    }

    public static int area_of_room(int l, int w){
        int a = l*w;
        return a;
    }

    public static int volume_of_room(int h, int l, int w){
        int v = h*l*w;
        return v;
    }

    public static double student_per_m_square(int l, int w, int num){
        double x = num / (l*w);
        return x;
    }

    public static double diagonal_of_room(int w, int l, int h){
        double d = java.lang.Math.sqrt(java.lang.Math.pow(l, 2)+java.lang.Math.pow(w, 2)+java.lang.Math.pow(h, 2));
        return d;
    }

    public static MessageDigest hash_it() throws NoSuchAlgorithmException {
        java.security.MessageDigest md1 = java.security.MessageDigest.getInstance("SHA");
        return md1;
    }

    public static void main(String[] args) throws NoSuchMethodException, NoSuchAlgorithmException {
        Classroom classroom = new Classroom(21, 12, 16, 192, "Psychology");
        System.out.println("Area of the room: " + area_of_room(classroom.getLength_of_room(), classroom.getWidth_of_room()));
        System.out.println("Volume of the room: " + volume_of_room(classroom.getHeight_of_room(), classroom.getLength_of_room(), classroom.getWidth_of_room()));
        System.out.println("Number of students per meter square: " + student_per_m_square(classroom.getLength_of_room(), classroom.getWidth_of_room(), classroom.getNum_of_students()));
        System.out.println("Diagonal of the room: " + diagonal_of_room(classroom.getWidth_of_room(), classroom.getLength_of_room(), classroom.getHeight_of_room()));
        System.out.println("Course name: " + getClass_name(classroom.getCourse_name()));
        System.out.println(hash_it());

    }

}