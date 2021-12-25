package models;

public class CartItemModel {
	private int productID, quantity;

	public CartItemModel(int productID, int quantity) {
		super();
		this.productID = productID;
		this.quantity = quantity;
	}
	
	public CartItemModel() {
		
	}
	
	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
}
