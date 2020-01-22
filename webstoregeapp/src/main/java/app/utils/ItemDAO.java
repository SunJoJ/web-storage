package app.utils;

import app.jdbc.MySQLConnection;
import app.model.Item;
import app.model.Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Class contains methods which allows to interact with Item data
 *
 * @author andrz
 * @version 1.0
 * @since 10.01.2020
 */
public class ItemDAO {

    static Connection currentCon = null;
    static ResultSet rs = null;

    /**
     * Method allows to search for ordered items at database by given id of order
     *
     * @param id of order
     * @return List<Item>
     */
    public static List<Item> searchById(int id) {

        Statement stmt = null;

        List<Item> itemList = new ArrayList<>();

        String searchQuery =
                "SELECT * FROM produkt, produkt_zamowienie WHERE produkt.produkt_id = produkt_zamowienie.Produkt_id " +
                        "AND produkt_zamowienie.Zamowienie_id ='" + id + "'";

        System.out.println("Query: " + searchQuery);

        try
        {
            currentCon = MySQLConnection.getMySQLConnection();
            stmt=currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);

            while(rs.next()){
                int itemId = rs.getInt("produkt.produkt_id");
                String name = rs.getString("produkt.nazwa");
                float cost = rs.getInt("produkt.cena");
                int weight = rs.getInt("produkt.waga");

                Item item = new Item();
                item.setId(itemId);
                item.setName(name);
                item.setCost(cost);
                item.setWeight(weight);
                itemList.add(item);
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

        return itemList;
    }

}
