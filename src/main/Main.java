package main;

import controller.EmployeeController;

public class Main {
	
//	public static EmployeeModel employee = null;

	public Main() {
//		new LoginPage();
		EmployeeController.getInstance().viewLoginPage();
	}

	public static void main(String[] args) {
		new Main();
	}

}
