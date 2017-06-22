package it.polimi.ingsw.GC_43.model.effects;

import java.util.ArrayList;
import it.polimi.ingsw.GC_43.model.resources.*;


import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;

public class ChoiceEffect extends Effect{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3980248365505372817L;

	private ArrayList<Resource> costs;
	private ArrayList<Resource> gains;
	
	public ChoiceEffect(ArrayList<Resource> costs,ArrayList<Resource> gains){
		this.costs=new ArrayList<Resource>();
		this.gains=new ArrayList<Resource>();
		this.costs=costs;
		this.gains=gains;
	}
	public String toString(){

		String toString="Costs = ";
		if(this.costs!=null){
			for(Resource cost:this.costs){
				toString=toString+cost.getResourceType()+": "+cost.getValue()+" ";
			}
		}
		
		toString=toString+"\n   gains = ";
		
		if(this.gains!=null){
		for(Resource gain:this.gains){
			toString=toString+gain.getResourceType()+": "+gain.getValue()+" ";
		}
	}
	return toString;
		
}
	public boolean check(Player player){
		boolean checkResult=true;
		for(Resource resource: this.costs){
			if(player.getPlayerResource(resource.getResourceType())<resource.getValue())
					checkResult=false;
		}
		
		return checkResult;
	}
	public void executeEffect(FamilyMember familyMember){
		System.out.println("Executing ChoiceEffect Effect");

		executeEffect(familyMember.getPlayer());
	}
	public void executeEffect(Player player){
		for (Resource resource: this.costs){
			if(resource.getResourceType().equals("coin")){
				player.subResource(resource.getResourceType(), resource.getValue());				}
		}
	
		for (Resource resource: this.gains){
			if(!(resource.getResourceType().equals("privilegeCouncil")))
				player.addResource(resource.getResourceType(), resource.getValue()+player.getPlayerBounusMalus().getMalusOnAcquiringResources().get(resource.getResourceType()));
			}
		}
	
	public ArrayList<Resource> getCosts() {
		return costs;
	}
	public void setCosts(ArrayList<Resource> costs) {
		this.costs = costs;
	}
	public ArrayList<Resource> getGains() {
		return gains;
	}
	public void setGains(ArrayList<Resource> gains) {
		this.gains = gains;
	}	
	
	
		
}

