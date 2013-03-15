package gui;

import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import data.Main;
import data.User;

public class PersonListPanel extends JPanel{
	
	private Main main;
	private JList personList;
	private JScrollPane personPane;
	private DefaultListSelectionModel selectionModel;
	private DefaultListModel listModel;
	
	
	public PersonListPanel(Main main){
		this.main = main;
		listModel = new DefaultListModel();
		selectionModel = new DefaultListSelectionModel();
		personList = new JList(listModel);
		addPersons();
		personList.setSelectionModel(selectionModel);
		personPane = new JScrollPane(personList);
		personPane.setPreferredSize(new Dimension(75,100));
		
		
		add(personPane);
		
	}
	
	private void addPersons(){
		System.out.println(main.getPersons());
		for(int i = 0; i<main.getPersons().size(); i++){
			listModel.addElement(main.getPersons().get(i));
		}
		}
	
	
	 
}
