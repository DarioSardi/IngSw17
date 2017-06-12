package it.polimi.ingsw.GC_43.model.actionCreations;

import java.util.HashMap;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.actions.CouncilPalaceAction;
import it.polimi.ingsw.GC_43.model.actions.MarketAction;

public class MarketActionCreationRoutine implements ActionCreation{
	
	 	private MarketAction marketAction;
	    private Board board;
	    private HashMap<String,Integer>copyOfPlayerResource;


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
	        
	        getMarketChoices(this.marketAction.getFamilyMember());

	        return true;
	    }

		private void getMarketChoices(FamilyMember familyMember) {
			String toString="Please select the market action space you prefer: \n"+this.board.getMarket().toString();
			int choice = CommonActionCreatorRoutine.askForSingleChoice(toString, -1, this.board.getMarket().getMarketActionSpaces().size());
	//		if(board.getMarket().getMarketActionSpaces().get(choice).isOccupied()||familyMember.getDiceValue()+this.marketAction.getServantsUsed()<this.board.getMarket().getMarketActionSpaces().get(choice).getMinDiceValue()){
				System.out.println("\n You cannot select this market action space. You will be asked again, remember to select -1 to dismiss the action");
				getMarketChoices(familyMember);
			}
//		}

}
