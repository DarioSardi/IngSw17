package it.polimi.ingsw.GC_43.model.resources;

public class Stone extends Resource{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4592385277333009494L;
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
	public String getResourceType(){
		return this.resourceType;
	}
}