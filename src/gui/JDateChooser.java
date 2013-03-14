package gui;
/*
2:  *  JDateChooser.java  - A bean for choosing a date
3:  *  Copyright (C) 2004 Kai Toedter
4:  *  kai@toedter.com
5:  *  www.toedter.com
6:  *
7:  *  This program is free software; you can redistribute it and/or
8:  *  modify it under the terms of the GNU Lesser General Public License
9:  *  as published by the Free Software Foundation; either version 2
10:  *  of the License, or (at your option) any later version.
11:  *
12:  *  This program is distributed in the hope that it will be useful,
13:  *  but WITHOUT ANY WARRANTY; without even the implied warranty of
14:  *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
15:  *  GNU Lesser General Public License for more details.
16:  *
17:  *  You should have received a copy of the GNU Lesser General Public License
18:  *  along with this program; if not, write to the Free Software
19:  *  Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
20:  */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.net.URL;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.toedter.calendar.JCalendar;


/**
* 49: * A date chooser containig a date spinner and a button, that makes a
* JCalendar visible for 50: * choosing a date. 51: * 52: * @author Kai Toedter
* 53: * @version 1.2.2 54:
*/
public class JDateChooser extends JPanel implements ActionListener, PropertyChangeListener, ChangeListener {
	protected JButton calendarButton;
	protected JSpinner dateSpinner;
	protected JSpinner.DateEditor editor;
	protected JCalendar jcalendar;
	protected JPopupMenu popup;
	protected SpinnerDateModel model;
	protected String dateFormatString;
	protected boolean dateSelected;
	protected boolean isInitialized;
	protected Date lastSelectedDate;
	protected boolean startEmpty;

	/**
	 * 70: * Creates a new JDateChooser object. 71:
	 */
	public JDateChooser() {
		this(null, null, false, null);
	}

	/**
	 * 77: * Creates a new JDateChooser object. 78: * 79: * @param icon the new
	 * icon 80:
	 */
	public JDateChooser(ImageIcon icon) {
		this(null, null, false, icon);
	}

	/**
	 * 86: * Creates a new JDateChooser object. 87: * 88: * @param startEmpty
	 * true, if the date field should be empty 89:
	 */
	public JDateChooser(boolean startEmpty) {
		this(null, null, startEmpty, null);
	}

	/**
	 * 95: * Creates a new JDateChooser object with given date format string.
	 * The default date format 96: * string is "MMMMM d, yyyy". 97: * 98: * @param
	 * dateFormatString the date format string 99: * @param startEmpty true, if
	 * the date field should be empty 100:
	 */
	public JDateChooser(String dateFormatString, boolean startEmpty) {
		this(null, dateFormatString, startEmpty, null);
	}

	/**
	 * 106: * Creates a new JDateChooser object from a given JCalendar. 107: *
	 * 108: * @param jcalendar the JCalendar 109:
	 */
	public JDateChooser(JCalendar jcalendar) {
		this(jcalendar, null, false, null);
	}

	/**
	 * 115: * Creates a new JDateChooser. 116: * 117: * @param jcalendar the
	 * jcalendar or null 118: * @param dateFormatString the date format string
	 * or null (then "MMMMM d, yyyy" is used) 119: * @param startEmpty true, if
	 * the date field should be empty 120: * @param icon the icon or null (then
	 * an internal icon is used) 121:
	 */
	public JDateChooser(JCalendar jcalendar, String dateFormatString, boolean startEmpty, ImageIcon icon) {
		
		if (jcalendar == null) {
			jcalendar = new JCalendar();
		}

		this.jcalendar = jcalendar;

		if (dateFormatString == null) {
			dateFormatString = "MMMMM d, yyyy";
		}

		this.dateFormatString = dateFormatString;
		this.startEmpty = startEmpty;

		setLayout(new BorderLayout());

		jcalendar.getDayChooser().addPropertyChangeListener(this);
		jcalendar.getDayChooser().setAlwaysFireDayProperty(true); // always fire
																	// "day"
																	// property
																	// even if
																	// the user
																	// selects
																	// the
																	// already
																	// selected
																	// day again
		model = new SpinnerDateModel();

		/*
		 * 144: The 2 lines below were moved to the setModel method. 145:
		 * model.setCalendarField(java.util.Calendar.WEEK_OF_MONTH); 146:
		 * model.addChangeListener(this); 147:
		 */

		// Begin Code change by Mark Brown on 24 Aug 2004
		setModel(model);
		dateSpinner = new JSpinner(model) {
			public void setEnabled(boolean enabled) {
				super.setEnabled(enabled);
				calendarButton.setEnabled(enabled);
			}
		};
		// End Code change by Mark Brown

		String tempDateFortmatString = "";

		if (!startEmpty) {
			tempDateFortmatString = dateFormatString;
		}

		editor = new JSpinner.DateEditor(dateSpinner, tempDateFortmatString);
		
//		Dette har jeg endret på
//		System.out.println(editor.getPreferredSize());
		editor.setPreferredSize(new Dimension(150, 28));
//		Her slutter endringen
		
		dateSpinner.setEditor(editor);
		add(dateSpinner, BorderLayout.CENTER);

		// Display a calendar button with an icon
		if (icon == null) {
			URL iconURL = getClass().getResource("/JDateChooserColor16.gif");
			icon = new ImageIcon(iconURL);
		}

		calendarButton = new JButton(icon);
		calendarButton.setMargin(new Insets(0, 0, 0, 0));
		calendarButton.addActionListener(this);

		// Alt + 'C' selects the calendar.
		calendarButton.setMnemonic(KeyEvent.VK_C);

		add(calendarButton, BorderLayout.EAST);

		calendarButton.setMargin(new Insets(0, 0, 0, 0));
		popup = new JPopupMenu() {
			public void setVisible(boolean b) {
				Boolean isCanceled = (Boolean) getClientProperty("JPopupMenu.firePopupMenuCanceled");

				if (b || (!b && dateSelected) || ((isCanceled != null) && !b && isCanceled.booleanValue())) {
					super.setVisible(b);
				}
			}
		};

		popup.setLightWeightPopupEnabled(true);
		
		popup.add(jcalendar);
		lastSelectedDate = model.getDate();
		isInitialized = true;
	}

	/**
	 * 205: * Called when the jalendar button was pressed. 206: * 207: * @param
	 * e the action event 208:
	 */
	public void actionPerformed(ActionEvent e) {
		int x = calendarButton.getWidth()
				- (int) popup.getPreferredSize().getWidth();
		int y = calendarButton.getY() + calendarButton.getHeight();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(model.getDate());
		jcalendar.setCalendar(calendar);
		popup.show(calendarButton, x, y);
		dateSelected = false;
	}

	/**
	 * Listens for a "date" property change or a "day" property change event
	 * from the JCalendar. 222: * Updates the dateSpinner and closes the popup.
	 * 223: * 224: * @param evt the event 225:
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("day")) {
			dateSelected = true;
			popup.setVisible(false);
			setDate(jcalendar.getCalendar().getTime());
			setDateFormatString(dateFormatString);
		} else if (evt.getPropertyName().equals("date")) {
			setDate((Date) evt.getNewValue());
		}
	}

	/**
	 * 238: * Updates the UI of itself and the popup. 239:
	 */

	public void updateUI() {
		super.updateUI();

		if (jcalendar != null) {
			SwingUtilities.updateComponentTreeUI(popup);
		}
	}

	/**
	 * 249: * Sets the locale. 250: * 251: * @param l The new locale value 252:
	 */
	public void setLocale(Locale l) {
		dateSpinner.setLocale(l);
		editor = new JSpinner.DateEditor(dateSpinner, dateFormatString);
		dateSpinner.setEditor(editor);
		jcalendar.setLocale(l);
	}

	/**
	 * 261: * Gets the date format string. 262: * 263: * @return Returns the
	 * dateFormatString. 264:
	 */
	public String getDateFormatString() {
		return dateFormatString;
	}

	/**
	 * 270: * Sets the date format string. E.g "MMMMM d, yyyy" will result in
	 * "July 21, 2004" if this is 271: * the selected date and locale is
	 * English. 272: * 273: * @param dateFormatString The dateFormatString to
	 * set. 274:
	 */
	public void setDateFormatString(String dateFormatString) {
		this.dateFormatString = dateFormatString;
		editor.getFormat().applyPattern(dateFormatString);
		invalidate();
	}

	/**
	 * 282: * Returns "JDateChooser". 283: * 284: * @return the name value 285:
	 */
	public String getName() {
		return "JDateChooser";
	}

	/**
	 * 291: * Returns the date. 292: * 293: * @return the current date 294:
	 */
	public Date getDate() {
		return model.getDate();
	}

	/**
	 * 300: * Sets the date. Fires the property change "date". 301: * 302: * @param
	 * date the new date. 303:
	 */
	public void setDate(Date date) {
		model.setValue(date);
		if (getParent() != null) {
			getParent().validate();
		}
	}

	/**
	 * 312: * Fires property "date" changes, recting on the spinner's state
	 * changes. 313: * 314: * @param e the change event 315:
	 */
	public void stateChanged(ChangeEvent e) {
		if (isInitialized) {
			firePropertyChange("date", lastSelectedDate, model.getDate());
			lastSelectedDate = model.getDate();
		}
	}

	/*
	 * 324: * The methods: 325: * public JSpinner getSpinner() 326: * public
	 * SpinnerDateModel getModel() 327: * public void setModel(SpinnerDateModel
	 * mdl) 328: * 329: * were added by Mark Brown on 24 Aug 2004. They were
	 * added to allow the setting 330: * of the SpinnerDateModel from a source
	 * outside the JDateChooser control. This 331: * was necessary in order to
	 * allow the JDateChooser to be integrated with applications 332: * using
	 * persistence frameworks like Oracle's ADF/BC4J. 333:
	 */

	/**
	 * 336: * Return this controls JSpinner control. 337: * 338: * @return the
	 * JSpinner control 339:
	 */
	public JSpinner getSpinner() {
		return dateSpinner;
	}

	/**
	 * 345: * Return the SpinnerDateModel associated with this control. 346: *
	 * 347: * @return the SpinnerDateModel 348:
	 */
	public SpinnerDateModel getModel() {
		return model;
	}

	/**
	 * 354: * Set the SpinnerDateModel for this control. This method allows the
	 * JDateChooser 355: * control to be used with some persistence frameworks
	 * (ie. Oracle ADF) to bind the 356: * control to the database Date value.
	 * 357: * 358: * @param mdl the SpinnerDateModel 359:
	 */
	public void setModel(SpinnerDateModel mdl) {
		model = mdl;
		model.setCalendarField(java.util.Calendar.WEEK_OF_MONTH);
		model.addChangeListener(this);
		// Begin Code change by Martin Pietruschka on 16 Sep 2004
		if (dateSpinner != null)
			dateSpinner.setModel(model);
		// End Code change by Mark Brown
	}
	
	
//	main for å teste panelet separat.
	public static void main(String[] args) {
		JFrame frame = new JFrame("DateChooser");
		frame.getContentPane().add(new JDateChooser());
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
