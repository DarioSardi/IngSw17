package it.polimi.ingsw.GC_43.controller;

import java.io.Serializable;

public class QuitMsg implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2480122387528433906L;
	private String password;
	private int ID;
	
	public QuitMsg(String password, int i) {
		super();
		this.password = password;
		ID = i;
	}

	public String getPassword() {
		return password;
	}

	public int getID() {
		return ID;
	}
	
	
	
}
