package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListDataListener;

import customModels.GroupComboBoxModel;
import customModels.UserComboBoxModel;

import data.Appointment;
import data.Group;
import data.Main;
import data.Member;
import data.Participant;
import data.Room;
import data.Server;
import data.User;

public class NewAppointmentPanel extends JPanel{
	
	private JPanel titlePanel;
	private JPanel descriptionPanel;
	private JPanel jointTitleDescPanel;
	
	private JPanel startTimePanel;
	private JPanel endTimePanel;
	private JPanel jointTimePanel;
	
	private JPanel personsPanel;
	private JPanel groupPanel;
	private JPanel roomPanel;
	private JPanel buttonPanel;
	private JPanel jointPersGroupRoomButPanel;
	
	private JLabel titleLab;
	private JLabel descLab;
	private JLabel startLab;
	private JLabel colonLab;
	private JLabel endLab;
	private JLabel personLab;
	private JLabel groupLab;
	private JLabel roomLab;
	
	private JTextField titleField;
	private JTextArea descriptionField;
	
	public JDateChooser startTime;
	private JDateChooser endTime;
	
	private JSpinner startHourSpinner;
	private JSpinner endHourSpinner;
	private JSpinner startMinuteSpinner;
	private JSpinner endMinuteSpinner;
	
	private JComboBox personsCombo;
	private JList personsList;
	private JButton plusPerson;
	private JButton minusPerson;
	
	private JComboBox groupCombo;
	private JList groupList;
	private JButton plusGroup;
	private JButton minusGroup;
	
	private JList roomList;
	
	private JButton saveButton;
	private JButton cancelButton;
	
	private Appointment model;
	
	private UserComboBoxModel userComboModel;
	private GroupComboBoxModel groupComboModel;
	private DefaultListModel personListModel;
	private DefaultListModel groupListModel;
	private DefaultListModel roomListModel;
	
	private boolean isNull = true;
	private Main main;
	private MainGUI mainGui;
	
	public NewAppointmentPanel(Main main, MainGUI mainGui) {
		this(null, main, mainGui);
	}
	
	public NewAppointmentPanel(Appointment appointment, Main main, MainGUI mainGui) {
		createPanels();
		this.main = main;
		this.mainGui = mainGui;
		
		createAppointmentModel(appointment);
		
		if (!isNull) {
			updateNotNullGUI();
		}
		else if (isNull) {
			updateNullGUI();
		}
	}
	
	private void createAppointmentModel(Appointment oldApp) {
		if (oldApp == null) {
			this.isNull = true;
//			Appointment newApp = new Appointment(0, "",null, null, main.getUser());
		}
		else if (oldApp != null) {
			this.isNull = false;
			this.model = oldApp;
		}
	}
	
	private void updateNullGUI() {
		setSpinnerModelsNull();
		setComboBoxModels();
		setButtonListeners();
		addListModelsNull();
	}
	
	private void updateNotNullGUI() {
		if (!isNull) {
			titleField.setText(model.getTitle());
			descriptionField.setText(model.getDescription());
			startTime.setDate(model.getStartTime().getTime());
			endTime.setDate(model.getFinishTime().getTime());
			setSpinnerModelsNotNull();
			setComboBoxModels();
			setButtonListeners();
			addListModelsNotNull();
		}
		
	}
	
	private void removeGroupFromAppointment() {
		Group selected = (Group) groupList.getSelectedValue();
		groupListModel.removeElement(selected);
	}
	
	private void addGroupToAppointment() {
		Group selected = (Group) groupComboModel.getSelectedItem();
		groupListModel.addElement(selected);
	}
	
	private void removePersonFromAppointment() {
		Participant selected = (Participant) personsList.getSelectedValue();
		personListModel.removeElement(selected);
	}
	
	private void addPersonToAppointment() {
		User selected = (User) userComboModel.getSelectedItem();
		personListModel.addElement(selected);
	}
	
	private void addListModelsNull() {
		personListModel = new DefaultListModel();
		groupListModel = new DefaultListModel();
		roomListModel = new DefaultListModel();
		
		personsList.setModel(personListModel);
		groupList.setModel(groupListModel);
		roomList.setModel(roomListModel);
		
		ArrayList<Room> rooms = main.getRooms();
//		fyll inn romlisten med rom
		for (int i = 0; i < rooms.size(); i++) {
			roomListModel.addElement(rooms.get(i));
		}
		
	}
	
	private void addListModelsNotNull() {
		personListModel = new DefaultListModel();
		groupListModel = new DefaultListModel();
		roomListModel = new DefaultListModel();
		
		personsList.setModel(personListModel);
		groupList.setModel(groupListModel);
		roomList.setModel(roomListModel);
		
//		fyll inn personlisten med participants
		for (int i = 0; i < model.getParticipantLength(); i ++) {
			personListModel.addElement(model.getParticipant(i));
		}
		
		ArrayList<Room> rooms = main.getRooms();
//		fyll inn romlisten med rom
		for (int i = 0; i < rooms.size(); i++) {
			roomListModel.addElement(rooms.get(i));
		}
		
		roomList.setSelectedValue(model.getRoom(), true);
		
	}
	
	private void setButtonListeners() {
		plusPerson.addActionListener(new plusPersonListener());
		minusPerson.addActionListener(new minusPersonListener());
		plusGroup.addActionListener(new plusGroupListener());
		minusGroup.addActionListener(new minusGroupListener());
	}
	
	private void setComboBoxModels() {
		
		ArrayList<User> users = main.getPersons();
		userComboModel = new UserComboBoxModel(users);
		personsCombo.setModel(userComboModel);
		
		ArrayList<Group> groups = main.getGroups();
		groupComboModel = new GroupComboBoxModel(groups);
		groupCombo.setModel(groupComboModel);
		
	}
	
	private void setSpinnerModelsNull() {
		startHourSpinner.setModel(new SpinnerNumberModel(0, 0, 23, 1));
		startMinuteSpinner.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		
		endHourSpinner.setModel(new SpinnerNumberModel(0, 0, 23, 1));
		endMinuteSpinner.setModel(new SpinnerNumberModel(0, 0, 59, 1));
	}
	
	private void setSpinnerModelsNotNull() {
		startHourSpinner.setModel(new SpinnerNumberModel(model.getStartHour(), 00, 23, 1));
		startMinuteSpinner.setModel(new SpinnerNumberModel(model.getStartMinute(), 00, 59, 1));
		
		endHourSpinner.setModel(new SpinnerNumberModel(model.getFinishedHour(), 00, 23, 1));
		endMinuteSpinner.setModel(new SpinnerNumberModel(model.getFinishedMinute(), 00, 59, 1));
		
	}
	
	private void createPanels() {
		createJointTitleDescPanel();
		createJointTimePanel();
		createJointPersGroupRoomButPanel();
		
		setLayout(new GridBagLayout());
		GridBagConstraints top = new GridBagConstraints();
		top.insets = new Insets(15, 15, 5, 15);
		top.anchor = GridBagConstraints.LINE_START;
		add(jointTitleDescPanel, top);
		
		GridBagConstraints center = new GridBagConstraints();
		center.gridy = 1;
		center.insets = new Insets(5, 15, 5, 15);
		center.anchor = GridBagConstraints.LINE_START;
		add(jointTimePanel, center);
		
		GridBagConstraints bottom = new GridBagConstraints();
		bottom.gridy = 2;
		bottom.insets = new Insets(5, 15, 15, 15);
		bottom.anchor = GridBagConstraints.LINE_START;
		add(jointPersGroupRoomButPanel, bottom);
	}
	
	private void createJointTitleDescPanel() {
		//create titlePanel
		titlePanel = new JPanel(new BorderLayout());
		titleLab = new JLabel("Title");
		titleField = new JTextField(20);
		titlePanel.add(titleLab, BorderLayout.NORTH);
		titlePanel.add(titleField, BorderLayout.SOUTH);
		
		//create descriptionPanel
		descriptionPanel = new JPanel(new BorderLayout());
		descLab = new JLabel("Description");
		descriptionField = new JTextArea(5, 30);
		descriptionField.setWrapStyleWord(true);
		descriptionField.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(descriptionField);
		descriptionPanel.add(descLab, BorderLayout.NORTH);
		descriptionPanel.add(scroll, BorderLayout.SOUTH);
		
		//put them together in jointTitleDescPanel
		jointTitleDescPanel = new JPanel(new BorderLayout());
		
		titlePanel.setBorder(new EmptyBorder(10, 10, 5, 0));
		jointTitleDescPanel.add(titlePanel, BorderLayout.NORTH);
		
		descriptionPanel.setBorder(new EmptyBorder(5, 10, 5, 0));
		jointTitleDescPanel.add(descriptionPanel, BorderLayout.SOUTH);
	}
	
	private void createJointTimePanel() {
		createStartTimePanel();
		createEndTimePanel();
		
		jointTimePanel = new JPanel(new BorderLayout());
		startTimePanel.setBorder(new EmptyBorder(0, 0, 0, 30));
		jointTimePanel.add(startTimePanel, BorderLayout.WEST);
		jointTimePanel.add(endTimePanel, BorderLayout.EAST);
		
	}
	
	private void createEndTimePanel() {
//		create endTimePanel
		endTimePanel = new JPanel(new GridBagLayout());
		
		endLab = new JLabel("End");
		GridBagConstraints endLabC = new GridBagConstraints();
		endLabC.insets = new Insets(10,10,5,0);
		endLabC.anchor = GridBagConstraints.LINE_START;
		endTimePanel.add(endLab, endLabC);
		
		endTime = new JDateChooser();
		GridBagConstraints endTimeC = new GridBagConstraints();
		endTimeC.gridy = 1;
		endTimeC.gridwidth = 3;
		endTimeC.insets = new Insets(5, 10, 5, 0);
		endTimePanel.add(endTime, endTimeC);
		
		endHourSpinner = new JSpinner();
		endHourSpinner.setPreferredSize(new Dimension(45, 28));
		GridBagConstraints eHourC = new GridBagConstraints();
		eHourC.gridy = 2;
		eHourC.insets = new Insets(5, 10, 5, 5);
		endTimePanel.add(endHourSpinner, eHourC);
		
		colonLab = new JLabel(":");
		GridBagConstraints eColonC = new GridBagConstraints();
		eColonC.gridx = 1;
		eColonC.gridy = 2;
		endTimePanel.add(colonLab, eColonC);
		
		endMinuteSpinner = new JSpinner();
		endMinuteSpinner.setPreferredSize(new Dimension(45, 28));
		GridBagConstraints eMinuteC = new GridBagConstraints();
		eMinuteC.gridy = 2;
		eMinuteC.gridx = 2;
		eMinuteC.insets = new Insets(5, 0, 5, 0);
		eMinuteC.anchor = GridBagConstraints.LINE_START;
		endTimePanel.add(endMinuteSpinner, eMinuteC);
	}
	
	private void createStartTimePanel() {
		//create startTimePanel
		startTimePanel = new JPanel(new GridBagLayout());
		
		startLab = new JLabel("Start");
		GridBagConstraints startLabC = new GridBagConstraints();
		startLabC.insets = new Insets(10,10,5,0);
		startLabC.anchor = GridBagConstraints.LINE_START;
		startTimePanel.add(startLab, startLabC);
		
		startTime = new JDateChooser();
		GridBagConstraints startTimeC = new GridBagConstraints();
		startTimeC.gridy = 1;
		startTimeC.gridwidth = 3;
		startTimeC.insets = new Insets(5, 10, 5, 0);
		startTimePanel.add(startTime, startTimeC);
		
		startHourSpinner = new JSpinner();
		startHourSpinner.setPreferredSize(new Dimension(45, 28));
		GridBagConstraints hourC = new GridBagConstraints();
		hourC.gridy = 2;
		hourC.insets = new Insets(5, 10, 5, 5);
		startTimePanel.add(startHourSpinner, hourC);
		
		colonLab = new JLabel(":");
		GridBagConstraints colonC = new GridBagConstraints();
		colonC.gridx = 1;
		colonC.gridy = 2;
		startTimePanel.add(colonLab, colonC);
		
		startMinuteSpinner = new JSpinner();
		startMinuteSpinner.setPreferredSize(new Dimension(45, 28));
		GridBagConstraints minuteC = new GridBagConstraints();
		minuteC.gridy = 2;
		minuteC.gridx = 2;
		minuteC.insets = new Insets(5, 0, 5, 0);
		minuteC.anchor = GridBagConstraints.LINE_START;
		startTimePanel.add(startMinuteSpinner, minuteC);
	}
	
	private void createJointPersGroupRoomButPanel() {
		createPersonsPanel();
		createGroupPanel();
		createRoomPanel();
		createButtonPanel();
		
		jointPersGroupRoomButPanel = new JPanel(new GridBagLayout());
		
		GridBagConstraints pgbc = new GridBagConstraints();
		jointPersGroupRoomButPanel.add(personsPanel, pgbc);
		
		GridBagConstraints ggbc = new GridBagConstraints();
		ggbc.gridx = 1;
		jointPersGroupRoomButPanel.add(groupPanel, ggbc);
		
		GridBagConstraints rgbc = new GridBagConstraints();
		rgbc.gridx = 2;
		jointPersGroupRoomButPanel.add(roomPanel, rgbc);
		
		GridBagConstraints bgbc = new GridBagConstraints();
		bgbc.gridx = 2;
		bgbc.gridy = 1;
		bgbc.anchor = GridBagConstraints.LAST_LINE_END;
		bgbc.insets = new Insets(20,0,0,0);
		jointPersGroupRoomButPanel.add(buttonPanel, bgbc);
		
	}
	
	private void createPersonsPanel() {
		personsPanel = new JPanel(new GridBagLayout());
		
		personLab = new JLabel("Persons");
		GridBagConstraints plc = new GridBagConstraints();
		
		plc.anchor = GridBagConstraints.LINE_START;
		plc.insets = new Insets(10, 10, 5, 0);
		personsPanel.add(personLab, plc);
		
		personsCombo = new JComboBox();
		personsCombo.setPreferredSize(new Dimension(150, 30));
		GridBagConstraints pcc = new GridBagConstraints();
		pcc.gridy = 1;
		pcc.anchor = GridBagConstraints.LINE_START;
		pcc.insets = new Insets(5, 10, 5, 0);
		personsPanel.add(personsCombo, pcc);
		
		personsList = new JList(new DefaultListModel());
		JScrollPane listScroll = new JScrollPane(personsList);
		listScroll.setPreferredSize(new Dimension(150, 150));
		GridBagConstraints pc = new GridBagConstraints();
		pc.gridy = 2;
		pc.gridheight = 3;
		pc.gridwidth = 2;
		pc.insets = new Insets(5, 10, 0, 5);
		personsPanel.add(listScroll, pc);
		
		plusPerson = new JButton("+");
		//plusPerson.setPreferredSize(new Dimension(33, 29));
		GridBagConstraints ppc = new GridBagConstraints();
		ppc.gridx = 2;
		ppc.gridy = 3;
		ppc.weighty = 1;
		ppc.insets = new Insets(0, 0, 5, 5);
		ppc.anchor = GridBagConstraints.PAGE_END;
		personsPanel.add(plusPerson, ppc);
		
		minusPerson = new JButton("-");
		//minusPerson.setPreferredSize(new Dimension(33, 29));
		GridBagConstraints mpc = new GridBagConstraints();
		mpc.gridx = 2;
		mpc.gridy = 4;
		mpc.weighty = 1;
		mpc.insets = new Insets(5, 0, 0, 5);
		mpc.anchor = GridBagConstraints.PAGE_START;
		personsPanel.add(minusPerson, mpc);
		
	}
	
	private void createGroupPanel() {
		groupPanel = new JPanel(new GridBagLayout());
		
		groupLab = new JLabel("Group");
		GridBagConstraints glc = new GridBagConstraints();
		glc.anchor = GridBagConstraints.LINE_START;
		glc.insets = new Insets(10, 10, 5, 0);
		groupPanel.add(groupLab, glc);
		
		groupCombo = new JComboBox();
		groupCombo.setPreferredSize(new Dimension(150, 30));
		GridBagConstraints gcc = new GridBagConstraints();
		gcc.gridy = 1;
		gcc.anchor = GridBagConstraints.LINE_START;
		gcc.insets = new Insets(5, 10, 5, 0);
		groupPanel.add(groupCombo, gcc);
		
		groupList = new JList(new DefaultListModel());
		JScrollPane groupListScroll = new JScrollPane(groupList);
		groupListScroll.setPreferredSize(new Dimension(150, 150));
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridy = 2;
		gc.gridheight = 3;
		gc.gridwidth = 2;
		gc.insets = new Insets(5, 10, 0, 5);
		groupPanel.add(groupListScroll, gc);
		
		plusGroup = new JButton("+");
		//plusGroup.setPreferredSize(new Dimension(33, 29));
		GridBagConstraints pgc = new GridBagConstraints();
		pgc.gridx = 2;
		pgc.gridy = 3;
		pgc.weighty = 1;
		pgc.insets = new Insets(0, 0, 5, 5);
		pgc.anchor = GridBagConstraints.PAGE_END;
		groupPanel.add(plusGroup, pgc);
		
		minusGroup = new JButton("-");
		//minusGroup.setPreferredSize(new Dimension(33, 29));
		GridBagConstraints mgc = new GridBagConstraints();
		mgc.gridx = 2;
		mgc.gridy = 4;
		mgc.weighty = 1;
		mgc.insets = new Insets(5, 0, 0, 5);
		mgc.anchor = GridBagConstraints.PAGE_START;
		groupPanel.add(minusGroup, mgc);
	}
	
	private void createRoomPanel() {
		roomPanel = new JPanel(new GridBagLayout());
		
		roomLab = new JLabel("Room");
		GridBagConstraints rlc = new GridBagConstraints();
		rlc.anchor = GridBagConstraints.LINE_START;
		rlc.insets = new Insets(10, 10, 5, 0);
		roomPanel.add(roomLab, rlc);
		
		roomList = new JList(new DefaultListModel());
		JScrollPane roomListScroll = new JScrollPane(roomList);
		roomListScroll.setPreferredSize(new Dimension(150, 190));
		GridBagConstraints rc = new GridBagConstraints();
		rc.gridy = 2;
		rc.gridheight = 3;
		rc.gridwidth = 2;
		rc.insets = new Insets(5, 5, 0, 5);
		roomPanel.add(roomListScroll, rc);
	}
	
	private void createButtonPanel() {
		buttonPanel = new JPanel(new GridBagLayout());
		
		saveButton = new JButton("Save");
		saveButton.addActionListener(new saveButtonListener());
		saveButton.setPreferredSize(new Dimension(100, 30));
		GridBagConstraints sbc = new GridBagConstraints();
		sbc.insets = new Insets(5, 0, 5, 5);
		buttonPanel.add(saveButton, sbc);
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new cancelButtonListener());
		cancelButton.setPreferredSize(new Dimension(100, 30));
		GridBagConstraints cbc = new GridBagConstraints();
		cbc.gridx = 1;
		cbc.insets = new Insets(5, 5, 5, 5);
		buttonPanel.add(cancelButton, cbc);
		
	}
	
	private void saveAppointment() {
		String title = titleField.getText();
		String descr = descriptionField.getText();
		
		//construct starttime
		int syear = startTime.getDate().getYear();
		int smonth = startTime.getDate().getMonth();
		int sddate = startTime.getDate().getDate();
		int sh = (Integer) startHourSpinner.getValue();
		int sm = (Integer) startMinuteSpinner.getValue();
		Date sDate = new Date(syear, smonth, sddate, sh, sm);
		
		//set startTime
		Calendar start = new GregorianCalendar();
		start.setTime(sDate);
		
		//construct endTime
		int eyear = endTime.getDate().getYear();
		int emonth = endTime.getDate().getMonth();
		int eddate = endTime.getDate().getDate();
		int eh = (Integer) endHourSpinner.getValue();
		int em = (Integer) endMinuteSpinner.getValue();
		Date eDate = new Date(eyear, emonth, eddate, eh, em);
		
		//set endTime
		Calendar finish = new GregorianCalendar();
		finish.setTime(eDate);
		
		User owner = main.getUser();
		Room r = (Room) roomList.getSelectedValue();
		Appointment saveApp;
		
		if (isNull) {
			saveApp = new Appointment(0, title, start, finish, owner);
		}else {
			saveApp = new Appointment(model.getAppointmentId(), title, start, finish, owner);
		}
		
		
		//legg til participants
		ArrayList<Participant> deletablePart = new ArrayList<Participant>();
		
		deletablePart.add(new Participant(saveApp, owner));
		Map<String, User> userMap = new HashMap<String, User>();
		for (int i = 0; i < personListModel.getSize(); i++) {
			userMap.put(((User) personListModel.get(i)).getUsername(), ((User) personListModel.get(i)));
		}
		
		//fra gruppe
		for (int i = 0; i < groupListModel.getSize(); i++) {
			ArrayList<Member> members = ((Group) groupListModel.get(i)).getMembers();
			for (int j = 0; j < members.size(); j++) {
				userMap.put(members.get(j).getUser().getUserName(), members.get(j).getUser());
			}
		}
		
		ArrayList<User> allPart = new ArrayList<User>(userMap.values());
		
		for (int i = 0; i < allPart.size(); i++) {
			deletablePart.add(new Participant(saveApp, allPart.get(i)));
		}

		saveApp.setRoom(r);
		saveApp.setDescription(descr);
		main.getServer().insertAppointment(saveApp);
		
		//delete participants
		for (int i = 0; i < deletablePart.size(); i++) {
			deletablePart.get(i).remove();
		}
		saveApp.remove();
		
		
	}
	
	private class saveButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			saveAppointment();
			mainGui.newAppointmentFrame.dispose();
		}
	}
	
	private class cancelButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			mainGui.newAppointmentFrame.dispose();
		}
	}
	
	private class plusPersonListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			addPersonToAppointment();
		}
	}
	
	private class minusPersonListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			removePersonFromAppointment();
		}
	}
	
	private class plusGroupListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			addGroupToAppointment();
		}
	}
	
	private class minusGroupListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			removeGroupFromAppointment();
		}
	}
	
}
