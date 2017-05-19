package it.polimi.ingsw.GC_43.model;

public class FaithPoint extends Resource{
	private String tipoRisorsa = "faithPoint";
	private int value;
	
	public FaithPoint(int quantity){
		this.value = quantity;
	}
	
	public String getTipoRisorsa() {
		return tipoRisorsa;
	}
	public void setTipoRisorsa(String tipoRisorsa) {
		this.tipoRisorsa = tipoRisorsa;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	
	
}
