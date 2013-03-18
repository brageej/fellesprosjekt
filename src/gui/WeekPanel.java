package gui;

import gui.DayCalendarPanel.SelectionListener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.toedter.calendar.JCalendar;

import data.Main;

public class WeekPanel extends JPanel {
	
	private JPanel personListPanel;
	private JPanel groupListPanel;
	private JScrollPane calendarPane;
	private Main main;
	private CalendarPanel dateChooser;
	private DayCalendarPanel dayCalendarPanel;
	private JPanel mon;
	private JPanel tue;
	private JPanel wed;
	private JPanel thu;
	private JPanel fri;
	private JPanel sat;
	private JPanel sun;
	private DefaultListModel groupListModel;
	private DefaultListModel personListModel;
	private DefaultListSelectionModel personSelectionModel;
	private DefaultListSelectionModel groupSelectionModel;
	private JList list;
	
	public WeekPanel(Main main){
		this.main = main; 
		setLayout(new GridBagLayout());
		GridBagConstraints mainC = new GridBagConstraints();
		
		JPanel calendarPanePanel = new JPanel();
		JPanel weekCalendarPanel = new JPanel();
		setLayout(new GridBagLayout());
		GridBagConstraints weekC = new GridBagConstraints();
		mon = addDayPanel("Mon");
		tue = addDayPanel("Tue");
		wed = addDayPanel("Wed");
		thu = addDayPanel("Thu");
		fri = addDayPanel("Fri");
		sat = addDayPanel("Sat");
		sun = addDayPanel("Sun");
		TimePanel time = new TimePanel();
		
		weekC.gridx = 0;
		weekC.gridy = 1;
		weekCalendarPanel.add(time,weekC);
		
		weekC.gridx = 1;
		weekC.gridy = 1;
		weekCalendarPanel.add(mon,weekC);
		weekC.gridx = 2;
		weekC.gridy = 1;
		weekCalendarPanel.add(tue,weekC);
		weekC.gridx = 3;
		weekC.gridy = 1;
		weekCalendarPanel.add(wed,weekC);
		weekC.gridx = 4;
		weekC.gridy = 1;
		weekCalendarPanel.add(thu,weekC);
		weekC.gridx = 5;
		weekC.gridy = 1;
		weekCalendarPanel.add(fri,weekC);
		weekC.gridx = 6;
		weekC.gridy = 1;
		weekCalendarPanel.add(sat,weekC);
		weekC.gridx = 7;
		weekC.gridy = 1;
		weekCalendarPanel.add(sun,weekC);
		calendarPane = new JScrollPane(weekCalendarPanel);
		calendarPane.setPreferredSize(new Dimension(650,500));
		calendarPanePanel.add(calendarPane);
		
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
		//personSelectionModel.addListSelectionListener(new selectionListener());
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
		DefaultListSelectionModel groupSelectionModel = new DefaultListSelectionModel();
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
		

		
		mainC.gridx = 0;
		mainC.gridy = 0;
		add(listAndCalendarPanel,mainC);
		
		mainC.gridx = 1;
		mainC.gridy = 0;
		add(calendarPanePanel,mainC);
		

	}
	
//	private void addAppointments(){
//		for(int i = 0; i<main.getUser().get)
//	}
	
	public JPanel addDayPanel(String string){
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
		dayPanel.add(new JLabel(string),dayC);
		dayC.gridx = 0;
		dayC.gridy = 1;
		dayPanel.add(mainPanel,dayC);
		return dayPanel;
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
	

}
