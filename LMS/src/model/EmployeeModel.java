package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmployeeModel extends DBConnect {
	
	 
	    private String emp_id;
		private String fname,lname,email,phone,department,designation,dob,reports_to,role;
		public String getEmp_id() {
			return emp_id;
		}
		public void setEmp_id(String emp_id) {
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
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getDepartment() {
			return department;
		}
		public void setDepartment(String department) {
			this.department = department;
		}
		public String getDesignation() {
			return designation;
		}
		public void setDesignation(String designation) {
			this.designation = designation;
		}
		public String getDob() {
			return dob;
		}
		public void setDob(String dob) {
			this.dob = dob;
		}
		public String getReports_to() {
			return reports_to;
		}
		public void setReports_to(String reports_to) {
			this.reports_to = reports_to;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}

	  public EmployeeModel getDetails(String sql) {
	  try(PreparedStatement ps = conn.prepareStatement(sql))
		{
			
          ResultSet rs = ps.executeQuery();
      	EmployeeModel empmodel=new EmployeeModel();
          
          while (rs.next())
          {
          	empmodel.setEmp_id(rs.getString("emp_id"));
          	empmodel.setFname(rs.getString("fname"));
          	empmodel.setLname(rs.getString("lname"));
          	empmodel.setEmail(rs.getString("email"));
          	empmodel.setDepartment(rs.getString("department"));
          	empmodel.setDob(rs.getString("dob"));
          	empmodel.setReports_to(rs.getString("reports_to"));
          	empmodel.setDesignation(rs.getString("designation"));
          	empmodel.setPhone(rs.getString("phone"));
        

          }
      	return empmodel;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	  }
}
