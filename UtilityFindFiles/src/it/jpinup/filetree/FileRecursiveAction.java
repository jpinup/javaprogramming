package it.jpinup.filetree;

import it.jpinup.map.ListHashMap;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

import org.apache.commons.io.FilenameUtils;

public class FileRecursiveAction extends RecursiveAction {
	ListHashMap listaFiles;
	File root;
	List<RecursiveAction> listaTasks = new ArrayList<RecursiveAction>();
	
	public FileRecursiveAction(ListHashMap listaFiles, File root) {

		this.listaFiles = listaFiles;
		this.root = root;
	}

	protected void compute() {

		if (root.isDirectory()) {

			File[] files = root.listFiles();
			for (File fileCurrent : files) {
				checkDir(fileCurrent);

			}
			for (RecursiveAction task : listaTasks) {
				task.join();
			}
		} else {

				addFile(root);
		}
	}

	private void checkDir(File fileCurrent) {
		if (fileCurrent.isDirectory()) {
			FileRecursiveAction myRecursiveTask = new FileRecursiveAction(
					listaFiles, fileCurrent);
			listaTasks.add(myRecursiveTask);
			myRecursiveTask.fork();
		} else {

			addFile(fileCurrent);
		}
	}

	private void addFile(File filet) {
		
		String ext = FilenameUtils.getExtension(filet.getName());
		listaFiles.put(ext, filet);
	}

}