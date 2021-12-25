package models;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import connect.Connect;

public class TransactionModel {
	
	private Connect con = Connect.getInstance();

	public TransactionModel() {
		// TODO Auto-generated constructor stub
	}
	
	public void addTransaction(String purchaseDate, Integer employeeId, String paymentType) {
		
		String query = "INSERT INTO transaction VALUES (NULL, ?, ?, ?)";
		PreparedStatement ps = con.preparedStatement(query);
		
		try {
			ps.setString(1, purchaseDate);
			ps.setInt(2, employeeId);
			ps.setString(3, paymentType);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
