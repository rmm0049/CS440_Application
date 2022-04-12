package helpers;

import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * This class encapsulates the Swing code used
 * throughout these examples for getting usernames,
 * passwords, and other user inputs.
 */
public class Wizard implements AutoCloseable {
	public Wizard() {
		JTextField user = new JTextField();
		JPasswordField pass = new JPasswordField();
		int result = JOptionPane.showConfirmDialog(
			null,
			new Object[]{
				new JLabel("Username: "), user,
				new JLabel("Password: "), pass
			},
			"Credentials",
			JOptionPane.OK_CANCEL_OPTION
		);

		if (result != JOptionPane.OK_OPTION)
			throw new SecurityException("User did not provide credentials.");

		username = user.getText().toUpperCase();
		password = pass.getPassword();
	}

	private String username;
	public String getUsername() {
		return username;
	}

	private char[] password;
	public String getPassword() {
		return password != null ? new String(password) : null;
	}

	public String getData(String prompt) {
		JTextField data = new JTextField();
		int result = JOptionPane.showConfirmDialog(
			null,
			new Object[]{
				new JLabel(prompt + ": "), data
			},
			"Data",
			JOptionPane.OK_CANCEL_OPTION
		);

		if (result != JOptionPane.OK_OPTION)
			return null;

		return data.getText();
	}

	public boolean getConfirmation(String prompt) {
		int result = JOptionPane.showConfirmDialog(
			null,
			prompt,
			"Confirmation",
			JOptionPane.YES_NO_OPTION,
			JOptionPane.QUESTION_MESSAGE
		);

		return result == JOptionPane.YES_OPTION;
	}

	@Override
	public void close() {
		if (username != null) {
			username = null;
		}

		if (password != null) {
			Arrays.fill(password, (char) 0);
			password = null;
		}
	}
}
