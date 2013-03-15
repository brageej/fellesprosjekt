package gui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import data.Main;

public class WeekPanel extends JPanel {
	
	private WeekCalendarPanel calendarPanel;
	private PersonListPanel personListPanel;
	private JScrollPane calendarPane;
	private Main main;
	
	public WeekPanel(Main main){
		this.main = main; 
		calendarPanel = new WeekCalendarPanel();
		calendarPane = new JScrollPane(calendarPanel);
		calendarPane.setPreferredSize(new Dimension(650,200));
		personListPanel = new PersonListPanel(this.main);
		
		add(personListPanel);
		add(calendarPane);
	}

}
