package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import models.EmployeeModel;

import view.LoginPage;
import view.ManageProductPage;


public class EmployeeController {
	
	private static EmployeeController instance;
	private LoginPage loginPage;

	public EmployeeController() {
		// TODO Auto-generated constructor stub
	}
	
	public static EmployeeController getInstance() {
		if(instance == null) {
			instance = new EmployeeController();
		}
		return instance;
	}
	
	public void viewLoginPage() {
		loginPage = new LoginPage();
	}
	
	public void login(String username, String password) {
		if(username.equals("") || password.equals("")) {
			loginPage.showMessage("All field must not be empty!");
			loginPage.getFrame().dispose();
			viewLoginPage();
			return;
		}
		else {
			EmployeeModel employeeModel = new EmployeeModel();
			ResultSet rs = employeeModel.getEmployee(username, password);
			try {
				if(rs.next()) {
//				Main.employee = new EmployeeModel(rs.getInt("id"), rs.getInt("role_id"), rs.getString("name"), rs.getString("username"), rs.getInt("salary"), rs.getString("status"), rs.getString("password"));
					if(rs.getInt("role_id") == 1){
						
					}
					else if(rs.getInt("role_id") == 2){
						ProductController.getInstance().viewManageProductPage();
						loginPage.getFrame().dispose();
					}
					else if(rs.getInt("role_id") == 3){

					}
					else if(rs.getInt("role_id") == 4){

					}
				}
				else {
					loginPage.showMessage("Invalid Username or Password!");
					loginPage.getFrame().dispose();
					viewLoginPage();
					return;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
