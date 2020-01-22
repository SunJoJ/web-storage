package app.servlets;

import app.model.*;
import app.utils.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class contains servlet methods witch are responsible for communication with orders page
 *
 * @author andrz
 * @version 1.0
 * @since 20.01.2020
 */
public class OrdersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Order> orderList = OrdersDAO.findAllOrders();

        List<User> userList = new ArrayList<>();
        List<Address> addresses = new ArrayList<>();
        for (Order o : orderList) {
            User user = UserDAO.searchUserById(o.getClientId());
            userList.add(user);
            Address address = AddressDAO.searchById(user.getAddressId());
            addresses.add(address);
        }

        HashMap<Integer, List<Item>> orderItems = new HashMap<>();
        for (Order o: orderList) {
            List<Item> itemList = ItemDAO.searchById(o.getId());
            orderItems.put(o.getId(), itemList);
        }

        req.setAttribute("userList", userList);
        req.setAttribute("orderList", orderList);
        req.setAttribute("addresses", addresses);
        req.setAttribute("orderItems", orderItems);


        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/orders.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String parameter = req.getParameter("submit");
        String idStr = parameter.substring(1);
        int orderId = Integer.parseInt(idStr);
        String op = String.valueOf(parameter.charAt(0));

        if(op.equals("m")){
            Order order = OrdersDAO.searchById(orderId);
            List<Item> itemList = ItemDAO.searchById(orderId);
            User user = UserDAO.searchUserById(order.getClientId());
            Address address = AddressDAO.searchById(user.getAddressId());

            List<Location> locationList = new ArrayList<>();
            for (Item item : itemList) {
                Location location = LocationDAO.searchById(item.getId());
                locationList.add(location);
            }

            req.setAttribute("order", order);
            req.setAttribute("itemList", itemList);
            req.setAttribute("user", user);
            req.setAttribute("address", address);
            req.setAttribute("locationList", locationList);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/processOrder.jsp");
            requestDispatcher.forward(req, resp);

        } else if(op.equals("g")){

            Order order = OrdersDAO.searchById(orderId);
            List<Item> itemList = ItemDAO.searchById(orderId);
            User user = UserDAO.searchUserById(order.getClientId());
            Address address = AddressDAO.searchById(user.getAddressId());

            ServletOutputStream os = resp.getOutputStream();
            resp.setContentType("application/pdf");
            Document doc = new Document();

            Font bfBold18 = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD, new BaseColor(0, 0, 0));
            Font bfBold12 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLDITALIC, new BaseColor(0, 0, 0));
            Font bf12 = new Font(Font.FontFamily.TIMES_ROMAN, 12);

            try {

                PdfWriter.getInstance(doc, os);

                doc.addAuthor("andrz");
                doc.addCreationDate();
                doc.addProducer();
                doc.addTitle("Raport");
                doc.setPageSize(PageSize.LETTER);
                doc.open();

                doc.add( new Paragraph(user.getName() + " " + user.getSurname(), bfBold18));
                doc.add(new Paragraph( "Tel: " + user.getPhoneNumber(), bfBold12));
                doc.add(new Paragraph("Adres: " + address.toString(), bfBold12));
                for(int i = 0; i < itemList.size(); i++){
                    doc.add(new Paragraph(itemList.get(i).getName(), bf12));
                }

                doc.close();

            } catch(DocumentException e) {
                e.printStackTrace();
            }
            catch(Exception e){
                e.printStackTrace();
            }

        }

    }



}
