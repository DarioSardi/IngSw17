package model.actionSpace;

import model.Bonus;
import model.FamilyMember;
import model.GlobalVariables;
import model.Player;

public class HarvestArea extends ActionArea{
		
	public HarvestArea(Bonus b) {
		this.getSpaces().add(new Space(false,GlobalVariables.minDiceValueHarvestArea,this,b));
		this.getSpaces().add(new Space(true,GlobalVariables.minDiceValueHarvestArea,this,b));
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
		return p.getPlayerBounusMalus().getBonusHarvestArea();
	}



 
}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   