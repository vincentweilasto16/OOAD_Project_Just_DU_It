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
			viewAddProductPage();
			return;
		}
		else {
			count++;
		}
		if(description.equals("") || description.isEmpty()) {
			addProductPage.showMessage("Description must not empty");
			viewAddProductPage();
			return;
		}
		else {
			count++;
		}
		
		if(price.isEmpty()) {
			addProductPage.showMessage("Price must not empty");
			viewAddProductPage();
			return;
		}
		else {
			count++;
			 try {
			        priceNum = Integer.parseInt(price);
			        count++;
					if(priceNum <= 0) {
						addProductPage.showMessage("Price must above zero");
						viewAddProductPage();
						return;
					}
					else {
						count++;
					}
			    } catch (Exception e) {
			    	addProductPage.showMessage("Price must be numeric");
			    	viewAddProductPage();
			    	return;
			    }
		}
		if(stock.isEmpty()) {
			addProductPage.showMessage("Stock must not empty");
			viewAddProductPage();
			return;
		}
		else {
			count++;
			 try {
			        stockNum = Integer.parseInt(price);
			        count++;
					if(stockNum <= 0) {
						addProductPage.showMessage("Stock must above zero");
						viewAddProductPage();
						return;
					}
					else {
						count++;
					}
			    } catch (Exception e) {
			    	addProductPage.showMessage("Stock must be numeric");
			    	viewAddProductPage();
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
		Integer productId = 0;
		productId = Integer.parseInt(id);
		
		if(name.equals("") || name.isEmpty()) {
			manageProductPage.showManageProductPageMessage("Name must not empty");
			viewManageProductPage();
		}
		else {
			count++;
		}
		if(description.equals("") || description.isEmpty()) {
			manageProductPage.showManageProductPageMessage("Description must not empty");
			viewManageProductPage();
		}
		else {
			count++;
		}
		
		if(price.isEmpty()) {
			manageProductPage.showManageProductPageMessage("Price must not empty");
			viewManageProductPage();
		}
		else {
			count++;
			 try {
			        priceNum = Integer.parseInt(price);
			        count++;
					if(priceNum <= 0) {
						manageProductPage.showManageProductPageMessage("Price must above zero");
						viewManageProductPage();
					}
					else {
						count++;
					}
			    } catch (Exception e) {
			    	manageProductPage.showManageProductPageMessage("Price must be numeric");
			    	viewManageProductPage();
			    }
		}
		if(stock.isEmpty()) {
			manageProductPage.showManageProductPageMessage("Stock must not empty");
			viewManageProductPage();
		}
		else {
			count++;
			 try {
			        stockNum = Integer.parseInt(stock);
			        count++;
					if(stockNum <= 0) {
						manageProductPage.showManageProductPageMessage("Stock must above zero");
						viewManageProductPage();
					}
					else {
						count++;
					}
			    } catch (Exception e) {
			    	manageProductPage.showManageProductPageMessage("Stock must be numeric");
			    	viewManageProductPage();
			    }
		}
		
		if(count == 8) {
			ProductModel productModel = new ProductModel();
			productModel.updateProduct(productId, name, description, priceNum, stockNum);
			manageProductPage.showManageProductPageMessage("Product Has Been Updated Successfully!");
			viewManageProductPage();
		}
	}
	
	public void deleteProduct(String id) {
		Integer productId = Integer.parseInt(id);
		
		ProductModel productModel = new ProductModel();
		productModel.deleteProduct(productId);
		manageProductPage.showManageProductPageMessage("Product Has Been Deleted Successfully!");
		viewManageProductPage();
	}
	
	public Vector<ProductModel> getAllProduct() {
		
		ProductModel productModel = new ProductModel();
		return productModel.getAllProduct();

	}

}
