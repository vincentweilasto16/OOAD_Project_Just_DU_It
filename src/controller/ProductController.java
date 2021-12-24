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
			    } catch (Exception e) {
			    	addProductPage.showMessage("Price must be numeric");
			    	return;
			    }
			 
			 	if(priceNum <= 0) {
					addProductPage.showMessage("Price must above zero");
					return;
				}
				else {
					count++;
				}
		}
		if(stock.isEmpty()) {
			addProductPage.showMessage("Stock must not empty");
			return;
		}
		else {
			count++;
			 try {
			        stockNum = Integer.parseInt(stock);
			        count++;
					
			    } catch (Exception e) {
			    	addProductPage.showMessage("Stock must be numeric");
			    	return;
			    }
			 
				if(stockNum <= 0) {
					addProductPage.showMessage("Stock must above zero");
					return;
				}
				else {
					count++;
				}
		}
		
		if(count == 8) {
			ProductModel productModel = new ProductModel();
			productModel.insertProduct(name, description, priceNum, stockNum);
			addProductPage.showMessage("Add Product Successfully!");
			addProductPage.getFrame().dispose();
			viewManageProductPage();
		}
	}
	
	public int updateProduct(int selectedIndex, String id, String name, String description, String price, String stock) {
		
		if(selectedIndex == -1) {
			manageProductPage.showManageProductPageMessage("Nothing updated!");
//			return;
		}
		else {
			int count = 0;
			Integer priceNum = 0;
			Integer stockNum = 0;
			Integer productId = Integer.parseInt(id);
			if(name.equals("") || name.isEmpty()) {
				manageProductPage.showManageProductPageMessage("Name must not empty");
//				return;
			}
			else {
				count++;
			}
			if(description.equals("") || description.isEmpty()) {
				manageProductPage.showManageProductPageMessage("Description must not empty");
//				return;
			}
			else {
				count++;
			}
			
			if(price.isEmpty()) {
				manageProductPage.showManageProductPageMessage("Price must not empty");
//				return;
			}
			else {
				count++;
				 try {
				        priceNum = Integer.parseInt(price);
				        count++;
						if(priceNum <= 0) {
							manageProductPage.showManageProductPageMessage("Price must above zero");
//							return;
						}
						else {
							count++;
						}
				    } catch (Exception e) {
				    	manageProductPage.showManageProductPageMessage("Price must be numeric");
//				    	return;
				    }
			}
			if(stock.isEmpty()) {
				manageProductPage.showManageProductPageMessage("Stock must not empty");
			}
			else {
				count++;
				 try {
				        stockNum = Integer.parseInt(stock);
				        count++;
						if(stockNum <= 0) {
							manageProductPage.showManageProductPageMessage("Stock must above zero");
//							return;
						}
						else {
							count++;
						}
				    } catch (Exception e) {
				    	manageProductPage.showManageProductPageMessage("Stock must be numeric");
//				    	return;
				    }
			}
			
			if(count == 8) {
				ProductModel productModel = new ProductModel();
				productModel.updateProduct(productId, name, description, priceNum, stockNum);
				manageProductPage.showManageProductPageMessage("Product Has Been Updated Successfully!");
				manageProductPage.getFrame().dispose();
				viewManageProductPage();
				return -1;
			}
		}
		
		return selectedIndex;
		
		
	}
	
	public void deleteProduct(int selectedIndex, String id) {		
		if(selectedIndex == -1) {
			manageProductPage.showManageProductPageMessage("Nothing to delete!");
		}
		else {
			int productId = Integer.parseInt(id);			
			ProductModel productModel = new ProductModel();
			productModel.deleteProduct(productId);
			manageProductPage.showManageProductPageMessage("Product Has Been Deleted Successfully!");
			manageProductPage.getFrame().dispose();
			viewManageProductPage();
		}
	}
	
	public Vector<ProductModel> getAllProduct() {
		
		ProductModel productModel = new ProductModel();
		return productModel.getAllProduct();

	}

}
