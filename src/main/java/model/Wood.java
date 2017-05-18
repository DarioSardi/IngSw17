package model;

public class Wood extends Resource{
	private String tipoRisorsa = "woods";
	private int value;
	
	public Wood (int value){
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