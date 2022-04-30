package controller;
import java.awt.TextArea;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;

import model.AdminModel;
import model.DBConnect;
import model.ManagerModel;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ManagerController {

	 ManagerModel managermodel;
	 public ManagerController() 
		{ 
			  managermodel = new ManagerModel(); 
		}
	
	public String sUsername;
	public String sPassword;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	ObservableList<String> lstUserType = FXCollections.observableArrayList("Annual", "Casual", "Sick", "Maternity", "Paternity");
	ObservableList<String> lstactions = FXCollections.observableArrayList("Approve","Deny");
	

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
    private JFXComboBox<String> ATblComboBoxAction;
    
    @FXML
    private TableView<ManagerModel> approvetable;
    
    @FXML
    private TableColumn<ManagerModel, String> ATblDateFrom;

    @FXML
    private TableColumn<ManagerModel, String> ATblDateTo;

    @FXML
    private TableColumn<ManagerModel, String> ATblEmployeeID;

    @FXML
    private TableColumn<ManagerModel, String> ATblLeaveType;

    @FXML
    private TableColumn<ManagerModel, String> ATblNoOfDays;

    @FXML
    private TableColumn<ManagerModel, String> ATblReason;
    
    @FXML
    private TableColumn<ManagerModel, String> ATblfname;
    
    @FXML
    private JFXTextArea employeeidfetch;
    

    @FXML
    private Button logoutbutton;
    
	DBConnect dbConnect = null;
	Statement Statement = null;
	ObservableList<ManagerModel> leavelist;
	int index = -1;
	String emp_id,sql;
	PreparedStatement pst = null;	
    
    @FXML
	private void initialize()
	{
		combobox.setItems(lstUserType);
		dbConnect = new DBConnect();
		ATblComboBoxAction.setItems(lstactions);
		
	}
    
    @FXML
    private void loadtable() {
    	dbConnect = new DBConnect();
    	String query = "SELECT emptbl.emp_id, emptbl.fname, lrtbl.fromdate, lrtbl.todate, lrtbl.type, lrtbl.approve, lrtbl.comments,lrtbl.nod FROM employees emptbl, leaverecords lrtbl WHERE (emptbl.emp_ID = lrtbl.emp_ID AND emptbl.reports_to ='"+sUsername+"') AND lrtbl.approve is null;";
		System.out.println(query);
		
		leavelist = managermodel.getemployeeleaves(query);
		System.out.println(leavelist);
		
		ATblEmployeeID.setCellValueFactory(new PropertyValueFactory<ManagerModel,String>("emp_id"));
		ATblfname.setCellValueFactory(new PropertyValueFactory<ManagerModel,String>("fname"));
		ATblLeaveType.setCellValueFactory(new PropertyValueFactory<ManagerModel,String>("type"));
		ATblDateFrom.setCellValueFactory(new PropertyValueFactory<ManagerModel,String>("fromdate"));
		ATblDateTo.setCellValueFactory(new PropertyValueFactory<ManagerModel,String>("todate"));
		ATblNoOfDays.setCellValueFactory(new PropertyValueFactory<ManagerModel,String>("nod"));
		ATblReason.setCellValueFactory(new PropertyValueFactory<ManagerModel,String>("comments"));
		
		approvetable.setItems(leavelist);
    }
    @FXML
    private void getselected() {
    	index = approvetable.getSelectionModel().getSelectedIndex();
    	if(index<= -1) {
    		return;
    	}
    	employeeidfetch.setText(ATblEmployeeID.getCellData(index).toString());
    }
    
    @FXML
    public void onApprove() {
    	index = approvetable.getSelectionModel().getSelectedIndex();
    	String idforapprove = ATblEmployeeID.getCellData(index).toString();
    	String leavetypeapprove = ATblLeaveType.getCellData(index).toString();
    	String leavetopush;
    	if(leavetypeapprove.equalsIgnoreCase("Annual")) {
    		leavetopush = "1";
    	}
    	else if(leavetypeapprove.equalsIgnoreCase("Casual")) {
    		leavetopush = "2";
    	}
    	else if(leavetypeapprove.equalsIgnoreCase("Sick")) {
    		leavetopush = "3";
    	}
    	else if(leavetypeapprove.equalsIgnoreCase("Maternity")) {
    		leavetopush = "4";
    	}
    	else {
    		leavetopush = "5";
    	}
    	Connection conn = dbConnect.getconnection();
    	try {
    		emp_id = employeeidfetch.getText().toString();
    		if(ATblComboBoxAction.getValue() == "Approve") {
        		sql = "UPDATE leaverecords set approve='YES' where emp_id=? AND type=?;";
        	}
        	else {
        		sql = "UPDATE leaverecords set approve='NO' where emp_id =? AND type=?;";
        	}
    		System.out.println(sql);
        	pst = conn.prepareStatement(sql);
        	pst.setString(1, idforapprove);
        	pst.setString(2, leavetopush);
    		pst.execute();
    		JOptionPane.showMessageDialog(null,"Update done");
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }
    
    
    @FXML
  private void onApplyLeave(ActionEvent event) throws IOException
    {
    	try {
    		LocalDate dleavefrom = leavefrom.getValue();
    		LocalDate dleaveto = leaveto.getValue();
    		String leavetype = combobox.getValue();
    		String sreason = reasonbox.getText();
    		int nod = countLeaveDays(dleavefrom,dleaveto);
    		int leavetypeint=-1;
    		
    		switch(leavetype) {
    		case "Annual": leavetypeint=1;
    			break;
    		case "Casual":leavetypeint=2;
    			break;
    		case "Sick": leavetypeint=3;
    			break;
    		case "Maternity": leavetypeint=4;
    			break;
    		case "Paternity": leavetypeint=5;
    			break;
    		}

    		Statement = dbConnect.getconnection().createStatement();
    		
    		String sql = "INSERT into leaverecords (emp_id,fromdate,todate,nod,type,comments) VALUES"
    				+ " ('"+sUsername+"','"+dleavefrom+"','"+dleaveto+"','"+nod+"','"+leavetypeint+"','"+sreason+"')";
    		
    		int con = Statement.executeUpdate(sql);
			if (con > 0) 
			{
				JOptionPane.showMessageDialog(null,"Leave applied for "+nod+" days");
			}
    		
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    	
    	
    	
    }
  
  private static int countLeaveDays(final LocalDate startDate,final LocalDate endDate)
  {
      // Validate method arguments
      if (startDate == null || endDate == null) {
          throw new IllegalArgumentException("Invalid method argument(s) to countBusinessDaysBetween (" + startDate+ "," + endDate + ")");
      }

      // Predicate 1: Is a given date is a holiday
     // Predicate<LocalDate> isHoliday = date -> holidays.isPresent() 
           //   && holidays.get().contains(date);

      // Predicate 2: Is a given date is a weekday
      Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY
              || date.getDayOfWeek() == DayOfWeek.SUNDAY;

      // Get all days between two dates
      long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);

      // Iterate over stream of all dates and check each day against any weekday or
      // holiday
      return Stream.iterate(startDate, date -> date.plusDays(1))
              .limit(daysBetween)
              .filter(isWeekend.negate())
              .collect(Collectors.toList()).size()+1;
      
  }
   public void onlogout(ActionEvent event) throws IOException
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/LoginView.fxml"));
		root = fxmlLoader.load();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root, 600, 400);
		stage.setScene(scene);
		stage.show();
    }

}
