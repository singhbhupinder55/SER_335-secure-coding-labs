package org.jfm.main;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

// SER335 LAB6 TASK1 - Added import
import edu.asu.ser335.jfm.*;

// SER335 LAB6 TASK1 - Added imports

import io.whitfin.siphash.SipHasher;


/**
 * @author Nikhil Hiremath
 *
 */

public class LoginPannel extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel labelUsername = new JLabel("Enter username: ");
	private JLabel labelPassword = new JLabel("Enter password: ");
	private JLabel labelRole = new JLabel("Enter Role: ");
	private JLabel message;
	private JTextField textUsername = new JTextField(20);
	private JPasswordField fieldPassword = new JPasswordField(20);
	// private JTextField textRole = new JTextField(20);
	private JButton buttonLogin = new JButton("Login");
	private JPanel newPanel;

	private JComboBox<String> roleList;
	public static String role;

	public static MainFrame mainFrame;

	// SER335 LAB6 TASK1
	private static IValidateUserStrategy validateStrategy;


	public LoginPannel() {
		super("Login Pannel");

		// create a new panel with GridBagLayout manager
		newPanel = new JPanel(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);

		// add components to the panel
		// UserName
		constraints.gridx = 0;
		constraints.gridy = 0;
		newPanel.add(labelUsername, constraints);

		constraints.gridx = 1;
		newPanel.add(textUsername, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;

		// Password
		newPanel.add(labelPassword, constraints);

		constraints.gridx = 1;
		newPanel.add(fieldPassword, constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;

		// Role
		newPanel.add(labelRole, constraints);
		constraints.gridx = 1;

		// newPanel.add(textRole, constraints);
		// drop down

		roleList = new JComboBox<String>(RolesSingleton.getRoleMapping().getDisplayRoles());

		// add to the parent container (e.g. a JFrame):
		newPanel.add(roleList, constraints);

		// System.out.println("Selected role: " + role);

		constraints.gridx = 0;
		constraints.gridy = 3;

		message = new JLabel();
		newPanel.add(message, constraints);
		constraints.gridx = 1;

		constraints.gridwidth = 3;
		constraints.anchor = GridBagConstraints.CENTER;
		newPanel.add(buttonLogin, constraints);
		// Adding the listeners to components..

		buttonLogin.addActionListener(this);

		// set border for the panel
		newPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Login Panel"));

		// add the panel to this frame
		add(newPanel);

		// SER335 LAB6 TASK1 - Load password validation strategy
		validateStrategy = ValidateUserFactory.getStrategy();

		pack();


		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String userName = textUsername.getText();
		String password = String.valueOf(fieldPassword.getPassword());
		role = (String) roleList.getSelectedItem();

		// Validate User and redirect to Main application on successful login
		if (validateUser(userName, password, role)) {
			message.setText(" Hello " + userName + "");

			// on successful login redirect to next screen
			mainFrame = new MainFrame(role);
			// Validate frames that have preset sizes
			mainFrame.validate();
			// Center the window
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Dimension frameSize = mainFrame.getSize();
			if (frameSize.height > screenSize.height) {
				frameSize.height = screenSize.height;
			}
			if (frameSize.width > screenSize.width) {
				frameSize.width = screenSize.width;
			}
			mainFrame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
			mainFrame.setVisible(true);

			// Close Login Window (JPanel)
			dispose();
		} else {
			message.setText(" Invalid user.. ");
		}
	}


	// Task H1: Implement authentication for login
	public boolean validateUser(String uName, String pwd, String role) {

		try {

			// Task H1 - Step 1: Check if user exists
			if (!UsersSingleton.getUserRoleMapping().containsKey(uName)) {
				// SER335 LAB6 TASK1 - Log failed attempt
				JFMLogger.logFailedLogin(uName, pwd, role);
				return false;
			}

			// SER335 LAB6 TASK1 - Special Null Strategy handling for blank username and password
			if (validateStrategy instanceof NullValidateUserStrategy && uName.isEmpty() && pwd.isEmpty()) {
				JFMLogger.logSuccessfulLogin(uName, role);
				return true;
			}


			// Task H1 - Step 2: Check if role matches
			if (!UsersSingleton.getUserRoleMapping().get(uName).equals(role)){
				// SER335 LAB6 TASK1 - Log failed attempt
				JFMLogger.logFailedLogin(uName, pwd, role);
				return false;
			}

			// Task H1 - Step 3: Validate password using Strategy (after basic checks)
			if (!validateStrategy.validateUser(pwd)) {
				JFMLogger.logFailedLogin(uName, pwd, role);
				return false;
			}

			// Task H1 - Step 3a: Generate salt and hash the input password
			SaltsSingleton salts = SaltsSingleton.getUserSalts();
			String salt = salts.getUserSalt(uName);
			if (salt == null) {
				// SER335 LAB6 TASK1 - Log failed attempt
				JFMLogger.logFailedLogin(uName, pwd, role);
				return false;
			}

			long hashed = SipHasher.hash(salt.getBytes(), pwd.getBytes());

			System.out.println("DEBUG: Hashed value for login attempt = " + hashed);


			// Task H1 - Step 3b: Compare hashed password with stored password
			if (!UsersSingleton.getUserPasswordMapping().containsKey(uName)) {
				// SER335 LAB6 TASK1 - Log failed attempt
				JFMLogger.logFailedLogin(uName, pwd, role);
				return false;
			}

			long storedHash = Long.parseLong(UsersSingleton.getUserPasswordMapping().get(uName));

			if (hashed != storedHash) {
				// SER335 LAB6 TASK1 - Log failed attempt
				JFMLogger.logFailedLogin(uName, pwd, role);
				return false;
			}

			// Optional: Log successful login (Bonus)
			JFMLogger.logSuccessfulLogin(uName, role);


			return true; // Login success!

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
}
