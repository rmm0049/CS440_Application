package main;

import static helpers.Configuration.JDBC_URL;

import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import helpers.Listeners;
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
			JButton newClassButton = new JButton("Add New Class");
			JButton newEquipmentButton = new JButton("Add New Equipment");
			JButton viewCustomersButton = new JButton("View Customers");
			JButton viewEquipmentButton = new JButton("View Equipment");
			JButton viewEmployeesButton = new JButton("View Employees");
			JButton viewClassesButton = new JButton("View Classes");
			JButton viewEmpScheduleButton = new JButton("View Employee Schedule");


			frame.add(registerCustomerButton);
			frame.add(newEmpButton);
			frame.add(newClassButton);
			frame.add(newEquipmentButton);			
			frame.add(viewCustomersButton);
			frame.add(viewEmployeesButton);
			frame.add(viewClassesButton);
			frame.add(viewEquipmentButton);
			frame.add(viewEmpScheduleButton);
			frame.add(new JButton("filler"));
			frame.add(new JButton("filler"));
			frame.add(new JButton("filler"));
			
			
			//Action listeners for buttons
			registerCustomerButton.addActionListener(Listeners.registerCustomer);
			newEmpButton.addActionListener(Listeners.newEmployee);
			newClassButton.addActionListener(Listeners.addNewClass);
			newEquipmentButton.addActionListener(Listeners.newEquipment);
			viewCustomersButton.addActionListener(Listeners.viewCustomers);
			viewEquipmentButton.addActionListener(Listeners.viewEquipment);
			viewEmployeesButton.addActionListener(Listeners.viewEmployees);
			viewClassesButton.addActionListener(Listeners.viewClasses);
			viewEmpScheduleButton.addActionListener(Listeners.viewEmpSchedule);
			
			frame.setLayout(new GridLayout(3,4));
//			frame.add(panel);
			frame.setVisible(true);
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
