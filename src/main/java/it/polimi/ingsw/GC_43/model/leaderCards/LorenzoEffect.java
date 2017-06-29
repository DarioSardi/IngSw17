package it.polimi.ingsw.GC_43.model.leaderCards;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class LorenzoEffect extends Effect{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -766947496483863115L;
	private boolean alreadyChosen;
	private Effect chosenEffect;

	public LorenzoEffect(){
		this.alreadyChosen=false;
		
	}
	
	
	public String toString() {
		String toString = "Hey , here is Lorenzo il Magnifico card effect: you can choose whatever leader effects you want among those leader cards already played in the game";
		return toString;
	}
	
	
	
	public void executeEffect(FamilyMember fam){
		System.out.println("Executing FAKE effect LorenzoEffect");

	}
	



	public boolean isAlreadyChosen() {
		return alreadyChosen;
	}


	public void setAlreadyChosen(boolean alreadyChosen) {
		this.alreadyChosen = alreadyChosen;
	}


	public Effect getChosenEffect() {
		return chosenEffect;
	}


	public void setChosenEffect(Effect chosenEffect) {
		this.chosenEffect = chosenEffect;
	}
	
	
	
	
	
	
}
