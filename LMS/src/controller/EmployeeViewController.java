package controller;

import java.awt.Button;

import javafx.fxml.FXML;
import javafx.scene.control.*;


public class EmployeeViewController {
	
	@FXML
    private ToggleGroup LeaveType;

    @FXML
    private RadioButton annualleave;

    @FXML
    private RadioButton casualleave;

    @FXML
    private Button logoutButton;

    @FXML
    private RadioButton maternityleave;

    @FXML
    private RadioButton paternityleave;

    @FXML
    private RadioButton sickleave;
	
	public void initialize() {}
	
	public void apply() {
		
	}

}
