package view.manager;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import controller.EmployeeController;
import controller.TransactionController;
import models.TransactionItemModel;
import models.TransactionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;

public class TransactionReportPage implements ActionListener {

	private JFrame frame;
	private JLabel titleLbl, dateLabel, lblDetailTransaction;
	private JButton btnSearch, btnLogout, btnManageEmployee;
	private DefaultTableModel dtm, dtmItem;
	private JSpinner date;
	private JScrollPane scrollPane, scrollPaneItem;
	private JTable table, tableItem;
	private int selectedIndex = -1;


	/**
	 * Create the application.
	 */
	public TransactionReportPage() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 755);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		titleLbl = new JLabel("Just DU It!");
		titleLbl.setFont(new Font("Times New Roman", Font.BOLD, 18));
		titleLbl.setBounds(376, 34, 91, 16);
		frame.getContentPane().add(titleLbl);
		
//		Vector<TransactionModel> transactions = TransactionController.getInstance().getAllTransaction(date);
		
		Object [] header = new Object[] {"ID", "Purchase Date", "Employee Name", "Payment Type"};
		Object [][] data = new Object[][] {
			
		};
			
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
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(63, 147, 80, 25);
		btnSearch.addActionListener(this);
		frame.getContentPane().add(btnSearch);
		
		btnLogout = new JButton("Logout");
		btnLogout.setBounds(669, 13, 97, 25);
		btnLogout.addActionListener(this);
		frame.getContentPane().add(btnLogout);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(51, 185, 703, 221);
		frame.getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);
		
		date = new JSpinner(new SpinnerDateModel(new Date(1641031232041L), new Date(1609495232041L), new Date(1641031232041L), Calendar.MONTH));
		JSpinner.DateEditor editor = new JSpinner.DateEditor(date, "MM/yyyy");
		date.setEditor(editor);
		date.setBounds(167, 98, 137, 22);
		frame.getContentPane().add(date);
		
		
	    dateLabel = new JLabel("Month & year");
	    dateLabel.setBounds(63, 101, 80, 16);
		frame.getContentPane().add(dateLabel);
		
		btnManageEmployee = new JButton("Manage Employee");
		btnManageEmployee.setBounds(614, 74, 152, 25);
		btnManageEmployee.addActionListener(this);
		frame.getContentPane().add(btnManageEmployee);
		
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
		scrollPaneItem.setBounds(51, 457, 703, 221);
		frame.getContentPane().add(scrollPaneItem);
		scrollPaneItem.setViewportView(tableItem);
		
		lblDetailTransaction = new JLabel("Detail Transaction Item List");
		lblDetailTransaction.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblDetailTransaction.setBounds(302, 428, 246, 16);
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
		if(e.getSource().equals(btnSearch)) {
			String temp = new SimpleDateFormat("MM/yyyy").format(date.getValue());
			Vector<TransactionModel> transaction =  TransactionController.getInstance().getAllTransaction(temp);
			
			dtm.setRowCount(0);	
			
			for (TransactionModel t : transaction) {
				Vector<Object> row = new Vector<>();
				row.add(t.getId());
				row.add(t.getPurchaseDate());
				row.add(t.getEmployee().getName());
				row.add(t.getPaymentType());
				dtm.addRow(row);
			}
			
		}
		else if(e.getSource().equals(btnManageEmployee)) {
			EmployeeController.getInstance().viewManageEmployeePage();
			frame.dispose();
		}
		else if(e.getSource().equals(btnLogout)) {
			EmployeeController.getInstance().logout();
			frame.dispose();
		}
	}
}
