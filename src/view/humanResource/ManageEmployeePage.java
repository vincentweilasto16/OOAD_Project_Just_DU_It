package view.humanResource;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.EmployeeController;
import models.EmployeeModel;
import view.LoginPage;

public class ManageEmployeePage implements ActionListener{

	private JFrame frame;
	private JLabel titleLbl, idLbl, roleLbl, nameLbl, usernameLbl, statusLbl;
	private JTextField idTxt, roleTxt, nameTxt, usernameTxt, statusTxt;
	private JButton btnUpdate, btnFire, btnAddEmployee, btnLogout;
	private DefaultTableModel dtm;
	private JScrollPane scrollPane;
	private JTable table;
	private int selectedIndex = -1;
	private JTextField salaryTxt;
	private JLabel passwordLbl;
	private JTextField passwordTxt;

	
	public ManageEmployeePage() {
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
		titleLbl.setBounds(376, 34, 91, 16);
		frame.getContentPane().add(titleLbl);
		
		Vector<EmployeeModel> employees = EmployeeController.getInstance().getAllEmployee();
		
		Object [] header = new Object[] {"ID", "Role", "Name", "Price", "Status", "Salary"};
		Object [][] data = new Object[employees.size()][6];
		
		for (int i = 0; i < employees.size(); i++) {
			data[i][0] = employees.get(i).getId();
			data[i][1] = employees.get(i).getRoleId();
			data[i][2] = employees.get(i).getName();
			data[i][3] = employees.get(i).getUsername();
			data[i][4] = employees.get(i).getStatus();
			data[i][5] = employees.get(i).getSalary();
		}
			
		dtm = new DefaultTableModel(data, header) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		table = new JTable(dtm);
		table.setRowHeight(30);
		
		scrollPane = new JScrollPane(table);
		frame.getContentPane().add(scrollPane);
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				selectedIndex = table.getSelectedRow();
				String id = dtm.getValueAt(selectedIndex, 0).toString();
				String role = dtm.getValueAt(selectedIndex, 1).toString();
				String name = dtm.getValueAt(selectedIndex, 2).toString();
				String username = dtm.getValueAt(selectedIndex, 3).toString();
				String status = dtm.getValueAt(selectedIndex, 4).toString();
				String salary = dtm.getValueAt(selectedIndex, 5).toString();
				
				idTxt.setText(id);
				roleTxt.setText(role);
				nameTxt.setText(name);
				usernameTxt.setText(username);
				statusTxt.setText(status);
				salaryTxt.setText(salary);
			}
		});

		
		idLbl = new JLabel("ID");
		idLbl.setBounds(43, 360, 35, 25);
		frame.getContentPane().add(idLbl);
		
		idTxt = new JTextField();
		idTxt.setEditable(false);
		idTxt.setBounds(128, 357, 150, 30);
		frame.getContentPane().add(idTxt);
		idTxt.setColumns(10);
		
		roleLbl = new JLabel("Role");
		roleLbl.setBounds(44, 404, 42, 25);
		frame.getContentPane().add(roleLbl);
		
		roleTxt = new JTextField();
		roleTxt.setColumns(10);
		roleTxt.setBounds(128, 400, 150, 30);
		frame.getContentPane().add(roleTxt);
		
		nameLbl = new JLabel("Name");
		nameLbl.setBounds(43, 445, 73, 25);
		frame.getContentPane().add(nameLbl);
		
		nameTxt = new JTextField();
		nameTxt.setColumns(10);
		nameTxt.setBounds(128, 443, 150, 30);
		frame.getContentPane().add(nameTxt);
		
		usernameLbl = new JLabel("Username");
		usernameLbl.setBounds(342, 360, 72, 25);
		frame.getContentPane().add(usernameLbl);
		
		usernameTxt = new JTextField();
		usernameTxt.setColumns(10);
		usernameTxt.setBounds(448, 357, 150, 30);
		frame.getContentPane().add(usernameTxt);
		
		statusLbl = new JLabel("Status");
		statusLbl.setBounds(342, 404, 57, 25);
		frame.getContentPane().add(statusLbl);
		
		statusTxt = new JTextField();
		statusTxt.setColumns(10);
		statusTxt.setEditable(false);
		statusTxt.setBounds(448, 401, 150, 30);
		frame.getContentPane().add(statusTxt);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(43, 554, 97, 25);
		btnUpdate.addActionListener(this);
		frame.getContentPane().add(btnUpdate);
		
		btnFire = new JButton("Fire Employee");
		btnFire.setBounds(164, 554, 125, 25);
		btnFire.addActionListener(this);
		frame.getContentPane().add(btnFire);
		
		btnAddEmployee = new JButton("Add Employee");
		btnAddEmployee.setBounds(640, 615, 116, 25);
		btnAddEmployee.addActionListener(this);
		frame.getContentPane().add(btnAddEmployee);
		
		btnLogout = new JButton("Logout");
		btnLogout.setBounds(669, 13, 97, 25);
		btnLogout.addActionListener(this);
		frame.getContentPane().add(btnLogout);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 91, 703, 221);
		frame.getContentPane().add(scrollPane);
		
		JLabel lblSalary = new JLabel("Salary");
		lblSalary.setBounds(342, 445, 57, 25);
		frame.getContentPane().add(lblSalary);
		
		salaryTxt = new JTextField();
		salaryTxt.setColumns(10);
		salaryTxt.setBounds(448, 445, 150, 30);
		frame.getContentPane().add(salaryTxt);
		
		passwordLbl = new JLabel("Password");
		passwordLbl.setBounds(43, 493, 73, 25);
		frame.getContentPane().add(passwordLbl);
		
		passwordTxt = new JTextField();
		passwordTxt.setColumns(10);
		passwordTxt.setBounds(128, 494, 150, 30);
		frame.getContentPane().add(passwordTxt);
	
		scrollPane.setViewportView(table);
		
	}
	
	public void showManageProductPageMessage(String message) {
		JOptionPane.showMessageDialog(frame, message);
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnAddEmployee)) {
			EmployeeController.getInstance().viewAddEmployeePage();
			frame.dispose();
		}
		else if(e.getSource().equals(btnUpdate)) {
			String id = idTxt.getText();
			String name = nameTxt.getText();
			String salary = salaryTxt.getText();
		    String password = passwordTxt.getText();
			
			selectedIndex = EmployeeController.getInstance().updateEmployee(selectedIndex, id, name, salary, password);
		}
		else if(e.getSource().equals(btnFire)) {
				String id = idTxt.getText();
				String status = statusTxt.getText();
				EmployeeController.getInstance().fireEmployee(selectedIndex, id, status);
				selectedIndex = -1;
		}
		else if(e.getSource().equals(btnLogout)) {
			EmployeeController.getInstance().logout();
			frame.dispose();
		}
	
	}
}
