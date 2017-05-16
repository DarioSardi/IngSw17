package model;

public class Coin extends Resource{
	private String tipoRisorsa = "coin";
	private int value;
	
	public Coin (int value){
		this.value = value;
	}
	
	@Override
	public int getValue(){
		return this.value;
	}
	
	@Override
	public void setValue(int value) {
		this.value = value;
	}
	
	@Override
	public String getTipoRisorsa(){
		return this.tipoRisorsa;
	}
}