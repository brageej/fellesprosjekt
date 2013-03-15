package data;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Calendar;


public class Appointment {
	
	private int appointmentId;
	
	private String title;
	private String description;
	private int startTime;
	private int finishTime;
	private int alarmTime; 
	private Room room;
	private User owner;
	
	private ArrayList<Participant> participants;
	
	public Appointment(String title, int startTime, int finishTime, User owner) {
		this.title = title;
		this.startTime = startTime;
		this.finishTime = finishTime;
		this.owner = owner;
	}
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public ArrayList<Participant> getParticipants() {
		return participants;
	}
	
	public void setParticipants(ArrayList<Participant> participants) {
		this.participants = participants;
	}

	public User getOwner() {
		return owner;
	}
	
	public int getAlarmTime() {
		return this.alarmTime;
	}
	
	public String getStartHour() {
		String hour = this.startTime + "";
		if (hour.length() != 4) {
			return "0";
		}
		return hour.substring(0, 2);
	}
	
	public String getStartMinute() {
		String minute = this.startTime + "";
		if (minute.length() != 4) {
			return "0";
		}
		return minute.substring(2, 4);
	}
	
	public String getFinishedHour() {
		String hour = this.finishTime + "";
		if (hour.length() != 4) {
			return "0";
		}
		return hour.substring(0, 2);
	}
	
	public String getFinishedMinute() {
		String minute = this.finishTime + "";
		if (minute.length() != 4) {
			return "0";
		}
		return minute.substring(2, 4);
	}
	
	public String getAlarmHour() {
		String alarm = this.alarmTime + "";
		if (alarm.length() != 4) {
			return "0";
		}
		return alarm.substring(0, 2);
	}
	
	public String getAlarmMinute() {
		String alarm = this.alarmTime + "";
		if (alarm.length() != 4) {
			return "0";
		}
		return alarm.substring(2, 4);
	}
	
	public void setAlarmTime(int alarmTime) {
		this.alarmTime = alarmTime;
	}
	
//	public static void main(String[] args) {
//		Appointment a = new Appointment();
//		a.setAlarmTime(1045);
//		System.out.println(a.getAlarmMinute());
//	}
}
