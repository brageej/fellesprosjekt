package data;

import java.util.ArrayList;


public class User {
	
	private String username;
	
	private String password;
	private String name;
	
	private ArrayList<Appointment> owner;
	private ArrayList<Group> leader;
	private ArrayList<Participant> appointments;
	private ArrayList<Member> groups;
	
	public String getName(){
		return this.name;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password
				+ ", name=" + name + ", owner=" + owner + ", leader=" + leader
				+ ", appointments=" + appointments + ", groups=" + groups + "]";
	}
	
	

}
