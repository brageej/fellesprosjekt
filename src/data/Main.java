package data;


import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Main {
	
	private Server server;
	private User user;
	
	public Main() {
		server = new Server(this);
		
	}
	
	public Server getServer() {
		return this.server;
	}
	
	public ArrayList<User> getPersons(){
		return new ArrayList<User>(server.getUsers().values());		
	}
	
	public User getUser() {
		return this.user;
	}
	
	public ArrayList<Room> getRooms() {
		return new ArrayList<Room>(server.getRooms().values());
	}
	
	public ArrayList<Group> getGroups(){
		return new ArrayList<Group>(server.getGroups().values());
	}
	
	public void newUser(String username, String password, String name,Main main){
		server.addUser(new User(username, password, name));
	}
	
	public void setUser(User user){
		this.user = user;
	}
	
	public boolean authenticateUser(String userName, String password) {
		User user = server.getUsers().get(userName);
		if (user == null) {
			return false;
		}
		
		if (user.correctPassword(password)) {
			this.user = user;
			return true;
		}
		return false;
	}
	
	void connectionLost() {
		System.out.println("lost");
	}
	
	public static void main(String[] args) {
		new Main();

	}
}
