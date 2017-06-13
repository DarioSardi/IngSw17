package it.polimi.ingsw.GC_43.controller;

import java.io.Serializable;

public class SimpleMessage implements Serializable{
	private String msg;

	public SimpleMessage(String msg) {
		super();
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

}
