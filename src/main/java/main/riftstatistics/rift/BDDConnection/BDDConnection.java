package main.riftstatistics.rift.BDDConnection;

import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BDDConnection {
    private static BDDConnection instance;
    private static Connection connection;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/riftstatistics";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public BDDConnection () {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized BDDConnection getInstance() {
        if (instance == null) {
            instance = new BDDConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
