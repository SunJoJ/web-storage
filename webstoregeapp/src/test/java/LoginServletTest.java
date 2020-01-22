import app.model.User;
import app.servlets.LoginServlet;
import app.utils.UserDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class LoginServletTest extends Mockito {

    private LoginServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private User user;
    private RequestDispatcher requestDispatcher;

    @Before
    public void setUp() {
        servlet = new LoginServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        requestDispatcher = mock(RequestDispatcher.class);
        user = new User();
    }

    @Test
    public void correctUserInRequest() throws ServletException, IOException {

        user.setEmail("abc");
        user.setPassword("123");
        when(request.getSession()).thenReturn(session);
        user = UserDAO.login(user);
        assertEquals(user.getName(), "Adam");
        when(session.getAttribute("currentSessionUser")).thenReturn(user);
        when(request.getRequestDispatcher("views/loggedView.jsp")).thenReturn(requestDispatcher);
        servlet.doPost(request, response);
    }

}
