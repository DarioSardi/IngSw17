package it.polimi.ingsw.GC_43.model.actionCreations;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.GlobalVariables;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.actionSpace.Tower;
import it.polimi.ingsw.GC_43.model.actionSpace.TowerColors;
import it.polimi.ingsw.GC_43.model.actions.TowerAction;
import it.polimi.ingsw.GC_43.model.cards.BuildingCard;
import it.polimi.ingsw.GC_43.model.effects.Effect;
import it.polimi.ingsw.GC_43.model.effects.MultipleChoiceEffect;
import it.polimi.ingsw.GC_43.model.effects.MultipleCouncilPrivileges;

public class TowerActionCreationRoutine implements ActionCreation {
	
	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TowerAction towerAction;
	    private Board board;
	    private HashMap<String,Integer>copyOfPlayerResource;
	    //player ID will be the ID of the instance of playerImpl != player of the model


	    public TowerActionCreationRoutine(String playerID, Player player,Board board){
	        this.towerAction=new TowerAction(playerID, player);
	        this.board=board;
	        this.copyOfPlayerResource=CommonActionCreatorRoutine.copyPlayerResources(player);
	    }


	    public boolean prepareAction() {
	    	
	        this.towerAction.setFamilyMember(CommonActionCreatorRoutine.askForFamilyMemberChoice(this.towerAction.getPlayer()));
	        this.towerAction.setFamilyMemberColor(this.towerAction.getFamilyMember().getColor());
	        this.towerAction.setServantsUsed(CommonActionCreatorRoutine.askForServantsUsage(towerAction.getPlayer(),this.towerAction.getFamilyMember().getDiceValue()));

	        selectTowerAndFloor(board.getTowers());
	
	        
	        ////////   QUA ARRIVED
	        
	        
//	        checkInstantEffects();
	        

	        return true;
	    }
	    
	    
	    private boolean selectTowerAndFloor(ArrayList <Tower> towers) {
	    	
	    	//TOWER CHOICE
	    	String question= this.board.towersToString();
	    	int towerChoice=CommonActionCreatorRoutine.askForSingleChoice(question, 0, towers.size());
	    	
	    	//FLOOR CHOICE
	    	question= towers.get(towerChoice).toStringAll();
	    	int floorChoice=CommonActionCreatorRoutine.askForSingleChoice(question, 0, towers.get(towerChoice).getFloors().size());
	    	
	    	
	    	int servantsUsed=this.towerAction.getServantsUsed();
	    	int familyMemberDie=this.towerAction.getFamilyMember().getDiceValue();
	    	int floorMinDieValue=towers.get(towerChoice).getFloors().get(floorChoice).getMinDiceValue();
	    	int extraBonusOnTower=lookForExtraBonus(towers.get(towerChoice));
	    	
	    	if(familyMemberDie+servantsUsed+extraBonusOnTower<floorMinDieValue||!(towers.get(towerChoice).check(this.towerAction.getFamilyMember()))){
	    		System.out.println("You can not access this floor, please try again");
	    		return selectTowerAndFloor(towers);
	    	}
	    	
	    	else{
	    		this.towerAction.getTowerChoices().add(towerChoice);
	    		this.towerAction.getTowerChoices().add(floorChoice);
	    	}
	    	
	    	//VEDITI I CASI INSTANT BONUS  E VEDI PER LE CHOICE !!
	    	return true;
	        
	    }

	    
	    
	    
	    
	    private int lookForExtraBonus(Tower tower) {
	    	int extraBonusOnDie=0;
	    	if(tower.getTowerColor()==TowerColors.BUILDINGS_TOWER)
	    		extraBonusOnDie=this.towerAction.getPlayer().getPlayerBounusMalus().getBonusDiceBuildingTower();

	    	if(tower.getTowerColor()==TowerColors.CHARACTERS_TOWER)
	    		extraBonusOnDie=this.towerAction.getPlayer().getPlayerBounusMalus().getBonusDiceCharacterTower();

	    	if(tower.getTowerColor()==TowerColors.TERRITORIES_TOWER)
	    		extraBonusOnDie=this.towerAction.getPlayer().getPlayerBounusMalus().getBonusDiceTerritoryTower();

	    	if(tower.getTowerColor()==TowerColors.VENTURES_TOWER)
	    		extraBonusOnDie=this.towerAction.getPlayer().getPlayerBounusMalus().getBonusDiceVentureTower();
	    	
	    	return extraBonusOnDie;
		}


		/*@require bonus malus on familyMember die to be already applied, but that's normal*/
/*	    private void getInputsForProduction(FamilyMember familyMember){
	    	int dieValue=familyMember.getDiceValue()+this.productionAction.getServantsUsed()+familyMember.getPlayer().getPlayerBounusMalus().getBonusProductionArea();
	        for(BuildingCard buildingCard: this.productionAction.getPlayer().getPlayerCards().getArrayBuildingCards()){
	            if(dieValue>=buildingCard.getProductionDice()){
	                for( Effect effect: buildingCard.getPermaBonus()){
	                    if(effect.getClass().toString().contains("MultipleChoiceEffect")){
	                        askForMultipleChoice((MultipleChoiceEffect)effect);
	                    }
	                    if(effect.getClass().toString().contains("MultipleCouncilPrivileges")){
	                        askForMultipleCouncilPrivilege((MultipleCouncilPrivileges)effect);
	                    }
	                }
	            }
	        }

	    }


	    private void askForMultipleCouncilPrivilege(MultipleCouncilPrivileges effect) {
	    	int numberOfCopies=effect.getNumberOfCopies();
	    	while(numberOfCopies>0){
	    		int choice= askForMultipleChoice(effect.getPrivilegeChoices());
	    		if(choice!=-1){
	    			effect.getPrivilegeChoices().getChoices().remove(choice);
	    		}

	    		numberOfCopies--;
	    	}
		}


		private void askForASingleChoice(Effect effect) {
	        String question="Do you want to perform this resource exchange?\n Input 1 for yes I do, 0 for I don't\n";
	        int choice=CommonActionCreatorRoutine.askForSingleChoice(question,0,1);
	        this.productionAction.getProductionChoices().add(choice);

	    }

	    private int askForMultipleChoice(MultipleChoiceEffect effect) {
	    	int maxRange=effect.getChoices().size();
	        String question="Please select the exchange effect you want to perform. Input -1 as do nothing:\n"+effect.toString();
	        int choice=CommonActionCreatorRoutine.askForSingleChoice(question,-1,maxRange);
	        if(effect.check(this.productionAction.getFamilyMember())){
	            this.productionAction.getProductionChoices().add(choice);
	        }
	        else{
	            question="\nYou can't do this action because you do not have enough resources. Insert 0 to leave this choice or 1 to retry";
	            choice= CommonActionCreatorRoutine.askForSingleChoice(question,-1,maxRange);
	            if(choice==0){
	                System.out.println("\nChoice skipped");
	            }
	            else{
	                askForMultipleChoice(effect);
	            }
	        }
	        return choice;
	    }


*/
}
