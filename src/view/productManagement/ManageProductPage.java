package view.productManagement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.EmployeeController;
import controller.ProductController;
import models.ProductModel;
import view.LoginPage;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class ManageProductPage implements ActionListener {

	private JFrame frame;
	private JLabel titleLbl, idLbl, nameLbl, descriptionLbl, priceLbl, stockLbl;
	private JTextField idTxt, nameTxt, descriptionTxt, priceTxt, stockTxt;
	private JButton btnUpdate, btnDelete, btnAddProduct, btnLogout;
	private DefaultTableModel dtm;
	private JScrollPane scrollPane;
	private JTable table;
	private int selectedIndex = -1;


	/**
	 * Create the application.
	 */
	public ManageProductPage() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
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
		
		
		
//		Vector<ProductModel> products = ProductController.getInstance().getAllProduct();
//		for (ProductModel productModel : products) {
//			Vector<Object> row = new Vector<>();
//			row.add(productModel.getId());
//			row.add(productModel.getName());
//			row.add(productModel.getDescription());
//			row.add(productModel.getPrice());
//			row.add(productModel.getStock());
//			dtm.addRow(row);
//		}
//		
//		table.setModel(dtm);
		scrollPane = new JScrollPane(table);
		frame.getContentPane().add(scrollPane);
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				selectedIndex = table.getSelectedRow();
				String id = dtm.getValueAt(selectedIndex, 0).toString();
				String name = dtm.getValueAt(selectedIndex, 1).toString();
				String description = dtm.getValueAt(selectedIndex, 2).toString();
				String price = dtm.getValueAt(selectedIndex, 3).toString();
				String stock = dtm.getValueAt(selectedIndex, 4).toString();
				
				idTxt.setText(id);
				nameTxt.setText(name);
				descriptionTxt.setText(description);
				priceTxt.setText(price);
				stockTxt.setText(stock);
			}
		});

		
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
		nameTxt.setColumns(10);
		nameTxt.setBounds(128, 400, 150, 30);
		frame.getContentPane().add(nameTxt);
		
		descriptionLbl = new JLabel("Description");
		descriptionLbl.setBounds(43, 445, 73, 25);
		frame.getContentPane().add(descriptionLbl);
		
		descriptionTxt = new JTextField();
		descriptionTxt.setColumns(10);
		descriptionTxt.setBounds(128, 442, 150, 30);
		frame.getContentPane().add(descriptionTxt);
		
		priceLbl = new JLabel("Price");
		priceLbl.setBounds(44, 488, 35, 25);
		frame.getContentPane().add(priceLbl);
		
		priceTxt = new JTextField();
		priceTxt.setColumns(10);
		priceTxt.setBounds(128, 485, 150, 30);
		frame.getContentPane().add(priceTxt);
		
		stockLbl = new JLabel("Stock");
		stockLbl.setBounds(43, 531, 35, 25);
		frame.getContentPane().add(stockLbl);
		
		stockTxt = new JTextField();
		stockTxt.setColumns(10);
		stockTxt.setBounds(128, 528, 150, 30);
		frame.getContentPane().add(stockTxt);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(43, 580, 97, 25);
		btnUpdate.addActionListener(this);
		frame.getContentPane().add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(166, 580, 97, 25);
		btnDelete.addActionListener(this);
		frame.getContentPane().add(btnDelete);
		
		btnAddProduct = new JButton("Add Product");
		btnAddProduct.setBounds(640, 615, 116, 25);
		btnAddProduct.addActionListener(this);
		frame.getContentPane().add(btnAddProduct);
		
		btnLogout = new JButton("Logout");
		btnLogout.setBounds(669, 13, 97, 25);
		btnLogout.addActionListener(this);
		frame.getContentPane().add(btnLogout);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 91, 703, 221);
		frame.getContentPane().add(scrollPane);
		
//		table = new JTable();
//		table.setModel(new DefaultTableModel(
//			new Object[][] {
//				
//			},
//			new String[] {
//				"ID", "Name", "Description", "Price", "Stock"
//			}
//		) {
//			Class[] columnTypes = new Class[] {
//				String.class, String.class, String.class, String.class, String.class
//			};
//			public Class getColumnClass(int columnIndex) {
//				return columnTypes[columnIndex];
//			}
//			boolean[] columnEditables = new boolean[] {
//				false, false, false, false, false
//			};
//			public boolean isCellEditable(int row, int column) {
//				return columnEditables[column];
//			}
//		});
		scrollPane.setViewportView(table);
		
	}
	
	public void showManageProductPageMessage(String message) {
		JOptionPane.showMessageDialog(frame, message);
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnAddProduct)) {
			ProductController.getInstance().viewAddProductPage();
			frame.dispose();
		}
		else if(e.getSource().equals(btnUpdate)) {
			String id = idTxt.getText();
			String name = nameTxt.getText();
			String description = descriptionTxt.getText();
			String price = priceTxt.getText();
			String stock = stockTxt.getText();
			
			selectedIndex = ProductController.getInstance().updateProduct(selectedIndex, id, name, description, price, stock);
			
//			frame.dispose();
		}
		else if(e.getSource().equals(btnDelete)) {
				String id = idTxt.getText();
				ProductController.getInstance().deleteProduct(selectedIndex, id);
				selectedIndex = -1;	
				
			
		}
		else if(e.getSource().equals(btnLogout)) {
			EmployeeController.getInstance().logout();
			frame.dispose();
		}
	}
}
