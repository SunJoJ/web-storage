package app.utils;

import app.jdbc.MySQLConnection;
import app.model.Address;
import app.model.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class contains methods which allows to interact with Address data
 *
 * @author andrz
 * @version 1.0
 * @since 10.01.2020
 */
public class AddressDAO {

    static Connection currentCon = null;
    static ResultSet rs = null;

    /**
     * Method allows to search for Address at database by given id
     *
     * @param id of address you are looking for
     * @return Address
     */
    public static Address searchById(int id) {
        Statement stmt = null;
        Address address = new Address();

        String searchQuery =
                "SELECT * FROM adres WHERE adres_id = '" + id + "'";

        System.out.println("Query: " + searchQuery);

        try
        {
            currentCon = MySQLConnection.getMySQLConnection();
            stmt=currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);
            boolean exist = rs.next();

            if(exist) {
                String country = rs.getString("panstwo");
                String city = rs.getString("miasto");
                String street = rs.getString("ulica");
                String postCode = rs.getString("kod_pocztowy");
                address.setId(id);
                address.setCountry(country);
                address.setCity(city);
                address.setStreet(street);
                address.setPostCode(postCode);
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

        return address;
    }


}
