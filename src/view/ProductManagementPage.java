package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

public class ProductManagementPage extends JFrame {
	
	JPanel mainPanel, northPanel, southPanel, centerPanel, tablePanel, descriptorPanel;
	DefaultTableModel dtm;
	JTable table;
	JScrollPane scrollPane;
	JLabel titleLbl;
	JButton addBtn, updateBtn, deleteBtn, logOutBtn;
	
	JFrame frame;
	
	public ProductManagementPage() {
		frame = this;
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		northPanel = new JPanel();
		centerPanel = new JPanel(new BorderLayout());
		tablePanel = new JPanel();
		descriptorPanel = new JPanel();
		southPanel = new JPanel();
		
		//top
		titleLbl = new JLabel("Just DU It!");
		titleLbl.setFont(new Font("Roboto", Font.BOLD, 24));
		northPanel.add(titleLbl);
		
		//center
		Object [] header = new Object[] {"name", "description", "price", "stock"};
		//Kuliah, Cinta, Trending, Kuliner
		Object [][] data = new Object[][] {
			{"Male","Ferdinand","Kuliah","gile cuk tugas banyak banget, project dimana - mana tolong"},
			{"Male","Erwin","Cinta","duh, kepikiran dia terus, tembak ga ya ?"},
			{"Female","Andre","Trending","Gimana si cara magang di tokped, minta tips dong"},
			{"Female","Hansen","Kuliner","Gais, kalian boleh coba ada es krim baru depan binus, depan lawson"},
		};
		
		dtm = new DefaultTableModel(data, header) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(dtm);
		table.setRowHeight(30);
		scrollPane = new JScrollPane(table);
		
		scrollPane.setPreferredSize(new Dimension(900, 500));
		tablePanel.add(scrollPane);

		centerPanel.add(tablePanel, BorderLayout.CENTER);
		
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
//				int selected_idx = table.getSelectedRow();
//				String text = dtm.getValueAt(selected_idx, 3).toString();
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
		
		init();
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
