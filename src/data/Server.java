package data;

import java.util.ArrayList;
import java.util.Map;

public class Server {
	
	private Map<Integer, Appointment> appointments;
	private Map<String, Group> groups;
	private ArrayList<Member> members;
	private ArrayList<Participant> participants;
	private Map<String, Room> rooms;
	private ArrayList<Subgroup> subgroups;
	private Map<String, User> users;
	
	Map<Integer, Appointment> getAppointments() {
		return appointments;
	}
	
	Map<String, Group> getGroups() {
		return groups;
	}
	
	ArrayList<Member> getMembers() {
		return members;
	}
	
	ArrayList<Participant> getParticipants() {
		return participants;
	}
	
	Map<String, Room> getRooms() {
		return rooms;
	}
	
	ArrayList<Subgroup> getSubgroups() {
		return subgroups;
	}
	
	Map<String, User> getUsers() {
		return users;
	}

}
