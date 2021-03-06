package models;

public class CartItemModel {
	private int quantity;
	private ProductModel product;

	public CartItemModel(int productID, int quantity, int price, int stock, String name, String description) {
		super();
		this.quantity = quantity;
		this.product = new ProductModel(productID, price, stock, name, description);
	}
	
	public CartItemModel() {
		
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ProductModel getProduct() {
		return product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}
	
	
	
	
	
}
