package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TimePanel extends JPanel{
	
	private JLabel time1 = new JLabel("7:00-8:00");
	private JLabel time2 = new JLabel("8:00-9:00");
	private JLabel time3 = new JLabel("9:00-10:00");
	private JLabel time4 = new JLabel("10:00-11:00");
	private JLabel time5 = new JLabel("11:00-12:00");
	private JLabel time6 = new JLabel("12:00-13:00");
	private JLabel time7 = new JLabel("13:00-14:00");
	private JLabel time8 = new JLabel("14:00-15:00");
	private JLabel time9 = new JLabel("15:00-16:00");
	private JLabel time10 = new JLabel("16:00-17:00");
	private JLabel time11 = new JLabel("17:00-18:00");
	private JLabel time12 = new JLabel("18:00-19:00");
	private JLabel time13 = new JLabel("19:00-20:00");
	private JLabel time14 = new JLabel("");
	
	public TimePanel(){
		setPreferredSize(new Dimension(75,650));
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(30,0,0,0);
		
		c.gridx = 0;
		c.gridy = 0;
		add(time1, c);
		
		c.gridx = 0;
		c.gridy = 1;
		add(time2, c);
		
		c.gridx = 0;
		c.gridy = 2;
		add(time3, c);
		
		c.gridx = 0;
		c.gridy = 3;
		add(time4, c);
		
		c.gridx = 0;
		c.gridy = 4;
		add(time5, c);
		
		c.gridx = 0;
		c.gridy = 5;
		add(time6, c);
		
		c.gridx = 0;
		c.gridy = 6;
		add(time7, c);
		
		c.gridx = 0;
		c.gridy = 7;
		add(time8, c);
		
		c.gridx = 0;
		c.gridy = 8;
		add(time9, c);
		
		c.gridx = 0;
		c.gridy = 9;
		add(time10, c);
		
		c.gridx = 0;
		c.gridy = 10;
		add(time11, c);
		
		c.gridx = 0;
		c.gridy = 11;
		add(time12, c);
		
		c.gridx = 0;
		c.gridy = 12;
		add(time13, c);
		
		c.gridx = 0;
		c.gridy = 13;
		add(time14, c);
	}

}
