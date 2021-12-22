package view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class CashierPage extends JFrame {
	
//	JMenuBar menuBar;
//	JMenu userMenu, homeMenu;
//	JMenuItem home, logout;
	

	public CashierPage() {
//		menu();
		init();
	}
	
//	private void menu() {
//		menuBar = new JMenuBar();
//		userMenu = new JMenu("User");
//		userMenu.setPreferredSize(new Dimension(50, 20));
//		homeMenu = new JMenu("Home");
//		homeMenu.setPreferredSize(new Dimension(50, 20));
//		
//		home = new JMenuItem("home");
//		logout = new JMenuItem("logout");
//		logout.setPreferredSize(new Dimension(50, 20));
//		
//		userMenu.add(logout);
//		homeMenu.add(home);
//		
//		menuBar.add(userMenu);
//		menuBar.add(homeMenu);
//		setJMenuBar(menuBar);
//	}
	
	
	void init() {
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Just DU It!");
		setVisible(true);
	}

}
