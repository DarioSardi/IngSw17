package it.polimi.ingsw.GC_43.model;

public class Coin extends Resource{
	private String resourceType = "coins";
	private int value;
	
	public Coin (int value){
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
		return "resource type: " + this.resourceType;
	}
}