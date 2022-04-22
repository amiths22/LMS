package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.AdminModel;

public class AdminController {
	
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
    private TableColumn<AdminModel , Integer> tablecoluserid;
    
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
    	comboboxreportsto.setItems(lstreportsto);
    	comboboxrole.setItems(lstrole);
    	
    	tablecoluserid.setCellValueFactory(new PropertyValueFactory<AdminModel,Integer>("tablecoluserid"));
    	tabcolfname.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("tabcolfname"));
    	tabcollname.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("tabcollname"));
    	tabcolemail.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("tabcolemail"));
    	tabcolPnumber.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("tabcolPnumber"));
    	
    	table.setItems(tablelist);
    }
    
    
	
}
