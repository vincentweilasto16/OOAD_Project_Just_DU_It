package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import models.EmployeeModel;
import models.ProductModel;
import view.LoginPage;
import view.humanResource.AddEmployeePage;
import view.humanResource.ManageEmployeePage;
import view.productManagement.AddProductPage;
import view.productManagement.ManageProductPage;

public class EmployeeController {
	
	private static EmployeeController instance;
	private LoginPage loginPage;
	private ManageEmployeePage manageEmployeePage;
	private AddEmployeePage addEmployeePage;

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
	
	public void viewManageEmployeePage() {
		manageEmployeePage = new ManageEmployeePage();
	}
	
	public void viewAddEmployeePage() {
		addEmployeePage = new AddEmployeePage();
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
					}
					else if(rs.getInt("role_id") == 3){
						viewManageEmployeePage();
					}
					else if(rs.getInt("role_id") == 4){

					}
					loginPage.getFrame().dispose();
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
	
	public Vector<EmployeeModel> getAllEmployee() {
		EmployeeModel employee = new EmployeeModel();
		return employee.getAllEmployee();

	}
	
	public void addEmployee(String role, String name, String username, String salary) {
		int count = 0;
		int roleId = 0;
		int salaryTemp;
		
		if(role.equals("")) {
			addEmployeePage.showMessage("Role must not empty");
			return;
		}
		else {
			count++;
			 try {
			        roleId = Integer.parseInt(role);
			        count++;
	
			    } catch (Exception e) {
			    	addEmployeePage.showMessage("role must be numeric");
			    	return;
			    }
			 
				if(roleId < 1 || roleId > 4) {
					addEmployeePage.showMessage("role not exists, Please input between 1 and 4!");
					return;
				}
				else {
					count++;
				}
		}
		
		if(name.equals("")) {
			addEmployeePage.showMessage("Name must not empty");
			return;
		}
		else {
			count++;
		}
		
		if(username.equals("")) {
			addEmployeePage.showMessage("Username must not empty");
			return;
		}
		else {
			count++;
		}
		
		if(salary.equals("")) {
			addEmployeePage.showMessage("Salary must not empty");
			return;
		}
		else {
			count++;
			 try {
			        salaryTemp = Integer.parseInt(salary);
			        count++;
					
			    } catch (Exception e) {
			    	addEmployeePage.showMessage("Salary must be numeric");
			    	return;
			    }
			 
				if(salaryTemp <= 0) {
					addEmployeePage.showMessage("Salary must above zero");
					return;
				}
				else {
					count++;
				}
		}
		
		if(count == 8) {
			EmployeeModel employeeModel = new EmployeeModel();
			employeeModel.insertEmployee(roleId, name, username, salaryTemp);
			addEmployeePage.showMessage("Add Employee Successfully!");
			addEmployeePage.getFrame().dispose();
			viewManageEmployeePage();
		}
	}
	
	public void fireEmployee(int selectedIndex, String id, String status) {
		if(selectedIndex == -1) {
			manageEmployeePage.showManageProductPageMessage("Nothing Fired!");
		}
		else {
			if(status.equals("Not Active")) {
				manageEmployeePage.showManageProductPageMessage("This employee has been fired!");
			}
			else {
				int employeeId = Integer.parseInt(id);			
				EmployeeModel employeeModel = new EmployeeModel();
				employeeModel.fireEmployee(employeeId);
				manageEmployeePage.showManageProductPageMessage("Successfully fired employee!");
				manageEmployeePage.getFrame().dispose();
				viewManageEmployeePage();				
			}
		}
	}
	
	public int updateEmployee(int selectedIndex, String id, String name, String salary, String password) {
		
		if(selectedIndex == -1) {
			manageEmployeePage.showManageProductPageMessage("Nothing updated!");
		}
		else {
			int count = 0;
			Integer salaryTemp = 0;
			int employeeId = Integer.parseInt(id);
			
			if(name.equals("")) {
				manageEmployeePage.showManageProductPageMessage("Name must not empty");
			}
			else {
				count++;
			}
			
			if(salary.equals("")) {
				manageEmployeePage.showManageProductPageMessage("Salary must not empty");
			}
			else {
				count++;
				 try {
				        salaryTemp = Integer.parseInt(salary);
				        count++;
						if(salaryTemp <= 0) {
							manageEmployeePage.showManageProductPageMessage("Salary must above zero");
						}
						else {
							count++;
						}
				    } catch (Exception e) {
				    	manageEmployeePage.showManageProductPageMessage("Salary must be numeric");
				    }
			}
			
			if(password.equals("")) {
				manageEmployeePage.showManageProductPageMessage("Password must not empty");
			}
			else {
				count++;
			}
			
			if(count == 5) {
				EmployeeModel employeeModel = new EmployeeModel();
				employeeModel.updateEmployee(employeeId, name, salaryTemp, password);
				manageEmployeePage.showManageProductPageMessage("Employee Has Been Updated Successfully!");
				manageEmployeePage.getFrame().dispose();
				viewManageEmployeePage();
				return -1;
			}
		}
		
		return selectedIndex;
		
		
	}
}
