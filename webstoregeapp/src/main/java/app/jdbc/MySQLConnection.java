package app.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class contains methods responsible for connection with MySQL database
 *
 * @author andrz
 * @version 1.0
 * @since 20.01.2020
 */
public class MySQLConnection {

    public static Connection getMySQLConnection()
            throws ClassNotFoundException, SQLException {

        String hostName = "127.0.0.1";
        String dbName = "storagedb";
        String userName = "root";
        String password = "topkeklol322lol";
        return getMySQLConnection(hostName, dbName, userName, password);
    }

    public static Connection getMySQLConnection(String hostName, String dbName,
                                                String userName, String password) throws SQLException,
            ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");

        // URL Connection for MySQL:
        // Example:
        // jdbc:mysql://localhost:3306/test
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        Connection conn = DriverManager.getConnection(connectionURL, userName,
                password);
        return conn;
    }

}
