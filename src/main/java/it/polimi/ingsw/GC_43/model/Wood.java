package it.polimi.ingsw.GC_43.model;

public class Wood extends Resource{
	private String resourceType = "wood";
	private int value;
	
	public Wood (int value) {
		this.value = value;
	}
	
	@Override
	public int getValue() {
		return this.value;
	}
	
	@Override
	public void setValue(int value) {
		this.value = value;
	}
	
	@Override
<<<<<<< HEAD
	public String getTipoRisorsa(){
		return this.resourceType;
=======
	public String getResourceType() {
		return this.resourceType;
	}
	
	@Override
	public String toString(){
		return "Risorse type:" + this.resourceType;
>>>>>>> ef6e9fa106caf12f58cf3c71fe823c87c7bac599
	}
}