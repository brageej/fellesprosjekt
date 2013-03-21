package gui;

import java.awt.Color;

import javax.swing.JPanel;

import data.Appointment;
import data.Participant;

public class AppPanel extends JPanel {
	
	private Appointment appointment;
	private Participant participant;
	
	public AppPanel(Appointment appointment, Participant participant){
		this.appointment = appointment;
		this.participant = participant;
	}
	
	public Appointment getAppointment(){
		return this.appointment;
	}
	
	public Participant getParticipant(){
		return this.participant;
	}

}
