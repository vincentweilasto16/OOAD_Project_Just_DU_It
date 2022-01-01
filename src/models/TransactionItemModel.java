package models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import connect.Connect;

public class TransactionItemModel {
	
	private int transactionId;
	private int productId;
	private int quantity;
	private Connect con = Connect.getInstance();

	public TransactionItemModel() {
		// TODO Auto-generated constructor stub
	}
	
	public TransactionItemModel(int transactionId, int productId, int quantity) {
		super();
		this.transactionId = transactionId;
		this.productId = productId;
		this.quantity = quantity;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
	
	
	private TransactionItemModel map(ResultSet rs) {
		try {
			int transactionId = rs.getInt("transaction_id");
			int productId = rs.getInt("product_id");
			int quantity = rs.getInt("quantity");
			
			return new TransactionItemModel(transactionId, productId, quantity);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Vector<TransactionItemModel> getTransactionReport(int id) {
		
		String query = "SELECT * FROM transactionitem WHERE transaction_id = ?";
		PreparedStatement ps = con.preparedStatement(query);
		ResultSet rs = null;
		
		Vector<TransactionItemModel> transactions = new Vector<>();

		
		try {
			ps.setInt(1, id);
			ps.execute();
			rs = ps.getResultSet();
			
			while(rs.next()) {
				TransactionItemModel transaction = map(rs);
				transactions.add(transaction);
			}

			return transactions;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
