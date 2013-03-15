package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import data.Main;
import data.Server;
import data.User;

public class TabPanel extends JPanel {
	
	private Main main;
	private JTabbedPane tabbedPane;
	
	public TabPanel(Main main){
		this.main = main;
		setLayout(new GridBagLayout());
		GridBagConstraints mainC = new GridBagConstraints();
		
		//Lager alle faner med tilhørende panels
		tabbedPane = new JTabbedPane();
		
		JComponent panel = new WeekPanel(this.main);
		JScrollPane weekPane = new JScrollPane();
		weekPane.setPreferredSize(new Dimension(650,200));
		tabbedPane.addTab("Week", panel);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		
		JComponent panel2 = new NotificationPanel();
		tabbedPane.addTab("Day", panel2);
		panel2.setPreferredSize(new Dimension(500,100));
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		
		JComponent panel3 = new NotificationPanel();
		tabbedPane.addTab("Group", panel3);
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
		
		JComponent panel4 = new NotificationPanel();
		tabbedPane.addTab("Groups", panel4);
		tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
		
		JComponent panel5 = new NotificationPanel();
		tabbedPane.addTab("Notifications", panel5);
		tabbedPane.setMnemonicAt(4, KeyEvent.VK_5);
		
		//Plasserer fanevinduet
		mainC.gridx = 1;
		mainC.gridy = 2;
		add(tabbedPane,mainC);
		
		//Lager NewAppointment-knapp og Log out knapp:
		
		JButton newAppointment = new JButton("New Appointment");
		mainC.gridx = 0;
		mainC.gridy = 0;
		add(newAppointment,mainC);
		
		JButton Logout = new JButton("Log out");
		mainC.gridx = 2;
		mainC.gridy= 0;
		add(Logout,mainC);
		
	}
	

}
