package view.transactionManagement;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
import controller.TransactionController;
import main.Main;
import models.CartItemModel;
import models.EmployeeModel;
import models.ProductModel;
import view.LoginPage;
import javax.swing.SwingConstants;

public class ManageCartPage implements ActionListener {

	private JFrame frame;
	private JLabel titleLbl, lblCart, totalPriceLbl, totalPriceResultLbl;
	private JButton btnRemove, btnLogout, btnConfirm, btnBack;
	private DefaultTableModel dtm;
	private JScrollPane scrollPane;
	private JTable table;
	private int selectedIndex = -1;
	private String id;
	private JTextField totalPriceTxt;
	private String totalPrice;
	private JComboBox<String> paymentType;
	private JLabel paymentTypeLbl;

	public ManageCartPage() {
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().setEnabled(false);
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
		
//		scrollPane = new JScrollPane(table);
//		frame.getContentPane().add(scrollPane);
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				selectedIndex = table.getSelectedRow();
				id = dtm.getValueAt(selectedIndex, 0).toString();
			}
		});
		
		btnRemove = new JButton("Remove Product");
		btnRemove.setBounds(34, 615, 165, 25);
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
		btnConfirm.setBounds(653, 615, 117, 25);
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
		

//		totalPriceTxt = new JTextField("0");
//		totalPriceTxt.setEditable(false);
//		totalPriceTxt.setBounds(634, 449, 116, 22);
////		totalPriceTxt.setText(totalPrice);
//		frame.getContentPane().add(totalPriceTxt);
//		totalPriceTxt.setColumns(10);
		
		
		int totalPriceNum = CartController.getInstance().calculateTotalPrice();
		totalPrice = Integer.toString(totalPriceNum);
		
		totalPriceResultLbl = new JLabel(totalPrice);
		totalPriceResultLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		totalPriceResultLbl.setBounds(640, 452, 110, 16);
		frame.getContentPane().add(totalPriceResultLbl);
		
		totalPriceLbl = new JLabel("Total Price");
		totalPriceLbl.setBounds(561, 452, 67, 16);
		frame.getContentPane().add(totalPriceLbl);
		
		Vector<String> paymentTypes = new Vector<>();
		paymentTypes.add("Cash");
		paymentTypes.add("Credit");
		paymentType = new JComboBox<String>(paymentTypes);
		paymentType.setBounds(139, 452, 102, 25);
		frame.getContentPane().add(paymentType);
		
		paymentTypeLbl = new JLabel("Payment Type");
		paymentTypeLbl.setBounds(46, 455, 85, 16);
		frame.getContentPane().add(paymentTypeLbl);
		
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
			String payment = paymentType.getSelectedItem().toString();
			long millis = System.currentTimeMillis();  
			Date purchaseDate = new Date(millis);
			
			TransactionController.getInstance().addTransaction(purchaseDate, Main.employee.getId(), payment);
		}
		else if(e.getSource().equals(btnBack)) {
			CartController.getInstance().viewAddToCartPage();
			frame.dispose();
		}
		else {
			EmployeeController.getInstance().logout();
			frame.dispose();
		}
	
	}
}
