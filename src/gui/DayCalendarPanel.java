package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

import data.Group;
import data.Main;
import data.User;

public class DayCalendarPanel extends JPanel implements PropertyChangeListener {
	
	private TimePanel timePanel;
	private DayPanel dayPanel;
	private JScrollPane calendarPane;
	private ListAndCalendarPanel listAndCalendarPanel;
	private User thisUser;
	private GridBagConstraints paneC;
	private GridBagConstraints mainC;
	private Main main;
	private JPanel userDayPanel;
	
	
	public DayCalendarPanel(Main main){
		this.main = main;
		System.out.println(this.main);

		userDayPanel = new JPanel();
		timePanel = new TimePanel();
		dayPanel = new DayPanel(main.getUser().getUserName());
		paneC = new GridBagConstraints();
		paneC.gridx = 0;
		paneC.gridy = 1;
		userDayPanel.add(this.timePanel,paneC);
		paneC.gridx = 1;
		paneC.gridy = 1;
		userDayPanel.add(this.dayPanel,paneC);
		calendarPane = new JScrollPane(userDayPanel);
		calendarPane.setPreferredSize(new Dimension(650,500));
		JPanel dayCalendarPanel = new JPanel();
		dayCalendarPanel.add(calendarPane);
		
		listAndCalendarPanel = new ListAndCalendarPanel(this.main,true,this);
		
		
		mainC = new GridBagConstraints();
		mainC.gridx = 0;
		mainC.gridy = 1;
		add(listAndCalendarPanel,mainC);
		
		mainC.gridx = 1;
		mainC.gridy = 1;
		add(dayCalendarPanel,mainC);
		
	}
	
	public void addDayPanel(Object object){
		if(object instanceof User){
			DayPanel userPanel = new DayPanel(((User) object).getUserName());
		}
		if(object instanceof Group){
			for(int i=0; i<((Group) object).getMembers().size();i++){
				addDayPanel((Group) object).
			}
			DayPanel groupPanel = new DayPanel(object.)
		}
		dayPanel = new DayPanel(user.getUserName());
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName() == "List"){
			addDayPanel(evt.getNewValue());
		}
		
	}

}
