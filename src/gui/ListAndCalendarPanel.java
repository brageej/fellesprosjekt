package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import data.Main;

public class ListAndCalendarPanel extends JPanel {
	
	private ListPanel personList;
	private ListPanel groupList;
	private CalendarPanel calendar;
	private Main main;
	private DayCalendarPanel dayCalendarPanel;
	
	public ListAndCalendarPanel(Main main, boolean visible, DayCalendarPanel panel){
		this.dayCalendarPanel = panel;
		this.main = main;
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		personList = new ListPanel(this.main,"person");
		groupList = new ListPanel(this.main, "Groups");
		calendar = new CalendarPanel();
		if(!visible){
			personList.setVisible(true);
			groupList.setVisible(true);
		}
		
		c.gridx = 0;
		c.gridy = 0;
		add(calendar, c);
		c.gridx = 0;
		c.gridy = 1;
		add(personList, c);
		c.gridx = 0;
		c.gridy = 2;
		add(groupList, c);
		
	}
	
	public ListPanel getListPanel(){
		return personList;
	}
	
	

}
