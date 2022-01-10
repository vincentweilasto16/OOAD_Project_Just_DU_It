package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import connect.Connect;

public class EmployeeModel {
	
	private Connect con = Connect.getInstance();
	private int id, salary;
	private String name, username, status, password;
	private Role roleModel;

	public EmployeeModel() {
		
	}
	
	public EmployeeModel(int id, int salary, int roleId, String roleName, String name, String username, String status) {
		super();
		this.id = id;
		this.salary = salary;
		this.name = name;
		this.username = username;
		this.status = status;
		this.roleModel = new Role(roleId, roleName);
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
	
	public EmployeeModel getEmployee(String username, String password) {
		String query = "SELECT * FROM employee e JOIN role r ON e.role_id = r.id WHERE username = ? AND password = ?";
		PreparedStatement ps = con.preparedStatement(query);
		ResultSet rs = null;
		
		try {
			ps.setString(1, username);
			ps.setString(2, password);
			ps.execute();
			
			rs = ps.getResultSet();
			
			if(rs.next()) {
				EmployeeModel employee = map(rs);
				return employee;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private EmployeeModel map(ResultSet rs) {
		try {
			int id = rs.getInt("id");
			int role = rs.getInt("role_id");
			String name = rs.getString("e.name");
			String username = rs.getString("username");
			String status = rs.getString("status");
			int salary = rs.getInt("salary");
			String roleName = rs.getString("r.name");
			
			return new EmployeeModel(id, salary, role, roleName, name, username, status);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Vector<EmployeeModel> getAllEmployee() {

		String query = "SELECT * FROM employee e JOIN role r ON e.role_id = r.id";
		ResultSet rs = con.executeQuery(query);
		
		Vector<EmployeeModel> employees = new Vector<>();
		
		try {
			while(rs.next()) {
				employees.add(map(rs));	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return employees;
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
	
	public void updateEmployee(int employeeId, String name, int salary, String password) {
		String query = "UPDATE employee SET name = ?, salary = ?, password = ? WHERE id = ?";
		PreparedStatement ps = con.preparedStatement(query);
		
		try {
			ps.setString(1, name);
			ps.setInt(2, salary);
			ps.setString(3, password);
			ps.setInt(4, employeeId);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Role getRoleModel() {
		return roleModel;
	}

	public void setRoleModel(Role roleModel) {
		this.roleModel = roleModel;
	}
	
	
}
