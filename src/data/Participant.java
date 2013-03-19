package data;

import java.util.Calendar;
import java.util.GregorianCalendar;



public class Participant {
	
	private Appointment appointment;
	private User user;
	
	private Calendar alarmTime;
	private String status;
	
	public Participant(Appointment appointment, User user) {
		this.appointment = appointment;
		this.user = user;
		this.alarmTime = new GregorianCalendar();
		this.alarmTime.setTimeInMillis(0);
		this.status = "No answer";
	}
	
	public Participant(Appointment appointment, User user, long alarm, String status) {
		this.appointment = appointment;
		this.user = user;
		this.alarmTime = new GregorianCalendar();
		this.alarmTime.setTimeInMillis(alarm);
		this.status = status;
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
	
	public Calendar getAlarm() {
		return alarmTime;
	}

}
