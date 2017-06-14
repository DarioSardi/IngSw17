package it.polimi.ingsw.GC_43.model.resources;

public class Wood extends Resource{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5990690733738912182L;
	private String resourceType = "wood";
	private int value;
	
	public Wood (int value) {
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