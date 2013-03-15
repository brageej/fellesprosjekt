package data;

import gui.TabPanel;

import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Main {
	
	Server server = new Server();
	User thisUser;
	
	public ArrayList<User> getPersons(){
		return server.getPersons();
		
	}
	
	public ArrayList<Group> getGroups(){
		return server.getGroups();
	}
	
	public void newUser(String username, String password, String name){
		server.addUser(new User(username, password, name));
	}
	

	
	public static void main(String[] args){
		Main main= new Main();
		main.newUser("Brageej","passord","Brage E Jarhen" );
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
