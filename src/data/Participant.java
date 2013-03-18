package data;

import java.util.Calendar;



public class Participant {
	
	private Appointment appointment;
	private User user;
	
	private Calendar alarmTime;
	private String status;
	
	public Participant(Appointment appointment, User user) {
		this.appointment = appointment;
		this.user = user;
		
		this.status = "No answer";
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
	
	public Appointment getAppointment() {
		return appointment;
	}
	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Calendar getAlarmTime() {
		return alarmTime;
	}
	public void setAlarmTime(Calendar alarm) {
		this.alarmTime = alarm;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String toString() {
		return user.getName() + " - " + status;
	}
}
