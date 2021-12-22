package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class RegisterPage extends JFrame {
	
	JInternalFrame internalFrame;
	JDesktopPane desktopPane;
	JPanel mainPanel, northPanel, centerPanel, southPanel, rolePanel;
	JLabel titleLbl, nameLbl, salaryLbl, usernameLbl, passwordLbl, confirmPasswordLbl, roleLbl, loginLbl;
	JTextField usernameTxt, nameTxt, salaryTxt;
	JPasswordField passwordTxt, confirmPasswordTxt;
	ButtonGroup roleGroup;
	JRadioButton cashierBtn, productManagementBtn, humanResourceManagementBtn, managerBtn;
//	JComboBox<Role> roleCombo;
	JButton registerBtn;
	
	JFrame frame;
	
	public void setDesktopPane() {
		desktopPane = new JDesktopPane();
		internalFrame = new JInternalFrame();
		internalFrame.setSize(500, 400);
		internalFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		internalFrame.setLocation((800 - 500)/2, (600 - 400)/2);
		internalFrame.setResizable(false);
		internalFrame.setVisible(true);
	}
	
	public RegisterPage() {
		frame = this;
		
//		setDesktopPane();
		
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		centerPanel = new JPanel(new GridLayout(6, 6));
		rolePanel = new JPanel();
		northPanel = new JPanel();
		southPanel = new JPanel();
		
		titleLbl = new JLabel("Just DU It!");
		titleLbl.setFont(new Font("Roboto", Font.BOLD, 26));
		northPanel.add(titleLbl);
		
		nameLbl = new JLabel("Name");
		usernameLbl = new JLabel("Username");
		passwordLbl = new JLabel("Password");
		confirmPasswordLbl = new JLabel("Confirm Password");
		salaryLbl = new JLabel("Salary");
		roleLbl = new JLabel("Role");
		nameTxt = new JTextField();
		usernameTxt = new JTextField();
		passwordTxt = new JPasswordField();
		confirmPasswordTxt = new JPasswordField();
		salaryTxt = new JPasswordField();
		cashierBtn = new JRadioButton("Cashier");
		productManagementBtn = new JRadioButton("Product Management");
		humanResourceManagementBtn = new JRadioButton("Human Resource Management");
		managerBtn = new JRadioButton("Manager");
		roleGroup = new ButtonGroup();
		roleGroup.add(cashierBtn);
		roleGroup.add(productManagementBtn);
		roleGroup.add(humanResourceManagementBtn);
		roleGroup.add(managerBtn);
		rolePanel.add(cashierBtn);
		rolePanel.add(productManagementBtn);
		rolePanel.add(humanResourceManagementBtn);
		rolePanel.add(managerBtn);
		
		centerPanel.add(nameLbl);
		centerPanel.add(nameTxt);
		centerPanel.add(usernameLbl);
		centerPanel.add(usernameTxt);
		centerPanel.add(passwordLbl);
		centerPanel.add(passwordTxt);
		centerPanel.add(confirmPasswordLbl);
		centerPanel.add(confirmPasswordTxt);
		centerPanel.add(salaryLbl);
		centerPanel.add(salaryTxt);
		centerPanel.add(roleLbl);
		centerPanel.add(rolePanel);
		
		registerBtn = new JButton("Register");
		loginLbl = new JLabel("Login");
		loginLbl.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		loginLbl.setForeground(Color.BLUE);
		southPanel.add(registerBtn);
		southPanel.add(loginLbl);
		
		//add components
		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		
//		internalFrame.add(mainPanel);
//		
//		desktopPane.add(internalFrame);
//		add(desktopPane);
		
		add(mainPanel);
		
		loginLbl.addMouseListener(new MouseListener() {
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
				new LoginPage();
				frame.dispose();
			}
		});
		
		registerBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				register();
			}
		});
		
		init();
	}
	
	void register() {
		String name = nameTxt.getText();
		String username = usernameTxt.getText();
		String password = new String(passwordTxt.getPassword());
		String confirmPassword = new String(confirmPasswordTxt.getPassword());
		int salary = Integer.parseInt(salaryTxt.getText());
		
		if(name.equals("") || username.equals("") || password.equals("") || confirmPassword.equals("") || salaryTxt.getText().equals("")) {
			JOptionPane.showMessageDialog(frame, "All field must not be empty!");
		}
		else if(!password.equals(confirmPassword)) {
			JOptionPane.showMessageDialog(frame, "Password and Confirm Password must be same!");
		}
		else if(!cashierBtn.isSelected() && !productManagementBtn.isSelected() && !humanResourceManagementBtn.isSelected() && !managerBtn.isSelected()) {
			JOptionPane.showMessageDialog(frame, "Role Must Be Selected!");
		}
		else {
			
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
