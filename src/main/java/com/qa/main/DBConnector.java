package com.qa.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.qa.utils.DatabaseConfiguration;

public class DBConnector {

	private Connection conn; 
	private Statement stmt; 
	
	public DBConnector() throws SQLException {
		// Opening a connection to the specified URL with specific credentials
		conn = DriverManager.getConnection(DatabaseConfiguration.URL, DatabaseConfiguration.USER, DatabaseConfiguration.PASSWORD); 
		this.stmt = conn.createStatement();
	}
	 
	// CREATE
	public void createPerson(String first, String last) throws SQLException {
		//String sql = "INSERT INTO Customer (`firstname`,`surname`) VALUES" + "('" + first + "','" + last + "');";
		String sql = String.format("INSERT INTO Customer(`firstname`,`surname`) VALUES ('%s', '%s');", first,last);
		stmt.executeUpdate(sql);
	}
	
	// READ
	public void readAllPeople() throws SQLException {
		String sql = "SELECT * FROM Customer;"; 
		ResultSet rs = stmt.executeQuery(sql);
			
		while(rs.next()) {
			//System.out.println(rs.getString("firstname") + " " + rs.getString("surname"));
			System.out.println(String.format("ID = '%d', Fname: '%s', LName: '%s'",rs.getInt("custID"), rs.getString("firstname"), rs.getString("surname") ));
		}
	}
	
	// READ ONE
	public void readSingle(int id) {
		String sql = "SELECT * FROM Customer WHERE custID=" + id; 
	}
	
	// UPDATE
	public void updatePerson(int id, String fname, String lname) throws SQLException { 
		//UPDATE Customer SET `FIRSTNAME`= "SOMETHING", `LASTNAME`="SOMETHING" WHERE id=2;
		String sql = String.format("UPDATE customer SET `firstname`= '%s', `surname`='%s' WHERE custID='%d';", fname, lname, id);
		stmt.executeUpdate(sql);
	}

	// DELETE
	public void deletePerson(int delid) throws SQLException {
		String sql = String.format("DELETE FROM Customer where custID = '%d';", delid);
		stmt.executeUpdate(sql);
	}
	
	
	public void close() throws SQLException { 
		conn.close(); 
	}
	
}
