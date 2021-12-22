package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import connect.Connect;

public class AddProductPage extends JFrame {

	JInternalFrame internalFrame;
	JDesktopPane desktopPane;
	JPanel mainPanel, northPanel, centerPanel, southPanel;
	JLabel titleLbl, nameLbl, descriptionLbl, priceLbl, stockLbl;
	JTextField nameTxt, priceTxt, stockTxt;
	JTextArea descriptionArea;
	JButton addBtn, cancelBtn;
	JScrollPane areaPane;
	
	JFrame frame;

	public AddProductPage() {
		frame = this;
		
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		centerPanel = new JPanel(new GridLayout(4, 4, 0, 30));
		northPanel = new JPanel();
		southPanel = new JPanel();
		
		titleLbl = new JLabel("Just DU It!");
		titleLbl.setFont(new Font("Roboto", Font.BOLD, 24));
		northPanel.add(titleLbl);
		
		nameLbl = new JLabel("Name");
		descriptionLbl = new JLabel("Description");
		priceLbl = new JLabel("Price");
		stockLbl = new JLabel("Stock");
		
		nameTxt = new JTextField();
		priceTxt = new JTextField();
		stockTxt = new JTextField();
		descriptionArea = new JTextArea();
		descriptionArea.setFont(new Font("Calibri", Font.PLAIN, 18));
		descriptionArea.setLineWrap(true);
		areaPane = new JScrollPane(descriptionArea);
		
		centerPanel.add(nameLbl);
		centerPanel.add(nameTxt);
		centerPanel.add(descriptionLbl);
		centerPanel.add(areaPane);
		centerPanel.add(priceLbl);
		centerPanel.add(priceTxt);
		centerPanel.add(stockLbl);
		centerPanel.add(stockTxt);
		
		addBtn = new JButton("Add Product");
		cancelBtn = new JButton("Back");
		southPanel.add(addBtn);
		southPanel.add(cancelBtn);
		
		
		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		
		
		add(mainPanel);
		
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addProduct();
			}
		});
		
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ProductManagementPage();
				frame.dispose();
			}
		});
		
		init();
	}
	
	void addProduct(){
		int count = 0;
		String name = nameTxt.getText();
		String description = descriptionArea.getText();
		String priceText = priceTxt.getText();
		String stockText = stockTxt.getText();
		int price = Integer.parseInt(priceText);
		int stock = Integer.parseInt(stockText);
		
		if(name.equals("") || name.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "Name must not empty");
		}
		else {
			count++;
		}
		if(description.equals("") || description.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "Description must not empty");
		}
		else {
			count++;
		}
		if(priceTxt.getText().isEmpty()) {
			JOptionPane.showMessageDialog(frame, "Price must not empty");
		}
		else {
			count++;
			if(isNumeric(priceTxt.getText()) == false) {
				JOptionPane.showMessageDialog(frame, "Price must be numeric");
			}
			else {
				count++;
				if(price <= 0) {
					JOptionPane.showMessageDialog(frame, "Price must above zero");
				}
				else {
					count++;
				}
			}
		}
		if(stockTxt.getText().isEmpty()) {
			JOptionPane.showMessageDialog(frame, "Stock must not empty");
		}
		else {
			count++;
			if(isNumeric(stockTxt.getText()) == false) {
				JOptionPane.showMessageDialog(frame, "Stock must be numeric");
			}
			else {
				count++;
				if(stock <= 0) {
					JOptionPane.showMessageDialog(frame, "Stock must above zero");
				}
				else {
					count++;
				}
			}
		}
		
		if(count == 8) {
			
			Connect con = Connect.getInstance();
			PreparedStatement ps = con.preparedStatement("INSERT INTO product VALUES (NULL, ?, ?, ?, ?)");
			
			try {
				ps.setString(1, name);
				ps.setString(2, description);
				ps.setInt(3, price);
				ps.setInt(4, stock);
				
				ps.execute();
				
				JOptionPane.showMessageDialog(frame, "Success Add New Product!");
				nameTxt.setText("");
				descriptionArea.setText("");
				priceTxt.setText("");
				stockTxt.setText("");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	void init() {
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Just DU It!");
		setVisible(true);
	}

}
