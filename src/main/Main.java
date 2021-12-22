package main;

import models.Employee;
import view.CashierPage;
import view.LoginPage;
import view.ProductManagementPage;

public class Main {
	
	public static Employee employee = null;

	public Main() {
		new LoginPage();
	}

	public static void main(String[] args) {
		new Main();
	}

}
