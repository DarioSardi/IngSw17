package it.polimi.ingsw.GC_43.model.resources;

public class Coin extends Resource{
	private String resourceType = "coins";
	private int value;
	
	public Coin (int value) {
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