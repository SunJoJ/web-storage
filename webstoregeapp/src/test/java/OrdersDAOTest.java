import app.model.Order;
import app.model.User;
import app.utils.OrdersDAO;
import app.utils.UserDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class OrdersDAOTest extends Mockito {

    private Order order;

    @Before
    public void setUp(){
        order = new Order();
    }

    @Test
    public void isOrderIdEqual(){
        order = OrdersDAO.searchById(3);
        assertEquals(order.getId(), 3);
    }

}
