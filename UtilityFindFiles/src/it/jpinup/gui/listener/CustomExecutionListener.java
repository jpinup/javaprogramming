/*
 * author: Ezio Tomassetti
 */
package it.jpinup.gui.listener;

import it.jpinup.filetree.FileRecursiveAction;
import it.jpinup.map.ListHashMap;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Calendar;
import java.util.concurrent.ForkJoinPool;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class CustomExecutionListener implements ActionListener, Runnable {

	private JTextField filePath;
	private JTextField counter;
	private ListHashMap listaFiles;

	public CustomExecutionListener(JTextField filePath, JTextField counter,
			ListHashMap listaFiles) {
		this.filePath = filePath;
		this.counter = counter;
		this.listaFiles = listaFiles;
		
	}

	public void actionPerformed(ActionEvent arg0) {
		SwingUtilities.invokeLater(this);

	}

	public void run() {
		listaFiles.clear();
		// TODO Auto-generated method stub
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		File fileRoot = new File(filePath.getText());
		FileRecursiveAction myRecursiveTask = new FileRecursiveAction(
				listaFiles, fileRoot);
		Calendar dat = Calendar.getInstance();
		// Restituzione delle informazioni
		int hh = dat.get(Calendar.HOUR);
		int mm = dat.get(Calendar.MINUTE);
		int ss = dat.get(Calendar.SECOND);
		System.out.println("Ora inizio:" + hh + ":" + mm + ":" + ss);
		forkJoinPool.invoke(myRecursiveTask);
		dat = Calendar.getInstance();
		// Restituzione delle informazioni
		hh = dat.get(Calendar.HOUR);
		mm = dat.get(Calendar.MINUTE);
		ss = dat.get(Calendar.SECOND);
		System.out.println("Ora fine:" + hh + ":" + mm + ":" + ss);
		counter.setText(listaFiles.countValues().toString());
	}

	public ListHashMap getListaFiles() {
		return listaFiles;
	}
}
