package controller;


import javafx.fxml.FXML;
import javafx.scene.control.*;

import model.LoginManager;

/** Controls the login screen */
public class LoginController {
	
  @FXML private TextField username;
  @FXML private TextField password;
  @FXML private Button login;
  
 // public void initialize() {}
  
  public void initManager(final LoginManager loginManager) {
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
	  			}else  
	            loginManager.authenticate(user,pass);
		});
	   
	   
	   
	 
  }

  /**
   * Check authorization credentials.
   * 
   * If accepted, return a sessionID for the authorized session
   * otherwise, return null.
   */   
  
  

  
}
