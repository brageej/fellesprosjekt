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
import javax.swing.JTabbedPane;

public class TabPanel extends JPanel {
	
	public TabPanel(){
		setLayout(new GridBagLayout());
		GridBagConstraints mainC = new GridBagConstraints();
		
		//Lager alle faner med tilhørende panels
		JTabbedPane tabbedPane = new JTabbedPane();
		
		JComponent panel = new WeekCalendarPanel();
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
	
	public static void main(String[] args){
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Tabtest");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JComponent contentPane = new TabPanel();
		contentPane.setOpaque(true);
		frame.getContentPane().add(contentPane);
		frame.pack();
		frame.setVisible(true);
	}

}
