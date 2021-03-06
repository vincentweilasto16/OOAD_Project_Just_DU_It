package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import controller.EmployeeController;

public class LoginPage extends JFrame{
	
	JInternalFrame internalFrame;
	JDesktopPane desktopPane;
	JPanel mainPanel, northPanel, centerPanel, southPanel;
	JLabel titleLbl, usernameLbl, passwordLbl;
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
		southPanel.add(loginBtn);

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
				String username = usernameTxt.getText();
				String password = new String(passwordTxt.getPassword());
				
				EmployeeController.getInstance().login(username, password);
//				frame.dispose();
			}
		});
		
		
		init();
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(frame, message);
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
