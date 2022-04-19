package helpers;

import static helpers.Configuration.JDBC_URL;

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

public class Listeners {

	public static ActionListener newEmployee = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame newEmployeeFrame = new JFrame("Add New Employee");
			newEmployeeFrame.setSize(850, 200);
			JPanel newEmployeePanel = new JPanel();

			JTextField fname = new JTextField(10);
			JTextField minitial = new JTextField(1);
			JTextField lname = new JTextField(10);
			JTextField e_id = new JTextField(10);
			JTextField job_title = new JTextField(10);
			JTextField hourly_rate = new JTextField(10);
			JTextField manager_eid = new JTextField(10);

			JLabel fname_label = new JLabel("First Name");
			JLabel minitial_label = new JLabel("Middle Initial");
			JLabel lname_label = new JLabel("Last Name");
			JLabel e_id_label = new JLabel("Employee ID");
			JLabel job_title_label = new JLabel("Job Title");
			JLabel hourly_rate_label = new JLabel("Hourly Rate");
			JLabel manager_eid_label = new JLabel("Manager EID");

			JButton submit = new JButton("Submit");

			newEmployeePanel.add(fname_label);
			newEmployeePanel.add(fname);
			newEmployeePanel.add(minitial_label);
			newEmployeePanel.add(minitial);
			newEmployeePanel.add(lname_label);
			newEmployeePanel.add(lname);
			newEmployeePanel.add(e_id_label);
			newEmployeePanel.add(e_id);
			newEmployeePanel.add(job_title_label);
			newEmployeePanel.add(job_title);
			newEmployeePanel.add(hourly_rate_label);
			newEmployeePanel.add(hourly_rate);
			newEmployeePanel.add(manager_eid_label);
			newEmployeePanel.add(manager_eid);

			newEmployeePanel.add(submit);

			ActionListener submitNewEmployee = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// Submit INSERT query for new customer
					String query = "INSERT INTO EMPLOYEES\r\n" + "VALUES (?,?,?,?,?,?,?)";

					Connection con;
					try {
						con = DriverManager.getConnection(JDBC_URL, "TEAM_ALPHA", "875283");
						PreparedStatement prep = con.prepareStatement(query);

						if (fname.getText().isEmpty() || lname.getText().isEmpty() || e_id.getText().isEmpty()
								|| job_title.getText().isEmpty() || manager_eid.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null,
									"Last Name, e_id, job title and hourly rate cannot be null!", "Error",
									JOptionPane.PLAIN_MESSAGE);
						} else {
							prep.setString(1, fname.getText());
							prep.setString(2, minitial.getText());
							prep.setString(3, lname.getText());
							prep.setString(4, e_id.getText());
							prep.setString(5, job_title.getText());
							prep.setString(6, hourly_rate.getText());
							prep.setString(7, manager_eid.getText());

							try {
								final int count = prep.executeUpdate();
								newEmployeeFrame.dispose();
								JOptionPane.showMessageDialog(null, "Successfully added new employee!", "Success",
										JOptionPane.PLAIN_MESSAGE);
							} catch (SQLException sqle) {
								System.err.println(sqle.getMessage());
							}

						}

					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}
			};

			submit.addActionListener(submitNewEmployee);

			newEmployeeFrame.add(newEmployeePanel);
			newEmployeeFrame.setVisible(true);
		}
	};

	public static ActionListener registerCustomer = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame registerCustomerFrame = new JFrame("Register New Customer");
			registerCustomerFrame.setSize(850, 200);
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
					// Submit INSERT query for new customer
					String query = "INSERT INTO CUSTOMERS\r\n" + "VALUES (?,?,?,?,?,?,?,?)";

					Connection con;
					try {
						con = DriverManager.getConnection(JDBC_URL, "TEAM_ALPHA", "875283");
						PreparedStatement prep = con.prepareStatement(query);

						if (fname.getText().isEmpty() || lname.getText().isEmpty() || mem_id.getText().isEmpty()
								|| mem_type.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Last Name, mem_id, and mem_type cannot be null!",
									"Error", JOptionPane.PLAIN_MESSAGE);
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
								JOptionPane.showMessageDialog(null, "Successfully added new customer!", "Success",
										JOptionPane.PLAIN_MESSAGE);
							} catch (SQLException sqle) {
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

	public static ActionListener addNewClass = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
	};
	
	public static ActionListener newEquipment = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame newEquipmentFrame = new JFrame("Add New Equipment");
			newEquipmentFrame.setSize(850, 200);
			JPanel newEquipmentPanel = new JPanel();

			JTextField equip_name = new JTextField(10);
			JTextField serial_num = new JTextField(1);
			JTextField type = new JTextField(10);
			JTextField brand = new JTextField(10);
			JTextField weight = new JTextField(10);
			JTextField location = new JTextField(10);
			JTextField e_id = new JTextField(10);

			JLabel equip_name_label = new JLabel("Equipment Name");
			JLabel serial_num_label = new JLabel("Serial Number");
			JLabel type_label = new JLabel("Type");
			JLabel brand_label = new JLabel("Brand");
			JLabel weight_label = new JLabel("Weight");
			JLabel location_label = new JLabel("Location");
			JLabel e_id_label = new JLabel("Employee ID");

			JButton submit = new JButton("Submit");

			newEquipmentPanel.add(equip_name_label);
			newEquipmentPanel.add(equip_name);
			newEquipmentPanel.add(serial_num_label);
			newEquipmentPanel.add(serial_num);
			newEquipmentPanel.add(type_label);
			newEquipmentPanel.add(type);
			newEquipmentPanel.add(brand_label);
			newEquipmentPanel.add(brand);
			newEquipmentPanel.add(weight_label);
			newEquipmentPanel.add(weight);
			newEquipmentPanel.add(location_label);
			newEquipmentPanel.add(location);
			newEquipmentPanel.add(e_id_label);
			newEquipmentPanel.add(e_id);

			newEquipmentPanel.add(submit);

			ActionListener submitNewEquipment = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// Submit INSERT query for new customer
					String query = "INSERT INTO EQUIPMENT\r\n" + "VALUES (?,?,?,?,?,?,?)";

					Connection con;
					try {
						con = DriverManager.getConnection(JDBC_URL, "TEAM_ALPHA", "875283");
						PreparedStatement prep = con.prepareStatement(query);

						if (equip_name.getText().isEmpty() || serial_num.getText().isEmpty() || type.getText().isEmpty()
								|| brand.getText().isEmpty() || location.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null,
									"Equipment name, serial number, type, brand, and location cannot be null!", "Error",
									JOptionPane.PLAIN_MESSAGE);
						} else {
							prep.setString(1, equip_name.getText());
							prep.setString(2, serial_num.getText());
							prep.setString(3, type.getText());
							prep.setString(4, brand.getText());
							prep.setString(5, weight.getText());
							prep.setString(6, location.getText());
							prep.setString(7, e_id.getText());

							try {
								final int count = prep.executeUpdate();
								newEquipmentFrame.dispose();
								JOptionPane.showMessageDialog(null, "Successfully added new equipment!", "Success",
										JOptionPane.PLAIN_MESSAGE);
							} catch (SQLException sqle) {
								System.err.println(sqle.getMessage());
							}

						}

					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}
			};

			submit.addActionListener(submitNewEquipment);

			newEquipmentFrame.add(newEquipmentPanel);
			newEquipmentFrame.setVisible(true);
		}
	};

}
