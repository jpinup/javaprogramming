package it.jpinup.map;

import java.io.File;

public class ViewFile extends File{

	

	public ViewFile(String pathname) {
		super(pathname);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getName();
	}

}
