package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

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

import org.omg.CORBA.Bounds;

public class NotificationPanel extends JPanel {
	
	public NotificationPanel(){
		GridBagLayout mainLayout = new GridBagLayout();
		setLayout(mainLayout);
		GridBagConstraints mainC = new GridBagConstraints();
		JPanel Panel1 = new JPanel(false);
		Panel1.setLayout(new GridBagLayout());
		GridBagConstraints Panel1C = new GridBagConstraints();
		
		JLabel appointments = new JLabel("Appointments:");
		Panel1C.gridx=0;
		Panel1C.gridy=0;
		Panel1.add(appointments,Panel1C);
		
		JList appointmentList = new JList();
		JScrollPane scrollPane = new JScrollPane(appointmentList);
		scrollPane.setPreferredSize(new Dimension(100,150));
		Panel1C.gridx=0;
		Panel1C.gridy=1;
		Panel1.add(scrollPane,Panel1C);
		
		
		JPanel Panel2 = new JPanel(false);
		Panel2.setLayout(new GridBagLayout());
		GridBagConstraints Panel2C = new GridBagConstraints();
		
		JLabel titleLable = new JLabel("Title: ");
		Panel2C.gridx=0;
		Panel2C.gridy=0;
		Panel2.add(titleLable, Panel2C);
		
		JTextField titleText = new JTextField();
		titleText.setColumns(20);
		Panel2C.gridx=1;
		Panel2C.gridy=0;
		Panel2.add(titleText,Panel2C);
		
		JLabel descrLabel = new JLabel("Description: ");
		Panel2C.gridx=0;
		Panel2C.gridy=2;
		Panel2.add(descrLabel, Panel2C);
		
		JTextPane descrText = new JTextPane();
		JScrollPane descrPane = new JScrollPane(descrText);
		descrPane.setBorder(new LineBorder(Color.GRAY));
		descrPane.setPreferredSize(new Dimension(223,75));
		Panel2C.gridx=1;
		Panel2C.gridy=2;
		Panel2C.insets = new Insets(5,0,0,0);
		Panel2.add(descrPane,Panel2C);
		
		JLabel timeLabel = new JLabel("Time");
		Panel2C.gridx = 0;
		Panel2C.gridy = 3;
		Panel2.add(timeLabel,Panel2C);
		
		JTextField timeText = new JTextField();
		timeText.setColumns(20);
		Panel2C.gridx = 1;
		Panel2C.gridy = 3;
		Panel2.add(timeText, Panel2C);
		
		JLabel roomLabel = new JLabel("Room");
		Panel2C.gridx = 0;
		Panel2C.gridy = 4;
		Panel2.add(roomLabel,Panel2C);
		
		JTextField roomText = new JTextField();
		roomText.setColumns(20);
		Panel2C.gridx = 1;
		Panel2C.gridy = 4;
		Panel2.add(roomText, Panel2C);
		
		JPanel Panel3 = new JPanel();
		Panel3.setLayout(new GridBagLayout());
		GridBagConstraints Panel3C = new GridBagConstraints();
		
		JButton decline = new JButton("Decline");
		Panel3C.gridx = 1;
		Panel3C.gridy = 0;
		Panel3.add(decline,Panel3C);
		
		JButton accept = new JButton("Accept");
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


		
	}

}
