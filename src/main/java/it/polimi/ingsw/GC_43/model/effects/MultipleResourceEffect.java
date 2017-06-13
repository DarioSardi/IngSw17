package it.polimi.ingsw.GC_43.model.effects;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.resources.*;
//TODO era per council privilege che dava più di un bonus pietra+legno, vedi se sempllificare in altro modo
public class MultipleResourceEffect extends Effect {
	private ArrayList<ResourceEffect> resourcesToGet;

    public MultipleResourceEffect(ArrayList <ResourceEffect> resourcesToGet){ 
    	this.resourcesToGet= new ArrayList<ResourceEffect>();
    	this.resourcesToGet=resourcesToGet;

    }

    public String toString(){
    	String toString= "Resources gained:\n";
    	int index=1;
    	for(ResourceEffect resourceEffect: this.resourcesToGet){
    		toString= toString+" "+index+" "+resourceEffect.toString()+"\n"; 
    		index++;
    	}
    	return toString;

    }
    
    public void executeEffect(FamilyMember familyMember){
    	for(ResourceEffect resourceEffect: this.resourcesToGet){
            resourceEffect.executeEffect(familyMember);
            }

    }

}