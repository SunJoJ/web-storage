package app.utils;

import app.jdbc.MySQLConnection;
import app.model.Order;
import app.model.Package;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class contains methods which allows to interact with Package data
 *
 * @author andrz
 * @version 1.0
 * @since 10.01.2020
 */
public class PackageDAO {

    static Connection currentCon = null;
    static ResultSet rs = null;

    /**
     * This method allows to find Package by its id at database
     *
     * @param id of package
     * @return Package
     */
    public static Package searchById(int id) {

        Statement stmt = null;
        Package orderedPackage = new Package();

        String searchQuery =
                "SELECT * FROM przesylka, zamowienie WHERE zamowienie.zamowienie_id = '" + id + "'" +
                        " AND zamowienie.zamowienie_id=przesylka.zamowienie_id";

        System.out.println("Query: " + searchQuery);

        try
        {
            currentCon = MySQLConnection.getMySQLConnection();
            stmt=currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);
            boolean exist = rs.next();

            if(exist){
                orderedPackage.setPackageId(rs.getInt("przesylka.przesylka_id"));
                orderedPackage.setOrderId(rs.getInt("przesylka.zamowienie_id"));
                orderedPackage.setCost(rs.getFloat("przesylka.wartosc"));
                orderedPackage.setPayed(rs.getBoolean("przesylka.oplata"));
                orderedPackage.setDelivered(rs.getBoolean("przesylka.czy_dostarczona"));
                orderedPackage.setDateOfProcess(rs.getDate("przesylka.data_przygotowania"));
                orderedPackage.setDateOdDelivery(rs.getDate("przesylka.data_dostarczenia"));
                orderedPackage.setDateOfShipment(rs.getDate("przesylka.data_wyslania"));
            }

        }catch (Exception ex){
            System.out.println("An Exception has occurred! " + ex);
        }

        return orderedPackage;
    }


}
