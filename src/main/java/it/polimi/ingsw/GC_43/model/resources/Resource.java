package it.polimi.ingsw.GC_43.model.resources;

import java.io.Serializable;

public abstract class Resource implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4986086135097033826L;

	public abstract int getValue();
	
	public abstract String getResourceType();
	
	public String toString(){
		return this.getResourceType() +": "+ this.getValue();
	}
}