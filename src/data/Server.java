package data;

import java.util.ArrayList;

public class Server {
	
	private ArrayList<User> Persons = new ArrayList<User>();
	private ArrayList<Appointment> Appointments = new ArrayList<Appointment>();
	private ArrayList<Group> Groups = new ArrayList<Group>();
	private ArrayList<Room> Rooms = new ArrayList<Room>();
	
	
	public ArrayList<User> getPersons(){
		return this.Persons;
	}
	
	public ArrayList<Group> getGroups(){
		return this.Groups;
	}
	
	public ArrayList<Appointment> getAppointments(){
		return this.Appointments;
	}
	
	
	//Legge til brukere i brukerlista ArrayList<User> Persons
	public void addUser(User user){
		Persons.add(user);
	}
}
