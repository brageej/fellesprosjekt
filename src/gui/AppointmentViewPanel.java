package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import data.Appointment;
import data.Room;
import data.User;

public class AppointmentViewPanel extends JPanel{
	
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
	
	private Appointment model;
	
	public AppointmentViewPanel(Appointment model) {
		this.model = model;
		createPanels();
		updateComponents();
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
	
	private void addHourSpinnerModel() {
		alarmHour.setModel(new SpinnerNumberModel(Integer.parseInt(model.getAlarmHour()), 0, 23, 1));
	}
	
	private void addMinuteSpinnerModel() {
		alarmMinute.setModel(new SpinnerNumberModel(Integer.parseInt(model.getAlarmMinute()), 0, 59, 1));
	}
	
	private void createPersonsPanel() {
		personsPanel = new JPanel(new GridBagLayout());
		
		personsLab = new JLabel("Participants");
		GridBagConstraints rlc = new GridBagConstraints();
		rlc.anchor = GridBagConstraints.LINE_START;
		rlc.insets = new Insets(10, 10, 5, 0);
		personsPanel.add(personsLab, rlc);
		
		personsList = new JList(new DefaultListModel());
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
	
	private class deleteListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("Delete!");
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
		if (this.model != null) {
			addHourSpinnerModel();
			addMinuteSpinnerModel();
			this.titleField.setText(model.getTitle());
			this.descriptionArea.setText(model.getDescription());
			setTimeField();
			this.roomField.setText(model.getRoom().toString());
//			addPersonListModel();
			
			
		}
	}
	
	private void setTimeField() {
		String total =  model.getStartHour() + ":" + model.getStartMinute() + " - " + model.getFinishedHour() + ":" + model.getFinishedMinute();
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

	public Appointment getModel() {
		return model;
	}

	public void setModel(Appointment model) {
		this.model = model;
	}

	public static void main(String[] args) {
		Appointment m = new Appointment("Tittle", 1005, 1109, new User("u", "pass", "Torgeir"));
		m.setDescription("Dette er en description! ¾¿lpdlfaŒsodjfn¾ksndv¿jkabnsd¿jvbas¿¾odjb");
		m.setAlarmTime(1001);
		m.setRoom(new Room("345"));
		
		JFrame frame = new JFrame("TestYo!");
		frame.getContentPane().add(new AppointmentViewPanel(m));
		frame.pack();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
