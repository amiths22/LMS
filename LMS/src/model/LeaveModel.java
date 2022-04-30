package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LeaveModel extends DBConnect{
	
	String fromdate;
	String todate;
	String type;
	String comments;
	String nod;
	
	public String getFromdate() {
		return fromdate;
	}

	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}

	public String getTodate() {
		return todate;
	}

	public void setTodate(String todate) {
		this.todate = todate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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
	            	//leavemodel.setType(rs.getString("type"));
	            	if(rs.getString("type").equalsIgnoreCase("1"))
	            	{
	            		leavemodel.setType("Annual");
	            	}
	            	else if(rs.getString("type").equalsIgnoreCase("2")) {
	            		leavemodel.setType("Casual");
	            	}
	            	else if(rs.getString("type").equalsIgnoreCase("3")) {
	            		leavemodel.setType("Sick");
	            	}
	            	else if(rs.getString("type").equalsIgnoreCase("4")) {
	            		leavemodel.setType("Maternity");
	            	}
	            	else if(rs.getString("type").equalsIgnoreCase("5")) {
	            		leavemodel.setType("Paternity");

	            	}
	            	System.out.println(leavemodel.getType());
	            	leavemodel.setFromdate(rs.getString("fromdate"));
	            	System.out.println(leavemodel.getFromdate());
	            	leavemodel.setTodate(rs.getString("todate"));
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
	public String checkLeaveDateConflict(String query) {
		
		Map<Integer,String> leatyp = new HashMap<Integer,String>();
        leatyp.put(1,"Annual");
        leatyp.put(2,"Casual");
        leatyp.put(3,"Sick");
        leatyp.put(4,"Maternity");
        leatyp.put(5,"Paternity");
        
		try(PreparedStatement ps = conn.prepareStatement(query))
		{
			
		ResultSet rs=ps.executeQuery();
		
		int temp;
		
		while(rs.next()) {
		
			 temp=rs.getInt("type");
			 return "You have already applied "+leatyp.get(temp)+" for the given dates";
		
		} 
		
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "no";
		
	}
	public HashMap<String, Integer> getLeaveBalances(String query) {
		int al,cl,sl,ml,pl;
		int cal=0,ccl=0,csl=0,cml=0,cpl=0;
		HashMap<String,Integer> leaves= new HashMap<String,Integer>();
		String query1="Select * from leavetypes;";
		try(PreparedStatement ps = conn.prepareStatement(query1))
		{
			ResultSet rs1=ps.executeQuery();
			while(rs1.next()) {
			leaves.put(rs1.getString("slno"), rs1.getInt("leave_limit"));
			}
			/* for (Integer i : leaves.keySet()) {
			      System.out.println("key: " + i + " value: " + leaves.get(i));
			    }*/
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try(PreparedStatement ps = conn.prepareStatement(query))
		{
			
		ResultSet rs=ps.executeQuery();
		
		while(rs.next()) {
			int tempnod=rs.getInt("nod");
			String temptype=rs.getString("type");
			leaves.replace(temptype, leaves.get(temptype)-tempnod);
			/*switch(temptype) {
			case "1":
				
				cal+=tempnod;
			break;
			case 2:ccl+=tempnod;
			break;
			case 3:csl+=tempnod;
			break;
			case 4:cml+=tempnod;
			break;
			case 5:cpl+=tempnod;
			break;
			}*/	
		}
		System.out.println("ccl"+ccl);
		System.out.println("cal"+cal);
		System.out.println("csl"+csl);
		
	/*	for (Integer i : leaves.keySet()) {
		      System.out.println("key: " + i + " value: " + leaves.get(i));
		    }*/
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return leaves;
	}
}
