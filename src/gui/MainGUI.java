package gui;

import java.awt.Dimension;

import javax.swing.JFrame;

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
	
	public void showLoginPanel() {
		loginFrame = new JFrame("CalFifty - Login");
		loginFrame.getContentPane().add(new LoginPanel());
		establishFrame(loginFrame);
	}
	
	public void showTabPanel() {
		tabFrame = new JFrame("CalFifty");
		tabFrame.getContentPane().add(new TabPanel(main));
		establishFrame(tabFrame);
//		tabPanel.setPreferredSize(new Dimension(1070, 660));
	}
	
	public void showNewAppointmentNullPanel() {
		newAppointmentFrame = new JFrame("CalFifty - New Appointment");
		newAppointmentFrame.getContentPane().add(new NewAppointmentPanel());
		establishFrame(newAppointmentFrame);
	}
	
	public void showNewAppointmentEditPanel(Appointment appointment) {
		newAppointmentFrame = new JFrame("CalFifty - Edit Appointment");
		newAppointmentFrame.getContentPane().add(new NewAppointmentPanel(appointment));
		establishFrame(newAppointmentFrame);
	}
	
	public void showAppointmentViewPanel(Appointment model, Participant participant) {
		appointmentViewFrame = new JFrame("CalFifty - Appointment");
		appointmentViewFrame.getContentPane().add(new AppointmentViewPanel(model, participant));
		establishFrame(appointmentViewFrame);
	}
	
	private void establishFrame(JFrame frame) {
		frame.pack();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		MainGUI m = new MainGUI(new Main());
		
		m.showTabPanel();
	}

}
