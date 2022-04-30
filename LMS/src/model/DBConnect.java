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

	private static String url = "jdbc:mysql://papademas.net:3307/510fp?autoReconnect=true&useSSL=false";
	private static String user = "fp510";
	private static String pass = "510";

	public DBConnect() {

		try {
			conn = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
	}
}
