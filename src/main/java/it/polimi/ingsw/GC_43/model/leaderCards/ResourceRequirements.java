package it.polimi.ingsw.GC_43.model.leaderCards;
import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class ResourceRequirements extends Effect {

		
		/**
	 * 
	 */
	private static final long serialVersionUID = -2149581240398285001L;
		private String resourceType;
		private int resourceValue;
		
		
		public ResourceRequirements(String resourceType, int resourceValue){
			this.resourceType=resourceType;
			this.resourceValue= resourceValue;
			
		}
		
		public boolean check(FamilyMember familyMember){
			return check(familyMember.getPlayer());
		}
		
		public String toString() {
			String toString = this.resourceValue+" "+this.resourceType+" are required to play this card";
			return toString;
		}
		
		public boolean check(Player player){
			System.out.println("Executing check on ResourceRequirements");

			boolean checkResult=true;
			if(player.getPlayerResource(resourceType)<this.resourceValue)
				checkResult=false;
			
			return checkResult;
		}
		

	}

