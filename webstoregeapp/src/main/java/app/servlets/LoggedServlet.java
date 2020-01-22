package app.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class contains servlet methods witch are responsible for communication with loggedView page
 *
 * @author andrz
 * @version 1.0
 * @since 20.01.2020
 */
public class LoggedServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/loggedView.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/myOrders.jsp");
        requestDispatcher.forward(request, response);
    }

}