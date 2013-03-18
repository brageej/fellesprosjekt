package customModels;

import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import data.Group;

public class GroupComboBoxModel implements ComboBoxModel {
	
	protected ArrayList<Group> groups;
	protected Object selected;
	protected ArrayList<ListDataListener> listeners;
	
	public GroupComboBoxModel(ArrayList<Group> groups) {
		this.groups = groups;
		listeners = new ArrayList<ListDataListener>();
	}
	
	@Override
	public void addListDataListener(ListDataListener l) {
		listeners.add(l);

	}

	@Override
	public Object getElementAt(int index) {
		return groups.get(index);
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return groups.size();
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		listeners.remove(l);
	}

	@Override
	public Object getSelectedItem() {
		// TODO Auto-generated method stub
		return selected;
	}

	@Override
	public void setSelectedItem(Object anItem) {
		// TODO Auto-generated method stub
		selected = anItem;
	}

}
