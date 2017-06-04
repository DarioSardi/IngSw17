package it.polimi.ingsw.GC_43.model;

import it.polimi.ingsw.GC_43.model.effects.MultipleCouncilPrivileges;

public class CouncilPrivilege extends Resource{
	private MultipleCouncilPrivileges effect;
	private String resourceType = "councilPrivilege";
	private int value;
	
	public CouncilPrivilege (int value) {
		this.value = value;
	}
	
	@Override
	public String getResourceType() {
		return this.resourceType;
	}
	
	@Override
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	
	@Override
	public int getValue() {
		return this.value;
	}
	
	@Override
	public void setValue(int value) {
		this.value = value;
	}
	
	@Override
	public String toString(){
		return "resource type: " + this.resourceType + this.value;
	}
}