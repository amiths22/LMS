package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	protected Connection conn;
	public Connection getconnection() 
	{ 
		return conn; 
	}

	private static String url = "jdbc:mysql://192.168.1.19:3307/510labs?autoReconnect=true&useSSL=false";
	private static String user = "asatya";
	private static String pass = "asatya";

	public DBConnect() {

		try {
			conn = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
	}
}
