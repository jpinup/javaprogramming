/*
 * author: Ezio Tomassetti
 */
package it.jpinup.gui.listener;

import it.jpinup.gui.ImagePanel;
import it.jpinup.map.ViewFile;

import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

import org.apache.commons.io.FilenameUtils;

public class ListBoxListener extends MouseAdapter {

	JFrame frame;
	ImagePanel imagePanel;
	String[] imageExtension = { "jpg", "png", "gif", "bpm" };

	public ListBoxListener(JFrame frame, ImagePanel imagePanel) {
		this.frame = frame;
		this.imagePanel = imagePanel;
	}

	public void mouseClicked(MouseEvent evt) {
		
		JList list = (JList) evt.getSource();
		int index = list.locationToIndex(evt.getPoint());
		ViewFile file = (ViewFile) list.getModel().getElementAt(index);
		if (evt.getClickCount() == 1) {
			viewImage(file);

		}
		if (evt.getClickCount() == 2) {
			openFromSystem(file);

		}
	}

	private void viewImage(ViewFile file) {
		String ext = FilenameUtils.getExtension(file.getName());
		for (String extension : imageExtension) {
			if (ext.equals(extension)) {
				imagePanel.setImage(file.getPath());
				imagePanel.repaint();
			}
		}
	}

	private void openFromSystem(ViewFile file) {
		if (Desktop.isDesktopSupported()) {
		    try {
		        Desktop.getDesktop().open(file);
		    } catch (IOException ex) {
		        // no application registered for PDFs
		    }
		}
	}

}
