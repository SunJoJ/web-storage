package app.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class contains servlet methods witch are responsible for communication with panel page
 *
 * @author andrz
 * @version 1.0
 * @since 20.01.2020
 */
public class PanelServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/panel.jsp");
        requestDispatcher.forward(req, resp);
    }



}
