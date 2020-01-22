package app.servlets;

import app.model.Address;
import app.model.Item;
import app.model.Order;
import app.model.Package;
import app.model.User;
import app.utils.AddressDAO;
import app.utils.ItemDAO;
import app.utils.OrdersDAO;
import app.utils.PackageDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Class contains servlet methods witch are responsible for communication with myOrders page
 *
 * @author andrz
 * @version 1.0
 * @since 20.01.2020
 */
public class MyOrdersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(true);
        User user = (User) session.getAttribute("currentSessionUser");

        List<Order> orderList = OrdersDAO.searchByUserId(user.getId());
        Address address = AddressDAO.searchById(user.getAddressId());

        HashMap<Integer, List<Item>> orderedItems = new HashMap<>();
        for (Order o: orderList) {
            List<Item> itemList = ItemDAO.searchById(o.getId());
            orderedItems.put(o.getId(), itemList);
        }

        req.setAttribute("itemList", orderedItems);
        req.setAttribute("orderList", orderList);
        req.setAttribute("address", address);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/myOrders.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String parameter = req.getParameter("submit");
        String idStr = parameter.substring(1);
        int id = Integer.parseInt(idStr);
        String op = String.valueOf(parameter.charAt(0));
        System.out.println(parameter);
        System.out.println(id);

        if(op.equals("s")){
            Package orderPackage = PackageDAO.searchById(id);
            HttpSession session = req.getSession(true);
            User user = (User) session.getAttribute("currentSessionUser");
            Address address = AddressDAO.searchById(user.getAddressId());
            Order order = OrdersDAO.searchById(id);

            req.setAttribute("orderedPackage", orderPackage);
            req.setAttribute("address", address);
            req.setAttribute("order", order);

            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/views/status.jsp");
            requestDispatcher.forward(req, resp);
        } else {

            Order order = OrdersDAO.searchById(id);
            if(!order.getStatus().equals("Dostarczone") & !order.getStatus().equals("Wyslane")){
                OrdersDAO.deleteOrderById(id);
            }

            doGet(req, resp);
        }


    }

}
