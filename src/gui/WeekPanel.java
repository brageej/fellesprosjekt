package gui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class WeekPanel extends JPanel {
	
	private WeekCalendarPanel calendarPanel;
	private PersonListPanel personListPanel;
	private JScrollPane calendarPane;
	
	public WeekPanel(){
		calendarPanel = new WeekCalendarPanel();
		calendarPane = new JScrollPane(calendarPanel);
		calendarPane.setPreferredSize(new Dimension(650,200));
		personListPanel = new PersonListPanel();
		
		add(personListPanel);
		add(calendarPane);
	}

}
