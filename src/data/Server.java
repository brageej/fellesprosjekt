package data;

import java.util.ArrayList;

public class Server {
	
	private ArrayList<User> users = new ArrayList<User>();
	private ArrayList<Appointment> appointments = new ArrayList<Appointment>();
	private ArrayList<Group> groups = new ArrayList<Group>();
	private ArrayList<Room> rooms = new ArrayList<Room>();
	
	
	public ArrayList<User> getPersons(){
		return users;
	}
	
	
	//Legge til brukere i brukerlista ArrayList<User> Persons
	public void addUser(User user){
		users.add(user);
	}
}
