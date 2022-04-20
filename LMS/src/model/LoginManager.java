package model;

import java.io.IOException;
import java.sql.SQLException;

import controller.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/** Manages control flow for logins */
public class LoginManager {

	private String username;
	private String password;
	private int role;
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

	private Scene scene;

	public LoginManager(Scene scene) {
		this.scene = scene;
	}

	/**
	 * Callback method invoked to notify that a user has been authenticated. Will
	 * show the main application screen.
	 */
	public void authenticate(String user,String pass) {
		
		if(user.equals("amith")&&pass.equals("1234")) {
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
			showLoginScreen();
	}
	
	public Boolean getCredentials(String sUsername, String sPassword, String sUserType)
	{

		String query = "SELECT * FROM hms_login WHERE Email = ? and Password = ? and UserType = ?;";
		try(PreparedStatement stmt = connection.prepareStatement(query)) 
		{
			
			stmt.setString(1, sUsername);
			stmt.setString(2, sPassword);
			stmt.setString(3, sUserType);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{ 
				setUsername(rs.getString("Email"));
				setPassword(rs.getString("Password"));
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
	public void logout() {
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
	}

	private void showMainView() {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EmployeeView.fxml"));
			scene.setRoot((Parent) loader.load());
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
