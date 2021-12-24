package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import controller.ProductController;

import javax.swing.JButton;

public class AddProductPage implements ActionListener {

	private JFrame frame;
	private JTextField nameTxt, descriptionTxt, priceTxt, stockTxt;
	private JLabel titleLbl, nameLbl, descriptionLbl, priceLbl, stockLbl;
	JButton btnBack, btnAdd;


	/**
	 * Create the application.
	 */
	public AddProductPage() {
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
		titleLbl.setBounds(363, 61, 93, 16);
		frame.getContentPane().add(titleLbl);
		
		nameLbl = new JLabel("Name");
		nameLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nameLbl.setBounds(35, 212, 56, 21);
		frame.getContentPane().add(nameLbl);
		
		nameTxt = new JTextField();
		nameTxt.setBounds(210, 202, 211, 43);
		frame.getContentPane().add(nameTxt);
		nameTxt.setColumns(10);
		
		descriptionLbl = new JLabel("Description");
		descriptionLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		descriptionLbl.setBounds(35, 283, 91, 21);
		frame.getContentPane().add(descriptionLbl);
		
		descriptionTxt = new JTextField();
		descriptionTxt.setColumns(10);
		descriptionTxt.setBounds(210, 270, 314, 96);
		frame.getContentPane().add(descriptionTxt);
		
		priceLbl = new JLabel("Price");
		priceLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		priceLbl.setBounds(35, 404, 56, 21);
		frame.getContentPane().add(priceLbl);
		
		priceTxt = new JTextField();
		priceTxt.setColumns(10);
		priceTxt.setBounds(210, 394, 211, 43);
		frame.getContentPane().add(priceTxt);
		
		stockLbl = new JLabel("Stock");
		stockLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		stockLbl.setBounds(35, 480, 56, 21);
		frame.getContentPane().add(stockLbl);
		
		stockTxt = new JTextField();
		stockTxt.setColumns(10);
		stockTxt.setBounds(210, 470, 211, 43);
		frame.getContentPane().add(stockTxt);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(12, 13, 97, 25);
		btnBack.addActionListener(this);
		frame.getContentPane().add(btnBack);
		
		btnAdd = new JButton("Add");
		btnAdd.setBounds(634, 615, 136, 25);
		btnAdd.addActionListener(this);
		frame.getContentPane().add(btnAdd);
	}
	
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(frame, message);
	}
	
	public JFrame getFrame() {
		return frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnBack)) {
			ProductController.getInstance().viewManageProductPage();
			frame.dispose();
		}
		else if(e.getSource().equals(btnAdd)) {
			String name = nameTxt.getText();
			String description = descriptionTxt.getText();
			String price = priceTxt.getText();
			String stock = stockTxt.getText();
			
			ProductController.getInstance().addProduct(name, description, price, stock);
//			frame.dispose();
		}
	}

}
