package app.utils;

import app.jdbc.MySQLConnection;
import app.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Class contains methods which allows to interact with User data
 *
 * @author andrz
 * @version 1.0
 * @since 10.01.2020
 */
public class UserDAO {

    static Connection currentCon = null;
    static ResultSet rs = null;

    /**
     * This method allows to valid if given user exist at database and log him in
     *
     * @param user with setted email and password
     * @return valid user if such exist at database
     */
    public static User login(User user) {

        Statement stmt = null;

        String email = user.getEmail();
        String password = user.getPassword();

        String searchQuery =
                "select * from klient where email='"
                        + email
                        + "' AND haslo='"
                        + password
                        + "'";

        System.out.println("Your user name is " + email);
        System.out.println("Your password is " + password);
        System.out.println("Query: " + searchQuery);

        try
        {
            currentCon = MySQLConnection.getMySQLConnection();
            stmt=currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);
            boolean exist = rs.next();

            if (!exist)
            {
                System.out.println("Sorry, you are not a registered user! Please sign up first");
                user.setValid(false);
            }

            else if (exist)
            {
                user.setName(rs.getString("imie"));
                System.out.println("Welcome " + user.getName());
                user.setId(rs.getInt("klient_id"));
                user.setAddressId(rs.getInt("adres_id"));
                System.out.println(user.getId());
                user.setValid(true);
            }
        }
        catch (Exception ex)
        {
            System.out.println("Log In failed: An Exception has occurred! " + ex);
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

        return user;
    }

    /**
     * This method allows to find user by id at database
     *
     * @param id of user you are looking for
     * @return User
     */
    public static User searchUserById(int id){

        Statement stmt = null;

        User user = new User();

        String searchQuery =
                "select * from klient where klient_id='" + id + "'";


        System.out.println("Query: " + searchQuery);

        try {
            currentCon = MySQLConnection.getMySQLConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);
            boolean exist = rs.next();

            if(exist){
                user.setName(rs.getString("imie"));
                user.setSurname(rs.getString("nazwisko"));
                user.setPesel(rs.getString("pesel"));
                user.setPhoneNumber(rs.getString("nr_telefonu"));
                user.setId(rs.getInt("klient_id"));
                user.setAddressId(rs.getInt("adres_id"));
                user.setValid(true);
            }

        }catch (Exception ex){
            System.out.println(ex);
        }

        return user;
    }



}
