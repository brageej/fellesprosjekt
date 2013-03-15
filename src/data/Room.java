package data;

import java.util.ArrayList;


public class Room {
	
	private String roomNumber;
	private int size;
	private ArrayList<Appointment> appointments;
	
	
	public Room(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	public String toString() {
		return this.roomNumber;
	}
}
