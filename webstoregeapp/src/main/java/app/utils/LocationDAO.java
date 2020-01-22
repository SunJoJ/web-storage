package app.utils;

import app.jdbc.MySQLConnection;
import app.model.Item;
import app.model.Location;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;


/**
 * Class contains methods which allows to interact with Location data
 *
 * @author andrz
 * @version 1.0
 * @since 10.01.2020
 */
public class LocationDAO {

    static Connection currentCon = null;
    static ResultSet rs = null;

    /**
     * Method allows to search for location by its id
     *
     * @param id of location
     * @return Location
     */
    public static Location searchById(int id) {
        Statement stmt = null;
        Location location = new Location();

        String searchQuery =
                "SELECT * FROM polozenie WHERE polozenie.produkt_id ='" + id + "'";

        System.out.println("Query: " + searchQuery);

        try {
            currentCon = MySQLConnection.getMySQLConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);

            if(rs.next()){
                location.setLocationId(rs.getInt("polozenie_id"));
                location.setItemId(rs.getInt("produkt_id"));
                location.setLine(rs.getString("aleja"));
                location.setSector(rs.getString("sektor"));
                location.setShelf(rs.getString("polka"));
            }

        }catch (Exception ex){
            System.out.println(ex);
        }


        return location;
    }

}
