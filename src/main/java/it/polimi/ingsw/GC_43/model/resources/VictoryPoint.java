package it.polimi.ingsw.GC_43.model.resources;

public class VictoryPoint extends Resource{
	/**
	 * 
	 */
	private static final long serialVersionUID = -346351842808003260L;
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
