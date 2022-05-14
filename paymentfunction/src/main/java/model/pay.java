package model;
import java.sql.*; 

public class pay {
	
	private Connection connect() 
	 { 
	 Connection con = null; 
	 try
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 
	 //DB connection  
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_project","root",""); 
	 } 
	 catch (Exception e) 
	 {e.printStackTrace();} 
	 return con; 
	 } 
	 public String insertItem(String userID, String Amount, String paymenttype,String Date) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for inserting."; } 
	 // create a prepared statement
	 String query = " insert into payment(`paymentID`,`userID`,`Amount`,`paymenttype`,`Date`)"
	 + " values (?, ?, ?, ?, ?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, userID); 
	 preparedStmt.setString(3, Amount); 
	 preparedStmt.setString(4, paymenttype); 
	 preparedStmt.setString(5, Date);
	 preparedStmt.execute(); 
	 con.close();
	 
	 String newItems = readItems(); 
	 output = "{\"status\":\"success\", \"data\": \"" + 
	 newItems + "\"}"; 
	 

	 
	 
	 } 
	 catch (Exception e) 
	 
		 { 
		 output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 

	
	 return output; 
	 } 
	public String readItems() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; 
	 } 
	 // Prepare the  table to be displayed
	 output = "<table border='1'><tr><th>User ID</th><th>Amount</th>" +
	 "<th>Payment Type</th>" + 
	 "<th>Date</th>" +
	 "<th>Update</th><th>Remove</th></tr>"; 
	 
	 String query = "select * from payment"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String paymentID = Integer.toString(rs.getInt("paymentID")); 
	 String userID = rs.getString("userID"); 
	 String Amount = rs.getString("Amount"); 
	 String paymenttype =rs.getString("paymenttype"); 
	 String Date = rs.getString("Date"); 
	 
	 output += "<tr><td><input id='hidpaymentIDUpdate' name='hidpaymentIDUpdate' type='hidden' value='" + paymentID + "'>"
			 + userID + "</td>"; 
			 output += "<td>" + Amount + "</td>"; 
			 output += "<td>" + paymenttype + "</td>"; 
			 output += "<td>" + Date + "</td>"; 
			 // buttons
	 output += "<td><input name='.btnUpdate'type='button' value='Update'class='btnUpdate btn btn-warning' data-paymentid='"+paymentID+"'>"
			+ "</td><td> <input name='btnRemove' type='button' value='Remove' class='btn btn-danger'data-paymentid='"+paymentID+"'></td></tr>"; 
			 } 
	 con.close(); 
	 // Complete the 
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the items."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	public String updateItem(String paymentID, String userID, String Amount, String paymenttype, String Date)
	{ 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 // create a prepared statement
		 String query = "UPDATE payment SET userID=?,Amount=?,paymenttype=?,Date=? WHERE paymentID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, userID); 
		 preparedStmt.setString(2, Amount); 
		 preparedStmt.setString(3, paymenttype); 
		 preparedStmt.setString(4, Date); 
		 preparedStmt.setInt(5, Integer.parseInt(paymentID)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newItems = readItems(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newItems + "\"}"; 

		 } 
		 catch (Exception e) 
		 { 
			 output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}"; 
					 System.err.println(e.getMessage()); 

		 } 
		 return output; 
		 } 
		public String deleteItem(String paymentID) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for deleting."; } 
		 // create a prepared statement
		 String query = "delete from payment where paymentID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(paymentID)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newItems = readItems(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newItems + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}"; 
  
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 }
		
		 
}
