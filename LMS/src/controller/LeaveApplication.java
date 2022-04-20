package controller;


import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.LoginModel;

public class LeaveApplication extends Application {
	@Override
	public void start(Stage stage) {
		try {
			System.out.println("Test");
			 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/LoginView.fxml"));
			 Scene scene = new Scene(fxmlLoader.load(), 800, 1000);

			//scene.getStylesheets().add(getClass().getResource("Application.css").toExternalForm());
			stage.setTitle("Leave management System");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}

/*
/** Main application class for the login demo application 
public class LeaveApplication extends Application {
 
  public static void main(String[] args) { launch(args); }
  @Override public void start(Stage stage) throws IOException {
   /* Scene scene = new Scene(new StackPane());
    LoginController
    LoginManager loginManager = new LoginManager(scene);
    loginManager.showLoginScreen();

    stage.setScene(scene);
    stage.show();*/
    
   /* 
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/LoginView.fxml"));
	Scene scene = new Scene(fxmlLoader.load(), 800, 1000);

	//scene.getStylesheets().add(getClass().getResource("Application.css").toExternalForm());
	stage.setTitle("Leave management System");
	stage.setScene(scene);
	stage.show();
  }
}*/


