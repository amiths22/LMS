package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnect {
    // Code database URL
    static final String DB_URL =
            "jdbc:mysql://192.168.1.19:3307/510labs?autoReconnect=true&useSSL=false";
    // Database credentials
    static final String USER = "db510", PASS = "510";
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

}
