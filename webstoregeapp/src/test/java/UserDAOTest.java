import app.model.User;
import app.utils.UserDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class UserDAOTest extends Mockito {

    private User user1;
    private User user2;

    @Before
    public void setUp() {
        user1 = new User();
        user1.setEmail("abc");
        user1.setPassword("123");
        user2 = new User();
        user2.setEmail("abc");
        user2.setPassword("1234");
    }

    @Test
    public void isUserValid() {
        user1 = UserDAO.login(user1);
        user2 = UserDAO.login(user2);
        assertTrue(user1.isValid());
        assertFalse(user2.isValid());
    }

}
