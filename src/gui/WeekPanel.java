package gui;

import gui.DayCalendarPanel.SelectionListener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.toedter.calendar.JCalendar;

import data.Main;
import data.User;

public class WeekPanel extends JPanel implements PropertyChangeListener {
	
	private JPanel personListPanel;
	private JPanel groupListPanel;
	private JScrollPane calendarPane;
	private Main main;
	private JPanel dateChooser;
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
	private User thisUser;
	private Date date;
	private Calendar cal;
	private JCalendar calendar;
	private ArrayList<DayPanel> weekdays;
	private ArrayList<String> appPanels;
	private ArrayList<AppPanel> Jpanels;
	
	private MainGUI mainGui;
	
	public WeekPanel(Main main, MainGUI mainGui){
		this.main = main; 
		this.mainGui = mainGui;
		this.thisUser = this.main.getUser();
		appPanels = new ArrayList<String>();
		weekdays = new ArrayList<DayPanel>();
		Jpanels = new ArrayList<AppPanel>();
		makeAppPanels();
		setLayout(new GridBagLayout());
		GridBagConstraints mainC = new GridBagConstraints();
		
		JPanel calendarPanePanel = new JPanel();
		JPanel weekCalendarPanel = new JPanel();
		setLayout(new GridBagLayout());
		GridBagConstraints weekC = new GridBagConstraints();
		mon = new DayPanel("Mon");
		weekdays.add((DayPanel) mon);
		tue = new DayPanel("Tue");
		weekdays.add((DayPanel) tue);
		wed = new DayPanel("Wed");
		weekdays.add((DayPanel) wed);
		thu = new DayPanel("Thu");
		weekdays.add((DayPanel) thu);
		fri = new DayPanel("Fri");
		weekdays.add((DayPanel) fri);
		sat = new DayPanel("Sat");
		weekdays.add((DayPanel) sat);
		sun = new DayPanel("Sun");
		weekdays.add((DayPanel) sun);
		
		
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
		dateChooser = new JPanel();
		calendar = new JCalendar();
		calendar.setPreferredSize(new Dimension(150,150));
		calendar.addPropertyChangeListener(this);
		date = calendar.getDate();
		cal = new GregorianCalendar();
		cal.setTime(date);
		//addAppointments(getStartDate(),weekdays);
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
		

		
		mainC.gridx = 0;
		mainC.gridy = 0;
		add(listAndCalendarPanel,mainC);
		
		mainC.gridx = 1;
		mainC.gridy = 0;
		add(calendarPanePanel,mainC);
		
		main.getServer().addPropertyChangeListener(this);
	}
	

	
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
	
	public Date getStartDate(){
		Date startDate;
		int dayOfMonth = cal.getTime().getDate();
		int day = cal.getTime().getDay();
		if(day!=0){
			startDate = new Date(cal.getTime().getYear(),cal.getTime().getMonth(),dayOfMonth-(day-1));
		}
		else{
			startDate = new Date(cal.getTime().getYear(),cal.getTime().getMonth(),dayOfMonth-6);
		}
		return startDate;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		date = calendar.getDate();
		cal.setTime(date);
		addAppointments(getStartDate(), weekdays);
	}
	
	private void addAppointments(Date date, ArrayList<DayPanel> weekdays){
		for(int k = 0; k<weekdays.size(); k++){
			weekdays.get(k).getMainPanel().removeAll();
			for(int i=0; i<thisUser.getAppointments().size();i++){
				if(thisUser.getAppointments().get(i).getAppointment().getStartTime().getTime().getYear() == date.getYear() && thisUser.getAppointments().get(i).getAppointment().getStartTime().getTime().getMonth() == date.getMonth() && thisUser.getAppointments().get(i).getAppointment().getStartTime().getTime().getDate() == date.getDate()){
					int startHour = thisUser.getAppointments().get(i).getAppointment().getStartHour()-7;
					int startMinute = thisUser.getAppointments().get(i).getAppointment().getStartMinute()/30;
					int distanceFromTopStart = startHour + startMinute;
					int finishHour = thisUser.getAppointments().get(i).getAppointment().getFinishedHour()-7;
					int finishMinute = thisUser.getAppointments().get(i).getAppointment().getFinishedMinute()/30;
					int distanceFromTopEnd = finishHour + finishMinute;
					int duration = distanceFromTopEnd - distanceFromTopStart;
					if(duration == 0){
						duration = 1;
					}
					for(int j=0; j<duration; j++){
						AppPanel panel = new AppPanel(thisUser.getAppointments().get(i).getAppointment(),thisUser.getAppointments().get(i));
						if(thisUser.getAppointments().get(i).getStatus().equals("Accepted")){
							panel.setBackground(Color.GREEN);
						}
						else if(thisUser.getAppointments().get(i).getStatus().equals("Declined")){
							panel.setBackground(Color.YELLOW);
						}
						else if(thisUser.getAppointments().get(i).getStatus().equals("No answer")){
							panel.setBackground(Color.RED);
						}
						else if(thisUser.getAppointments().get(i).getStatus().equals("Canceled")){
							panel.setBackground(Color.GRAY);
						}
						else{
							panel.setBackground(Color.BLUE);
						}
						panel.addMouseListener(new myMouseListener());
						Jpanels.set(distanceFromTopStart+j, panel);
						//appPanels.set(distanceFromTopStart+j, "BLUE");
					}

				}

			}
			for(int h=0; h<Jpanels.size(); h++){
				weekdays.get(k).addPanel(Jpanels.get(h));
			}
			weekdays.get(k).validate();
			weekdays.get(k).repaint();
			makeAppPanels();
			date.setDate(date.getDate()+1);
		}
	}
	
	private void makeAppPanels(){
//		appPanels.clear();
//		for (int i = 0; i< 14; i++){
//			appPanels.add("WHITE");
//		}
		Jpanels.clear();
		for(int j = 0; j<14; j++){
			AppPanel panel = new AppPanel(null,null);
			panel.setBackground(Color.WHITE);
			Jpanels.add(panel);
		}
	}




	public class myMouseListener implements MouseListener{

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
	

}
