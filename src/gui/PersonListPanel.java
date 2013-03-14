package gui;

import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import data.Main;

public class PersonListPanel extends JPanel{
	
	private Main main = new Main();
	private JList personList;
	private JScrollPane personPane;
	
	
	public PersonListPanel(){
		personList = new JList();
		addPersons();
		personPane = new JScrollPane(personList);
		personPane.setPreferredSize(new Dimension(75,100));
		
		
		add(personPane);
		
	}
	
	private void addPersons(){
		personList = new JList(main.getPersons().toArray());
		}
	
	
	 
}
