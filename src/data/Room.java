package data;

import java.util.ArrayList;


public class Room {
	
	private String roomNumber;
	private int size;
	private ArrayList<Appointment> appointments;
	
	
	public Room(String roomNumber, int size){
		this.roomNumber = roomNumber;
		this.size = size;
		this.appointments = new ArrayList<Appointment>();
	}
	//construct with appointment-list
	public Room(String roomNumber, int size, ArrayList<Appointment> appointments){
		this.roomNumber = roomNumber;
		this.size = size;
		this.appointments = appointments;
	}
	
	public void addAppointment(Appointment newAppointment){
		if (!this.appointments.contains(newAppointment)){
			this.appointments.add(newAppointment);			
		}
	}
	public void removeAppointment(Appointment delAppointment){
		this.appointments.remove(delAppointment);
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public int getSize() {
		return size;
	}

	public ArrayList<Appointment> getAppointments() {
		return appointments;
	}

	
	
	

}
