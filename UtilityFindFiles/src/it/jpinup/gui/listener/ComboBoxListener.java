/*
 * author: Ezio Tomassetti
 */
package it.jpinup.gui.listener;

import it.jpinup.map.ListHashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

public class ComboBoxListener implements ListDataListener {
	ListHashMap listHashMap;

	public ComboBoxListener(ListHashMap listHashMap) {
		this.listHashMap = listHashMap;
	}

	@Override
	public void intervalAdded(ListDataEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void intervalRemoved(ListDataEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contentsChanged(ListDataEvent e) {
		String key = ((DefaultComboBoxModel)e.getSource()).getSelectedItem().toString();
		listHashMap.changeList(key);

	}

}
