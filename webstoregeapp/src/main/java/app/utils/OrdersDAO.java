package app.utils;

import app.jdbc.MySQLConnection;
import app.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Class contains methods which allows to interact with Orders data
 *
 * @author andrz
 * @version 1.0
 * @since 10.01.2020
 */
public class OrdersDAO {

    static Connection currentCon = null;
    static ResultSet rs = null;

    /**
     * This method allows to search for orders of current user at database
     *
     * @param id of user which orders you are looking for
     * @return List<Order> with found orders
     */
    public static List<Order> searchByUserId(int id) {

        Statement stmt = null;
        List<Order> orderList = new ArrayList<>();

        String searchQuery =
                "SELECT * FROM zamowienie WHERE Klient_id = '" + id + "'";

        System.out.println("Query: " + searchQuery);

        try
        {
            currentCon = MySQLConnection.getMySQLConnection();
            stmt=currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);

            while(rs.next()){
                int orderId = rs.getInt("zamowienie.zamowienie_id");
                int clientId = rs.getInt("zamowienie.Klient_id");
                Date dateOfOrder = rs.getDate("zamowienie.data_zamowienia");
                String status = rs.getString("zamowienie.status");
                Order order = new Order();
                order.setId(orderId);
                order.setClientId(clientId);
                order.setDateOfOrder(dateOfOrder);
                order.setStatus(status);
                orderList.add(order);
            }
        }
        catch (Exception ex)
        {
            System.out.println("An Exception has occurred! " + ex);
        }

        finally
        {
            if (rs != null)	{
                try {
                    rs.close();
                } catch (Exception e) {}
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {}
                stmt = null;
            }

            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (Exception e) {
                }

                currentCon = null;
            }
        }

        return orderList;
    }

    /**
     * This method allows to find all orders
     *
     * @return List<Order> with found orders
     */
    public static List<Order> findAllOrders(){
        Statement stmt = null;
        List<Order> orderList = new ArrayList<>();

        String searchQuery =
                "SELECT * FROM zamowienie";

        System.out.println("Query: " + searchQuery);

        try {
            currentCon = MySQLConnection.getMySQLConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);

            while(rs.next()) {
                int orderId = rs.getInt("zamowienie.zamowienie_id");
                Date dateOfOrder = rs.getDate("zamowienie.data_zamowienia");
                int clientId = rs.getInt("zamowienie.Klient_id");
                String status = rs.getString("zamowienie.status");
                Order order = new Order();
                order.setId(orderId);
                order.setDateOfOrder(dateOfOrder);
                order.setClientId(clientId);
                order.setStatus(status);
                orderList.add(order);
            }

        }catch (Exception ex){
            System.out.println(ex);
        }

        finally
        {
            if (rs != null)	{
                try {
                    rs.close();
                } catch (Exception e) {}
                rs = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {}
                stmt = null;
            }
            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (Exception e) {
                }
                currentCon = null;
            }
        }

        return orderList;
    }

    /**
     * This method allows to search for order at database by its orderId
     *
     * @param id of order you are looking for
     * @return Order if such order exist at database
     */
    public static Order searchById(int id) {

        Statement stmt = null;
        Order order = new Order();

        String searchQuery =
                "SELECT * FROM zamowienie WHERE Zamowienie_id = '" + id + "'";

        System.out.println("Query: " + searchQuery);

        try
        {
            currentCon = MySQLConnection.getMySQLConnection();
            stmt=currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);

            if(rs.next()){
                int orderId = rs.getInt("zamowienie.zamowienie_id");
                int clientId = rs.getInt("zamowienie.Klient_id");
                Date dateOfOrder = rs.getDate("zamowienie.data_zamowienia");
                String status = rs.getString("zamowienie.status");

                order.setStatus(status);
                order.setId(orderId);
                order.setClientId(clientId);
                order.setDateOfOrder(dateOfOrder);
            }
        }
        catch (Exception ex)
        {
            System.out.println("An Exception has occurred! " + ex);
        }

        finally
        {
            if (rs != null)	{
                try {
                    rs.close();
                } catch (Exception e) {}
                rs = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {}
                stmt = null;
            }
            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (Exception e) {
                }
                currentCon = null;
            }
        }

        return order;
    }

    /**
     * This method allows to find and delete order by its orderId from database
     *
     * @param id of order
     */
    public static void deleteOrderById(int id){

        Statement stmt = null;
        PreparedStatement pstmt = null;
        String idStr = String.valueOf(id);

        String searchQuery =
                "DELETE FROM zamowienie WHERE Zamowienie_id = ? ";

        System.out.println("Query: " + searchQuery);

        try {
            currentCon = MySQLConnection.getMySQLConnection();
            pstmt = currentCon.prepareStatement(searchQuery);
            pstmt.setString(1, idStr);
            pstmt.executeUpdate();


        }catch (Exception ex){
            System.out.println(ex);
        }

        finally
        {
            if (rs != null)	{
                try {
                    rs.close();
                } catch (Exception e) {}
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {}
                stmt = null;
            }

            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (Exception e) {
                }

                currentCon = null;
            }
        }
    }

    /**
     * This method allows to find and update order's status by its id
     *
     * @param id of order
     */
    public static void updateOrderById(int id) {

        PreparedStatement pstmt = null;

        String searchQuery =
                "UPDATE zamowienie SET status='Obsluzone' WHERE zamowienie.zamowienie_id = ? ";

        System.out.println("Query: " + searchQuery);

        try {
            currentCon = MySQLConnection.getMySQLConnection();
            pstmt = currentCon.prepareStatement(searchQuery);
            pstmt.setString(1, String.valueOf(id));
            pstmt.executeUpdate();

        }catch (Exception ex){
            System.out.println(ex);
        }

    }


}
