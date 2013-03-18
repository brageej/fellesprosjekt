package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.User;

public class DayPanel extends JPanel{
	
	public DayPanel(User user){
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JPanel mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(75,600));
		setPreferredSize(new Dimension(75,650));
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		c.gridx = 0;
		c.gridy = 0;
		add(new JLabel(user.getUserName()),c);
		c.gridx = 0;
		c.gridy = 1;
		add(mainPanel,c);
		
	}
	public DayPanel(String string){
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JPanel mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(75,600));
		setPreferredSize(new Dimension(75,650));
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		c.gridx = 0;
		c.gridy = 0;
		add(new JLabel(string),c);
		c.gridx = 0;
		c.gridy = 1;
		add(mainPanel,c);
	}
	
	

}
