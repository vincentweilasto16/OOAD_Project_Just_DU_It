package view.humanResource;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.EmployeeController;
import controller.ProductController;

public class AddEmployeePage implements ActionListener {

	private JPanel contentPane;
	private JFrame frame;
	private JTextField roleTxt;
	private JLabel titleLbl, roleLbl, nameLbl, usernameLbl;
	JButton btnBack, btnAdd;
	private JTextField nameTxt;
	private JTextField usernameTxt;
	private JTextField salaryTxt;
	private JLabel salaryLbl;

	public AddEmployeePage() {
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
		titleLbl.setBounds(363, 61, 93, 16);
		frame.getContentPane().add(titleLbl);
		
		roleLbl = new JLabel("Role");
		roleLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		roleLbl.setBounds(82, 195, 56, 21);
		frame.getContentPane().add(roleLbl);
		
		roleTxt = new JTextField();
		roleTxt.setBounds(208, 190, 211, 33);
		frame.getContentPane().add(roleTxt);
		roleTxt.setColumns(10);
		
		nameLbl = new JLabel("Name");
		nameLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nameLbl.setBounds(82, 255, 91, 21);
		frame.getContentPane().add(nameLbl);
		
		usernameLbl = new JLabel("Username");
		usernameLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		usernameLbl.setBounds(82, 310, 91, 21);
		frame.getContentPane().add(usernameLbl);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(12, 13, 97, 25);
		btnBack.addActionListener(this);
		frame.getContentPane().add(btnBack);
		
		btnAdd = new JButton("Add");
		btnAdd.setBounds(634, 615, 136, 25);
		btnAdd.addActionListener(this);
		frame.getContentPane().add(btnAdd);
		
		nameTxt = new JTextField();
		nameTxt.setColumns(10);
		nameTxt.setBounds(208, 250, 211, 33);
		frame.getContentPane().add(nameTxt);
		
		usernameTxt = new JTextField();
		usernameTxt.setColumns(10);
		usernameTxt.setBounds(208, 305, 211, 33);
		frame.getContentPane().add(usernameTxt);
		
		salaryTxt = new JTextField();
		salaryTxt.setColumns(10);
		salaryTxt.setBounds(208, 364, 211, 33);
		frame.getContentPane().add(salaryTxt);
		
		salaryLbl = new JLabel("Salary");
		salaryLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		salaryLbl.setBounds(82, 364, 56, 21);
		frame.getContentPane().add(salaryLbl);
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
			EmployeeController.getInstance().viewManageEmployeePage();
			frame.dispose();
		}
		else if(e.getSource().equals(btnAdd)) {
			String role = roleTxt.getText();
			String name = nameTxt.getText();
			String username = usernameTxt.getText();
			String salary = salaryTxt.getText();
			
			EmployeeController.getInstance().addEmployee(role, name, username, salary);
//			frame.dispose();
		}
	}
	

}
