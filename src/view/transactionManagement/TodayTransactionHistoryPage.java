package view.transactionManagement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import controller.CartController;
import controller.EmployeeController;
import controller.ProductController;
import controller.TransactionController;
import models.ProductModel;
import models.TransactionModel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class TodayTransactionHistoryPage implements ActionListener {

	private JFrame frame;
	private JLabel titleLbl;
	private JButton btnBack, btnLogout;
	private DefaultTableModel dtm;
	private JScrollPane scrollPane;
	private JTable table;
	private int selectedIndex = -1;


	/**
	 * Create the application.
	 */
	public TodayTransactionHistoryPage() {
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
		
		Vector<TransactionModel> transactions = TransactionController.getInstance().getTodayTransaction();
		
		Object [] header = new Object[] {"ID", "Purchase Date", "Employee ID", "Payment Type"};
		Object [][] data = new Object[transactions.size()][4];
		
		for (int i = 0; i < transactions.size(); i++) {
			data[i][0] = transactions.get(i).getId();
			data[i][1] = transactions.get(i).getPurchaseDate();
			data[i][2] = transactions.get(i).getEmployeeId();
			data[i][3] = transactions.get(i).getPaymentType();
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
//		scrollPane = new JScrollPane(table);
//		frame.getContentPane().add(scrollPane);
		
//		table.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				selectedIndex = table.getSelectedRow();
//			}
//		});
		
		btnBack = new JButton("Back");
		btnBack.setBounds(22, 525, 80, 25);
		btnBack.addActionListener(this);
		frame.getContentPane().add(btnBack);
		
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
		if(e.getSource().equals(btnBack)) {
			CartController.getInstance().viewAddToCartPage();
			frame.dispose();
		}
		else if(e.getSource().equals(btnLogout)) {
			EmployeeController.getInstance().logout();
			frame.dispose();
		}
	}
}
