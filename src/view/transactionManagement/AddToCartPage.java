package view.transactionManagement;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
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
import models.EmployeeModel;
import models.ProductModel;
import view.LoginPage;

public class AddToCartPage implements ActionListener {

	private JFrame frame;
	private JLabel titleLbl, idLbl, nameLbl, lblQuantity;
	private JButton btnAddCart, btnLogout, btnViewCart;
	private DefaultTableModel dtm;
	private JScrollPane scrollPane;
	private JTable table;
	private int selectedIndex = -1;
	private JTextField idTxt, nameTxt, quantityTxt;

	public AddToCartPage() {
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
		titleLbl.setBounds(376, 34, 91, 16);
		frame.getContentPane().add(titleLbl);
		
		Vector<ProductModel> products = ProductController.getInstance().getAllProduct();
		
		Object [] header = new Object[] {"ID", "Name", "Description", "Price", "Stock"};
		Object [][] data = new Object[products.size()][5];
		
		for (int i = 0; i < products.size(); i++) {
			data[i][0] = products.get(i).getId();
			data[i][1] = products.get(i).getName();
			data[i][2] = products.get(i).getDescription();
			data[i][3] = products.get(i).getPrice();
			data[i][4] = products.get(i).getStock();
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
				String id = dtm.getValueAt(selectedIndex, 0).toString();
				String name = dtm.getValueAt(selectedIndex, 1).toString();
				
				idTxt.setText(id);
				nameTxt.setText(name);
			}
		});
		
		btnAddCart = new JButton("Add Product to Cart");
		btnAddCart.setBounds(43, 506, 165, 25);
		btnAddCart.addActionListener(this);
		frame.getContentPane().add(btnAddCart);
		
		btnLogout = new JButton("Logout");
		btnLogout.setBounds(669, 13, 97, 25);
		btnLogout.addActionListener(this);
		frame.getContentPane().add(btnLogout);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 91, 703, 234);
		frame.getContentPane().add(scrollPane);
		
	    btnViewCart = new JButton("View Cart");
	    btnViewCart.addActionListener(this);
		btnViewCart.setBounds(633, 338, 117, 25);
		frame.getContentPane().add(btnViewCart);
		
		idLbl = new JLabel("ID");
		idLbl.setBounds(43, 360, 35, 25);
		frame.getContentPane().add(idLbl);
		
		idTxt = new JTextField();
		idTxt.setEditable(false);
		idTxt.setBounds(128, 357, 150, 30);
		frame.getContentPane().add(idTxt);
		idTxt.setColumns(10);
		
		nameLbl = new JLabel("Name");
		nameLbl.setBounds(44, 404, 42, 25);
		frame.getContentPane().add(nameLbl);
		
		nameTxt = new JTextField();
		nameTxt.setEditable(false);
		nameTxt.setColumns(10);
		nameTxt.setBounds(128, 404, 150, 30);
		frame.getContentPane().add(nameTxt);
		
		lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(43, 455, 78, 25);
		frame.getContentPane().add(lblQuantity);
		
		quantityTxt = new JTextField();
		quantityTxt.setBounds(128, 456, 150, 30);
		frame.getContentPane().add(quantityTxt);
		quantityTxt.setColumns(10);
	
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
		if(e.getSource().equals(btnAddCart)) {
			String id, quantity;
			id = idTxt.getText();
			quantity = quantityTxt.getText();
			CartController.getInstance().addToCart(selectedIndex, id, quantity);
		}
		else if(e.getSource().equals(btnViewCart)){
			CartController.getInstance().viewManageCartPage();
			frame.dispose();
		}
		else if(e.getSource().equals(btnLogout)) {
			new LoginPage();
			frame.dispose();
		}
	
	}
}
