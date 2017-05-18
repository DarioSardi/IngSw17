package model;

public class MilitaryPoint extends Resource {
	private String tipoRisorsa = "militaryPoint";
	private int value;
	
	public MilitaryPoint(int quantity){
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
