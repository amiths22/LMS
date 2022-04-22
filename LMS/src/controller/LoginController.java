package controller;


//import java.awt.event.ActionEvent;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.LoginModel;


/** Controls the login screen */
public class LoginController {
	
  @FXML private TextField username;
  @FXML private TextField password;
  @FXML private Button login;
  
  private Stage stage;
	private Scene scene;
	private Parent root;
	private int role;
 private boolean authok = false;
private LoginModel loginmanager;

 
 public LoginController() 
	{ 
		  loginmanager = new LoginModel(); 
	}
 
 public void onclicklogin(ActionEvent event)
	{
	 System.out.println("Login clicked");
		String username = this.username.getText();
		String password = this.password.getText();

		// Validations
		if (username.equalsIgnoreCase("") && password.equalsIgnoreCase("")) {
  			Alert alert = new Alert(Alert.AlertType.ERROR);
  			alert.setTitle("Error");
  			alert.setHeaderText("please enter username and password");
  			alert.showAndWait();
  		} else if (username.equalsIgnoreCase("")) {
  			Alert alert = new Alert(Alert.AlertType.ERROR);
  			alert.setTitle("Error");
  			alert.setHeaderText("please enter username");
  			alert.showAndWait();
  		} else if (password.equalsIgnoreCase("")) {
  			Alert alert = new Alert(Alert.AlertType.ERROR);
  			alert.setTitle("Error");
  			alert.setHeaderText("please enter password");
  			alert.showAndWait();
  			}
		checkCredentials(username, password, event);
  			}

		// authentication check
		
		
 public void checkCredentials(String username, String password, ActionEvent event) {
		
		//String sUserType = ((RadioButton) UserType.getSelectedToggle()).getText();
	 
		
		Boolean isValid = loginmanager.authenticate(username, password);
		System.out.println("authentication success");
		if (!isValid) 
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
  			alert.setTitle("Error");
  			alert.setHeaderText("User does not exist");
  			alert.showAndWait();
			return;
		}
		else {
			 role=loginmanager.getRole();
			 System.out.println("role"+role);
		}
		try 
		{
			String newscene="";
			int width = 0;
			int height = 0;

			switch(role){
			
			case 0: 
			{
				newscene="/view/EmployeeView.fxml";
				width = 571;
				height = 358;
				
			}
			break;

			case 1:
			{
				newscene="/view/ManagerView.fxml";
				width = 571;
				height = 358;
			}
			break;
			
			case 2:
			{
				newscene="/view/adminview.fxml";
				width = 857;
				height = 565;
			}
			break;
		}

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(newscene));
			Parent root = fxmlLoader.load();

			if(role==0)
			{
				EmployeeController empCtrl = ((EmployeeController)fxmlLoader.getController());
				empCtrl.sUsername = username;
				empCtrl.sPassword = password;
				System.out.println("success");
			}
			else if(role==1)
			{
				ManagerController manCtrl = ((ManagerController)fxmlLoader.getController());
				manCtrl.sUsername = username;
				manCtrl.sPassword = password;
			}
			else if(role == 2)
			{
				AdminController admCtrl = ((AdminController)fxmlLoader.getController());
				admCtrl.sUsername = username;
				admCtrl.sPassword = password;
			}
			
			Node source = (Node) event.getSource();
			Stage stage = (Stage)source.getScene().getWindow();
			Scene scene = new Scene(root, width, height);
			//scene.getStylesheets().add(getClass().getResource("/application/Application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();

		} catch (Exception e) {
			System.out.println("Error occured while checking credentials: " + e);
		}

	}

  /*public void initManager(final LoginManager loginManager) {
	  
	  System.out.println("init manager entered");
	   login.setOnAction((e) -> {
	    	  String user = username.getText();
	    	  String pass = password.getText();
	    	  if (user.equalsIgnoreCase("") && pass.equalsIgnoreCase("")) {
	  			Alert alert = new Alert(Alert.AlertType.ERROR);
	  			alert.setTitle("Error");
	  			alert.setHeaderText("please enter username and password");
	  			alert.showAndWait();
	  		} else if (user.equalsIgnoreCase("")) {
	  			Alert alert = new Alert(Alert.AlertType.ERROR);
	  			alert.setTitle("Error");
	  			alert.setHeaderText("please enter username");
	  			alert.showAndWait();
	  		} else if (pass.equalsIgnoreCase("")) {
	  			Alert alert = new Alert(Alert.AlertType.ERROR);
	  			alert.setTitle("Error");
	  			alert.setHeaderText("please enter password");
	  			alert.showAndWait();
	  			}else {
	           authok= loginManager.authenticate(user,pass);
	           System.out.println(authok);
	           
	  			}
	    	  if(authok) {
	   		   System.out.println("authok entered");
	   		   //loginManager.showMainView();
	   		   switch(loginManager.getRole()) {
	   		   
	   		   case 1:loginManager.showMainView();
	   		   break;
	   		   case 0: loginManager.showLoginScreen();
	   		   }
	   		  
	   	   }
	    	  else {
	    		  Alert alert = new Alert(Alert.AlertType.ERROR);
		  			alert.setTitle("Error");
		  			alert.setHeaderText("Username or password incorect");
		  			alert.showAndWait();
	    	  }
		});
	   
	   if(authok) {
		   System.out.println("authok entered");
		   loginManager.showMainView();
		   
		  
	   }
	   
	   
	   
	 
  }*/

  /**
   * Check authorization credentials.
   * 
   * If accepted, return a sessionID for the authorized session
   * otherwise, return null.
   */   
  
  

  
}
