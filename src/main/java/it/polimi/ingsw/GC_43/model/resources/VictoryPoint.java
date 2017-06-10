package it.polimi.ingsw.GC_43.model.resources;

public class VictoryPoint extends Resource{
	private String resourceType = "victoryPoint";
	private int value;

	public VictoryPoint(int value){
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