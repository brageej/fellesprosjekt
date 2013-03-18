package customModels;

import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import data.User;

public class UserComboBoxModel implements ComboBoxModel {

	protected ArrayList<User> data;
	protected Object selected;
	protected ArrayList<ListDataListener> listeners;
	
	public UserComboBoxModel(ArrayList<User> users) {
		data = users;
		listeners = new ArrayList<ListDataListener>();

	}
	
	public Object getElementAt(int i) {
		return data.get(i);
	}

	public int getSize() {
		return data.size();
	}

	public void removeListDataListener(ListDataListener l) {
		listeners.remove(l);
	}

	public Object getSelectedItem() {
		return selected;
	}

	public void setSelectedItem(Object newValue) {
		this.selected = newValue;
	}

	public void addListDataListener(ListDataListener l) {
		listeners.add(l);
	}
}
