package it.polimi.ingsw.GC_43.model.resources;

public class Servant extends Resource{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6319695199878140691L;
	private String resourceType = "servant";
	private int value;
	
	public Servant (int value) {
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