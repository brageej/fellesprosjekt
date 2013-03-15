package data;

import java.util.ArrayList;


public class User {
	
	private String username;;
	
	private String password;
	private String name;
	
	private ArrayList<Appointment> owner;
	private ArrayList<Group> leader;
	private ArrayList<Participant> appointments;
	private ArrayList<Member> groups;
	
	
	public User(String username, String password, String name){
		this.username = username;
		this.name = name;
		this.password = password;
		
	}
	
	public String getName(){
		return this.name;
	}

	@Override
	public String toString() {
		return name;
	}
	
	

}
