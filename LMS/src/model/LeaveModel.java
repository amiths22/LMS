package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LeaveModel extends DBConnect{
	
	Date dleavefrom;
	Date dleaveto;
	String leavetype;
	String scomments;
	String nod;
	public Date getDleavefrom() {
		return dleavefrom;
	}
	public void setDleavefrom(Date date) {
		this.dleavefrom = date;
	}
	public Date getDleaveto() {
		return dleaveto;
	}
	public void setDleaveto(Date date) {
		this.dleaveto = date;
	}
	public String getLeavetype() {
		return leavetype;
	}
	public void setLeavetype(String leavetype) {
		this.leavetype = leavetype;
	}
	public String getScomments() {
		return scomments;
	}
	public void setScomments(String scomments) {
		this.scomments = scomments;
	}
	public String getNod() {
		return nod;
	}
	public void setNod(String nod) {
		this.nod = nod;
	}

	 public ObservableList<LeaveModel> getleavehistory(String query){
			ObservableList<LeaveModel> leaveslist = FXCollections.observableArrayList();
			System.out.println("try entered1");
			try(PreparedStatement ps = conn.prepareStatement(query))
			{
				System.out.println("try entered");
	            ResultSet rs = ps.executeQuery();
	            System.out.println(ps);
	            
	            while (rs.next())
	            {
	            	LeaveModel leavemodel=new LeaveModel();
	            	leavemodel.setLeavetype(rs.getString("type"));
	            	System.out.println(leavemodel.getLeavetype());
	            	leavemodel.setDleavefrom(rs.getDate("fromdate"));
	            	System.out.println(leavemodel.getDleavefrom());
	            	leavemodel.setDleaveto(rs.getDate("todate"));
	            	leavemodel.setNod(rs.getString("nod"));
	            	System.out.println(leavemodel);
	            	leaveslist.add(leavemodel);
	            }
			}
			catch(SQLException e) {
				System.out.println("Error Displaying user details ");
			}
			return leaveslist;
		}
}
