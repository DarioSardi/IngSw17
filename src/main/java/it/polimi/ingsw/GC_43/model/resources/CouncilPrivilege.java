package it.polimi.ingsw.GC_43.model.resources;

public class CouncilPrivilege extends Resource{
	/**
	 * 
	 */
	private static final long serialVersionUID = -21680491143309448L;
	private String resourceType = "councilPrivilege";
	private int value;
	
	public CouncilPrivilege (int value) {
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