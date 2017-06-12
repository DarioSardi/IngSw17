package it.polimi.ingsw.GC_43.model.resources;

public abstract class Resource {
	
	public abstract int getValue();
	
	public abstract String getResourceType();
	
	public String toString(){
		return this.getResourceType() +": "+ this.getValue();
	}
}