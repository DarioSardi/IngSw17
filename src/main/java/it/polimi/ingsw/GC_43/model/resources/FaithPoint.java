package it.polimi.ingsw.GC_43.model.resources;

public class FaithPoint extends Resource{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4888658486109889327L;
	private String resourceType = "faithPoint";
	private int value;
	

	public FaithPoint(int value) {
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
