package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import connect.Connect;

public class ProductModel {
	
	private Connect con = Connect.getInstance();
	private Integer id, price, stock;
	private String name, description;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public ProductModel() {
		
	}
	
	public ProductModel(Integer id, Integer price, Integer stock, String name, String description) {
			super();
			this.id = id;
			this.price = price;
			this.stock = stock;
			this.name = name;
			this.description = description;
	}
	
	private ProductModel map(ResultSet rs) {
		try {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String description = rs.getString("description");
			int price = rs.getInt("price");
			int stock = rs.getInt("stock");
			
			return new ProductModel(id, price, stock, name, description);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Vector<ProductModel> getAllProduct() {

		String query = "SELECT * FROM product";
		ResultSet rs = con.executeQuery(query);
		
		Vector<ProductModel> products = new Vector<>();
		
		try {
			while(rs.next()) {
				ProductModel product = map(rs);
				products.add(product);
			}
//			for (ProductModel p : products) {
//				System.out.println(p.getName());
//			}
			return products;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	
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
	
	public void deleteProduct(int id) {
		
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
