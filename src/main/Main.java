package main;

import static helpers.Configuration.JDBC_URL;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import helpers.Wizard;

// username -> TEAM_ALPHA
// password -> 875283

public class Main {

	public static void main(String[] args) {
		// Just simple connection to the Oracle DBMS
		// with team account
		System.out.println("Connecting...");
		try (
			Wizard wiz = new Wizard();
			Connection con = DriverManager.getConnection(
				JDBC_URL,
				wiz.getUsername(),
				wiz.getPassword()
			);
		) {
			System.out.println("Connected successfully.\n");
			
			// JFrame for application
			JFrame frame = new JFrame("Gym Management System");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(800,600);
			
			//Content for main landing page
			JPanel panel = new JPanel();
			JButton registerCustomerButton = new JButton("Register New Customer");
			JButton newEmpButton = new JButton("Add New Employee");
			panel.add(registerCustomerButton);
			panel.add(newEmpButton);
			
			//Action listeners for buttons
			registerCustomerButton.addActionListener(registerCustomer);
			newEmpButton.addActionListener(newEmployee);
			
			frame.getContentPane().add(BorderLayout.WEST,panel);
			frame.setVisible(true);
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ActionListener newEmployee = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame newEmployeeFrame = new JFrame("Register New Customer");
			newEmployeeFrame.setSize(600,400);
			JPanel newEmployeePanel = new JPanel();
			
			newEmployeeFrame.setVisible(true);
		}
	};
	
	public static ActionListener registerCustomer = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame registerCustomerFrame = new JFrame("Register New Customer");
			registerCustomerFrame.setSize(600,400);
			JPanel registerCustomerPanel = new JPanel();
			
			// Text fields
			JTextField fname = new JTextField(10);
			JTextField minitial = new JTextField(1);
			JTextField lname = new JTextField(10);
			JTextField mem_id = new JTextField(10);
			JTextField start_date = new JTextField(10);
			JTextField exp_date = new JTextField(10);
			JTextField e_id = new JTextField(10);
			JTextField mem_type = new JTextField(10);
			
			// Labels
			JLabel fname_label = new JLabel("First Name");
			JLabel minitial_label = new JLabel("Middle Initial");
			JLabel lname_label = new JLabel("Last Name");
			JLabel mem_id_label = new JLabel("Member ID");
			JLabel start_date_label = new JLabel("Start Date");
			JLabel exp_date_label = new JLabel("Expiration Date");
			JLabel e_id_label = new JLabel("Trainer EID");
			JLabel mem_type_label = new JLabel("Membership Type");
			
			JButton submit = new JButton("Submit");
			
			
			registerCustomerPanel.add(fname_label);
			registerCustomerPanel.add(fname);
			registerCustomerPanel.add(minitial_label);
			registerCustomerPanel.add(minitial);
			registerCustomerPanel.add(lname_label);
			registerCustomerPanel.add(lname);
			registerCustomerPanel.add(mem_id_label);
			registerCustomerPanel.add(mem_id);
			registerCustomerPanel.add(start_date_label);
			registerCustomerPanel.add(start_date);
			registerCustomerPanel.add(exp_date_label);
			registerCustomerPanel.add(exp_date);
			registerCustomerPanel.add(e_id_label);
			registerCustomerPanel.add(e_id);
			registerCustomerPanel.add(mem_type_label);
			registerCustomerPanel.add(mem_type);
			
			ActionListener submitCustomerRegister = new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					//Submit INSERT query for new customer
					String query = "INSERT INTO CUSTOMERS\r\n" + 
							"VALUES (?,?,?,?,?,?,?,?)";
					
					Connection con;
					try {
						con = DriverManager.getConnection(JDBC_URL, "TEAM_ALPHA", "875283");
						PreparedStatement prep = con.prepareStatement(query);
						
						
						if (fname.getText().isEmpty()
								|| lname.getText().isEmpty()
								|| mem_id.getText().isEmpty()
								|| mem_type.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Last Name, mem_id, and mem_type cannot be null!","Error", JOptionPane.PLAIN_MESSAGE);
						} else {
							prep.setString(1, fname.getText());
							prep.setString(2, minitial.getText());
							prep.setString(3, lname.getText());
							prep.setString(4, mem_id.getText());
							prep.setString(5, start_date.getText());
							prep.setString(6, exp_date.getText());
							prep.setString(7, e_id.getText());
							prep.setString(8, mem_type.getText());
							
							try {
								final int count = prep.executeUpdate();
								registerCustomerFrame.dispose();
								JOptionPane.showMessageDialog(null, "Successfully added new customer!","Success", JOptionPane.PLAIN_MESSAGE);
							}
							catch (SQLException sqle) {
								System.err.println(sqle.getMessage());
							}
							
						}
						
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					
				}
			};
			
			submit.addActionListener(submitCustomerRegister);
			registerCustomerPanel.add(submit);
			registerCustomerFrame.getContentPane().add(registerCustomerPanel);
			
			registerCustomerFrame.setVisible(true);
			
		}
	};
	
	
}
