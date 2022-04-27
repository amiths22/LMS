package controller;
import java.awt.TextArea;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.event.Event;
import model.AdminModel;
import model.DBConnect;
import model.EmployeeModel;
import model.LeaveModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.time.DayOfWeek;
import java.time.temporal.ChronoUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class EmployeeController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	ObservableList<String> listLeaveType = FXCollections.observableArrayList("Annual", "Casual", "Sick", "Maternity", "Paternity");

    @FXML
    private JFXTextArea reasonbox;
    
    @FXML
    private Button applyleavebutton;
    
    @FXML
    private Label usernamelabel;

    @FXML
    private DatePicker leavefrom;
    
    @FXML
    private Tab leavehistory;
    
    @FXML
    private Tab userdetails;

    @FXML
    private DatePicker leaveto;
    
    @FXML
    private ComboBox<String> combobox;
    
    @FXML
    private TableColumn<LeaveModel , String> tabcoltype;

    @FXML
    private TableColumn<LeaveModel, Date> tabcolfrom;

    @FXML
    private TableColumn<LeaveModel , Date> tabcolto;

    @FXML
    private TableColumn<LeaveModel , String> tabcolnod;

    @FXML
    private TableView<LeaveModel> lhtable;
   // table.setView(true);
    
    @FXML
    private Button logoutbutton;
    
	DBConnect dbConnect = null;
	Statement Statement = null;
	public String sUsername;
	public String sPassword;
    ObservableList<LeaveModel> leaveslist;
    LeaveModel leavemodel =new LeaveModel();

    
    @FXML
	private void initialize()
	{
		combobox.setItems(listLeaveType);
		dbConnect = new DBConnect();
		//usernamelabel.setText(sUsername);
		System.out.println(sUsername);
		
		
	}
    @FXML
    void event(Event ev) {
        if (leavehistory.isSelected()) {
            System.out.println("Tab is Selected");
            //Do stuff here
        
    	String query = "SELECT * from leaverecords where emp_id='"+sUsername+"' and approve='yes';";
		System.out.println(query);
    	leaveslist = leavemodel.getleavehistory(query); 
    	System.out.println(leaveslist);
    	
    	//LeavehistoryTable();
    	
    	tabcoltype.setCellValueFactory(new PropertyValueFactory<LeaveModel,String>("type"));
    	tabcolfrom.setCellValueFactory(new PropertyValueFactory<LeaveModel,Date>("fromdate"));
    	/*tabcolfrom.setCellFactory(column -> {
            TableCell<LeaveModel, Date> cell = new TableCell<LeaveModel, Date>() {
                private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                        this.setText(format.format(item));

                    }
                }
            };

            return cell;
        });*/
    	tabcolto.setCellValueFactory(new PropertyValueFactory<LeaveModel,Date>("todate"));
    	tabcolnod.setCellValueFactory(new PropertyValueFactory<LeaveModel,String>("nod"));
    	
        lhtable.setItems(leaveslist);

    	
    }
        
    }
    @FXML
    void event1(Event ev) {   
    if (userdetails.isSelected()) {
		usernamelabel.setText(sUsername);

    }
    }
    public void onapplyleave(ActionEvent event) throws IOException
    {
    	try {
    		LocalDate dleavefrom = leavefrom.getValue();
    		LocalDate dleaveto = leaveto.getValue();
    		String leavetype = combobox.getValue();
    		String scomments = reasonbox.getText();
    		int nod=countLeaveDays(dleavefrom,dleaveto);
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
    		
    		System.out.println("leavedays"+nod);
    		System.out.println("Empid"+sUsername);
    		
    		Statement = dbConnect.getconnection().createStatement();
    		
    		String sql = "INSERT into leaverecords (emp_id,fromdate,todate,nod,type,comments) VALUES"
    				+ " ('"+sUsername+"','"+dleavefrom+"','"+dleaveto+"','"+nod+"','"+leavetypeint+"','"+scomments+"')";
    		
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
   public void onlogout(ActionEvent event) throws IOException
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/LoginView.fxml"));
		root = fxmlLoader.load();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root, 600, 400);
		stage.setScene(scene);
		stage.show();
    }
    
   /*void LeavehistoryTable() {
		// TODO Auto-generated method stub
		TableColumn<LeaveModel, String> leavetype = new TableColumn<>("type");
		leavetype.setCellValueFactory(new PropertyValueFactory<LeaveModel, String>("type"));
    
       // Course Title Column
       TableColumn<LeaveModel, String> fromdate = new TableColumn<>("fromdate");
       fromdate.setCellValueFactory(new PropertyValueFactory<LeaveModel, String>("fromdate"));

       TableColumn<LeaveModel, String> todate = new TableColumn<>("todate");
       todate.setCellValueFactory(new PropertyValueFactory<LeaveModel, String>("todate"));

       TableColumn<LeaveModel, String> nod = new TableColumn<>("nod");
       nod.setCellValueFactory(new PropertyValueFactory<LeaveModel, String>("nod"));
            
       lhtable.setItems(leaveslist);
       
       //System.out.println(availableCourseList);
       
       
       lhtable.getColumns().addAll(leavetype, fromdate, todate, nod);
       lhtable.setVisible(true);

	}*/

    private static int countLeaveDays(final LocalDate startDate,
            final LocalDate endDate
            )
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

}
