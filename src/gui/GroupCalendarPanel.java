package gui;

import gui.DayCalendarPanel.SelectionListener;
import gui.DayCalendarPanel.myMouseListener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.toedter.calendar.JCalendar;

import data.Group;
import data.Main;
import data.Participant;
import data.User;

public class GroupCalendarPanel extends JPanel implements PropertyChangeListener, ActionListener {
	private GridBagConstraints mainC;
	private Main main;
	private Date date;
	
	private JPanel calendarPanePanel;
	private TimePanel timePanel;
	private DayPanel thisUser;
	private JScrollPane userPane;
	private JPanel users;
	private GridBagConstraints userC;
		
	private JPanel listAndCalendarPanel;
	private GridBagConstraints listAndCalendarC;
	
	private JPanel dateChooser;
	private JCalendar calendar;
	
	private JPanel personListPanel;
	private GridBagConstraints personC;
	private JList personList;
	private DefaultListModel personListModel;
	private DefaultListSelectionModel personSelectionModel;
	private JScrollPane personPane;
	private JLabel userLabel;
	
	private JPanel groupListPanel;
	private DefaultListModel groupListModel;
	private GridBagConstraints groupC;
	private JList groupList;
	private DefaultListSelectionModel groupSelectionModel;
	private JLabel groups;
	private JScrollPane groupPane;
	
	private ArrayList<Object> selectedGroups;
	private ArrayList<DayPanel> dayPanels;
	
	
	public GroupCalendarPanel(Main main){
		this.main = main; 
		selectedGroups = new ArrayList<Object>();
		dayPanels = new ArrayList<DayPanel>();
		
		setLayout(new GridBagLayout());
		mainC = new GridBagConstraints();
		
		calendarPanePanel = new JPanel();
		users = new JPanel();
		setLayout(new GridBagLayout());
		userC = new GridBagConstraints();
		thisUser = new DayPanel(main.getUser());
		timePanel = new TimePanel();
		
		userC.gridx = 0;
		userC.gridy = 1;
		users.add(timePanel,userC);
		
		userC.gridx = 1;
		userC.gridy = 1;
		users.add(thisUser,userC);

		userPane = new JScrollPane(users);
		userPane.setPreferredSize(new Dimension(650,500));
		calendarPanePanel.add(userPane);
		
		
		listAndCalendarPanel = new JPanel();
		dateChooser = new JPanel();
		calendar = new JCalendar();
		date = calendar.getDate();
		System.out.println(date);
		calendar.getDayChooser().addPropertyChangeListener(this);
		calendar.setPreferredSize(new Dimension(150,150));
		dateChooser.add(calendar);	
		personListPanel = new JPanel();
		personListPanel.setLayout(new GridBagLayout());
		personC = new GridBagConstraints();
		personListModel = new DefaultListModel();
		personSelectionModel = new DefaultListSelectionModel();
		personList = new JList(personListModel);
		personList.setSelectionModel(personSelectionModel);
		personPane = new JScrollPane(personList);
		personPane.setPreferredSize(new Dimension(150,150));
		userLabel = new JLabel("Users");
		addPersons();
		
		personC.gridx = 0;
		personC.gridy = 0;
		personListPanel.add(userLabel,personC);
		
		personC.gridx = 0;
		personC.gridy = 1;
		personListPanel.add(personPane,personC);
		
		
		groupListPanel = new JPanel();
		groupListPanel.setLayout(new GridBagLayout());
		groupC = new GridBagConstraints();
		groupListModel = new DefaultListModel();
		groupSelectionModel = new DefaultListSelectionModel();
		groupList = new JList(groupListModel);
		groupList.setSelectionModel(groupSelectionModel);
		groupList.addListSelectionListener(new SelectionListener());
		groupPane = new JScrollPane(groupList);
		groupPane.setPreferredSize(new Dimension(150,150));
		groups = new JLabel("Groups");
		addGroups();
		
		groupC.gridx = 0;
		groupC.gridy = 0;
		groupListPanel.add(groups,groupC);
		
		groupC.gridx = 0;
		groupC.gridy = 1;
		groupListPanel.add(groupPane,groupC);
		
		
		listAndCalendarPanel.setLayout(new GridBagLayout());
		listAndCalendarC = new GridBagConstraints();
		listAndCalendarC.gridx = 0;
		listAndCalendarC.gridy = 0;
		listAndCalendarPanel.add(dateChooser, listAndCalendarC);
		listAndCalendarC.gridx = 0;
		listAndCalendarC.gridy = 1;
		listAndCalendarPanel.add(personListPanel, listAndCalendarC);
		listAndCalendarC.gridx = 0;
		listAndCalendarC.gridy = 2;
		listAndCalendarPanel.add(groupListPanel, listAndCalendarC);
		

		
		mainC.gridx = 0;
		mainC.gridy = 0;
		add(listAndCalendarPanel,mainC);
		
		mainC.gridx = 1;
		mainC.gridy = 0;
		add(calendarPanePanel,mainC);
	}
	
	public void addDayPanel(Object object){
		if(object != main.getUser()){
			selectedGroups.add(object);
		}		
		if(object != null){
			if(object instanceof User){
				DayPanel dayPanel = new DayPanel((User) object);
				testAddAppointments(dayPanel,(User)object);
				dayPanels.add(dayPanel);
				userC.gridx ++;
				userC.gridy = 0;
				users.add(dayPanel,userC);
				validate();

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
	
	public Date getDate(){
		return this.date;
	}
	
	private void addAppointments(DayPanel dayPanel, User user){
		ArrayList<Participant> appointments = user.getAppointments();
		for(int i = 0; i< appointments.size();i++){
			if(appointments.get(i).getAppointment().getStartTime().getTime().getDay() == date.getDay()){
				int startHour = appointments.get(i).getAppointment().getStartHour()-7;
				int startMinute = appointments.get(i).getAppointment().getStartMinute()/60;
				int distanceFromTopStart = startHour + startMinute;
				int finishHour = appointments.get(i).getAppointment().getFinishedHour()-7;
				int finishMinute = appointments.get(i).getAppointment().getFinishedMinute()/60;
				int distanceFromTopEnd = finishHour + finishMinute;
				int duration = distanceFromTopEnd - distanceFromTopStart;
				for(int j=0; j<distanceFromTopStart;j++){
					JPanel appPanel = new JPanel();
					appPanel.setBackground(Color.WHITE);
					dayPanel.addPanel(appPanel);
				}
				for(int k = 0; k<duration; k++){
					JPanel appPanel2 = new JPanel();
					appPanel2.setBackground(Color.BLUE);
					dayPanel.addPanel(appPanel2);
				}
				for(int l = 0; l<distanceFromTopStart+duration; l++){
					JPanel appPanel3 = new JPanel();
					appPanel3.setBackground(Color.WHITE);
					dayPanel.addPanel(appPanel3);
				}
			}
		}
	}
	
	private void testAddAppointments(DayPanel dayPanel, User user){
		for(int j=0; j<4;j++){
			JPanel appPanel = new JPanel();
			appPanel.setBackground(Color.WHITE);
			dayPanel.addPanel(appPanel);
		}
		for(int k = 0; k<2; k++){
			JPanel appPanel2 = new JPanel();
			appPanel2.setBackground(Color.BLUE);
			dayPanel.addPanel(appPanel2);
		}
		for(int l = 0; l<7; l++){
			JPanel appPanel3 = new JPanel();
			appPanel3.setBackground(Color.WHITE);
			dayPanel.addPanel(appPanel3);
		}
	}
	
	public class SelectionListener implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent evt) {
			if(evt.getValueIsAdjusting()){
				if(selectedGroups.contains(groupList.getSelectedValue())){
					for(int i = 0; i<users.getComponentCount();i++){
						users.remove(i+1);
						users.validate();
						users.repaint();
					}
				}
				else{
					Group group = (Group) groupList.getSelectedValue(); 
					for(int j = 0; j<group.getMembers().size(); j++){
						addDayPanel(group.getMembers().get(j).getUser());
					}
				}


				

			}
			
		}


		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		date = calendar.getDate();
		
	}
}
