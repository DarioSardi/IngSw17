package it.polimi.ingsw.GC_43.model;

public class Stone extends Resource{
	private String resourceType = "stone";	
	private int value;
	
	public Stone (int value) {
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