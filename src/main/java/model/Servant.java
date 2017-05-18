package model;

public class Servant extends Resource{
	private String tipoRisorsa = "servants";
	private int value;
	
	public Servant (int value){
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