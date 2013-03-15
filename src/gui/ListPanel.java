package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import data.Main;
import data.User;

public class ListPanel extends JPanel{
	
	private Main main;
	private JList list;
	private JScrollPane pane;
	private DefaultListSelectionModel selectionModel;
	private DefaultListModel listModel;
	private JLabel label;
	
	
	
	public ListPanel(Main main,String type){
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		this.main = main;
		listModel = new DefaultListModel();
		selectionModel = new DefaultListSelectionModel();
		list = new JList(listModel);
		list.setSelectionModel(selectionModel);
		pane = new JScrollPane(list);
		pane.setPreferredSize(new Dimension(150,150));
		if(type == "person"){
			addPersons();
			label = new JLabel("Users");
		}
		else{
			addGroups();
			label = new JLabel("Groups");
		}
		
		c.gridx = 0;
		c.gridy = 0;
		add(label,c);
		
		c.gridx = 0;
		c.gridy = 1;
		add(pane,c);
		
	}
	
	private void addPersons(){
		for(int i = 0; i<main.getPersons().size(); i++){
			listModel.addElement(main.getPersons().get(i));
		}
		}
	
	private void addGroups(){
		for(int i = 0; i<main.getGroups().size(); i++){
			listModel.addElement(main.getGroups().get(i));
		}
	}
	
	
	 
}
