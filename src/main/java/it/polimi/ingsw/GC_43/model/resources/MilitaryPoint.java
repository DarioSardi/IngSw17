package it.polimi.ingsw.GC_43.model.resources;

public class MilitaryPoint extends Resource {
	private String resourceType = "militaryPoint";
	private int value;
	
	public MilitaryPoint(int value){
		this.value = value;
	}	
	
	@Override
	public int getValue() {
		return this.value;
	}
	
	@Override
	public String getResourceType(){
		return this.resourceType;
	}
}
