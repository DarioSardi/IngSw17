package it.polimi.ingsw.GC_43.model;

public class MilitaryPoint extends Resource {
	private String resourceType = "militaryPoint";
	private int value;
	
	public MilitaryPoint(int value) {
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
	public String getResourceType() {
		return this.resourceType;
	}
	
	@Override
	public String toString(){
		return "Risorse type:" + this.resourceType;
	}
}
