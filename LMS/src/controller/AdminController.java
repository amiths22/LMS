package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javax.swing.JOptionPane;

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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.AdminModel;
import model.DBConnect;
import model.LoginModel;

public class AdminController {
	
	AdminModel adminmodel;
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	 public AdminController() 
		{ 
			  adminmodel = new AdminModel(); 
		}
	
	
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
    private TableColumn<AdminModel, String> tabcoldep;
    
    @FXML
    private Label lblerror;

    @FXML
    private TableColumn<AdminModel , String> tablecoluserid;
    
	DBConnect dbConnect = null;
	Statement Statement = null;
	public String sUsername;
	public String sPassword;
	int aroleint;
    
  //Initialize table array
	/*ObservableList<AdminModel> tablelist = FXCollections.observableArrayList(
			
			new AdminModel(1, "Joe","Joey","test@test.com","8792"),
			new AdminModel(2, "Joe","Joey","test@test.com","8792"),
			new AdminModel(3, "Joe","Joey","test@test.com","8792"),
			new AdminModel(4, "Joe","Joey","test@test.com","8792"),
			new AdminModel(5, "Joe","Joey","test@test.com","8792")
			
			
			);*/
    ObservableList<AdminModel> userslist;
    ResultSet rs = null;
	
    @FXML
    public void initialize() {
    	dbConnect = new DBConnect();
    	comboboxreportsto.setItems(lstreportsto);
    	comboboxrole.setItems(lstrole);
    	
    	
    	String query = "SELECT * from employees;";
    	
    	userslist = adminmodel.getdataofusers(query); 
    	System.out.println(userslist);
    	
    	tablecoluserid.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("emp_id"));
    	tabcolfname.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("fname"));
    	tabcollname.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("lname"));
    	tabcolemail.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("email"));
    	tabcolPnumber.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("phone"));
    	tabcoldep.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("department"));
    	
    	table.setItems(userslist);
    	
    }
    
    @FXML
    void adduser(ActionEvent event) throws IOException {
    	System.out.println("Button clicked");
    	
    	try {
  
    		String aid = addemployeeid.getText();
    		String afn = addfirstname.getText();
    		String aln = addlastname.getText();
    		String adep = adddepartment.getText();
    		String ades = adddesignation.getText();
    		String aemail = addemail.getText();
    		String apass = addpassword.getText();
    		String aphone = addphone.getText();
    		String arole = comboboxrole.getValue();
    		String arepto = comboboxreportsto.getValue();
    		LocalDate adob = adddob.getValue();
    		
    		if (aid == null || aid.trim().equals("")) {
				lblerror.setText("ID Cannot be empty or spaces");
				return;
			}
    		if (afn == null || afn.trim().equals("")) {
				lblerror.setText("First Name Cannot be empty or spaces");
				return;
			}
    		if (aln == null || aln.trim().equals("")) {
				lblerror.setText("Last Name Cannot be empty or spaces");
				return;
			}
    		if (adep == null || adep.trim().equals("")) {
				lblerror.setText("Department Cannot be empty or spaces");
				return;
			}
    		if (ades == null || ades.trim().equals("")) {
				lblerror.setText("Designation Cannot be empty or spaces");
				return;
			}
    		if (aemail == null || aemail.trim().equals("")) {
				lblerror.setText("Email Cannot be empty or spaces");
				return;
			}
    		if (apass == null || apass.trim().equals("")) {
				lblerror.setText("Password Cannot be empty or spaces");
				return;
			}
    		if (aphone == null || aphone.trim().equals("")) {
				lblerror.setText("Phone number Cannot be empty or spaces");
				return;
			}
    		
    		if(arole.equalsIgnoreCase("Manager")) {
    			aroleint = 1;
    		}
    		else if(arole.equalsIgnoreCase("Employee")) {
    			aroleint = 0;
    		}
    		
    		
    		Statement = dbConnect.getconnection().createStatement();
    		
    		String sql = "INSERT into employees (emp_id,fname,lname,department,role,dob,reports_to,email,password,phone,designation) VALUES"
    				+ " (' "+aid+" ',' "+afn+" ','"+aln+"',' "+adep+" ','"+aroleint+"','"+adob+"','"+arepto+"','"+aemail+"','"+apass+"','"+aphone+"','"+ades+"' )";
    		
    		int con = Statement.executeUpdate(sql);
			if (con > 0) 
			{
				JOptionPane.showMessageDialog(null,"User added successfully");
			}
		}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	
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
