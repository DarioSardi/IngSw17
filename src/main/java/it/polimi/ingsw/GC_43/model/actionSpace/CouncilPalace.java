package it.polimi.ingsw.GC_43.model.actionSpace;

import it.polimi.ingsw.GC_43.model.Bonus;
import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.GlobalVariables;
import it.polimi.ingsw.GC_43.model.Player;

public class CouncilPalace extends ActionArea{

	public CouncilPalace(Bonus b) {
		this.getSpaces().add(new CouncilPalaceSpace(GlobalVariables.mindDiceValueCouncilPalace, this,b));
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
