package controller;

import java.io.IOException;
import java.sql.Statement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.AdminModel;
import model.DBConnect;

public class AdminController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	//Initialize combox array
	ObservableList<String> lstrole = FXCollections.observableArrayList("Employee", "Manager");
	ObservableList<String> lstreportsto = FXCollections.observableArrayList("joe", "joey");

    @FXML
    private JFXTextArea adddepartment;

    @FXML
    private JFXTextArea adddesignation;

    @FXML
    private DatePicker adddob;

    @FXML
    private JFXTextArea addemail;

    @FXML
    private JFXTextArea addfirstname;

    @FXML
    private JFXTextArea addlastname;

    @FXML
    private JFXTextArea addpassword;

    @FXML
    private JFXTextArea addphone;

    @FXML
    private JFXButton adduserbutton;

    @FXML
    private JFXTextArea addemployeeid;

    @FXML
    private JFXComboBox<String> comboboxreportsto;

    @FXML
    private JFXComboBox<String> comboboxrole;
    
    @FXML
    private TableColumn<AdminModel , String> tabcolPnumber;

    @FXML
    private TableColumn<AdminModel, String> tabcolemail;

    @FXML
    private TableColumn<AdminModel , String> tabcolfname;

    @FXML
    private TableColumn<AdminModel , String> tabcollname;

    @FXML
    private TableView<AdminModel> table;
    
    @FXML
    private JFXButton btndeleteuser;

    @FXML
    private TableColumn<AdminModel , Integer> tablecoluserid;
    
	DBConnect dbConnect = null;
	Statement Statement = null;
	public String sUsername;
	public String sPassword;
    
  //Initialize table array
	ObservableList<AdminModel> tablelist = FXCollections.observableArrayList(
			
			new AdminModel(1, "Joe","Joey","test@test.com","8792"),
			new AdminModel(2, "Joe","Joey","test@test.com","8792"),
			new AdminModel(3, "Joe","Joey","test@test.com","8792"),
			new AdminModel(4, "Joe","Joey","test@test.com","8792"),
			new AdminModel(5, "Joe","Joey","test@test.com","8792")
			
			
			);
    
    @FXML
    public void initialize() {
    	dbConnect = new DBConnect();
    	comboboxreportsto.setItems(lstreportsto);
    	comboboxrole.setItems(lstrole);
    	
    	tablecoluserid.setCellValueFactory(new PropertyValueFactory<AdminModel,Integer>("id"));
    	tabcolfname.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("fname"));
    	tabcollname.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("lname"));
    	tabcolemail.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("email"));
    	tabcolPnumber.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("phonenumber"));
    	
    	table.setItems(tablelist);
    }
    
    public void onlogoutfromadmin(ActionEvent event) throws IOException
 	{
 		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/LoginView.fxml"));
 		root = fxmlLoader.load();
 		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
 		scene = new Scene(root, 600, 400);
 		stage.setScene(scene);
 		stage.show();
     }
    
    
	
}
