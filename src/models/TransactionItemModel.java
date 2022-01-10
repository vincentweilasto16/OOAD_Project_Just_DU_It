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
	private ProductModel product;
	private Connect con = Connect.getInstance();

	public TransactionItemModel() {
		// TODO Auto-generated constructor stub
	}
	
	public TransactionItemModel(int transactionId, int productId, int quantity, int price, int stock, String name, String description) {
		super();
		this.transactionId = transactionId;
		this.productId = productId;
		this.quantity = quantity;
		this.product = new ProductModel(productId, price, stock, name, description);
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
	public ProductModel getProduct() {
		return product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
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
			String name = rs.getString("name");
			int price = rs.getInt("price");
			int stock = rs.getInt("stock");
			String description = rs.getString("description");
			
			return new TransactionItemModel(transactionId, productId, quantity, price, stock, name, description);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Vector<TransactionItemModel> getTransactionReport(int id) {
		
		String query = "SELECT * FROM transactionitem ti JOIN product p ON ti.product_id = p.id WHERE transaction_id = ?";
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
