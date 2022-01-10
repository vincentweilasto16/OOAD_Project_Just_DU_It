package controller;

import java.util.Vector;

import models.CartItemModel;
import models.ProductModel;
import view.transactionManagement.AddToCartPage;
import view.transactionManagement.ManageCartPage;

public class CartController {
	
	private static CartController instance;
	private AddToCartPage addToCartPage;
	private ManageCartPage manageCartPage;
	private Vector<CartItemModel> cart = new Vector<>();
	private Vector<Integer> priceList = new Vector<>();
	private int totalPrice = 0;
	private int priceTemp = 0;

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
	
	public void addToCart(int selectedIndex, String id, String quantity, String price, String stock) {
		
		
		if(selectedIndex == -1) {
			addToCartPage.showMessage("Nothing to insert!");
		}
		else {
			totalPrice = 0;
			priceTemp = 0;
			int count = 0;
			int quantityTemp = 0;
			int productId = Integer.parseInt(id);
			priceTemp = Integer.parseInt(price);
			priceList.add(priceTemp);
			int stockTemp = Integer.parseInt(stock);
			CartItemModel cartItem = new CartItemModel();
			
			for (CartItemModel cartItemModel : cart) {
				if(cartItemModel.getProduct().getId() == productId) {
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
				ProductModel product = new ProductModel();
				product = ProductController.getInstance().getProduct(productId);
				CartItemModel cartItemModel = new CartItemModel(productId, quantityTemp, product.getPrice(), product.getStock(), product.getName(), product.getDescription());
				cart.add(cartItemModel);
			}
			else if(count == 3) {
				cartItem.setQuantity(temp);
			}
			
			int i = 0;
			for (CartItemModel cartItemModel : cart) {
				totalPrice += cartItemModel.getQuantity() * priceList.elementAt(i);
				i++;
			}
			
			addToCartPage.showMessage("Add Product to Cart Successfully!");
			addToCartPage.getFrame().dispose();
			viewManageCartPage();
			
			
		}
	}
	
	public int calculateTotalPrice() {
		if(cart.isEmpty()) {
			return 0;
		}
		else {
			return totalPrice;
		}
	}

	
	public void deleteItem(String id, int selectedIndex) {
		
		if(selectedIndex == -1) {
			manageCartPage.showMessage("Nothing to Remove From Cart!");
		}
		else{
			int productId = Integer.parseInt(id);
			
			for (int i = 0; i < cart.size(); i++) {
				if(cart.get(i).getProduct().getId() == productId) {
					totalPrice -= cart.get(i).getQuantity() * priceList.elementAt(i);
					cart.remove(i);
					priceList.remove(i);
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
		priceList.clear();
		manageCartPage.getFrame().dispose();
	}
	
	
	
	

}
