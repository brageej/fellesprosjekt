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
		
		this.groups = new ArrayList<Member>();
		this.leader = new ArrayList<Group>();
		this.owner = new ArrayList<Appointment>();
		this.appointments = new ArrayList<Participant>();
		
	}

	public void addGroup(Member newGroup){
		if (!this.groups.contains(newGroup)){
			this.groups.add(newGroup);
		}
	}
	public void removeGroup(Member delMember){
		this.groups.remove(delMember);
	}
	
	public void addLeadership(Group leaderGroup){
		if (!this.leader.contains(leaderGroup)){
			this.leader.add(leaderGroup);
		}
	}
	
	public void addOwnership(Appointment ownerAppointment){
		if (!this.owner.contains(ownerAppointment)){
			this.owner.add(ownerAppointment);
		}
	}
	
	public void addAppointment(Participant newAppointment){
		if (!this.appointments.contains(newAppointment)){
			this.appointments.add(newAppointment);
		}
	}
	public void removeAppointment(Participant delAppointment){
		this.appointments.remove(delAppointment);
	}
	
	
//	private void setMyAppointments(){
//		myAppointments = main.getMyAppointments();
//	}
	
	public String getName(){
		return this.name;
	}

	@Override
	public String toString() {
		return name;
	}
	
	public String getUserName(){
		return this.username;
	}
	
//	public ArrayList<Appointment> getOwner(){
//		return;
//		
//	}
	
	public boolean correctPassword(String checkPassword){
		if (this.password.equals(checkPassword)) return true;
		return false;
	}
	
	/* 
	 * Laget constructor med brukernavn, passord og navn. Laget metoder for å legge til
	 * medlemskap i gruppe, leder i gruppe, eier av avtale og deltager i avtale. 
	 * Metoder for å slette medlemskap (trenger vi å kunne slette leder) i gruppe 
	 * og deltager i avtale.
	 * 
	 * Metoder for å få navn og sjekke om passord er korrekt.
	 * Getters for navn og brukernavn.
	 * 
	 */

}
