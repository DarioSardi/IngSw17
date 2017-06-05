package it.polimi.ingsw.GC_43.model.actionSpace;

import java.util.ArrayList;

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
	
	@Override
	public String toString() {
		return this.getSpaces().get(0).toString(); //printa solo il primo e unico spazio
	}
	
	/**
	 * 
	 * @return true if the palace is empty
	 */
	public boolean isEmpty(){
		return this.getSpaces().get(0).getFamiliarIn().isEmpty();
	}
	
	public ArrayList<Player> entryOrder(){
		ArrayList<Player> ordered=new ArrayList<>();
		for(FamilyMember f: this.getSpaces().get(0).getFamiliarIn()){
			if(ordered.contains(f.getPlayer())){
				continue;
			}
			else{
				ordered.add(f.getPlayer());
			}
		}
		return ordered;
	}
	
	public CouncilPalaceSpace getCouncil(){
		return (CouncilPalaceSpace) this.getSpaces().get(0);
	}
}
