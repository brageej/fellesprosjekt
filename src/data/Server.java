package data;

import java.util.ArrayList;

public class Server {
	
	private ArrayList<User> Persons = new ArrayList<User>();
	private ArrayList<Appointment> Appointments = new ArrayList<Appointment>();
	private ArrayList<Group> Groups = new ArrayList<Group>();
	private ArrayList<Room> Rooms = new ArrayList<Room>();
	
	
	public ArrayList<User> getPersons(){
		return Persons;
	}
}
