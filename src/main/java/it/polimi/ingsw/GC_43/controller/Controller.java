package it.polimi.ingsw.GC_43.controller;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.actionPerforms.*;
import it.polimi.ingsw.GC_43.model.actions.*;
import it.polimi.ingsw.GC_43.model.initialization.InitGame;

public class Controller implements IController {
	    private Map<String, Player> matchPlayer= new HashMap<String, Player>();
	    private Board board;
	    private ArrayList<ClientHandler> clientHandlers;
	    
	    
	    public Controller(ArrayList<ClientHandler> clientHandlers){
	    	this.clientHandlers=clientHandlers;
	    	this.matchPlayer=new HashMap<String, Player>();
	    }
	    
	    public void initializeGame(){
	    	
	    	insertPlayers();

	    	
	    	
	    }
	    
	    public void insertPlayers(){
	    	ArrayList<String> playerIDs= new ArrayList<String>();
	    	for(ClientHandler clientHandler: this.clientHandlers){
	    		playerIDs.add(clientHandler.getUsername());
	    	}
	    	this.board=new Board(playerIDs);
//	    	new InitGame(board);
	    	
	    	
	    }
	    
//TODO AGGIUNGI BOOLEANO PER VEDERE SE PLAYER CONNESSO O NO;
	    
	    
	    
	    public boolean submit(Action action){
	    	action.setPlayer(this.matchPlayer.get(action.getPlayer().getPlayerName()));
	    	int actionID= action.getActionID();
	    	boolean result;
	    	switch(actionID){
	    		case 1:
	    			ProductionAction productionAction=(ProductionAction) action;
	    			ProductionActionPerformerRoutine productionActionImpl= new ProductionActionPerformerRoutine(productionAction, this.board);
	    			result = productionActionImpl.performAction();
	    			return result;
	    		case 2:
	    			HarvestAction harvestAction=(HarvestAction) action;
	    			HarvestActionPerformerRoutine harvestActionImpl= new HarvestActionPerformerRoutine(harvestAction, this.board);
	    			result = harvestActionImpl.performAction();
	    			return result;
	    			
	    		case 3:
	    			CouncilPalaceAction councilPalaceAction=(CouncilPalaceAction) action;
	    			CouncilPalacePerformerRoutine councilPalaceActionImpl= new CouncilPalacePerformerRoutine(councilPalaceAction, this.board);
	    			result = councilPalaceActionImpl.performAction();
	    			return result;
	    		case 4:
	 /*   			CouncilPalaceAction councilPalaceAction=(CouncilPalaceAction) action;
	    			CouncilPalacePerformerRoutine councilPalaceActionImpl= new CouncilPalacePerformerRoutine(councilPalaceAction, this.board);
	    			result = councilPalaceActionImpl.performAction();
	    			return result;*/
	    			
	    			
	    		
	    		default:
	    			return false;
	    	}
	    }


}
