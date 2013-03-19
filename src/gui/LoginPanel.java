package gui;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import data.Main;

public class LoginPanel extends JPanel{
	
	private JLabel userLabel;
	private JLabel passLabel;
	private JButton loginButton;
	private JButton exitButton;
	private JTextField usernameField;
	private JPasswordField passwordField;

	private Main main;
	private MainGUI mainGui;
	
	public LoginPanel(Main main, MainGUI mainGui) {
		this.main = main;
		this.mainGui = mainGui;
		

		setLayout(new GridBagLayout());
		
		initUserLabel();
		initPassLabel();
		initLoginButton();
		initExitButton();
		initUsernameField();
		initPasswordField();
	}
	
	private void initUserLabel() {
		GridBagConstraints userLabelC = new GridBagConstraints();
		userLabelC.fill = GridBagConstraints.NONE;
		userLabelC.insets = new Insets(30,20,10,0);
		
		userLabel = new JLabel("Username: ");
		userLabelC.gridx = 0;
		userLabelC.gridy = 0;
		userLabelC.gridwidth = 1;
		add(userLabel, userLabelC);
		
	}
	
	private void initPassLabel() {
		GridBagConstraints passLabelC = new GridBagConstraints();
		passLabelC.fill = GridBagConstraints.NONE;
		passLabelC.insets = new Insets(10,20,30,0);
		
		passLabel = new JLabel("Password: ");
		passLabelC.gridx = 0;
		passLabelC.gridy = 1;
		passLabelC.gridwidth = 1;
		add(passLabel, passLabelC);
	}
	
	private void initUsernameField() {
		GridBagConstraints userFieldC = new GridBagConstraints();
		userFieldC.fill = GridBagConstraints.NONE;
		userFieldC.insets = new Insets(30,5,10,20);
		
		usernameField = new JTextField(20);
		userFieldC.gridx = 1;
		userFieldC.gridy = 0;
		userFieldC.gridwidth = 2;
		add(usernameField, userFieldC);
	}
	
	private void initPasswordField() {
		GridBagConstraints passFieldC = new GridBagConstraints();
		passFieldC.fill = GridBagConstraints.NONE;
		passFieldC.insets = new Insets(10,5,30,20);
		
		passwordField = new JPasswordField(20);
		passFieldC.gridx = 1;
		passFieldC.gridy = 1;
		passFieldC.gridwidth = 2;
		add(passwordField, passFieldC);
	}
	
	private void initLoginButton() {
		GridBagConstraints logButC = new GridBagConstraints();
		logButC.fill = GridBagConstraints.NONE;
		logButC.insets = new Insets(0,0,20,0);
		
		loginButton = new JButton("Login");
		loginButton.addActionListener(new LoginListener());
		logButC.gridx = 2;
		logButC.gridy = 2;
		logButC.gridwidth = 1;
		add(loginButton, logButC);
	}
	
	private void initExitButton() {
		GridBagConstraints exitButC = new GridBagConstraints();
		exitButC.fill = GridBagConstraints.NONE;
		exitButC.insets = new Insets(0,0,20,0);
		
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ExitListener());
		exitButC.gridx = 1;
		exitButC.gridy = 2;
		exitButC.gridwidth = 1;
		add(exitButton, exitButC);
	}
	
	private class LoginListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String pass = String.valueOf(passwordField.getPassword());
			if (main.authenticateUser(usernameField.getText(), pass)) {
				mainGui.showTabPanel();
				mainGui.loginFrame.dispose();
			}
		}
	}
	
	private class ExitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
//	Main for testing purposes!
//	
//	public static void main(String[] args) {
//		JFrame frame = new JFrame("Fabulous!");
//		frame.getContentPane().add(new LoginPanel());
//		frame.pack();
//		frame.setResizable(false);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setVisible(true);
//	}
	
}
