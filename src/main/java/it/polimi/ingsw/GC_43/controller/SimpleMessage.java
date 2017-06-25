package it.polimi.ingsw.GC_43.controller;

import java.io.Serializable;

public class SimpleMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8116261020676230763L;
	private String msg;

	public SimpleMessage(String msg) {
		super();
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

}
