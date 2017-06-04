package it.polimi.ingsw.GC_43.model;

public class VictoryPoint extends Resource{
	private String resourceType = "victoryPoint";
	private int value;

	public VictoryPoint(int value){
		this.value = value;
	}
	
	@Override
	public String getResourceType() {
		return this.resourceType;
	}
	
	@Override
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
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
	public String toString(){
		return "resource type: " + this.resourceType + this.value;
	}
}
