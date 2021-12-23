package controller;

import java.util.Vector;

import models.ProductModel;
import view.AddProductPage;
import view.ManageProductPage;

public class ProductController {
	
	private static ProductController instance;
	private ManageProductPage manageProductPage;
	private AddProductPage addProductPage;

	private ProductController() {
		// TODO Auto-generated constructor stub
	}
	
	public static ProductController getInstance() {
		if(instance == null) {
			instance = new ProductController();
		}
		return instance;
	}
	
	public void viewManageProductPage() {
		manageProductPage = new ManageProductPage();
	}
	
	public void viewAddProductPage() {
		addProductPage = new AddProductPage();
	}
	
	public void addProduct(String name, String description, String price, String stock) {
		int count = 0;
		Integer priceNum = 0;
		Integer stockNum = 0;
		
		if(name.equals("") || name.isEmpty()) {
			addProductPage.showMessage("Name must not empty");
			return;
		}
		else {
			count++;
		}
		if(description.equals("") || description.isEmpty()) {
			addProductPage.showMessage("Description must not empty");
			return;
		}
		else {
			count++;
		}
		
		if(price.isEmpty()) {
			addProductPage.showMessage("Price must not empty");
			return;
		}
		else {
			count++;
			 try {
			        priceNum = Integer.parseInt(price);
			        count++;
					if(priceNum <= 0) {
						addProductPage.showMessage("Price must above zero");
						return;
					}
					else {
						count++;
					}
			    } catch (Exception e) {
			    	addProductPage.showMessage("Price must be numeric");
			    	return;
			    }
		}
		if(stock.isEmpty()) {
			addProductPage.showMessage("Stock must not empty");
		}
		else {
			count++;
			 try {
			        stockNum = Integer.parseInt(price);
			        count++;
					if(stockNum <= 0) {
						addProductPage.showMessage("Stock must above zero");
						return;
					}
					else {
						count++;
					}
			    } catch (Exception e) {
			    	addProductPage.showMessage("Stock must be numeric");
			    	return;
			    }
		}
		
		if(count == 8) {
			ProductModel productModel = new ProductModel();
			productModel.insertProduct(name, description, priceNum, stockNum);
			addProductPage.showMessage("Add Product Successfully!");
		}
	}
	
	public void updateProduct(String id, String name, String description, String price, String stock) {
		int count = 0;
		Integer priceNum = 0;
		Integer stockNum = 0;
		Integer productId = Integer.parseInt(id);
		
		if(name.equals("") || name.isEmpty()) {
			manageProductPage.showMessage("Name must not empty");
			return;
		}
		else {
			count++;
		}
		if(description.equals("") || description.isEmpty()) {
			manageProductPage.showMessage("Description must not empty");
			return;
		}
		else {
			count++;
		}
		
		if(price.isEmpty()) {
			manageProductPage.showMessage("Price must not empty");
			return;
		}
		else {
			count++;
			 try {
			        priceNum = Integer.parseInt(price);
			        count++;
					if(priceNum <= 0) {
						manageProductPage.showMessage("Price must above zero");
						return;
					}
					else {
						count++;
					}
			    } catch (Exception e) {
			    	manageProductPage.showMessage("Price must be numeric");
			    	return;
			    }
		}
		if(stock.isEmpty()) {
			manageProductPage.showMessage("Stock must not empty");
		}
		else {
			count++;
			 try {
			        stockNum = Integer.parseInt(price);
			        count++;
					if(stockNum <= 0) {
						manageProductPage.showMessage("Stock must above zero");
						return;
					}
					else {
						count++;
					}
			    } catch (Exception e) {
			    	manageProductPage.showMessage("Stock must be numeric");
			    	return;
			    }
		}
		
		if(count == 8) {
			ProductModel productModel = new ProductModel();
			productModel.updateProduct(productId, name, description, priceNum, stockNum);
			manageProductPage.showMessage("Product Has Been Updated Successfully!");
			viewManageProductPage();
		}
	}
	
	public void deleteProduct(String id) {
		Integer productId = Integer.parseInt(id);
		
		ProductModel productModel = new ProductModel();
		productModel.deleteProduct(productId);
		manageProductPage.showMessage("Product Has Been Deleted Successfully!");
		viewManageProductPage();
	}
	
	public Vector<ProductModel> getAllProduct() {
		
		ProductModel productModel = new ProductModel();
		return productModel.getAllProduct();

	}

}
