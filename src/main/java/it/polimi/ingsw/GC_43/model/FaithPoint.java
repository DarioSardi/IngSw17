package it.polimi.ingsw.GC_43.model;

public class FaithPoint extends Resource{
	private String resourceType = "faithPoint";
	private int value;
	

	public FaithPoint(int value) {
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
		return "Resource type: " + this.resourceType + this.value;
	}	
}
