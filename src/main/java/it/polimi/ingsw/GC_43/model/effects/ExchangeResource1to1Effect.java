package it.polimi.ingsw.GC_43.model.effects;
import java.util.ArrayList;

import  it.polimi.ingsw.GC_43.model.*;

public class ExchangeResource1to1Effect extends Effect {
	private ArrayList<Resource> costs;
	private ArrayList<Resource> gains;		
	
		public ExchangeResource1to1Effect(ArrayList<Resource> costs,ArrayList<Resource> gains){
			this.costs=new ArrayList<Resource>();
			this.gains=new ArrayList<Resource>();
			this.costs=costs;
			this.gains=gains;
	
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
		
		if(check(familyMember.getPlayer())){
			//TODO ask player what he wanna do
		}
	}
}

