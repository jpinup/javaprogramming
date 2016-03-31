package it.jpinup.map;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

public class ListHashMap extends ConcurrentHashMap<String, List<File>> {
	DefaultListModel<ViewFile> listModel;
	DefaultComboBoxModel<String> defaultComboBoxModel;

	public void setListModel(DefaultListModel<ViewFile> listModel) {
		this.listModel = listModel;
	}

	public void setDefaultComboBoxModel(DefaultComboBoxModel<String> defaultComboBoxModel) {
		this.defaultComboBoxModel = defaultComboBoxModel;
	}

	@Override
	public List<File> put(String key, List<File> value) {
		// TODO Auto-generated method stub
		return super.put(key, value);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		listModel.clear();
		defaultComboBoxModel.removeAllElements();
		super.clear();
	}

	public synchronized List<File> put(String key, File value) {
		// TODO Auto-generated method stub
		List<File> list = super.get(key);
		if (list == null) {
			defaultComboBoxModel.addElement(key);
			
			list = new ArrayList<File>();
		}
		list.add(value);
		listModel.addElement(new ViewFile(value.getPath()));
		return super.put(key, list);
	}

	public void countKey() {
		System.out.println("Estensioni:" + this.keySet().size());
	}

	public Integer countValues() {
		Set<String> keySet = (Set<String>) this.keySet();
		System.out.println("Estensioni:" + keySet.size());
		Iterator<String> iterator = keySet.iterator();
		while (iterator.hasNext()) {
			String chiave = iterator.next();
			List<File> listaInMap = this.get(chiave);
			if (listaInMap != null) {
				System.out.println("Estensione: " + chiave + " Filealori: " + listaInMap.size());
			}
		}
		return keySet.size();
	}

	public void changeList(String key) {

		listModel.clear();
		List<File> listaFiles = this.get(key);

		if (listaFiles != null) {

			for (File fileCurrent : listaFiles) {
				listModel.addElement(new ViewFile(fileCurrent.getPath()));
			}
		}

	}
}
