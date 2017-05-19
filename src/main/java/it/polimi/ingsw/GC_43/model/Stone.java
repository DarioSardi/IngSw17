package it.polimi.ingsw.GC_43.model;

public class Stone extends Resource{
	private String tipoRisorsa = "stone";	
	private int value;
	
	public Stone (int value){
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