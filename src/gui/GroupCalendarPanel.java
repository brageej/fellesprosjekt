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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
	private Calendar cal;
	private MainGUI mainGui;
	
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
	private ArrayList<String> appPanels;
	private ArrayList<AppPanel> Jpanels;
	
	
	public GroupCalendarPanel(Main main, MainGUI mainGui){
		this.main = main; 
		this.mainGui = mainGui;
		selectedGroups = new ArrayList<Object>();
		dayPanels = new ArrayList<DayPanel>();
		appPanels = new ArrayList<String>();
		Jpanels = new ArrayList<AppPanel>();
		makeAppPanels();
		
		setLayout(new GridBagLayout());
		mainC = new GridBagConstraints();
		
		calendarPanePanel = new JPanel();
		users = new JPanel();
		setLayout(new GridBagLayout());
		userC = new GridBagConstraints();
		timePanel = new TimePanel();
		
		userC.gridx = 0;
		userC.gridy = 1;
		users.add(timePanel,userC);
		

		userPane = new JScrollPane(users);
		userPane.setPreferredSize(new Dimension(650,500));
		calendarPanePanel.add(userPane);
		
		
		listAndCalendarPanel = new JPanel();
		dateChooser = new JPanel();
		calendar = new JCalendar();
		date = calendar.getDate();
		cal = new GregorianCalendar();
		cal.setTime(date);
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
		
		main.getServer().addPropertyChangeListener(this);
	}
	
	public void addDayPanel(Object object){
		if(object != main.getUser()){
			selectedGroups.add(object);
		}		
		if(object != null){
			if(object instanceof User){
				DayPanel dayPanel = new DayPanel((User) object);
				addAppointments(dayPanel,(User)object,getStartDate());
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
	
	private void addAppointments(DayPanel dayPanel, User user,Date date){
		dayPanel.getMainPanel().removeAll();
		ArrayList<Participant> appointments = user.getAppointments();
		for(int i = 0; i< appointments.size();i++){
			if(user.getAppointments().get(i).getAppointment().getStartTime().getTime().getYear() == date.getYear() && user.getAppointments().get(i).getAppointment().getStartTime().getTime().getMonth() == date.getMonth() && user.getAppointments().get(i).getAppointment().getStartTime().getTime().getDate() == date.getDate()){
				int startHour = appointments.get(i).getAppointment().getStartHour()-7;
				int startMinute = appointments.get(i).getAppointment().getStartMinute()/30;
				int distanceFromTopStart = startHour + startMinute;
				int finishHour = appointments.get(i).getAppointment().getFinishedHour()-7;
				int finishMinute = appointments.get(i).getAppointment().getFinishedMinute()/30;
				int distanceFromTopEnd = finishHour + finishMinute;
				int duration = distanceFromTopEnd - distanceFromTopStart;
				if(duration == 0){
					duration = 1;
				}
				for(int j=0; j<duration; j++){
					AppPanel panel = new AppPanel(user.getAppointments().get(i).getAppointment(),user.getAppointments().get(i));
					panel.setBackground(Color.BLUE);
					panel.addMouseListener(new myMouseListener());
					Jpanels.set(distanceFromTopStart+j, panel);
					appPanels.set(distanceFromTopStart+j, "BLUE");
				}
			}
		}
		for(int h=0; h<Jpanels.size(); h++){
			dayPanel.addPanel(Jpanels.get(h));
		}
		dayPanel.validate();
		dayPanel.repaint();
		makeAppPanels();

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
		cal.setTime(date);
		for(int i=0; i<dayPanels.size();i++){
			addAppointments(dayPanels.get(i),dayPanels.get(i).getUser(),getStartDate());
		}

		
	}
	
	public Date getStartDate(){
		Date startDate;
		int dayOfMonth = cal.getTime().getDate();
		startDate = new Date(cal.getTime().getYear(),cal.getTime().getMonth(),dayOfMonth);
		return startDate;
	}
	


	private class myMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			mainGui.showAppointmentViewPanel(((AppPanel) e.getComponent()).getAppointment(), ((AppPanel) e.getComponent()).getParticipant());
			System.out.println("Something happend");
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private void makeAppPanels(){
		appPanels.clear();
		for (int i = 0; i< 14; i++){
			appPanels.add("WHITE");
		}
		Jpanels.clear();
		for(int j = 0; j<14; j++){
			AppPanel panel = new AppPanel(null,null);
			panel.setBackground(Color.WHITE);
			Jpanels.add(panel);
		}
	}
}
