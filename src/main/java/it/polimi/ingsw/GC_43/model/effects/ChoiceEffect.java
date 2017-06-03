package it.polimi.ingsw.GC_43.model.effects;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.Resource;

public class ChoiceEffect {
	private ArrayList<Resource> costs;
	private ArrayList<Resource> gains;
	
	public ChoiceEffect(ArrayList<Resource> costs,ArrayList<Resource> gains){
		this.costs=new ArrayList<Resource>();
		this.gains=new ArrayList<Resource>();
		this.costs=costs;
		this.gains=gains;
	}
	public String toString(){

		String toString="Costs: ";
		for(Resource cost:this.costs){
			toString=toString+cost.getResourceType()+": "+cost.getValue()+" ";
		}
		toString=toString+"\ngains: ";

		for(Resource gain:this.gains){
			toString=toString+gain.getResourceType()+": "+gain.getValue()+" ";
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
		for (Resource resource: this.costs){
			if(resource.getResourceType().equals("coin")){
				familyMember.getPlayer().subResource(resource.getResourceType(), resource.getValue());
				}
		}
		for (Resource resource: this.gains){
			familyMember.getPlayer().addResource(resource.getResourceType(), resource.getValue()+familyMember.getPlayer().getPlayerBounusMalus().getMalusOnAcquiringResources().get(resource.getResourceType()));
				}
		
			}					
		}
