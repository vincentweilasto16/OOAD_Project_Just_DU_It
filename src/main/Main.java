package main;

import controller.EmployeeController;
import models.EmployeeModel;


public class Main {
	
	public static EmployeeModel employee = null;

	public Main() {
//		new LoginPage();
		EmployeeController.getInstance().viewLoginPage();
	}

	public static void main(String[] args) {
		new Main();
	}

}
