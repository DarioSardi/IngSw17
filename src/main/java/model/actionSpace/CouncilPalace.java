package model.actionSpace;

import model.Bonus;
import model.FamilyMember;
import model.GlobalVariables;
import model.Player;

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
