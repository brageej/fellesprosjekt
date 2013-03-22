package gui;

import gui.DayCalendarPanel.SelectionListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.omg.CORBA.Bounds;

import data.Appointment;
import data.Main;
import data.Participant;

public class NotificationPanel extends JPanel implements PropertyChangeListener {
	
	private GridBagLayout mainLayout;
	private GridBagConstraints mainC;
	private Main main;
	private Participant SelectedAppointment;
	private JList appointmentList;
	private DefaultListModel appointmentListModel;
	private DefaultListSelectionModel appointmentSelectionModel;
	
	private JLabel titleLable;
	private JTextField titleText;
	
	private JLabel descrLabel;
	private JTextPane descrText;
	
	private JLabel timeLabel;
	private JTextField timeText;
	
	private JLabel roomLabel;
	private JTextField roomText;
	private MainGUI mainGui;
	
	public NotificationPanel(Main main, MainGUI mainGui){
		mainLayout = new GridBagLayout();
		setLayout(mainLayout);
		mainC = new GridBagConstraints();
		this.main = main;
		this.mainGui = mainGui;
		
		JPanel Panel1 = new JPanel(false);
		Panel1.setLayout(new GridBagLayout());
		GridBagConstraints Panel1C = new GridBagConstraints();
		
		JLabel appointments = new JLabel("Appointments:");
		Panel1C.gridx=0;
		Panel1C.gridy=0;
		Panel1.add(appointments,Panel1C);
		
		appointmentListModel = new DefaultListModel();
		appointmentSelectionModel = new DefaultListSelectionModel();
		appointmentList = new JList(appointmentListModel);
		appointmentList.setSelectionModel(appointmentSelectionModel);
		appointmentList.addListSelectionListener(new SelectionListener());
		JScrollPane scrollPane = new JScrollPane(appointmentList);
		scrollPane.setPreferredSize(new Dimension(100,150));
		Panel1C.gridx=0;
		Panel1C.gridy=1;
		Panel1.add(scrollPane,Panel1C);
		addAppointments();
		
		
		JPanel Panel2 = new JPanel(false);
		Panel2.setLayout(new GridBagLayout());
		GridBagConstraints Panel2C = new GridBagConstraints();
		
		titleLable = new JLabel("Title: ");
		Panel2C.gridx=0;
		Panel2C.gridy=0;
		Panel2.add(titleLable, Panel2C);
		
		titleText = new JTextField();
		titleText.setColumns(20);
		Panel2C.gridx=1;
		Panel2C.gridy=0;
		Panel2.add(titleText,Panel2C);
		
		descrLabel = new JLabel("Description: ");
		Panel2C.gridx=0;
		Panel2C.gridy=2;
		Panel2.add(descrLabel, Panel2C);
		
		descrText = new JTextPane();
		JScrollPane descrPane = new JScrollPane(descrText);
		descrPane.setBorder(new LineBorder(Color.GRAY));
		descrPane.setPreferredSize(new Dimension(223,75));
		Panel2C.gridx=1;
		Panel2C.gridy=2;
		Panel2C.insets = new Insets(5,0,0,0);
		Panel2.add(descrPane,Panel2C);
		
		timeLabel = new JLabel("Time");
		Panel2C.gridx = 0;
		Panel2C.gridy = 3;
		Panel2.add(timeLabel,Panel2C);
		
		timeText = new JTextField();
		timeText.setColumns(20);
		Panel2C.gridx = 1;
		Panel2C.gridy = 3;
		Panel2.add(timeText, Panel2C);
		
		roomLabel = new JLabel("Room");
		Panel2C.gridx = 0;
		Panel2C.gridy = 4;
		Panel2.add(roomLabel,Panel2C);
		
		roomText = new JTextField();
		roomText.setColumns(20);
		Panel2C.gridx = 1;
		Panel2C.gridy = 4;
		Panel2.add(roomText, Panel2C);
		
		JPanel Panel3 = new JPanel();
		Panel3.setLayout(new GridBagLayout());
		GridBagConstraints Panel3C = new GridBagConstraints();
		
		JButton decline = new JButton("Decline");
		decline.setName("Decline");
		decline.addActionListener(new declineAction());
		Panel3C.gridx = 1;
		Panel3C.gridy = 0;
		Panel3.add(decline,Panel3C);
		
		JButton accept = new JButton("Accept");
		accept.setName("Accept");
		accept.addActionListener(new acceptAction());
		Panel3C.gridx = 2;
		Panel3C.gridy = 0;
		Panel3.add(accept,Panel3C);
		
		
		mainC.gridx = 0;
		mainC.gridy = 0;
		add(Panel1,mainC);
		
		mainC.gridx = 1;
		mainC.gridy = 0;
		add(Panel2,mainC);
		
		mainC.gridx = 1;
		mainC.gridy = 1;
		add(Panel3,mainC);

		main.getServer().addPropertyChangeListener(this);
		
	}
	
	private void addAppointments(){
		appointmentListModel.removeAllElements();
		for(int i = 0; i<main.getUser().getAppointments().size();i++){
			if(this.main.getUser().getAppointments().get(i).getStatus().equals("No answer")){
				appointmentListModel.addElement(this.main.getUser().getAppointments().get(i));
			}
		}
	}
	
	private class declineAction implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			if(SelectedAppointment != null){
				Participant participant = new Participant(SelectedAppointment.getAppointment(), SelectedAppointment.getUser(), SelectedAppointment.getAlarm().getTimeInMillis(), "Declined");
				main.getServer().updateParticipant(participant);
				participant.remove();
			}
			clear();
			addAppointments();
			
		}
		
	}
	
	private class acceptAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(SelectedAppointment != null){
				Participant participant = new Participant(SelectedAppointment.getAppointment(), SelectedAppointment.getUser(), SelectedAppointment.getAlarm().getTimeInMillis(), "Accepted");
				main.getServer().updateParticipant(participant);
				participant.remove();
			}
			clear();
			addAppointments();
			
		}
		
	}
	
	private class SelectionListener implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent arg0) {	
			if(!appointmentSelectionModel.isSelectionEmpty()){
				SelectedAppointment = (Participant) appointmentList.getSelectedValue();
				titleText.setText(SelectedAppointment.getAppointment().getTitle());
				descrText.setText(SelectedAppointment.getAppointment().getDescription());
				String startHour = Integer.toString((SelectedAppointment.getAppointment().getStartHour()));
				String startMinute = Integer.toString((SelectedAppointment.getAppointment().getStartMinute()));
				String finishHour = Integer.toString((SelectedAppointment.getAppointment().getFinishedHour()));
				String finishMinute = Integer.toString((SelectedAppointment.getAppointment().getFinishedMinute()));
				String time = startHour + ":" + startMinute + "-" + finishHour + ":" + finishMinute;
				timeText.setText(time);
				roomText.setText(SelectedAppointment.getAppointment().getRoom().getRoomNumber());
			}
		}
		
	}
	
	private void clear(){
		
		titleText.setText("");
		descrText.setText("");
		timeText.setText("");
		roomText.setText("");
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		addAppointments();
		
	}

}
