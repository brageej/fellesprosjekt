package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.util.Date;

import javax.swing.JPanel;

public class CalendarAppointmentPanel extends JPanel {
	
	public CalendarAppointmentPanel(int duration, int startTime){
		setPreferredSize(new Dimension(75,getHeight(duration)));
		setBackground(Color.BLUE);
		//int spaceFromTop = 18-(startTime.s);
		Insets insets = new Insets(getPosition(startTime),0,0,0);
	}
	
	private int getHeight(int duration){
		int i = duration/60;
		return i*36;
	}
	
	private int getPosition(int startTime){
		String hour = "";
		String minutes = "";
		String time = Integer.toString(startTime);
		for(int i=0; i<time.length();i++){
			if(i<2){
				hour = hour + time.charAt(i);
			}
			else{
				minutes = minutes + time.charAt(i);
			}
		}
		int hoursFromStart = Integer.parseInt(hour) + Integer.parseInt(minutes)/60;
		int placement = hoursFromStart*36;
		return placement;
	}
	
	public static void main(String[] args){
		CalendarAppointmentPanel test = new CalendarAppointmentPanel(10,10);
		
	}

}
