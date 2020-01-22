package app.servlets;

import app.model.User;
import app.utils.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class contains servlet methods witch are responsible for communication with login page
 *
 * @author andrz
 * @version 1.0
 * @since 20.01.2020
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/login.jsp");
        requestDispatcher.forward(req, resp);
    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        try
        {
            User user = new User();
            user.setEmail(request.getParameter("un"));
            user.setPassword(request.getParameter("pw"));
            user = UserDAO.login(user);

            if(user.isValid()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("currentSessionUser", user);


                RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/loggedView.jsp");
                requestDispatcher.forward(request, response);
            }else{
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/invalidLogin.jsp");
                requestDispatcher.forward(request, response);
            }

        } catch (Throwable theException) {
            System.out.println(theException);
        }
    }



}
