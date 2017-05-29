package it.polimi.ingsw.GC_43.model.effects;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.Resource;

public class MultipleExchange1to1ChoicesEffect extends Effect {
		private ArrayList<Resource> costs;
		private ArrayList<Resource> gains;
		private boolean oneGain;
		
			public MultipleExchange1to1ChoicesEffect(ArrayList<Resource> costs,ArrayList<Resource> gains, boolean oneGain){
				this.costs=new ArrayList<Resource>();
				this.gains=new ArrayList<Resource>();
				this.costs=costs;
				this.gains=gains;
				this.oneGain=oneGain;
		
			}
		
		
			
		public void executeEffect(FamilyMember familyMember){
			int  choiceNumber=0;
			boolean checkFirstChoice=checkChoice(choiceNumber, familyMember.getPlayer());
			choiceNumber++;
			boolean checkSecondChoice=checkChoice(choiceNumber,familyMember.getPlayer());
			//TODO to be finished asking player
		
		}



		private boolean checkChoice(int choiceNumber, Player player) {
			
			return player.getPlayerResource(this.costs.get(choiceNumber).getResourceType())>=this.getCosts().get(choiceNumber).getValue();
		}

		
		

		public boolean isOneGain() {
			return oneGain;
		}



		public void setOneGain(boolean oneGain) {
			this.oneGain = oneGain;
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


