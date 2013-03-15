package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import data.Main;

public class WeekPanel extends JPanel {
	
	private WeekCalendarPanel calendarPanel;
	private ListPanel personListPanel;
	private ListPanel groupListPanel;
	private JScrollPane calendarPane;
	private Main main;
	private CalendarPanel dateChooser;
	
	public WeekPanel(Main main){
		this.main = main; 
		setLayout(new GridBagLayout());
		GridBagConstraints mainC = new GridBagConstraints();
		
		JPanel calendarPanePanel = new JPanel();
		calendarPanel = new WeekCalendarPanel();
		calendarPane = new JScrollPane(calendarPanel);
		calendarPane.setPreferredSize(new Dimension(650,500));
		calendarPanePanel.add(calendarPane);
		
		JPanel listAndCalendarPanel = new JPanel();
		dateChooser = new CalendarPanel();
		personListPanel = new ListPanel(this.main, "person");
		groupListPanel = new ListPanel(this.main, "Group");
		listAndCalendarPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		listAndCalendarPanel.add(dateChooser, c);
		c.gridx = 0;
		c.gridy = 1;
		listAndCalendarPanel.add(personListPanel, c);
		c.gridx = 0;
		c.gridy = 2;
		listAndCalendarPanel.add(groupListPanel, c);
		
		mainC.gridx = 0;
		mainC.gridy = 0;
		add(listAndCalendarPanel,mainC);
		
		mainC.gridx = 1;
		mainC.gridy = 0;
		add(calendarPanePanel,mainC);
	}

}
