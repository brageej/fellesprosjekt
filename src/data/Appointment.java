package data;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Calendar;


public class Appointment {
	
	private int appointmentId;
	
	private String title;
	private String description;
	private Calendar startTime;
	private Calendar finishTime; 
	private Room room;
	private User owner;
	
	public static final String TITLE_PROP = "Title";
	public static final String DESC_PROP = "Description";
	public static final String START_TIME_PROP = "StartTime";
	public static final String FINISH_TIME_PROP = "FinishTime";
	public static final String ROOM_PROP = "Room";
	public static final String PARTICIPANT_PROP = "Participant";
	
	private ArrayList<Participant> participants;
	private PropertyChangeSupport pcs;
	
	public Appointment(String title, Calendar startTime, Calendar finishTime, User owner) {
		this.title = title;
		this.startTime = startTime;
		this.finishTime = finishTime;
		this.owner = owner;
		participants = new ArrayList<Participant>();
		pcs = new PropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}
	
	public int getParticipantLength() {
		return participants.size();
	}
	
	public void removeParticipant(Participant p) {
		participants.remove(p);
		pcs.firePropertyChange(PARTICIPANT_PROP, p, null);
	}
	
	public void addParticipant(Participant p) {
		participants.add(p);
		pcs.firePropertyChange(PARTICIPANT_PROP, null, p);
	}
	
	public Participant getParticipant(int index) {
		if (0 > index || index >= participants.size()) {
			return null;
		}
		return participants.get(index);
	}
	
	public Participant getParticipant(Participant p) {
		for (Participant par : participants) {
			if (par == p) {
				return par;
			}
		}
		return null;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		String oldTitle = this.title;
		this.title = title;
		pcs.firePropertyChange(TITLE_PROP, oldTitle, title);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		String oldDesc = this.description;
		this.description = description;
		pcs.firePropertyChange(DESC_PROP, oldDesc, description);
	}

	public Calendar getStartTime() {
		return startTime;
	}

	public void setStartTime(Calendar startTime) {
		Calendar oldTime = this.startTime;
		this.startTime = startTime;
		pcs.firePropertyChange(START_TIME_PROP, oldTime, startTime);
	}

	public Calendar getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Calendar finishTime) {
		Calendar oldTime = this.finishTime;
		this.finishTime = finishTime;
		pcs.firePropertyChange(FINISH_TIME_PROP, oldTime, finishTime);
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		Room oldRoom = this.room;
		this.room = room;
		pcs.firePropertyChange(ROOM_PROP, oldRoom, room);
	}

	public User getOwner() {
		return owner;
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
	
//	public static void main(String[] args) {
//		Appointment a = new Appointment();
//		a.setAlarmTime(1045);
//		System.out.println(a.getAlarmMinute());
//	}
}
