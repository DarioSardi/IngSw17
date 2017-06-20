package it.polimi.ingsw.GC_43.model.actionSpace;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.GlobalVariables;
import it.polimi.ingsw.GC_43.model.Player;

public class HarvestArea extends ActionArea{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 7626649697231179283L;

	public HarvestArea() {
		this.getSpaces().add(new Space(false,GlobalVariables.minDiceFirstHarvestArea,this));
		if(GlobalVariables.numberOfPlayers>=3){
		this.getSpaces().add(new Space(true,GlobalVariables.minDiceSecondHarvestArea,this));
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
		return p.getPlayerBounusMalus().getBonusHarvestArea();
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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("harvest area status: \n");
		sb.append("first area is ");
		if(getPrimarySpace().getFamiliarIn().isEmpty()){
			sb.append("free");
		}
		else{sb.append("not free!");}
		sb.append("\n");
		
		
		if(getSecondarySpace()==null){sb.append("second area not available");}
		else if(getSecondarySpace().getFamiliarIn().isEmpty()){
			sb.append("second area have no players in");
		}
		else{
			sb.append("second area have this players in:");
			for(FamilyMember f:this.getSecondarySpace().getFamiliarIn()){
				sb.append(f.getPlayer().getPlayerName());
				sb.append("\n");
				}
			}
		return sb.toString();

	}


 
}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   