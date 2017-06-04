package it.polimi.ingsw.GC_43.playerActions;

import java.util.HashMap;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;

public class CommonActionPerformerRoutine {
	

	public static HashMap<String,Integer> copyPlayerResources(Player player){
		HashMap<String,Integer> copyOfPlayerResources= new HashMap<String,Integer> ();
		copyOfPlayerResources.put("coin", player.getPlayerResource("coin"));
		copyOfPlayerResources.put("servant", player.getPlayerResource("servant"));
		copyOfPlayerResources.put("stone", player.getPlayerResource("stone"));
		copyOfPlayerResources.put("wood", player.getPlayerResource("wood"));
		copyOfPlayerResources.put("victoryPoint", player.getPlayerResource("victoryPoint"));
		copyOfPlayerResources.put("militaryPoint", player.getPlayerResource("militaryPoint"));
		copyOfPlayerResources.put("faithPoint", player.getPlayerResource("faithPoint"));
		return copyOfPlayerResources;

	}
	
	public static boolean checkResourceUsed(Player player, String resourceType, int quantityUsed){
		boolean checkResult=false;
		

		if(player.getPlayerResource(resourceType)>=quantityUsed)
			checkResult=true;
		
		return checkResult;
		
	}
	public static boolean checkServansUsed(Player player, int servantsUsed, FamilyMember familyMember) {
		boolean checkResult= checkResourceUsed(player,"servant",servantsUsed);
		if(checkResult){
			player.subResource("servant",servantsUsed);
			if(player.getPlayerBounusMalus().isTwoServantsCountAsOne()){
				servantsUsed=servantsUsed/2;
			}
		    familyMember.addFamilyMemberValue(servantsUsed);
		}
		return checkResult;

	}
	
	public static FamilyMember matchFamilyMember(Player player, int color){
		//TODO to think if correct ask your mates
		return 	player.findFamilyMemberByColor(color);
	}
	
	

}
