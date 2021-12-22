package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import connect.Connect;

public class ProductManagementPage extends JFrame {
	
	JPanel mainPanel, northPanel, southPanel, centerPanel, tablePanel;
	DefaultTableModel dtm;
	JTable table;
	JScrollPane scrollPane, descScrollPane;
	JLabel titleLbl, nameLbl, descriptionLbl, priceLbl, stockLbl;;
	JButton addBtn, updateBtn, deleteBtn, logOutBtn;
	JTextField nameTxt, priceTxt, stockTxt;
	JTextArea descriptionArea;

	
	JFrame frame;
	
	public ProductManagementPage() {
		frame = this;
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		northPanel = new JPanel();
		centerPanel = new JPanel(new GridLayout(2, 4, 10, 20));
		tablePanel = new JPanel();
		southPanel = new JPanel();
		
		titleLbl = new JLabel("Just DU It!");
		titleLbl.setFont(new Font("Roboto", Font.BOLD, 24));
		northPanel.add(titleLbl);

		
		Object [] header = new Object[] {"name", "description", "price", "stock"};
		Object [][] data = new Object[][] {};
		
		dtm = new DefaultTableModel(data, header) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(dtm);
		table.setRowHeight(30);
		scrollPane = new JScrollPane(table);
		
		scrollPane.setPreferredSize(new Dimension(900, 300));
		tablePanel.add(scrollPane);
		northPanel.add(tablePanel);
		
		nameLbl = new JLabel("Name");
		nameTxt = new JTextField();
		descriptionLbl = new JLabel("Description");
		descriptionArea = new JTextArea();
		descriptionArea.setFont(new Font("Calibri", Font.PLAIN, 18));
		descriptionArea.setLineWrap(true);
		descScrollPane = new JScrollPane(descriptionArea);
		priceLbl = new JLabel("Price");
		priceTxt = new JTextField();
		stockLbl = new JLabel("Stock");
		stockTxt = new JTextField();

		
		centerPanel.add(nameLbl);
		centerPanel.add(nameTxt);
		centerPanel.add(descriptionLbl);
		centerPanel.add(descScrollPane);
		centerPanel.add(priceLbl);
		centerPanel.add(priceTxt);
		centerPanel.add(stockLbl);
		centerPanel.add(stockTxt);

//		centerPanel.add(tablePanel, BorderLayout.NORTH);
//		centerPanel.add(updatePanel, BorderLayout.SOUTH);

		
		//south
		addBtn = new JButton("Add Product");
		updateBtn = new JButton("Update Product");
		deleteBtn = new JButton("Delete Product");
		logOutBtn = new JButton("Log Out");
		southPanel.add(addBtn);
		southPanel.add(updateBtn);
		southPanel.add(deleteBtn);
		southPanel.add(logOutBtn);
		
		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		
		add(mainPanel);
		
		table.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedIndex = table.getSelectedRow();
				Integer id = (Integer) dtm.getValueAt(selectedIndex, 1);
//				String name = dtm.getValueAt(selectedIndex, 2).toString();
//				String description = dtm.getValueAt(selectedIndex, 3).toString();
//				Integer price = (Integer) dtm.getValueAt(selectedIndex, 4);
//				Integer stock = (Integer) dtm.getValueAt(selectedIndex, 5);
//				descArea.setText(text);
			}
		});
		
		logOutBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginPage();
				frame.dispose();
			}
		});
		
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddProductPage();
				frame.dispose();
			}
		});
		
		updateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new UpdateProductPage();
				frame.dispose();
			}
		});
		
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				new PostPage();
//				frame.dispose();
			}
		});
		
		loadData();
		init();
	}
	
	void loadData() {
		
		Connect con = Connect.getInstance(); 
		String query = "SELECT * FROM product";
		ResultSet rs = con.executeQuery(query);
		
		try {
			while(rs.next()) {
				dtm.addRow(new Object[] {
						rs.getString("name"),
						rs.getString("description"),
						rs.getInt("price"),
						rs.getInt("stock")
				});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	void init() {
		setSize(1000, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Just DU It!");
		setVisible(true);
	}

}
