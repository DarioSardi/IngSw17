package it.polimi.ingsw.GC_43.model.resources;

public class MilitaryPoint extends Resource {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4745478162778595159L;
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
