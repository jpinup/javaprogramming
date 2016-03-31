package it.jpinup.gui.chooser;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class OpenFileChooser implements ActionListener {
	private JTextField textArea;
	private Component fileChooser;
	public OpenFileChooser(JTextField textArea2,Component fileChooser) {
		this.textArea = textArea2;
		this.fileChooser= fileChooser;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			textArea.setText("");
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    
			int n = fileChooser.showOpenDialog(fileChooser);
			if (n == JFileChooser.APPROVE_OPTION) {
				File f = fileChooser.getSelectedFile();
				String path = f.getAbsolutePath();
				textArea.setText(path);
			}
		} catch (Exception ex) {
		}
	}
}