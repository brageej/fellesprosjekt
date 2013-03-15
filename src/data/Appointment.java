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
	
	
	public int getAlarmTime() {
		return this.alarmTime;
	}
	
	public int getStartHour() {
		String hour = this.startTime + "";
		if (hour.length() != 4) {
			return 0;
		}
		return Integer.parseInt(hour.substring(0, 2));
	}
	
	public int getStartMinute() {
		String minute = this.startTime + "";
		if (minute.length() != 4) {
			return 0;
		}
		return Integer.parseInt(minute.substring(2, 4));
	}
	
	public int getFinishedHour() {
		String hour = this.finishTime + "";
		if (hour.length() != 4) {
			return 0;
		}
		return Integer.parseInt(hour.substring(0, 2));
	}
	
	public int getFinishedMinute() {
		String minute = this.finishTime + "";
		if (minute.length() != 4) {
			return 0;
		}
		return Integer.parseInt(minute.substring(2, 4));
	}
	
	public int getAlarmHour() {
		String alarm = this.alarmTime + "";
		if (alarm.length() != 4) {
			return 0;
		}
		return Integer.parseInt(alarm.substring(0, 2));
	}
	
	public int getAlarmMinute() {
		String alarm = this.alarmTime + "";
		if (alarm.length() != 4) {
			return 0;
		}
		return Integer.parseInt(alarm.substring(2, 4));
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
