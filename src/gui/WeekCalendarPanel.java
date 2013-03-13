package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class WeekCalendarPanel extends JPanel {
	
	public WeekCalendarPanel(){
		setLayout(new GridBagLayout());
		
		JPanel dayPanel = new JPanel();
		GridBagConstraints dayC = new GridBagConstraints();
		dayC.insets = new Insets(0,0,0,20);
		JLabel Mon = new JLabel("Mon");
		JLabel Tue = new JLabel("Tue");
		JLabel Wed = new JLabel("Wed");
		JLabel Thu = new JLabel("Thu");
		JLabel Fri = new JLabel("Fri");
		JLabel Sat = new JLabel("Sat");
		JLabel Sun = new JLabel("Sun");
		
		dayC.gridx=0;
		dayC.gridy=0;
		dayPanel.add(Mon, dayC);
		
		dayC.gridx=1;
		dayC.gridy=0;
		dayPanel.add(Tue, dayC);
		
		dayC.gridx=2;
		dayC.gridy=0;
		dayPanel.add(Wed, dayC);
		
		dayC.gridx=3;
		dayC.gridy=0;
		dayPanel.add(Thu, dayC);
		
		dayC.gridx=4;
		dayC.gridy=0;
		dayPanel.add(Fri, dayC);
		
		dayC.gridx=5;
		dayC.gridy=0;
		dayPanel.add(Sat, dayC);
		
		dayC.gridx=6;
		dayC.gridy=0;
		dayPanel.add(Sun, dayC);
		
		JPanel appPanel = new JPanel();
		
	}

}
