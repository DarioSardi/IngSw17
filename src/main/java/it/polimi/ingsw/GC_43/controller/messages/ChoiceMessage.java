package it.polimi.ingsw.GC_43.controller.messages;

public abstract class ChoiceMessage {
	
	public int answer;
	public abstract void choose();
	
	public int getAnswer() {
		return answer;
	}
	public void setAnswer(int answer) {
		this.answer = answer;
	}
	
	
}
