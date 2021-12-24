package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import connect.Connect;

public class EmployeeModel {
	
	private Connect con = Connect.getInstance();
	private int id, salary;
	private String role, name, username, status, password;

	public EmployeeModel() {
		
	}
	
	public EmployeeModel(int id, int salary, String role, String name, String username, String status) {
		super();
		this.id = id;
		this.salary = salary;
		this.role = role;
		this.name = name;
		this.username = username;
		this.status = status;
	}

	public Connect getCon() {
		return con;
	}

	public void setCon(Connect con) {
		this.con = con;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
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
	
	private EmployeeModel map(ResultSet rs) {
		try {
			int id = rs.getInt("id");
			String role = rs.getString("role_id");
			String name = rs.getString("name");
			String username = rs.getString("username");
			String status = rs.getString("status");
			int salary = rs.getInt("salary");
			
			return new EmployeeModel(id, salary, role, name, username, status);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Vector<EmployeeModel> getAllEmployee() {

		String query = "SELECT * FROM employee";
		ResultSet rs = con.executeQuery(query);
		
		Vector<EmployeeModel> employees = new Vector<>();
		
		try {
			while(rs.next()) {
				EmployeeModel employee = map(rs);
				employees.add(employee);
			}

			return employees;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void insertEmployee(Integer role, String name, String username, Integer salary) {
		
		String query = "INSERT INTO employee VALUES (NULL, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = con.preparedStatement(query);
		
		try {
			ps.setInt(1, role);
			ps.setString(2, name);
			ps.setString(3, username);
			ps.setInt(4, salary);
			ps.setString(5, "Active");
			ps.setString(6, username);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void fireEmployee(int id) {
		String query = "UPDATE employee SET status = ? WHERE id = ?";
		PreparedStatement ps = con.preparedStatement(query);
		
		try {
			ps.setString(1, "Not Active");
			ps.setInt(2, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
