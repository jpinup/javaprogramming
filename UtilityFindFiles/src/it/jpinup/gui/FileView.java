/*
 * author: Ezio Tomassetti
 */
package it.jpinup.gui;

import it.jpinup.gui.chooser.OpenFileChooser;
import it.jpinup.gui.listener.ComboBoxListener;
import it.jpinup.gui.listener.CustomExecutionListener;
import it.jpinup.gui.listener.ListBoxListener;
import it.jpinup.map.ListHashMap;
import it.jpinup.map.ViewFile;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class FileView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileView() {
		initUI();
	}

	private void initUI() {
		setLayout(new FlowLayout());

		setTitle("File Ext counter");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Container contentPane = new JPanel();
		ImagePanel imagePanel = new ImagePanel();
		imagePanel.setPreferredSize(new Dimension(400, 400));
		
		((JPanel) contentPane).setBorder(BorderFactory
				.createTitledBorder("File Ext counter"));
		BoxLayout layout = new BoxLayout(contentPane, BoxLayout.Y_AXIS);
		contentPane.setLayout(layout);
		JTextField filePath = new JTextField(90);
		filePath.setName("filePath");
		filePath.setEditable(true);
		ActionListener chooser = new OpenFileChooser(filePath, FileView.this);

		addFileChooser(filePath, contentPane, "Percorso root", "path", chooser);

		JTextField counter = new JTextField(90);
		counter.setText("Totale estensioni:");
		counter.setName("counter");
		counter.setEditable(false);

		JButton genera = new JButton("Analizza");
		
		DefaultListModel<ViewFile> listModel = new DefaultListModel<ViewFile>();
		
		DefaultComboBoxModel<String> defaultComboBoxModel = new DefaultComboBoxModel<String>();
		
		
		JComboBox<String> comboBox = new JComboBox<String>(defaultComboBoxModel);
		JPanel comboPane = new JPanel();
		comboPane.add(comboBox);
		
		JList<ViewFile> list = new JList<ViewFile>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setVisibleRowCount(-1);
		list.addMouseListener(new ListBoxListener(this,imagePanel));
		
		JScrollPane listPanel = new JScrollPane(list);
		listPanel.setPreferredSize(new Dimension(40, 220));
		ListHashMap listaFiles = new ListHashMap();
		listaFiles.setDefaultComboBoxModel(defaultComboBoxModel);
		listaFiles.setListModel(listModel);
		defaultComboBoxModel.addListDataListener(new ComboBoxListener(listaFiles));
		CustomExecutionListener generateListener = new CustomExecutionListener(
				filePath, counter,listaFiles);

		genera.addActionListener(generateListener);
		JPanel panelButton = new JPanel();
		panelButton.add(genera);

		contentPane.add(counter);
		contentPane.add(panelButton);
		contentPane.add(comboPane);
		contentPane.add(listPanel);
		
		contentPane.add(imagePanel);
		add(contentPane);

		pack();

	}

	private void addFileChooser(JTextField text, Container contentPane,
			String label, String name, ActionListener chooser) {
		JButton openFileChooser = new JButton("Apri File");
		openFileChooser.addActionListener(chooser);
		JPanel panelButton = new JPanel();
		panelButton.setAlignmentX(LEFT_ALIGNMENT);
		panelButton.setAlignmentY(LEFT_ALIGNMENT);

		panelButton.add(openFileChooser);
		panelButton.add(new JLabel(label));
		contentPane.add(text);
		contentPane.add(panelButton);
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {
				FileView ex = new FileView();
				ex.setVisible(true);
			}
		});

	}

}
