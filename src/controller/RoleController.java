package controller;

import java.util.Vector;

import models.Role;

public class RoleController {
	
	private static RoleController instance;

	public RoleController() {
		// TODO Auto-generated constructor stub
	}
	
	public static RoleController getInstance() {
		if(instance == null) {
			instance = new RoleController();
		}
		return instance;
	}
	
	public Vector<Role> getAllEmployeeRole(){
		Role roleModel = new Role();
		return roleModel.getAllRole();
	}
	
}
