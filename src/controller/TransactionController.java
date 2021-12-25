package controller;

import java.util.Date;
import models.TransactionModel;

public class TransactionController {
	
	private static TransactionController instance;

	public TransactionController() {
		// TODO Auto-generated constructor stub
	}
	
	public static TransactionController getInstance() {
		if(instance == null) {
			instance = new TransactionController();
		}
		return instance;
	}
	
	public void addTransaction(String purchaseDate, Integer employeeId, String paymentType) {
		TransactionModel transactionModel = new TransactionModel();
		transactionModel.addTransaction(purchaseDate, employeeId, paymentType);
//		addEmployeePage.showMessage("Add new Employee Successfully!");
//		addEmployeePage.getFrame().dispose();
//		viewManageEmployeePage();
	}
	


}
