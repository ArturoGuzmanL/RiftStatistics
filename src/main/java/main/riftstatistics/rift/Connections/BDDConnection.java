package main.riftstatistics.rift.Connections;

import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;

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

    public String getRiotAPIKey() {
        try (Connection conn = connection) {
            String query = "SELECT API_Key FROM api WHERE ID = ?";

            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, 2);

                try (ResultSet rs = pstmt.executeQuery()) {
                    rs.next();
                    String apiKey = rs.getString("API_Key");
                    return apiKey;
                }
            }
        } catch (SQLException e) {
            // Manejar la excepción
            e.printStackTrace();
        }
        return null;
    }
}
