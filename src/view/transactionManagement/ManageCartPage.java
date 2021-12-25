package view.transactionManagement;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.CartController;
import controller.EmployeeController;
import controller.ProductController;
import models.CartItemModel;
import models.EmployeeModel;
import models.ProductModel;
import view.LoginPage;

public class ManageCartPage implements ActionListener {

	private JFrame frame;
	private JLabel titleLbl, lblCart;
	private JButton btnRemove, btnLogout, btnConfirm, btnBack;
	private DefaultTableModel dtm;
	private JScrollPane scrollPane;
	private JTable table;
	private int selectedIndex = -1;
	private String id;

	public ManageCartPage() {
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		titleLbl = new JLabel("Just DU It!");
		titleLbl.setFont(new Font("Times New Roman", Font.BOLD, 18));
		titleLbl.setBounds(345, 34, 91, 16);
		frame.getContentPane().add(titleLbl);
		
		Vector<CartItemModel> cartItems = CartController.getInstance().getCartList();
		
		Object [] header = new Object[] {"ID", "Quantity"};
		Object [][] data = new Object[cartItems.size()][2];
		
		
		for (int i = 0; i < cartItems.size(); i++) {
			data[i][0] = cartItems.get(i).getProductID();
			data[i][1] = cartItems.get(i).getQuantity();
		}
		
			
		dtm = new DefaultTableModel(data, header) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		table = new JTable(dtm);
		table.setRowHeight(30);
		
		scrollPane = new JScrollPane(table);
		frame.getContentPane().add(scrollPane);
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				selectedIndex = table.getSelectedRow();
				id = dtm.getValueAt(selectedIndex, 0).toString();
			}
		});
		
		btnRemove = new JButton("Remove Product");
		btnRemove.setBounds(47, 468, 165, 25);
		btnRemove.addActionListener(this);
		frame.getContentPane().add(btnRemove);
		
		btnLogout = new JButton("Logout");
		btnLogout.setBounds(669, 13, 97, 25);
		btnLogout.addActionListener(this);
		frame.getContentPane().add(btnLogout);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 147, 703, 288);
		frame.getContentPane().add(scrollPane);
		
	    btnConfirm = new JButton("Confirm");
	    btnConfirm.addActionListener(this);
		btnConfirm.setBounds(633, 468, 117, 25);
		frame.getContentPane().add(btnConfirm);
		
		lblCart = new JLabel("Cart");
		lblCart.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblCart.setBounds(370, 104, 51, 16);
		frame.getContentPane().add(lblCart);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(this);
		btnBack.setBounds(47, 13, 117, 25);
		frame.getContentPane().add(btnBack);
	
		scrollPane.setViewportView(table);
		
	}
	
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(frame, message);
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnRemove)) {
			CartController.getInstance().deleteItem(id, selectedIndex);
			selectedIndex = -1;
		}
		else if(e.getSource().equals(btnConfirm)){
			
			
		}
		else if(e.getSource().equals(btnBack)) {
			CartController.getInstance().viewAddToCartPage();
			frame.dispose();
		}
		else {
			new LoginPage();
			frame.dispose();
		}
	
	}
}
