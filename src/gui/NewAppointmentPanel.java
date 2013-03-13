package gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class NewAppointmentPanel extends JPanel{
	
	private JPanel titlePanel;
	private JPanel descriptionPanel;
	private JPanel jointTitleDescPanel;
	
	private JPanel startTimePanel;
	private JPanel endTimePanel;
	private JPanel jointTimePanel;
	
	private JPanel personsPanel;
	private JPanel groupPanel;
	private JPanel roomPanel;
	private JPanel buttonPanel;
	private JPanel jointPersGroupRoomButPanel;
	
	private JLabel titleLab;
	private JLabel descLab;
	private JLabel startLab;
	private JLabel endLab;
	private JLabel personLab;
	private JLabel groupLab;
	private JLabel roomLab;
	
	private JTextField titleField;
	private JTextArea descriptionField;
	
	private JDateChooser startTime;
	private JDateChooser endTime;
	
	private JSpinner hourStartSpinner;
	private JSpinner hourEndSpinner;
	
	private JComboBox personsCombo;
	private JList personsList;
	private JButton plusPerson;
	private JButton minusPerson;
	
	private JComboBox groupCombo;
	private JList groupList;
	private JButton plusGroup;
	private JButton minusGroup;
	
	private JList roomList;
	
	private JButton saveButton;
	private JButton cancelButton;
	
	public NewAppointmentPanel() {
		createPanels();
		
		setLayout(new BorderLayout());
		add(jointTitleDescPanel);
	}
	
	private void createPanels() {
		createJointTitleDescPanel();
		createJointTimePanel();
		createJointPersGroupRoomButPanel();
	}
	
	private void createJointTitleDescPanel() {
		//create titlePanel
		titlePanel = new JPanel(new BorderLayout());
		titleLab = new JLabel("Title");
		titleField = new JTextField(20);
		titlePanel.add(titleLab, BorderLayout.NORTH);
		titlePanel.add(titleField, BorderLayout.SOUTH);
		
		//create descriptionPanel
		descriptionPanel = new JPanel(new BorderLayout());
		descLab = new JLabel("Description");
		descriptionField = new JTextArea(5, 30);
		descriptionField.setWrapStyleWord(true);
		JScrollPane scroll = new JScrollPane(descriptionField);
		descriptionPanel.add(descLab, BorderLayout.NORTH);
		descriptionPanel.add(scroll, BorderLayout.SOUTH);
		
		//put them together in jointTitleDescPanel
		jointTitleDescPanel = new JPanel(new BorderLayout());
		
		titlePanel.setBorder(new EmptyBorder(10, 10, 5, 0));
		jointTitleDescPanel.add(titlePanel, BorderLayout.NORTH);
		
		descriptionPanel.setBorder(new EmptyBorder(5, 10, 5, 0));
		jointTitleDescPanel.add(descriptionPanel, BorderLayout.SOUTH);
	}
	
	private void createJointTimePanel() {
		System.out.println("not done");
	}
	
	private void createJointPersGroupRoomButPanel() {
		System.out.println("not done");
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Fabuloussss!");
		frame.getContentPane().add(new NewAppointmentPanel());
		frame.pack();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
}
