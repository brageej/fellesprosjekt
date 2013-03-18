package data;

import java.util.ArrayList;

public class Server {
	
	private ArrayList<User> users = new ArrayList<User>();
	private ArrayList<Appointment> appointments = new ArrayList<Appointment>();
	private ArrayList<Group> groups = new ArrayList<Group>();
	private ArrayList<Room> rooms = new ArrayList<Room>();
	
	
	public ArrayList<User> getPersons(){
<<<<<<< HEAD
		return users;
=======
		return this.Persons;
	}
	
	public ArrayList<Group> getGroups(){
		return this.Groups;
	}
	
	public ArrayList<Appointment> getAppointments(){
		return this.Appointments;
>>>>>>> df884f1a9e13b589e19d0a2324558da2c13c4ec3
	}
	
	
	//Legge til brukere i brukerlista ArrayList<User> Persons
	public void addUser(User user){
		users.add(user);
	}
}
