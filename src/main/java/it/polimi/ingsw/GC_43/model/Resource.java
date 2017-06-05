package it.polimi.ingsw.GC_43.model;

public abstract class Resource {
	
	abstract public int getValue();
	abstract public void setValue(int value);
	abstract public String getResourceType();
	abstract public void setResourceType(String resourceType);
	
	public String toString(){
		return "resource type: " + this.getResourceType() +" "+ this.getValue();
	}
}