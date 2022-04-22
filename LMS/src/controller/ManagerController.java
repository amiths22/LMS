package controller;
import java.awt.TextArea;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javax.swing.JComboBox;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import model.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class ManagerController {

	public String sUsername;
	public String sPassword;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	ObservableList<String> lstUserType = FXCollections.observableArrayList("Annual", "Casual", "Sick", "Maternity", "Paternity");

    @FXML
    private JFXTextArea reasonbox;
    
    @FXML
    private Button applyleavebutton;

    @FXML
    private DatePicker leavefrom;

    @FXML
    private DatePicker leaveto;
    
    @FXML
    private ComboBox<String> combobox;
    

    @FXML
    private Button logoutbutton;
    
	DBConnect dbConnect = null;
	Statement Statement = null;
	
    
    @FXML
	private void initialize()
	{
		combobox.setItems(lstUserType);
		dbConnect = new DBConnect();
	}
    
/*    private void onApplyLeave(ActionEvent event) throws IOException
    {
    	try {
    		LocalDate dleavefrom = leavefrom.getValue();
    		LocalDate dleaveto = leaveto.getValue();
    		String leavetype = combobox.getValue();
    		String sreason = reasonbox.getText();
    		
    		Statement = dbConnect.connect().createStatement();
    		
    		//string sql = "INSERT INTO "
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    	
    }*/
   public void onlogout(ActionEvent event) throws IOException
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/LoginView.fxml"));
		root = fxmlLoader.load();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root, 400, 700);
		stage.setScene(scene);
		stage.show();
    }

}
