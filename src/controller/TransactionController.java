package controller;

import java.sql.Date;
import java.util.Vector;

import models.CartItemModel;
import models.TransactionItemModel;
import models.TransactionModel;
import view.manager.TransactionReportPage;
import view.transactionManagement.TodayTransactionHistoryPage;

public class TransactionController {
	
	private static TransactionController instance;
	private TodayTransactionHistoryPage todayTransactionHistoryPage;
	private TransactionReportPage transactionReportPage;
	
	public TransactionController() {
		// TODO Auto-generated constructor stub
	}
	
	public static TransactionController getInstance() {
		if(instance == null) {
			instance = new TransactionController();
		}
		return instance;
	}
	
	public void viewTodayTransactionHistoryPage() {
		todayTransactionHistoryPage = new TodayTransactionHistoryPage();
	}
	
	public void viewTransactionReportPage() {
		transactionReportPage = new TransactionReportPage();
	}
	
	public void addTransaction(Date purchaseDate, Integer employeeId, String paymentType) {
		TransactionModel transactionModel = new TransactionModel();
		int transactionId = transactionModel.addTransaction(purchaseDate, employeeId, paymentType);

		Vector<CartItemModel> cartItems = CartController.getInstance().getCartList();
		for (CartItemModel cartItemModel : cartItems) {
			ProductController.getInstance().reduceProductStock(cartItemModel.getProductID(), cartItemModel.getQuantity());
			addTransactionItem(transactionId, cartItemModel.getProductID(), cartItemModel.getQuantity());
		}
		CartController.getInstance().clearCartItemList();
		viewTodayTransactionHistoryPage();
	}
	
	public void addTransactionItem(Integer transactionId, Integer productId, Integer quantity) {
		TransactionItemModel transactionItem = new TransactionItemModel();
		transactionItem.addTransactionItem(transactionId, productId, quantity);
	}
	
	public Vector<TransactionModel> getAllTransaction(String date){
			TransactionModel transactionModel = new TransactionModel();
			return transactionModel.getTransactionReport(date);
	}
	
	public Vector<TransactionModel> getTodayTransaction() {
		TransactionModel transactionModel = new TransactionModel();
		return transactionModel.getTodayTransaction();

	}
	
	public Vector<TransactionItemModel> getAllTransactionItem(String id) {
		
		int idTemp = Integer.parseInt(id);
		TransactionItemModel transactionItemModel = new TransactionItemModel();
		return transactionItemModel.getTransactionReport(idTemp);
	}
	


}
