package it.polimi.ingsw.GC_43.model.effects;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.Resource;
import it.polimi.ingsw.GC_43.model.Wood;

public class MultipleResourceEffect extends Effect {
	private ArrayList<Resource> resourcesToGet;

    public MultipleResourceEffect(ArrayList <Resource> resourcesToGet){ 
    	this.resourcesToGet= new ArrayList<Resource>();
    	this.resourcesToGet=resourcesToGet;

    }

    public String toString(){
    	String toString= "Resources gained:\n";
    	int index=1;
    	for(Resource resource: this.resourcesToGet){
    		toString= toString+" "+index+" "+resource.toString()+"\n"; 
    		index++;
    	}
    	return toString;

    }
    public void executeEffect(FamilyMember familyMember){
    	for(Resource resource: this.resourcesToGet){
            int newValue=resource.getValue()+familyMember.getPlayer().getPlayerBounusMalus().getMalusOnAcquiringResources().get(resource.getResourceType());
            familyMember.getPlayer().addResource(resource.getResourceType(), newValue);    	
            }

    }

}