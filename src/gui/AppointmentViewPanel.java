package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import data.Appointment;
import data.Participant;
import data.Room;
import data.User;

public class AppointmentViewPanel extends JPanel implements PropertyChangeListener{
	
	private JLabel titleLab;
	private JTextField titleField;
	
	private JLabel descLab;
	private JTextArea descriptionArea;
	
	private JLabel timeLab;
	private JTextField timeField;
	
	private JLabel roomLab;
	private JTextField roomField;
	
	private JLabel colonLab;
	private JLabel alarmLab;
	private JDateChooser alarmTime;
	private JSpinner alarmHour;
	private JSpinner alarmMinute;
	
	private JLabel personsLab;
	private JList personsList;
	
	private JButton deleteButton;
	private JButton editButton;
	private JButton cancelButton;
	private JButton okButton;
	
	private JPanel titlePanel;
	private JPanel descPanel;
	private JPanel timePanel;
	private JPanel roomPanel;
	private JPanel alarmPanel;
	private JPanel personsPanel;
	private JPanel buttonPanel;
	
	private JPanel jointTitleDescPanel;
	private JPanel jointTimeRoomAlarmPanel;
	
	private DefaultListModel listModel;
	
	private Appointment model;
	private Participant participant;
	
	private DecimalFormat df = new DecimalFormat("00");
	
	public AppointmentViewPanel(Appointment model, Participant participant) {
		this.model = model;
		this.participant = participant;
		
		createPanels();
		
		if (this.model != null) {
			updateComponents();
			model.addPropertyChangeListener(this);
		}
		
		
	}
	
	private void createPanels() {
		createJointPersonGroupPanel();
		createJointTimeRoomAlarmPanel();
		createJointTitleDescPanel();
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints tgbc = new GridBagConstraints();
		tgbc.insets = new Insets(15, 15, 0, 0);
		tgbc.gridwidth = 2;
		tgbc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(jointTitleDescPanel, tgbc);
		
		GridBagConstraints rgbc = new GridBagConstraints();
		rgbc.gridy = 1;
		rgbc.insets = new Insets(0, 15, 10, 0);
		rgbc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(jointTimeRoomAlarmPanel, rgbc);
		
		GridBagConstraints pgbc = new GridBagConstraints();
		pgbc.gridx = 2;
//		pgbc.anchor = GridBagConstraints.FIRST_LINE_START;
		pgbc.insets = new Insets(15, 0, 5, 15);
		add(personsPanel, pgbc);
		
		GridBagConstraints agbc = new GridBagConstraints();
		agbc.gridx = 2;
		agbc.gridy = 1;
//		agbc.anchor = GridBagConstraints.LINE_END;
		agbc.insets = new Insets(5, 0, 10, 15);
		add(alarmPanel, agbc);
		
		GridBagConstraints bgbc = new GridBagConstraints();
		bgbc.gridx = 1;
		bgbc.gridy = 2;
		bgbc.gridwidth = 2;
		bgbc.insets = new Insets(5, 0, 15, 15);
		add(buttonPanel, bgbc);
		
		
	}
	
	private void createJointTitleDescPanel() {
		createTitlePanel();
		createDescPanel();
		
//		join them
		jointTitleDescPanel = new JPanel(new BorderLayout());
		
		titlePanel.setBorder(new EmptyBorder(10, 10, 5, 0));
		jointTitleDescPanel.add(titlePanel, BorderLayout.NORTH);
		
		descPanel.setBorder(new EmptyBorder(5, 10, 5, 0));
		jointTitleDescPanel.add(descPanel, BorderLayout.SOUTH);
		
	}
	
	private void createJointTimeRoomAlarmPanel() {
		createTimePanel();
		createRoomPanel();
		createAlarmPanel();
		
		jointTimeRoomAlarmPanel = new JPanel(new BorderLayout());
		
		timePanel.setBorder(new EmptyBorder(10, 10, 5, 0));
		jointTimeRoomAlarmPanel.add(timePanel, BorderLayout.NORTH);
		
		roomPanel.setBorder(new EmptyBorder(5, 10, 5, 0));
		jointTimeRoomAlarmPanel.add(roomPanel, BorderLayout.SOUTH);
		
	}
	
	private void createJointPersonGroupPanel() {
		createPersonsPanel();
		createButtonPanel();
	}
	
	private void createTitlePanel() {
		titlePanel = new JPanel(new BorderLayout());
		titleLab = new JLabel("Title");
		titleField = new JTextField(20);
		titleField.setEditable(false);
		titlePanel.add(titleLab, BorderLayout.NORTH);
		titlePanel.add(titleField, BorderLayout.SOUTH);
	}
	
	private void createDescPanel() {
		//create descriptionPanel
		descPanel = new JPanel(new BorderLayout());
		descLab = new JLabel("Description");
		descriptionArea = new JTextArea(5, 30);
		descriptionArea.setLineWrap(true);
		descriptionArea.setWrapStyleWord(true);
		descriptionArea.setEditable(false);
		JScrollPane scroll = new JScrollPane(descriptionArea);
		descPanel.add(descLab, BorderLayout.NORTH);
		descPanel.add(scroll, BorderLayout.SOUTH);
	}
	
	private void createTimePanel() {
		timePanel = new JPanel(new BorderLayout());
		timeLab = new JLabel("Time");
		timeField = new JTextField(20);
		timeField.setEditable(false);
		timePanel.add(timeLab, BorderLayout.NORTH);
		timePanel.add(timeField, BorderLayout.SOUTH);
	}
	
	private void createRoomPanel() {
		roomPanel = new JPanel(new BorderLayout());
		roomLab = new JLabel("Room");
		roomField = new JTextField(20);
		roomField.setEditable(false);
		roomPanel.add(roomLab, BorderLayout.NORTH);
		roomPanel.add(roomField, BorderLayout.SOUTH);
	}
	
	private void createAlarmPanel() {
		alarmPanel = new JPanel(new GridBagLayout());
		
		alarmLab = new JLabel("Alarm");
		GridBagConstraints alarmLabC = new GridBagConstraints();
		alarmLabC.insets = new Insets(10,10,5,0);
		alarmLabC.anchor = GridBagConstraints.LINE_START;
		alarmPanel.add(alarmLab, alarmLabC);
		
		alarmTime = new JDateChooser();
		GridBagConstraints alarmTimeC = new GridBagConstraints();
		alarmTimeC.gridy = 1;
		alarmTimeC.gridwidth = 3;
		alarmTimeC.insets = new Insets(5, 10, 5, 0);
		alarmPanel.add(alarmTime, alarmTimeC);
		
		alarmHour = new JSpinner();
		alarmHour.setPreferredSize(new Dimension(45, 28));
		
		GridBagConstraints hourC = new GridBagConstraints();
		hourC.gridy = 2;
		hourC.insets = new Insets(5, 10, 5, 5);
		alarmPanel.add(alarmHour, hourC);
		
		colonLab = new JLabel(":");
		GridBagConstraints colonC = new GridBagConstraints();
		colonC.gridx = 1;
		colonC.gridy = 2;
		alarmPanel.add(colonLab, colonC);
		
		alarmMinute = new JSpinner();
		alarmMinute.setPreferredSize(new Dimension(45, 28));
		
		GridBagConstraints minuteC = new GridBagConstraints();
		minuteC.gridy = 2;
		minuteC.gridx = 2;
		minuteC.insets = new Insets(5, 0, 5, 0);
		minuteC.anchor = GridBagConstraints.LINE_START;
		alarmPanel.add(alarmMinute, minuteC);
	}

	private void createPersonsPanel() {
		personsPanel = new JPanel(new GridBagLayout());
		
		personsLab = new JLabel("Participants");
		GridBagConstraints rlc = new GridBagConstraints();
		rlc.anchor = GridBagConstraints.LINE_START;
		rlc.insets = new Insets(10, 10, 5, 0);
		personsPanel.add(personsLab, rlc);
		
		personsList = new JList();
		listModel = new DefaultListModel();
		personsList.setModel(listModel);
		JScrollPane personListScroll = new JScrollPane(personsList);
		personListScroll.setPreferredSize(new Dimension(150, 190));
		GridBagConstraints rc = new GridBagConstraints();
		rc.gridy = 2;
		rc.gridheight = 3;
		rc.gridwidth = 2;
		rc.insets = new Insets(5, 5, 0, 5);
		personsPanel.add(personListScroll, rc);
	}
	
	private void createButtonPanel() {
		buttonPanel = new JPanel(new GridBagLayout());
		
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new deleteListener());
		deleteButton.setPreferredSize(new Dimension(100, 30));
		GridBagConstraints dbc = new GridBagConstraints();
		dbc.insets = new Insets(5, 0, 5, 5);
		buttonPanel.add(deleteButton, dbc);
		
		editButton = new JButton("Edit");
		editButton.addActionListener(new editListener());
		editButton.setPreferredSize(new Dimension(100, 30));
		GridBagConstraints ebc = new GridBagConstraints();
		ebc.gridx = 1;
		ebc.insets = new Insets(5, 0, 5, 5);
		buttonPanel.add(editButton, ebc);
		
		okButton = new JButton("Ok");
		okButton.addActionListener(new okListener());
		okButton.setPreferredSize(new Dimension(100, 30));
		GridBagConstraints sbc = new GridBagConstraints();
		sbc.gridx = 2;
		sbc.insets = new Insets(5, 0, 5, 5);
		buttonPanel.add(okButton, sbc);
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new cancelListener());
		cancelButton.setPreferredSize(new Dimension(100, 30));
		GridBagConstraints cbc = new GridBagConstraints();
		cbc.gridx = 3;
		cbc.insets = new Insets(5, 0, 5, 5);
		buttonPanel.add(cancelButton, cbc);
	}
	
	private void showWarningPane() {
		URL warningIconURL = getClass().getResource("/warning.png");
		Object options[] = {"Yes", "No"};
		String optionText = "Do you really want to delete this appointment?";
		String optionTitle = "Delete appointment?";
		ImageIcon icon = new ImageIcon(warningIconURL);
		
		int answer = JOptionPane.showOptionDialog(this,
			    optionText,
			    optionTitle,
			    JOptionPane.YES_NO_OPTION,
			    JOptionPane.WARNING_MESSAGE,
			    icon,
			    options,  //the titles of buttons
			    options[1]); //default button title
		
		if (answer == JOptionPane.YES_OPTION) {
			System.out.println("delete this!");
		}
	}
	
	private class deleteListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			showWarningPane();
		}
	}
	
	private class editListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Edit!");
		}		
	}
	
	private class cancelListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Cancel!");
		}
	}
	
	private class okListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Ok!");
		}
	}
	
	private void updateComponents() {
		addHourSpinnerModelAndDisplay();
		addMinuteSpinnerModelAndDisplay();
		alarmTime.setDate(participant.getAlarmTime().getTime());
		this.titleField.setText(model.getTitle());
		this.descriptionArea.setText(model.getDescription());
		setTimeField();
		this.roomField.setText(model.getRoom().toString());
		fillPersonListModel();
	}
	
	private void fillPersonListModel() {
		for (int i = 0; i < model.getParticipantLength(); i++) {
			listModel.addElement(model.getParticipant(i));
		}
		System.out.println(model.getParticipant(0));
		
	}
	
	private void addHourSpinnerModelAndDisplay() {
//		alarmHour.setEditor(new JSpinner.NumberEditor(alarmHour, "00"));
		alarmHour.setModel(new SpinnerNumberModel(participant.getAlarmHour(), 0, 23, 1));
		
	}
	
	private void addMinuteSpinnerModelAndDisplay() {
//		alarmMinute.setEditor(new JSpinner.NumberEditor(alarmMinute, "00"));
		alarmMinute.setModel(new SpinnerNumberModel(participant.getAlarmMinute(), 00, 59, 1));
	}
	
	private void setTimeField() {
		String total =  df.format(model.getStartHour()) + ":" 
				+ df.format(model.getStartMinute()) + " - " 
				+ df.format(model.getFinishedHour()) + ":" 
				+ df.format(model.getFinishedMinute());
		this.timeField.setText(total);
	}
	
	public JTextField getTitleField() {
		return titleField;
	}

	public JTextArea getDescriptionArea() {
		return descriptionArea;
	}

	public JTextField getTimeField() {
		return timeField;
	}

	public JTextField getRoomField() {
		return roomField;
	}

	public JDateChooser getAlarmTime() {
		return alarmTime;
	}

	public JSpinner getAlarmHour() {
		return alarmHour;
	}

	public JSpinner getAlarmMinute() {
		return alarmMinute;
	}

	public JList getPersonsList() {
		return personsList;
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent e) {
		if (e.getPropertyName() == Appointment.DESC_PROP) {
			descriptionArea.setText(model.getDescription());
		}else if (e.getPropertyName() == Appointment.FINISH_TIME_PROP || e.getPropertyName() == Appointment.START_TIME_PROP) {
			setTimeField();
		}else if (e.getPropertyName() == Appointment.PARTICIPANT_PROP) {
			fillPersonListModel();
		}else if (e.getPropertyName() == Appointment.ROOM_PROP) {
			roomField.setText(model.getRoom().toString());
		}else if (e.getPropertyName() == Appointment.TITLE_PROP) {
			titleField.setText(model.getTitle());
		} 
	}

	public static void main(String[] args) {
		Date start = new Date(2013, 4, 02, 12, 41);
		Date end = new Date(2013, 10, 30, 9, 00);
		Date alarm = new Date(2013, 3, 1, 12, 01);
		
		Calendar s = GregorianCalendar.getInstance();
		s.setTime(start);
		
		Calendar e = GregorianCalendar.getInstance();
		e.setTime(end);
		
		Calendar a = GregorianCalendar.getInstance();
		a.setTime(alarm);
		
		User t = new User("u", "pass", "Torgeir");
		User l = new User("p", "pass", "Bjarne");
		User o = new User("a", "pass", "Lol");
		User q = new User("c", "pass", "asfadsf");
		Appointment m = new Appointment("Tittle", s, e, t);
		Participant pa = new Participant(m, t);
		Participant pl = new Participant(m, l);
		Participant po = new Participant(m, o);
		Participant pq = new Participant(m, q);
		
		
		m.addParticipant(pa);
		m.addParticipant(po);
		m.addParticipant(pq);
		m.addParticipant(pl);
		
		m.setDescription("Dette er en description! ¾¿lpdlfaŒsodjfn¾ksndv¿jkabnsd¿jvbas¿¾odjb");
		pa.setAlarmTime(a);
		m.setRoom(new Room("345"));
		JFrame frame = new JFrame("TestYo!");
		frame.getContentPane().add(new AppointmentViewPanel(m, pa));
		frame.pack();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		m.setTitle("Ny tittle!");
	}

	
}
