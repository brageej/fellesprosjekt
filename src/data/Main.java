package data;

import gui.TabPanel;

import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Main {
	
<<<<<<< HEAD
	private Server server;
	private User user;
	
	public Main() {
		server = new Server();
	}
=======
	Server server = new Server();
	User thisUser;
>>>>>>> df884f1a9e13b589e19d0a2324558da2c13c4ec3
	
	public ArrayList<User> getPersons(){
		return server.getPersons();		
	}
	
	public User getUser() {
		return this.user;
	}
	
	public ArrayList<Group> getGroups(){
		return server.getGroups();
	}
	
	public void newUser(String username, String password, String name,Main main){
		server.addUser(new User(username, password, name, main));
	}
	
	public User getUser(){
		return this.thisUser;
	}
	
	public void setUser(User user){
		this.thisUser = user;
	}
	
//	//public ArrayList<Appointment> getMyAppointments(){
//		ArrayList<Appointment> appointments = server.getAppointments();
//		ArrayList<Appointment> myAppointments = new ArrayList<Appointment>();
//		for(int i = 0; i<appointments.size();i++){
//			//if(appointment)
//		}
//	}
	

	
	public static void main(String[] args){
		Main main= new Main();
		main.newUser("Brageej","passord","Brage E Jarhen",main );
		main.setUser(new User("brageej","passord","Brage E Jahren",main));
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("CalFifty");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JComponent contentPane = new TabPanel(main);
		contentPane.setOpaque(true);
		frame.getContentPane().add(contentPane);
		frame.pack();
		frame.setVisible(true);
	}

	
}
