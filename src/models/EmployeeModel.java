package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import connect.Connect;

public class EmployeeModel {
	
	private Connect con = Connect.getInstance();
	
	public ResultSet getEmployee(String username, String password) {
		String query = "SELECT * FROM employee WHERE username = ? AND password = ?";
		PreparedStatement ps = con.preparedStatement(query);
		ResultSet rs = null;
		
		try {
			ps.setString(1, username);
			ps.setString(2, password);
			ps.execute();
			
			rs = ps.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	
}
