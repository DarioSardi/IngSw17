package model;

public class Die {
	private int dieValue;
	private int dieColor;
	private int range=6;

	
	
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
	
	//TODO fare tutta la funzione su un unica riga di codice
	public void rollDie(){
		double Dvalue = Math.random()*this.range;
		int value = (int)Dvalue+1;
		this.setDieValue(value);	
	}

	public static void main(String args[]){
		Die d = new Die();
		d.rollDie();
		System.out.println(d.dieValue);
	}
	
}
