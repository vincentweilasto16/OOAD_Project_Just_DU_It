package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import connect.Connect;

public class Role {
	
	private int id;
	private String name;
	private Connect con = Connect.getInstance();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Role() {
		
	}
	
	public Vector<Role> getAllRole(){
		Vector<Role> role = new Vector<>();
		
		String query = "SELECT * FROM role";
		ResultSet res = con.executeQuery(query);
		
		try {
			while(res.next()) {
				role.add(new Role(res.getInt("id"), res.getString("name")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return role;
	}
	

}
