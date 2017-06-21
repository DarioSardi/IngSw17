package it.polimi.ingsw.GC_43.controller;

import java.io.Serializable;

public class ExcommunicationChoiceMsg implements Serializable {

	private static final long serialVersionUID = 3305174134033500179L;
	private Boolean choice;

	public ExcommunicationChoiceMsg(Boolean choice) {
		this.choice = choice;
		// TODO Auto-generated constructor stub
	}

	public Boolean getChoice() {
		return choice;
	}
	

}
