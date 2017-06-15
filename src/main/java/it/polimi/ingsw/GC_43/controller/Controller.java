package it.polimi.ingsw.GC_43.controller;

import java.util.ArrayList;


import java.util.HashMap;
import java.util.Map;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.actionPerforms.*;
import it.polimi.ingsw.GC_43.model.actions.*;
import it.polimi.ingsw.GC_43.model.initialization.GlobalVariablesInit;
import it.polimi.ingsw.GC_43.model.initialization.InitGame;

public class Controller implements IController {
	
	
	//MATCHING PLAYERS ON MODEL AND CLIENT HANDLERS
	    private Map<String, Player> matchPlayer;
	    private Map<String, ClientHandler> matchClientHandler;
	    private ArrayList<ClientHandler> clientHandlers;

	    
	//REFERENCE OF THE MODEL ON SERVER
	    private Board board;
	    
	    
	    public Controller(ArrayList<ClientHandler> clientHandlers){
	    	this.clientHandlers=new ArrayList<ClientHandler>();
	    	this.clientHandlers=clientHandlers;
	    	this.matchPlayer=new HashMap<String, Player>();
	    	this.matchClientHandler=new HashMap<String, ClientHandler>();
	    }
	    
	    
	    
	    
	    public void initializeGame(){
	    	
	    	insertPlayers();
	    	System.out.println("player inserted");
	    	setMatches();
	    	System.out.println("matches created");
	    	sendModelToClients();
	    	System.out.println("model sent");

	    	
	    	
	    }
	    
	    private void sendModelToClients() {
	    		for(ClientHandler clientHandler : this.clientHandlers){
	    			clientHandler.sendObject(this.board);
	    		}			
		}



	// MATCH PLAYER REQUIRE 
		private void setMatches() {
			int i=0;
	    	for(ClientHandler clientHandler: this.clientHandlers){
	    		
	    		this.matchClientHandler.put(clientHandler.getUsername(), clientHandler);
	    		this.matchPlayer.put(clientHandler.getUsername(), this.board.getPlayers().get(i));
	    		i++;
	    		
	    	}
	    	
		}




		public void insertPlayers(){
	    	ArrayList<String> playerIDs= new ArrayList<String>();
	    	for(ClientHandler clientHandler: this.clientHandlers){
	    		
	    		playerIDs.add(clientHandler.getUsername());
	    		
	    	}
	    	GlobalVariablesInit.readGlobalVariables();
	    	this.board=new Board(playerIDs);
	    	System.out.println("sto per inizializzare il gioco");
	    	new InitGame(board);
	    	
	    	
	    }
	    
		
		
		
	//GET CLIENT HANDLER OF TURN    
	    private ClientHandler getPlayerOfTurn(){
	    	String playerID=this.board.getPhasePlayer();
	    	return this.matchClientHandler.get(playerID);
	    	
	    }
	    
//TODO AGGIUNGI BOOLEANO PER VEDERE SE PLAYER CONNESSO O NO;
	    
	    
	    
	    public synchronized boolean submit(Action action){
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
	    			MarketAction marketAction=(MarketAction) action;
	    			MarketActionPerformerRoutine marketActionImpl= new MarketActionPerformerRoutine(marketAction, this.board);
	    			result = marketActionImpl.performAction();
	    			return result;
	    			
	    			
	    		
	    		default:
	    			return false;
	    	}
	    }


}
