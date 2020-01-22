package app.servlets;

import app.model.Package;
import app.utils.PackageDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class contains servlet methods witch are responsible for communication with status page
 *
 * @author andrz
 * @version 1.0
 * @since 20.01.2020
 */
public class StatusServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/status.jsp");
        requestDispatcher.forward(req, resp);
    }



}
