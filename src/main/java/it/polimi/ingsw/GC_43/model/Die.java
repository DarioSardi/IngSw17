package it.polimi.ingsw.GC_43.model;

public class Die {
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
	
	//FRANCESCO fare tutta la funzione su un unica riga di codice
	public void rollDie(){
		double Dvalue = Math.random()*this.range;
		int value = (int)Dvalue+1;
		this.setDieValue(value);	
	}


	
}
