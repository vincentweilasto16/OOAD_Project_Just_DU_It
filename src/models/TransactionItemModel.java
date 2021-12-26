package models;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import connect.Connect;

public class TransactionItemModel {
	
	private Connect con = Connect.getInstance();

	public TransactionItemModel() {
		// TODO Auto-generated constructor stub
	}
	
	public void addTransactionItem(Integer transactionId, Integer productId, Integer quantity) {
		
		String query = "INSERT INTO transactionitem VALUES (?, ?, ?)";
		PreparedStatement ps = con.preparedStatement(query);
		
		try {
			ps.setInt(1, transactionId);
			ps.setInt(2, productId);
			ps.setInt(3, quantity);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
