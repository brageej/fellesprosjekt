package gui;

import java.awt.Dimension;

import javax.swing.JPanel;

import com.toedter.calendar.JCalendar;

public class CalendarPanel extends JPanel{
	
	public CalendarPanel (){
		JCalendar calendar = new JCalendar();
		calendar.setPreferredSize(new Dimension(150,150));
		add(calendar);
	}

}
