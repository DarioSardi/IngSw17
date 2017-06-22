package it.polimi.ingsw.GC_43.model.actionCreations;

import java.util.HashMap;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.actions.MarketAction;
import it.polimi.ingsw.GC_43.model.effects.Effect;
import it.polimi.ingsw.GC_43.model.effects.MultipleChoiceEffect;
import it.polimi.ingsw.GC_43.model.effects.MultipleCouncilPrivileges;

public class MarketActionCreationRoutine implements ActionCreation{
	
	 	/**
	 * 
	 */
	private static final long serialVersionUID = -7728764206540843326L;

		private MarketAction marketAction;
	    private Board board;
	    private HashMap<String,Integer>copyOfPlayerResource;
	    private int index;


	    public MarketActionCreationRoutine(String playerID, Player player,Board board){
	        this.marketAction=new MarketAction(playerID, player);
	        this.board=board;
	        this.copyOfPlayerResource=CommonActionCreatorRoutine.copyPlayerResources(player);
	    }
	    
	    @Override
	    public boolean prepareAction() {
	        this.marketAction.setFamilyMember(CommonActionCreatorRoutine.askForFamilyMemberChoice(this.marketAction.getPlayer()));
	        this.marketAction.setFamilyMemberColor(this.marketAction.getFamilyMember().getColor());
	        this.marketAction.setServantsUsed(CommonActionCreatorRoutine.askForServantsUsage(marketAction.getPlayer(),this.marketAction.getFamilyMember().getDiceValue()));
	        if(!this.marketAction.getPlayer().getPlayerBounusMalus().isNoMarketActionSpaceBonus())
	        	getMarketChoices(this.marketAction.getFamilyMember());
	        else{
	        	System.out.println("You can not access to Market Action Spaces, since you have the related malus, no possibility to perform this action\n");
	        	return false;
	        }

	        System.out.println("Market Action completed !\n");
	        return true;
	    }

		private void getMarketChoices(FamilyMember familyMember) {
			String toString="Please select the market action space you prefer: \n"+this.board.getMarket().toString();
			int choice = CommonActionCreatorRoutine.askForSingleChoice(toString, 0, this.board.getMarket().getMarketActionSpaces().size());
			if(board.getMarket().getMarketActionSpaces().get(choice).isOccupied()||familyMember.getDiceValue()+this.marketAction.getServantsUsed()<this.board.getMarket().getMarketActionSpaces().get(choice).getMinDiceValue()){
				System.out.println("You cannot select this market action space. You will be asked again");
				getMarketChoices(familyMember);
			}
			else{
				System.out.println("Successful selection!");
				this.marketAction.setMarketActionSpaceSelected(choice);
				System.out.println("Entering checking for effects..");

		        checkChoiceNature(familyMember.getPlayer(), choice);

			}
		}
		
        public void checkChoiceNature(Player player, int choice){
			
        	for( Effect effect: this.board.getMarket().getMarketActionSpaces().get(choice).getBonus()){
        		System.out.println("nature of effect = "+effect.getClass().toString());
				if(effect.getClass().toString().contains("MultipleCouncilPrivileges")){
					askForMultipleCouncilPrivilege((MultipleCouncilPrivileges)effect);
				}
        	}

        }
        
        private void askForMultipleCouncilPrivilege(MultipleCouncilPrivileges multipleEffect) {
        	MultipleCouncilPrivileges effect=CommonActionCreatorRoutine.copyMultiplePrivileges(multipleEffect.getNumberOfCopies());
        	int numberOfCopies=effect.getNumberOfCopies();
        	while(numberOfCopies>0){

        		int choice= askForMultipleChoice(effect.getPrivilegeChoices());

        		if(choice!=-1){
        			effect.getPrivilegeChoices().getChoices().remove(choice);
            		System.out.println("choice removed.." );

        		}
        		System.out.println("remaining number of copies"+numberOfCopies );
        		numberOfCopies--;
        	}
    	}
        
        private int askForMultipleChoice(MultipleChoiceEffect effect) {
        	int maxRange=effect.getChoices().size();
            String question="Please select the exchange effect you want to perform. Input -1 as do nothing:\n"+effect.toString();
            int choice=CommonActionCreatorRoutine.askForSingleChoice(question,-1,maxRange);
            System.out.println("Choice taken is "+choice);
            if(effect.check(this.marketAction.getFamilyMember())){
                System.out.println("Choice taken is ok");

                this.marketAction.getMarketChoices().add(choice);
                System.out.println("Choice added..");

            }
            else{
                question="\nYou can't do this action because you do not have enough resources. Insert 0 to leave this choice or 1 to retry";
                choice= CommonActionCreatorRoutine.askForSingleChoice(question,-1,maxRange);
                if(choice==0){
                    System.out.println("Choice skipped");
                }
                else{
                    return askForMultipleChoice(effect);
                }
            }

            return choice;
        }

		public MarketAction getMarketAction() {
			return marketAction;
		}

		public void setMarketAction(MarketAction marketAction) {
			this.marketAction = marketAction;
		}

		public Board getBoard() {
			return board;
		}

		public void setBoard(Board board) {
			this.board = board;
		}

		public HashMap<String, Integer> getCopyOfPlayerResource() {
			return copyOfPlayerResource;
		}

		public void setCopyOfPlayerResource(HashMap<String, Integer> copyOfPlayerResource) {
			this.copyOfPlayerResource = copyOfPlayerResource;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
        
        
        
        


    

}
