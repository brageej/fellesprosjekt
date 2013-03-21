package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private MainGUI mainGui;
	private JTabbedPane tabbedPane;
	private JButton logout;
	
	private JButton newAppointment;
	
	public TabPanel(Main main, MainGUI mainGui){
		this.main = main;
		this.mainGui = mainGui;
		setLayout(new GridBagLayout());
		GridBagConstraints mainC = new GridBagConstraints();
		
		//Lager alle faner med tilhørende panels
		tabbedPane = new JTabbedPane();
		
		JComponent panel = new WeekPanel(this.main);
		tabbedPane.addTab("Week", panel);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		
		DayCalendarPanel dayCalendarPanel = new DayCalendarPanel(this.main);
		JComponent panel2 = dayCalendarPanel;
		tabbedPane.addTab("Day", panel2);
		//panel2.setPreferredSize(new Dimension(500,100));
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		
		JComponent panel3 = new GroupCalendarPanel(this.main);
		tabbedPane.addTab("Group", panel3);
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
		
		JComponent panel4 = new NotificationPanel(this.main);
		tabbedPane.addTab("Groups", panel4);
		tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
		
		JComponent panel5 = new NotificationPanel(this.main);
		tabbedPane.addTab("Notifications", panel5);
		tabbedPane.setMnemonicAt(4, KeyEvent.VK_5);
		
		tabbedPane.setPreferredSize(new Dimension(850,600));
		//setPreferredSize(new Dimension(1000,1000));
		
		//Plasserer fanevinduet
		mainC.gridx = 1;
		mainC.gridy = 2;
		add(tabbedPane,mainC);
		
		//Lager NewAppointment-knapp og Log out knapp:
		
		newAppointment = new JButton("New Appointment");
		newAppointment.addActionListener(new newAppointmentListener());
		mainC.gridx = 0;
		mainC.gridy = 0;
		add(newAppointment,mainC);
		
		logout = new JButton("Log out");
		logout.addActionListener(new logoutListener());
		mainC.gridx = 2;
		mainC.gridy= 0;
		add(logout,mainC);
		
	}
	
	private class newAppointmentListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			mainGui.showNewAppointmentNullPanel();
		}
	}
	
	private class logoutListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			mainGui.showLoginPanel();
			mainGui.tabFrame.dispose();
		}
	}

}
