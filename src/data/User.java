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
	private Main main;
	
	
	public User(String username, String password, String name){
		this.username = username;
		this.name = name;
		this.password = password;
		
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
	
	public void removeLeadership(Group leaderGroup){
		this.leader.remove(leaderGroup);
	}
	
	public void addOwnership(Appointment ownerAppointment){
		if (!this.owner.contains(ownerAppointment)){
			this.owner.add(ownerAppointment);
		}
	}
	
	public void removeOwnership(Appointment ownerAppointment){
		this.owner.remove(ownerAppointment);
	}
	
	public void addAppointment(Participant newAppointment){
		if (!this.appointments.contains(newAppointment)){
			this.appointments.add(newAppointment);
		}
	}
	public void removeAppointment(Participant delAppointment){
		this.appointments.remove(delAppointment);
	}
		
	
	@Override
	public String toString() {
		return name;
	}
	
	public String getUserName(){
		return this.username;
	}
	
	
	public boolean correctPassword(String checkPassword){
		if (this.password.equals(checkPassword)) return true;
		return false;
	}
	
	
	public ArrayList<Participant> getAppointments(){
		return appointments;
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
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getName() {
		return name;
	}

}
