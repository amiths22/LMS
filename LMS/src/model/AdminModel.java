package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdminModel extends DBConnect {
	
	String emp_id;
	String fname,lname,email,phone,department;
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	DBConnect dbConnect = null;
	
	/*public AdminModel(String emp_id, String fname, String lname, String email, String phone) {

		this.emp_id = emp_id;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.phone = phone;
	}*/
	
	
	public String getemp_id() {
		return emp_id;
	}

	public void setemp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getphone() {
		return phone;
	}

	public void setphone(String phone) {
		this.phone = phone;
	}
	
	public ObservableList<AdminModel> getdataofusers(String query){
		ObservableList<AdminModel> userlist = FXCollections.observableArrayList();
		System.out.println("try entered1");
		try(PreparedStatement ps = conn.prepareStatement(query))
		{
			System.out.println("try entered");
            ResultSet rs = ps.executeQuery();
            System.out.println(ps);
            
            while (rs.next())
            {
            	AdminModel adm=new AdminModel();
            	adm.setemp_id(rs.getString("emp_id"));
            	adm.setFname(rs.getString("fname"));
            	adm.setLname(rs.getString("lname"));
            	adm.setEmail(rs.getString("email"));
            	adm.setphone(rs.getString("phone"));
            	adm.setDepartment(rs.getString("department"));
            	userlist.add(adm);
            }
		}
		catch(SQLException e) {
			System.out.println("Error Displaying user details ");
		}
		return userlist;
	}
}
