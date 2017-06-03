package it.polimi.ingsw.GC_43.model.actionSpace;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.GlobalVariables;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class ProductionArea extends ActionArea{
		
	public ProductionArea(Effect e) {
		this.getSpaces().add(new Space(false,GlobalVariables.minDiceValueHarvestArea,this,e));
		if(GlobalVariables.numberOfPlayers>=3){
		this.getSpaces().add(new Space(true,GlobalVariables.minDiceValueHarvestArea,this,e));
		}
	}
	
	/**
	 * check if the player is already in one of the area
	 * @param f family member that is trying  to enter
	 * @return true if there are no conflicts (no other familar of the same color)
	 */
	@Override
	public boolean check(FamilyMember f) {
		if (f.getColor()==0){return true;}
		else{
				for(ActionSpace hs: this.getSpaces()){
					if(!hs.checkColor(f)){return false;}	
				}
				return true;
			}
	}
	/**
	 * return the bonus of the area
	 * @param p is the player that is requesting the bonus
	 * @return int the value of the bonus
	 */
	@Override
	public int getBonusOfArea(Player p) {
		return p.getPlayerBounusMalus().getBonusProductionArea();
	}
	
	public Space getPrimarySpace(){
		return (Space) this.getSpaces().get(0);
	}
	
	public Space getSecondarySpace(){
		if(this.getSpaces().size()==2){
			return (Space) this.getSpaces().get(1);
		}
		else return null;
	}
	



 
}
                  