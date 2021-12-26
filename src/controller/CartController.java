package controller;

import java.util.Vector;

import models.CartItemModel;
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
	
	public void viewManageCartPage(String totalPrice) {
		manageCartPage = new ManageCartPage();
		manageCartPage.setTotalPrice(totalPrice);
	}
	
	
	public static CartController getInstance() {
		if(instance == null) {
			instance = new CartController();
		}
		return instance;
	}
	
	public void addToCart(int selectedIndex, String id, String quantity, String price, String stock) {
		
		int totalPrice = 0;
		if(selectedIndex == -1) {
			addToCartPage.showMessage("Nothing to insert!");
		}
		else {

			int count = 0;
			int quantityTemp = 0;
			int productId = Integer.parseInt(id);
			int priceTemp = Integer.parseInt(price);
			int stockTemp = Integer.parseInt(stock);
			CartItemModel cartItem = new CartItemModel();
			
			for (CartItemModel cartItemModel : cart) {
				if(cartItemModel.getProductID() == productId) {
					cartItem = cartItemModel;
					count++;
					break;
				}
			}
			
			int temp = 0;
			
			if(quantity.equals("") || quantity.isEmpty()) {
				addToCartPage.showMessage("Quantity must not empty!");
			}
			else {
				try {
					quantityTemp = Integer.parseInt(quantity);
					count++;	

					
				} catch (Exception e) {
					addToCartPage.showMessage("Quantity must be numeric!");
				}
				
				temp = quantityTemp + cartItem.getQuantity();
				if(quantityTemp <= 0 || temp > stockTemp) {
					addToCartPage.showMessage("Quantity must above zero and must below stock!");
					count--;
				}
				else {
					count++;
				}
				
			}
			
			if(count == 2) {
				CartItemModel cartItemModel = new CartItemModel(productId, quantityTemp);
				cart.add(cartItemModel);
				addToCartPage.showMessage("Add Product to Cart Successfully!");
			}
			else if(count == 3) {
				cartItem.setQuantity(temp);
				addToCartPage.showMessage("Add Product to Cart Successfully!");
			}
			
			for (CartItemModel cartItemModel : cart) {
				totalPrice += cartItemModel.getQuantity() * priceTemp;
			}
			
					
			addToCartPage.getFrame().dispose();
//			manageCartPage = new ManageCartPage();
//			manageCartPage.setTotalPrice(Integer.toString(totalPrice));
			viewManageCartPage(Integer.toString(totalPrice));
		}
	}
	
//	public String calculateTotalPrice(int temp, int priceTemp) {
//		int totalPrice = 0;
//		totalPrice += temp * priceTemp;
//		
//		return Integer.toString(totalPrice);
//	}
	
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
		manageCartPage.getFrame().dispose();
	}
	
	
	
	

}
