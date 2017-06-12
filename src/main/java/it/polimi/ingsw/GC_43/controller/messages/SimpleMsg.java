package it.polimi.ingsw.GC_43.controller.messages;

public class SimpleMsg extends Message{
	private String choice;

	public SimpleMsg(String choice) {
		this.choice = choice;
	}

	public String getChoice() {
		return choice;
	}
	
	
}
