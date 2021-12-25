package controller;

import java.util.Vector;

import models.CartItemModel;
import models.EmployeeModel;
import models.ProductModel;
import view.transactionManagement.AddToCartPage;
import view.transactionManagement.ManageCartPage;

public class CartController {
	
	private static CartController instance;
	private AddToCartPage addToCartPage;
	private ManageCartPage manageCartPage;
	private Vector<CartItemModel> cart = new Vector<>();

	public Vector<CartItemModel> getCartList() {
		return cart;
	}

	public CartController() {
		// TODO Auto-generated constructor stub
	}
	
	public void viewAddToCartPage() {
		addToCartPage = new AddToCartPage();
	}
	
	public void viewManageCartPage() {
		manageCartPage = new ManageCartPage();
	}
	
	
	public static CartController getInstance() {
		if(instance == null) {
			instance = new CartController();
		}
		return instance;
	}
	
	public void addToCart(int selectedIndex, String id, String quantity) {
		
		if(selectedIndex == -1) {
			addToCartPage.showMessage("Nothing to insert!");
		}
		else {
			int count = 0;
			int quantityTemp = 0;
			int productId = Integer.parseInt(id);
			
			if(quantity.equals("")) {
				addToCartPage.showMessage("Quantity must not empty!");
			}
			else {
				try {
					quantityTemp = Integer.parseInt(quantity);
					count++;
					
					if(quantityTemp <= 0) {
						addToCartPage.showMessage("Quantity must above zero!");
					}
					else {
						count++;
					}
					
				} catch (Exception e) {
					addToCartPage.showMessage("Quantity must not empty!");
				}
			}
			
			if(count == 2) {
				CartItemModel cartItemModel = new CartItemModel(productId, quantityTemp);
				cart.add(cartItemModel);
				addToCartPage.showMessage("Add Product to Cart Successfully!");
				addToCartPage.getFrame().dispose();
				viewAddToCartPage();
			}
		}
		
	}
	
	public void deleteItem(String id, int selectedIndex) {
		
		if(selectedIndex == -1) {
			manageCartPage.showMessage("Nothing to Remove From Cart!");
		}
		else{
			int productId = Integer.parseInt(id);
		
			for (int i = 0; i < cart.size(); i++) {
				if(cart.get(i).getProductID() == productId) {
					cart.remove(i);
					break;
				}
			}
			
			manageCartPage.showMessage("Remove Product From Cart Successfully!");
			manageCartPage.getFrame().dispose();
			viewManageCartPage();
		}
		
	}
	
	public void clearCartItemList() {
		cart.clear();
		manageCartPage.showMessage("Successfull Clear Cart Item List!");
		viewManageCartPage();
	}
	
	
	
	

}
