package model;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


/** Manages control flow for logins */
public class LoginManager extends DBConnect {

	private String username;
	private String password;
	public int role;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}




	/**
	 * Callback method invoked to notify that a user has been authenticated. Will
	 * show the main application screen.
	 */
	public boolean authenticate(String user,String pass) {
		
		/*if(user.equals("amith")&&pass.equals("1234")) {
			System.out.println(user+"   "+pass);
		showMainView();
		DBConnect conn=new DBConnect();
		try {
			System.out.println(conn.connect());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else
			showLoginScreen();*/
		String query = "SELECT * FROM users WHERE email = ? and Password = ?";
		try(PreparedStatement stmt = conn.prepareStatement(query)) 
		{
			
			stmt.setString(1, user);
			stmt.setString(2, pass);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{ 
				setUsername(rs.getString("email"));
				setPassword(rs.getString("password"));
				setRole(rs.getInt("role"));
				System.out.println("records fetced");
			
				return true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();   
		}
		return false;
		
		
	}
	

	/**
	 * Callback method invoked to notify that a user has logged out of the main
	 * application. Will show the login application screen.
	 */
	/*public void logout() {
		showLoginScreen();
	}

	public void showLoginScreen() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginView.fxml"));
			System.out.println(loader.toString());
			scene.setRoot((Parent) loader.load());
			System.out.println("hi");
			LoginController controlle = loader.getController();
			System.out.println("hqqq"+controlle);
			System.out.println(this);
			controlle.initManager(this);
		} catch (IOException ex) {
			System.out.println("hello"+ex.getMessage());
			ex.printStackTrace();
		}
		catch (NullPointerException ex) {
			System.out.println("hello"+ex.getMessage());
			ex.printStackTrace();

		}
	}*/

	/*public void showMainView() {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EmployeeView.fxml"));
			scene.setRoot((Parent) loader.load());
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}*/
}
