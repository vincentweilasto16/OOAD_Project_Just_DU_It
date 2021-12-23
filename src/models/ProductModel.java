package models;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import connect.Connect;

public class ProductModel {
	
	private Connect con = Connect.getInstance();
	
//	public DefaultTableModel getAllProduct() {
//		
//		Connect con = Connect.getInstance(); 
//		String query = "SELECT * FROM product";
//		ResultSet rs = con.executeQuery(query);
//		DefaultTableModel dtm = null;
//		try {
//			while(rs.next()) {
//				dtm.addRow(new Object[] {
//						rs.getInt("id"),
//						rs.getString("name"),
//						rs.getString("description"),
//						rs.getInt("price"),
//						rs.getInt("stock")
//				});
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return dtm;
//	}
	
	public void insertProduct(String name, String description, Integer price, Integer stock) {
		
		String query = "INSERT INTO product VALUES (NULL, ?, ?, ?, ?)";
		PreparedStatement ps = con.preparedStatement(query);
		
		try {
			ps.setString(1, name);
			ps.setString(2, description);
			ps.setInt(3, price);
			ps.setInt(4, stock);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateProduct(Integer id, String name, String description, Integer price, Integer stock) {
		
		String query = "UPDATE product SET name = ?, description = ?, price = ?, stock = ? WHERE id = ?";
		PreparedStatement ps = con.preparedStatement(query);
		
		try {
			ps.setString(1, name);
			ps.setString(2, description);
			ps.setInt(3, price);
			ps.setInt(4, stock);
			ps.setInt(5, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void deleteProduct(Integer id) {
		
		String query = "DELETE FROM product WHERE id = ?";
		PreparedStatement ps = con.preparedStatement(query);
		
		try {
			ps.setInt(1, id);		
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
