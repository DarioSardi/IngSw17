package it.polimi.ingsw.GC_43.model.actionCreations;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.actionSpace.Tower;
import it.polimi.ingsw.GC_43.model.actionSpace.TowerColors;
import it.polimi.ingsw.GC_43.model.actions.TowerAction;
import it.polimi.ingsw.GC_43.model.cards.Card;
import it.polimi.ingsw.GC_43.model.cards.VentureCard;
import it.polimi.ingsw.GC_43.model.effects.ChoiceEffect;
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
	    //player ID will be the ID of the instance of playerImpl != player of the model


	    public TowerActionCreationRoutine(String playerID, Player player,Board board){
	        this.towerAction=new TowerAction(playerID, player);
	        this.board=board;
	    }


	    public boolean prepareAction() {
	    	
	    	if(!this.towerAction.isDefaultFamilyMember())
	    		this.towerAction.setFamilyMember(CommonActionCreatorRoutine.askForFamilyMemberChoice(this.towerAction.getPlayer()));
	    	
	    	this.towerAction.setFamilyMemberColor(this.towerAction.getFamilyMember().getColor());
	        
	    	this.towerAction.setServantsUsed(CommonActionCreatorRoutine.askForServantsUsage(towerAction.getPlayer(),this.towerAction.getFamilyMember().getDiceValue()));

	        selectTowerAndFloor(board.getTowers());
	        
	        
	        
	        
	        

	        return true;
	    }
	    
	    



		private boolean selectTowerAndFloor(ArrayList <Tower> towers) {
	    	
	    	//TOWER CHOICE
			String question= this.board.towersToString();
			int towerChoice;
			
			if(!this.towerAction.isDefaultTowerChoice())
	    		towerChoice=CommonActionCreatorRoutine.askForSingleChoice(question, 0, towers.size());
			else
				towerChoice= this.towerAction.getTowerChoice();
			
			
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
	    		this.towerAction.setTowerChoice(towerChoice);
	    		this.towerAction.setFloorChoice(floorChoice);
	    	}
	    	
	        checkAndSelectIfDoubleCost(this.board.getTowers().get(towerChoice).getFloors().get(floorChoice).getCard());

	    	
	        checkCardInstantEffects(this.board.getTowers().get(towerChoice).getFloors().get(floorChoice).getCard());

	    	
	    	//VEDITI I CASI INSTANT BONUS  E VEDI PER LE CHOICE !!
	    	return true;
	        
	    }

	    
	    
	    
	    
	    private void checkAndSelectIfDoubleCost(Card card) {
	    	if (card.getClass().toString().contains("VentureCard")){
	    		VentureCard ventureCard= (VentureCard) card;
	    		if(ventureCard.getMilitaryCost()>0){
	    			String question="\n Please input 0 to choose for this cost:\n "+ventureCard.getCost().toString()+"\n otherwise 1 for :\n military Points needed: "+ventureCard.getMilitaryMin()+"\n spending military points: "+ventureCard.getMilitaryCost();
	    			this.towerAction.setDoubleCostSelection(CommonActionCreatorRoutine.askForSingleChoice(question,0,1));
	    		}
	    		
	    		
	    	}
		}


		private void checkCardInstantEffects(Card card) {
	    	System.out.println("\nCard instant bonus requiring choices checks...\n");
	    	for(Effect effect: card.getInstantBonus()){
                if(effect.getClass().toString().contains("MultipleCouncilPrivileges")){
                    askForMultipleCouncilPrivilege((MultipleCouncilPrivileges)effect);
                }
	    	}
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

	    private int askForMultipleChoice(MultipleChoiceEffect effect) {
	    	int maxRange=effect.getChoices().size();
	        String question="Please select the exchange effect you want to perform. Input -1 as do nothing:\n"+effect.toString();
	        int choice=CommonActionCreatorRoutine.askForSingleChoice(question,-1,maxRange);
	        if(effect.check(this.towerAction.getFamilyMember())){
	            this.towerAction.getEffectChoices().add(choice);
	            ChoiceEffect choiceEffect=effect.getChoices().get(choice);
	            
	            //Ask per privilege council SE nello scambio, MAX DA RIVEDERE;
	            
	            if(choiceEffect.getGains().get(0).getClass().toString().contains("privilegeCouncil"))
	            	askForMultipleCouncilPrivilege(new MultipleCouncilPrivileges(1));
	        }
	        else{
	            question="\nYou can't do this action because you do not have enough resources. Insert 0 to leave this choice or 1 to retry";
	            choice= CommonActionCreatorRoutine.askForSingleChoice(question,-1,maxRange);
	            if(choice==0){
	                System.out.println("\nChoice skipped");
	            }
	            else{
	                return askForMultipleChoice(effect);
	            }
	        }
	        return choice;
	    }


}
