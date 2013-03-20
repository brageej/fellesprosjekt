package gui;

import java.awt.Dimension;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import data.Appointment;
import data.Main;
import data.Participant;

public class MainGUI {
	
	JFrame tabFrame;
	JFrame newAppointmentFrame;
	JFrame appointmentViewFrame;
	JFrame loginFrame;
	
	Main main;
	
	public MainGUI(Main main) {
		this.main = main;
	}
	
	public void showInvalidInfo() {
		String message = "Invalid username/password";
		String title = "CalFifty - Could not login";
		URL warningIconURL = getClass().getResource("/warning.png");
		ImageIcon icon = new ImageIcon(warningIconURL);
		
		JOptionPane.showMessageDialog(loginFrame, message, title, JOptionPane.WARNING_MESSAGE, icon);
	}
	
	public void showLoginPanel() {
		loginFrame = new JFrame("CalFifty - Login");
		loginFrame.getContentPane().add(new LoginPanel(this.main, this));
		establishFrame(loginFrame);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void showTabPanel() {
		tabFrame = new JFrame("CalFifty");
		tabFrame.getContentPane().add(new TabPanel(main, this));
		establishFrame(tabFrame);
		tabFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		tabPanel.setPreferredSize(new Dimension(1070, 660));
	}
	
	public void showNewAppointmentNullPanel() {
		newAppointmentFrame = new JFrame("CalFifty - New Appointment");
		newAppointmentFrame.getContentPane().add(new NewAppointmentPanel(this.main, this));
		establishFrame(newAppointmentFrame);
	}
	
	public void showNewAppointmentEditPanel(Appointment appointment) {
		newAppointmentFrame = new JFrame("CalFifty - Edit Appointment");
		newAppointmentFrame.getContentPane().add(new NewAppointmentPanel(appointment, this.main, this));
		establishFrame(newAppointmentFrame);
	}
	
	public void showAppointmentViewPanel(Appointment model, Participant participant) {
		appointmentViewFrame = new JFrame("CalFifty - Appointment");
		appointmentViewFrame.getContentPane().add(new AppointmentViewPanel(model, participant, this.main, this));
		establishFrame(appointmentViewFrame);
	}
	
	private void establishFrame(JFrame frame) {
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		MainGUI m = new MainGUI(new Main());
		m.showLoginPanel();
	}

}
