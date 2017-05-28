package it.polimi.ingsw.GC_43.model.effects;
import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.GC_43.model.GlobalVariables;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.Resource;

public class CostEffect extends Effect {
	private ArrayList<Resource> Costs;
	
	public CostEffect(ArrayList<Resource> Costs){
		this.Costs= Costs;
	}
	
	public boolean check(Player player, String cardType){
				for (Resource resource: Costs){
					if(resource.getResourceType().equals("coin")){
						if(player.getPlayerResource(resource.getResourceType())<resource.getValue()-player.getPlayerBounusMalus().getBonusCoinsOnBuyInTowers().get(cardType)){
							return false;
						}
					}
					else{
						if(player.getPlayerResource(resource.getResourceType())<resource.getValue());
						return false;
					}
				}
				
	//DARIO-FRANCESCO FARE CHECK TOWER TAX SUL SUO CODICE IN FLOOR SOTTRARRE TEMPO COIN TOWERTAX. sE CHECK
				//VA A BUON FINE ALLORA FAI BUY E GIà SOTRATE LE MONETE, ALTRIMENTI GLIELE RIAGGIUNGE
				
	//FRANCESCO problemone costruttore scelta discount. necessitiamo di un handler che ask al player
		return true;
	}
	public void executeEffect(Player player, String cardType){
		for (Resource resource: Costs){
			if(resource.getResourceType().equals("coin")){
				player.subResource(resource.getResourceType(), resource.getValue()-player.getPlayerBounusMalus().getBonusCoinsOnBuyInTowers().get(cardType));
				}
			else{
				player.subResource(resource.getResourceType(), resource.getValue());
				}			
			}
	}
}
	

