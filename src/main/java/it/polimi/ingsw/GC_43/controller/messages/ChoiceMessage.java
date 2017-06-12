package it.polimi.ingsw.GC_43.controller.messages;

import java.io.Serializable;
import java.util.Scanner;

public abstract class ChoiceMessage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6285249356713856391L;
	public int answer;
	public abstract void choose();
	public Scanner s;
	
	public int getAnswer() {
		return answer;
	}
	
	public void setScanner(Scanner s) {
		this.s=s;

	}
	
	public void setAnswer(int answer) {
		this.answer = answer;
	}
	
	
}
