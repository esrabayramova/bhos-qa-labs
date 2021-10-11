import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PermissionManagerTest {
    @Test
    public void test_setter(){
        PermissionManager.set_permission_level(PermissionManager.PermissionLevel.ADMIN);
        assertEquals("Admin", PermissionManager.get_permission_level());
    }

    @Test
    public void test_getter(){
        assertEquals("User", PermissionManager.get_permission_level());
    }
}