package controller;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.scene.control.ComboBox;
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
	final ObservableList lstreportsto = FXCollections.observableArrayList();
	final ObservableList updateslstreportsto = FXCollections.observableArrayList();
	
	ComboBox cboxreportsto = new ComboBox(lstreportsto);
	ComboBox cboxreportstoupdate = new ComboBox(updateslstreportsto);
	
	public void fillcombobox() {
		System.out.println("function entered");
		try {
			Connection conn = dbConnect.getconnection();
			String query = "SELECT emp_id from employees where role = '1';";
			System.out.println(query);
			pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			
			while(rs.next()) {
				System.out.println(rs.getString("emp_id"));
				lstreportsto.add(rs.getString("emp_id"));
				updateslstreportsto.add(rs.getString("emp_id"));
			}
			pst.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
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
   
    @FXML
    private JFXButton updatebutton;

    @FXML
    private JFXTextArea updatedep;

    @FXML
    private JFXTextArea updatedes;

    @FXML
    private DatePicker updatedob;

    @FXML
    private JFXTextArea updateemail;

    @FXML
    private JFXTextArea updateemployeeid;

    @FXML
    private JFXTextArea updatefname;

    @FXML
    private JFXTextArea updatelname;

    @FXML
    private JFXTextArea updatepassword;

    @FXML
    private JFXTextArea updatephone;
    
    @FXML
    private JFXComboBox<String> comboboxroleupdate;
    
    @FXML
    private JFXComboBox<String> comboboxreportstoupdate;

    @FXML
    private TableView<AdminModel> updatetable;

    @FXML
    private TableColumn<AdminModel , String> utablecolemail;

    @FXML
    private TableColumn<AdminModel , String> utablecolempid;

    @FXML
    private TableColumn<AdminModel , String> utablecolfname;

    @FXML
    private TableColumn<AdminModel , String> utablecollname;

    @FXML
    private TableColumn<AdminModel , String> utablecolpnumber;
    
    @FXML
    private TableColumn<AdminModel , String> updatecoldep;

    @FXML
    private TableColumn<AdminModel , String> updatecoldes;

    @FXML
    private TableColumn<AdminModel , String> updatecoldob;

    @FXML
    private TableColumn<AdminModel , String> updatecolreportsto;

    @FXML
    private TableColumn<AdminModel , String> updatecolrole;
    
	DBConnect dbConnect = null;
	Statement Statement = null;
	PreparedStatement pst = null;
	Connection con = null;
	public String sUsername;
	public String sPassword;
	int aroleint;
    int index = -1;
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
    	comboboxroleupdate.setItems(lstrole);
    	comboboxreportstoupdate.setItems(lstreportsto);
    	fillcombobox();
    	
    	
    	String query = "SELECT * from employees;";
    	
    	userslist = adminmodel.getdataofusers(query); 
    	System.out.println(userslist);
    	
    	tablecoluserid.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("emp_id"));
    	tabcolfname.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("fname"));
    	tabcollname.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("lname"));
    	tabcolemail.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("email"));
    	tabcolPnumber.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("phone"));
    	tabcoldep.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("department"));
    	
    	utablecolempid.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("emp_id"));
    	utablecolfname.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("fname"));
    	utablecollname.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("lname"));
    	utablecolemail.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("email"));
    	utablecolpnumber.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("phone"));
    	//updatecoldep.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("fname"));
    	updatecoldep.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("department"));
    	updatecoldes.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("designation"));
    	updatecoldob.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("dob"));
    	updatecolrole.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("role"));
    	updatecolreportsto.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("reports_to"));
    	
    	
    	table.setItems(userslist);
    	updatetable.setItems(userslist);
    	
    }
    
    @FXML
    void adduser(ActionEvent event) throws IOException {
    	System.out.println("Button clicked");
    	
    	try {
  
    		String aid = addemployeeid.getText().trim();
    		String afn = addfirstname.getText().trim();
    		String aln = addlastname.getText().trim();
    		String adep = adddepartment.getText().trim();
    		String ades = adddesignation.getText().trim();
    		String aemail = addemail.getText().trim();
    		String apass = addpassword.getText().trim();
    		String aphone = addphone.getText().trim();
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
    		//String regex = "/[^\s@]+@[^\s@]+\.[^\s@]+/";
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
    				+ " ('"+aid+"','"+afn+"','"+aln+"','"+adep+"','"+aroleint+"','"+adob+"','"+arepto+"','"+aemail+"','"+apass+"','"+aphone+"','"+ades+"' )";
    		
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
    
    @FXML
    void getSelected() {
    	index = updatetable.getSelectionModel().getSelectedIndex();
    	if(index<= -1) {
    		return;
    	}
    	updateemployeeid.setText(utablecolempid.getCellData(index).toString());
    	updatefname.setText(utablecolfname.getCellData(index).toString());
    	updatelname.setText(utablecollname.getCellData(index).toString());
    	updateemail.setText(utablecolemail.getCellData(index).toString());
    	updatephone.setText(utablecolpnumber.getCellData(index).toString());
    	updatedes.setText(updatecoldes.getCellData(index).toString());
    	updatedep.setText(updatecoldep.getCellData(index).toString());
    }
    
    public void deleteuser() throws SQLException {
    	index = table.getSelectionModel().getSelectedIndex();
    	String idfordelete = tablecoluserid.getCellData(index).toString();
    	Connection conn = dbConnect.getconnection();
    	String sql = "DELETE from employees where emp_id = ?;";
    	try {
    		pst = conn.prepareStatement(sql);
    		pst.setString(1, idfordelete);
    		pst.execute();
    		JOptionPane.showMessageDialog(null,"Delete done");
    		UpdateDeleteTable();
    	}
    	catch(Exception e) {
    		JOptionPane.showMessageDialog(null,e);
    	}
    }
    
    public void onrefresh() {
    	UpdateDeleteTable();
    	UpdateUTable();
    }
    
    @FXML
    public void UpdateTable() {
    	int value5int = -1;
    	try {
    		Connection conn = dbConnect.getconnection();
    		String value1 = updateemployeeid.getText();
    		String value2 = updatefname.getText();
    		String value3 = updatelname.getText();
    		String value4 = updatedep.getText();
    		String value5 = comboboxroleupdate.getValue();
    		if(value5.equalsIgnoreCase("Manager")) {
    			value5int = 1;
    		}
    		else if(value5.equalsIgnoreCase("Employee")) {
    			value5int = 0;
    		}
    		LocalDate value6 = updatedob.getValue();
    		String value7 = comboboxreportstoupdate.getValue();
    		String value8 = updateemail.getText();
    		String value9 = updatepassword.getText();
    		String value10 = updatephone.getText();
    		String value11 = updatedes.getText();
    		
    		String sql = "update employees set fname = '"+value2+"',lname = '"+value3+"',department = '"+value4+"',role = '"+value5int+"',dob = '"+value6+"',reports_to = '"+value7+"',email = '"+value8+"',password = '"+value9+"',phone = '"+value10+"',designation = '"+value11+"' where emp_id = '"+value1+"'; ";
    		System.out.println(sql);
    		pst = conn.prepareStatement(sql);
    		pst.execute();
    		JOptionPane.showMessageDialog(null,"Update done");
    		UpdateUTable();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public void UpdateDeleteTable() {
    	dbConnect = new DBConnect();
    	tablecoluserid.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("emp_id"));
    	tabcolfname.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("fname"));
    	tabcollname.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("lname"));
    	tabcolemail.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("email"));
    	tabcolPnumber.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("phone"));
    	tabcoldep.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("department"));
    	String query = "SELECT * from employees;";
    	userslist = adminmodel.getdataofusers(query);
    	table.setItems(userslist);
    }
    
    public void UpdateUTable() {
    	dbConnect = new DBConnect();
    	utablecolempid.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("emp_id"));
    	utablecolfname.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("fname"));
    	utablecollname.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("lname"));
    	utablecolemail.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("email"));
    	utablecolpnumber.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("phone"));
    	updatecoldep.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("department"));
    	updatecoldes.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("designation"));
    	updatecoldob.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("dob"));
    	updatecolrole.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("role"));
    	updatecolreportsto.setCellValueFactory(new PropertyValueFactory<AdminModel,String>("reports_to"));
    	
    	String query = "SELECT * from employees;";
    	userslist = adminmodel.getdataofusers(query);
    	updatetable.setItems(userslist);
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
