package data;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Calendar;


public class Appointment {
	
	private int appointmentId;
	
	private String title;
	private String description;
	private Calendar startTime;
	private Calendar finishTime;
	private Calendar alarmTime; 
	private Room room;
	private User owner;
	
	private ArrayList<Participant> participants;
	
	public Appointment(String title, Calendar startTime, Calendar finishTime, User owner) {
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

	public Calendar getStartTime() {
		return startTime;
	}

	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	public Calendar getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Calendar finishTime) {
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
	
	public Calendar getAlarmTime() {
		return this.alarmTime;
	}
	
	public int getStartHour() {
		if (this.startTime == null) {
			return 0;
		}
		int hour = this.startTime.get(Calendar.HOUR_OF_DAY);
		return hour;
	}
	
	public int getStartMinute() {
		if (this.startTime == null) {
			return 0;
		}
		int minute = this.startTime.get(Calendar.MINUTE);
		return minute;
	}
	
	public int getFinishedHour() {
		if (this.finishTime == null) {
			return 0;
		}
		int hour = this.finishTime.get(Calendar.HOUR_OF_DAY);
		return hour;
	}
	
	public int getFinishedMinute() {
		if (this.finishTime == null) {
			return 0;
		}
		int minute = this.finishTime.get(Calendar.MINUTE);
		return minute;
	}
	
	public int getAlarmHour() {
		if (this.alarmTime == null) {
			return 0;
		}
		int alarm = this.alarmTime.get(Calendar.HOUR_OF_DAY);
		return alarm;
	}
	
	public int getAlarmMinute() {
		if (this.alarmTime == null) {
			return 0;
		}
		int alarm = this.alarmTime.get(Calendar.MINUTE);
		return alarm;
	}
	
	public void setAlarmTime(Calendar alarmTime) {
		this.alarmTime = alarmTime;
	}
	
//	public static void main(String[] args) {
//		Appointment a = new Appointment();
//		a.setAlarmTime(1045);
//		System.out.println(a.getAlarmMinute());
//	}
}
