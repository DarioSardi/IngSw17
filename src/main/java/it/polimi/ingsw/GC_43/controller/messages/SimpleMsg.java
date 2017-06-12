package it.polimi.ingsw.GC_43.controller.messages;

import java.io.Serializable;

public class SimpleMsg extends Message implements Serializable{
	private String msg;

	public SimpleMsg(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}
	
	
}

