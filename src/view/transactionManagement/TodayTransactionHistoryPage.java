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
import controller.TransactionController;
import models.TransactionItemModel;
import models.TransactionModel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class TodayTransactionHistoryPage implements ActionListener {

	private JFrame frame;
	private JLabel titleLbl, lblDetailTransaction;
	private JButton btnBack, btnLogout;
	private DefaultTableModel dtm, dtmItem;
	private JScrollPane scrollPane, scrollPaneItem;
	private JTable table, tableItem;
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
		
		Object [] header = new Object[] {"ID", "Purchase Date", "Employee Name", "Payment Type"};
		Object [][] data = new Object[transactions.size()][4];
		
		for (int i = 0; i < transactions.size(); i++) {
			data[i][0] = transactions.get(i).getId();
			data[i][1] = transactions.get(i).getPurchaseDate();
			data[i][2] = transactions.get(i).getEmployee().getName();
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
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				selectedIndex = table.getSelectedRow();
				String id = dtm.getValueAt(selectedIndex, 0).toString();
				
				Vector<TransactionItemModel> item = TransactionController.getInstance().getAllTransactionItem(id);
				
				dtmItem.setRowCount(0);	
				for (TransactionItemModel i : item) {
					Vector<Object> row = new Vector<>();
					row.add(i.getProduct().getId());
					row.add(i.getProduct().getName());
					row.add(i.getProduct().getPrice());
					row.add(i.getQuantity());	
					dtmItem.addRow(row);
				}
			}
		});
		
		btnBack = new JButton("Back");
		btnBack.setBounds(12, 615, 80, 25);
		btnBack.addActionListener(this);
		frame.getContentPane().add(btnBack);
		
		btnLogout = new JButton("Logout");
		btnLogout.setBounds(669, 13, 97, 25);
		btnLogout.addActionListener(this);
		frame.getContentPane().add(btnLogout);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 131, 703, 189);
		frame.getContentPane().add(scrollPane);
		
		scrollPane.setViewportView(table);
		
		JLabel lblHistoryTransaction = new JLabel("History Transaction");
		lblHistoryTransaction.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblHistoryTransaction.setBounds(342, 84, 195, 16);
		frame.getContentPane().add(lblHistoryTransaction);
		
		//table for item list
		Object [] headerItem = new Object[] {"Product ID", "Name", "Price", "Quantity"};
		Object [][] dataItem = new Object[][] {};
			
		dtmItem = new DefaultTableModel(dataItem, headerItem) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		tableItem = new JTable(dtmItem);
		tableItem.setRowHeight(30);
		
		scrollPaneItem = new JScrollPane();
		scrollPaneItem.setBounds(48, 400, 709, 153);
		frame.getContentPane().add(scrollPaneItem);
		scrollPaneItem.setViewportView(tableItem);
		
		lblDetailTransaction = new JLabel("Detail Transaction Item List");
		lblDetailTransaction.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblDetailTransaction.setBounds(314, 371, 246, 16);
		frame.getContentPane().add(lblDetailTransaction);
		
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
