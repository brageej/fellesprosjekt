package gui;

import gui.ListPanel.listListener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.toedter.calendar.JCalendar;

import data.Group;
import data.Main;
import data.User;

public class DayCalendarPanel extends JPanel{
	
	private TimePanel timePanel;
	private JScrollPane calendarPane;;
	private User thisUser;
	private GridBagConstraints paneC;
	private GridBagConstraints mainC;
	private Main main;
	private JPanel userDayPanel;
	private JList list;
	private DefaultListModel personListModel;
	private DefaultListModel groupListModel;
	private JPanel personListPanel;
	private JPanel groupListPanel;
	private DefaultListSelectionModel personSelectionModel;
	private DefaultListSelectionModel groupSelectionModel;
	
	
	
	public DayCalendarPanel(Main main){
		this.main = main;
		System.out.println(this.main);

		userDayPanel = new JPanel();
		timePanel = new TimePanel();
		
		paneC = new GridBagConstraints();
		paneC.gridx = 0;
		paneC.gridy = 1;
		userDayPanel.add(this.timePanel,paneC);
		addDayPanel(main.getUser());

		calendarPane = new JScrollPane(userDayPanel);
		calendarPane.setPreferredSize(new Dimension(650,500));
		JPanel dayCalendarPanel = new JPanel();
		dayCalendarPanel.add(calendarPane);
		
		JPanel listAndCalendarPanel = new JPanel();
		JPanel dateChooser = new JPanel();
		JCalendar calendar = new JCalendar();
		calendar.setPreferredSize(new Dimension(150,150));
		dateChooser.add(calendar);
		
		personListPanel = new JPanel();
		personListPanel.setLayout(new GridBagLayout());
		GridBagConstraints personC = new GridBagConstraints();
		personListModel = new DefaultListModel();
		personSelectionModel = new DefaultListSelectionModel();
		list = new JList(personListModel);
		list.setSelectionModel(personSelectionModel);
		list.addListSelectionListener(new SelectionListener());
		JScrollPane pane = new JScrollPane(list);
		pane.setPreferredSize(new Dimension(150,150));
		addPersons();
		JLabel users = new JLabel("Users");

		
		personC.gridx = 0;
		personC.gridy = 0;
		personListPanel.add(users,personC);
		
		personC.gridx = 0;
		personC.gridy = 1;
		personListPanel.add(pane,personC);
		
		
		groupListPanel = new JPanel();
		groupListPanel.setLayout(new GridBagLayout());
		GridBagConstraints groupC = new GridBagConstraints();
		groupListModel = new DefaultListModel();
		groupSelectionModel = new DefaultListSelectionModel();
		JList groupList = new JList(groupListModel);
		list.setSelectionModel(groupSelectionModel);
		JScrollPane groupPane = new JScrollPane(groupList);
		groupPane.setPreferredSize(new Dimension(150,150));
		addGroups();
		JLabel groups = new JLabel("Groups");

		
		groupC.gridx = 0;
		groupC.gridy = 0;
		groupListPanel.add(groups,groupC);
		
		groupC.gridx = 0;
		groupC.gridy = 1;
		groupListPanel.add(groupPane,groupC);
		
		
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
		
		mainC = new GridBagConstraints();
		mainC.gridx = 0;
		mainC.gridy = 1;
		add(listAndCalendarPanel,mainC);
		
		mainC.gridx = 1;
		mainC.gridy = 1;
		add(dayCalendarPanel,mainC);
		
	}
	
	public void addDayPanel(Object object){
		if(object != null){
			if(object instanceof User){
				JPanel dayPanel = new JPanel();
				setLayout(new GridBagLayout());
				GridBagConstraints dayC = new GridBagConstraints();
				JPanel mainPanel = new JPanel();
				mainPanel.setPreferredSize(new Dimension(75,600));
				dayPanel.setPreferredSize(new Dimension(75,650));
				mainPanel.setBackground(Color.WHITE);
				mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				dayC.gridx = 0;
				dayC.gridy = 0;
				dayPanel.add(new JLabel(((User) object).getUserName()),dayC);
				dayC.gridx = 0;
				dayC.gridy = 1;
				dayPanel.add(mainPanel,dayC);
				paneC.gridx ++;
				paneC.gridy = 0;
				userDayPanel.add(dayPanel,paneC);
			}
		}

	}
	

	
	private void addPersons(){
		for(int i = 0; i<main.getPersons().size(); i++){
			personListModel.addElement(main.getPersons().get(i));
		}
		}
	
	private void addGroups(){
		for(int i = 0; i<main.getGroups().size(); i++){
			groupListModel.addElement(main.getGroups().get(i));
		}
	}
	
	public class SelectionListener implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent evt) {
			
			if(evt.getValueIsAdjusting()){
				System.out.println("det skjer noe");
				System.out.println(list.getSelectedValue());
				addDayPanel(list.getSelectedValue());

			}
			
		}


	}


}
