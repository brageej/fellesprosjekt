package data;

import java.util.ArrayList;


public class User {
	
	private String username;
	
	private String password;
	private String name;
	
	private ArrayList<Appointment> myAppointments;
	private ArrayList<Appointment> owner;
	private ArrayList<Group> leader;
	private ArrayList<Participant> appointments;
	private ArrayList<Member> groups;
	private Main main;
	
	
	public User(String username, String password, String name, Main main){
		this.username = username;
		this.name = name;
		this.password = password;
		this.main = main;
		//setMyAppointments();
		
	}
	
//	private void setMyAppointments(){
//		myAppointments = main.getMyAppointments();
//	}
	
	public String getName(){
		return this.name;
	}

//	@Override
//	public String toString() {
//		return name;
//	}
	
	public String getUserName(){
		return this.username;
	}
	
//	public ArrayList<Appointment> getOwner(){
//		return;
//		
//	}
	
	

}
