package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import data.Main;

public class WeekCalendarPanel extends JPanel {
	
	private CalendarModel model;
	private JTable table;
	private JScrollPane calendarPane;
	private JPanel mon;
	private JPanel tue;
	private JPanel wed;
	private JPanel thu;
	private JPanel fri;
	private JPanel sat;
	private JPanel sun;
	private Main main;
	
	
	
	public WeekCalendarPanel(Main main){
		this.main = main;
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		mon = (DayPanel) new DayPanel("Mon");
		tue = (DayPanel) new DayPanel("Tue");
		wed = (DayPanel) new DayPanel("Wed");
		thu = (DayPanel) new DayPanel("Thu");
		fri = (DayPanel) new DayPanel("Fri");
		sat = (DayPanel) new DayPanel("Sat");
		sun = (DayPanel) new DayPanel("Sun");
		TimePanel time = new TimePanel();
		

		//addAppointments();
		
		c.gridx = 0;
		c.gridy = 1;
		add(time,c);
		
		c.gridx = 1;
		c.gridy = 1;
		add(mon,c);
		c.gridx = 2;
		c.gridy = 1;
		add(tue,c);
		c.gridx = 3;
		c.gridy = 1;
		add(wed,c);
		c.gridx = 4;
		c.gridy = 1;
		add(thu,c);
		c.gridx = 5;
		c.gridy = 1;
		add(fri,c);
		c.gridx = 6;
		c.gridy = 1;
		add(sat,c);
		c.gridx = 7;
		c.gridy = 1;
		add(sun,c);
	}
	
//	private void addAppointments(){
//		for(int i = 0; i<main.getUser().get)
//	}
	
	public void addDayPanel(String string){
		JPanel dayPanel = new JPanel();
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JPanel mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(75,600));
		setPreferredSize(new Dimension(75,650));
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		c.gridx = 0;
		c.gridy = 0;
		dayPanel.add(new JLabel(string),c);
		c.gridx = 0;
		c.gridy = 1;
		dayPanel.add(mainPanel,c);
	}
	
	private void makeAppPanels(){
		for (int i = 0; i< 14; i++){
			appPanels.add(new AppPanel(Color.WHITE));
		}
	}

}
