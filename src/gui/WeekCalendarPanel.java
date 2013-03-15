package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class WeekCalendarPanel extends JPanel {
	
	private CalendarModel model;
	private JTable table;
	private JScrollPane calendarPane;
	
	
	public WeekCalendarPanel(){
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		DayPanel mon = (DayPanel) new DayPanel("Mon");
		DayPanel tue = (DayPanel) new DayPanel("Tue");
		DayPanel wed = (DayPanel) new DayPanel("Wed");
		DayPanel thu = (DayPanel) new DayPanel("Thu");
		DayPanel fri = (DayPanel) new DayPanel("Fri");
		DayPanel sat = (DayPanel) new DayPanel("Sat");
		DayPanel sun = (DayPanel) new DayPanel("Sun");
		TimePanel time = new TimePanel();
		
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

}
