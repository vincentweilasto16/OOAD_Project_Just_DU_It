package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import connect.Connect;
import main.Main;
import models.Employee;

public class LoginPage extends JFrame {
	
	JInternalFrame internalFrame;
	JDesktopPane desktopPane;
	JPanel mainPanel, northPanel, centerPanel, southPanel;
	JLabel titleLbl, usernameLbl, passwordLbl, registerLbl;
	JTextField usernameTxt;
	JPasswordField passwordTxt;
	JButton loginBtn;
	
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

	public LoginPage() {
		frame = this;
		
		setDesktopPane();
		
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		centerPanel = new JPanel(new GridLayout(2, 2, 0, 20));
		northPanel = new JPanel();
		southPanel = new JPanel();
		
		titleLbl = new JLabel("Just DU It!");
		titleLbl.setFont(new Font("Roboto", Font.BOLD, 26));
		northPanel.add(titleLbl);
		
		usernameLbl = new JLabel("username");
		passwordLbl = new JLabel("password");
		usernameTxt = new JTextField();
		passwordTxt = new JPasswordField();
		
		centerPanel.add(usernameLbl);
		centerPanel.add(usernameTxt);
		centerPanel.add(passwordLbl);
		centerPanel.add(passwordTxt);
		
		loginBtn = new JButton("Login");
		registerLbl = new JLabel("Register");
		registerLbl.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		registerLbl.setForeground(Color.BLUE);
		southPanel.add(loginBtn);
		southPanel.add(registerLbl);
		
		//add components
		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		
		internalFrame.add(mainPanel);
		
		desktopPane.add(internalFrame);
		add(desktopPane);
		
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		
		registerLbl.addMouseListener(new MouseListener() {
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
				new RegisterPage();
				frame.dispose();
			}
		});
		
		
		init();
	}
	
	void login() {
		String username = usernameTxt.getText();
		String password = new String(passwordTxt.getPassword());
		
		if(username.equals("") || password.equals("")) {
			JOptionPane.showMessageDialog(frame, "All field must not be empty!");
		}
		else {
			Connect con = Connect.getInstance();
			PreparedStatement ps = con.preparedStatement("SELECT * FROM employee WHERE username = ? AND password = ?");
			
			try {
				ps.setString(1, username);
				ps.setString(2, password);
				ps.execute();
				
				ResultSet rs = ps.getResultSet();
				
				if(rs.next()) {
					Main.employee = new Employee(rs.getInt("id"), rs.getInt("role_id"), rs.getString("name"), rs.getString("username"), rs.getInt("salary"), rs.getString("status"), rs.getString("password"));
					if(Main.employee.getRoleId() == 1){
						new CashierPage();
						this.dispose();
					}
					else if(Main.employee.getRoleId() == 2){
						new ProductManagementPage();
						this.dispose();
					}
					else if(Main.employee.getRoleId() == 3){
						new HumanResourceManagementPage();
						this.dispose();
					}
					else if(Main.employee.getRoleId() == 4){
						new ManagerPage();
						this.dispose();
					}
				}
				else {
					JOptionPane.showMessageDialog(this, "Invalid Username or Password!");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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