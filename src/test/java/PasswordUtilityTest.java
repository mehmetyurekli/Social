import com.mehmetyurekli.Util.PasswordUtility;
import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordUtilityTest {

    @Test
    public void testVerifyPassword() {
        char[] password = "abcdefgh".toCharArray();
        String hashed = PasswordUtility.hashPassword(password);
        assertTrue(PasswordUtility.verifyPassword(password, hashed));
    }

    @Test
    public void hashesNotEqual(){
        char[] password = "abcdefgh".toCharArray();
        char[] password2 = "12345678".toCharArray();
        String hashed1 = PasswordUtility.hashPassword(password);
        String hashed2 = PasswordUtility.hashPassword(password2);
        assertNotEquals(hashed1, hashed2);
    }

}
