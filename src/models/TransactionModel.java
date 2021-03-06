package models;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import connect.Connect;

public class TransactionModel {
	

	private Connect con = Connect.getInstance();
	private int id;
	private Date purchaseDate;
	private String paymentType;
	private EmployeeModel employee;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
	public EmployeeModel getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeModel employee) {
		this.employee = employee;
	}

	public TransactionModel(int id, int employeeId, int salary, int roleId, String roleName, String name, String username, String status,  Date purchaseDate, String paymentType) {
		super();
		this.id = id;
//		this.employeeId = employeeId;
		this.purchaseDate = purchaseDate;
		this.paymentType = paymentType;
		this.employee = new EmployeeModel(employeeId, salary, roleId, roleName, name, username, status);
	}

	public TransactionModel() {
		// TODO Auto-generated constructor stub
	}
	
	public int addTransaction(Date purchaseDate, Integer employeeId, String paymentType) {
		
		String query = "INSERT INTO transaction VALUES (NULL, ?, ?, ?)";
		PreparedStatement ps = con.preparedStatement(query);
		ResultSet rs = null;
		int transactionId = 0;
		
		try {
			ps.setDate(1, purchaseDate);
			ps.setInt(2, employeeId);
			ps.setString(3, paymentType);
			ps.execute();
			
			rs = ps.getGeneratedKeys();
			if(rs.next()) {
				transactionId = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return transactionId;
		
	}
	
	private TransactionModel map(ResultSet rs) {
		try {
			int id = rs.getInt("id");
			Date purchaseDate = rs.getDate("purchase_date");
			int employeeId = rs.getInt("employee_id");
			String paymentType = rs.getString("payment_type");
			int role = rs.getInt("e.role_id");
			String name = rs.getString("e.name");
			String username = rs.getString("username");
			String status = rs.getString("status");
			int salary = rs.getInt("salary");
			String roleName = rs.getString("r.name");
			
			return new TransactionModel(id, employeeId, salary, role, roleName, name, username, status, purchaseDate, paymentType);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Vector<TransactionModel> getTodayTransaction() {
		
		long millis = System.currentTimeMillis();  
		Date dateNow = new Date(millis);

		String query = "SELECT * FROM transaction t JOIN employee e ON t.employee_id = e.id JOIN role r ON e.role_id = r.id WHERE purchase_date = ?";
		PreparedStatement ps = con.preparedStatement(query);
		ResultSet rs = null;
		
		Vector<TransactionModel> transactions = new Vector<>();

		
		try {
			ps.setDate(1, dateNow);
			ps.execute();
			
			rs = ps.getResultSet();
			
			while(rs.next()) {
				TransactionModel transaction = map(rs);
				transactions.add(transaction);
			}

			return transactions;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Vector<TransactionModel> getTransactionReport(String date) {
		
		String[] dateTemp = date.split("/", 2);
	
		int month = Integer.parseInt(dateTemp[0]);
		int year = Integer.parseInt(dateTemp[1]);

		String query = "SELECT * FROM transaction t JOIN employee e ON t.employee_id = e.id JOIN role r ON e.role_id = r.id WHERE MONTH(purchase_date) = ? AND YEAR(purchase_date) = ?";
		PreparedStatement ps = con.preparedStatement(query);
		ResultSet rs = null;
		
		Vector<TransactionModel> transactions = new Vector<>();

		
		try {
			ps.setInt(1, month);
			ps.setInt(2, year);
			ps.execute();
			
			rs = ps.getResultSet();
			
			while(rs.next()) {
				TransactionModel transaction = map(rs);
				transactions.add(transaction);
			}

			return transactions;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	



}
