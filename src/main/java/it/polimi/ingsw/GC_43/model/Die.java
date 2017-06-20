package it.polimi.ingsw.GC_43.model;

import java.io.Serializable;
import java.util.Observable;

public class Die extends Observable implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7038764233797221676L;


	
	private int dieValue;
	private int dieColor;
	private int range=6;

	public Die(int color){
		this.dieColor= color;
		this.rollDie();
	}
	
	public int getDieValue() {
		return dieValue;
	}
	public void setDieValue(int dieValue) {
		this.dieValue = dieValue;
	}
	public int getDieColor() {
		return dieColor;
	}
	public void setDieColor(int deiColor) {
		this.dieColor = deiColor;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	
	public void rollDie(){
		double Dvalue = Math.random()*this.range;
		int value = (int)Dvalue+1;
		this.setDieValue(value);
		notifyObservers();
	}


	
}
