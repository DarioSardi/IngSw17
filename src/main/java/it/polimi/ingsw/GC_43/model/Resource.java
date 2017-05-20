package it.polimi.ingsw.GC_43.model;

public class Resource {		//SAMUEL Abstract
	private String tipoRisorsa = "";
	private int value;
	
	
	public int getValue(){
		return this.value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public String getTipoRisorsa(){
		return this.tipoRisorsa;
	}
}