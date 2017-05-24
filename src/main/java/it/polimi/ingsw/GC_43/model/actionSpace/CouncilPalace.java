package it.polimi.ingsw.GC_43.model.actionSpace;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.GlobalVariables;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class CouncilPalace extends ActionArea{

	public CouncilPalace(Effect e) {
		this.getSpaces().add(new CouncilPalaceSpace(GlobalVariables.mindDiceValueCouncilPalace, this,e));
	}

	@Override
	public boolean check(FamilyMember f) {
		return true;
	}

	@Override
	public int getBonusOfArea(Player p) {
		return 0;  //nessun bonus utile
	}
	
	
	
}
