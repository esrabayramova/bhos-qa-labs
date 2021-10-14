public class PermissionManager {

    public static enum PermissionLevel{
        ADMIN,
        DEVELOPER,
        USER
    }
    private static PermissionLevel mCurrentLevel = PermissionLevel.USER;

    public static PermissionLevel get_current_permission_level(){   //additional
        return mCurrentLevel;
    }

    public static void set_permission_level(PermissionLevel perml){
        mCurrentLevel = perml;
    }

    public static String get_permission_level(){
        String current_permission_level = " ";

        switch (mCurrentLevel){
            case USER: current_permission_level = "User"; break;
            case DEVELOPER: current_permission_level = "Developer"; break;
            case ADMIN: current_permission_level = "Admin"; break;
        }
        return current_permission_level;
    }

//    public static void get_cases(){
//        for (PermissionLevel  pm : PermissionLevel.values()) {
//            System.out.println(pm);
//        }
//    }

    public static void main(String[] args){
        //System.out.println(get_current_permission_level());
        System.out.println(get_permission_level());
        set_permission_level(PermissionLevel.DEVELOPER);
        //System.out.println(get_current_permission_level());
        System.out.println(get_permission_level());
        //get_cases();
    }
}
