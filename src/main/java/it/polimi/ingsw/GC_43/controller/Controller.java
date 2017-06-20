package it.polimi.ingsw.GC_43.controller;

import java.util.ArrayList;


import java.util.HashMap;
import java.util.Map;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.GlobalVariables;
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
	    private Lobby playersLobby;

	    
	//REFERENCE OF THE MODEL ON SERVER
	    private Board board;
	    private GlobalVariables globalVariables;
	    
	    
	    public Controller(ArrayList<ClientHandler> clientHandlers){
	    	this.clientHandlers=new ArrayList<ClientHandler>();
	    	this.clientHandlers=clientHandlers;
	    	this.matchPlayer=new HashMap<String, Player>();
	    	this.matchClientHandler=new HashMap<String, ClientHandler>();
	    	this.playersLobby=clientHandlers.get(0).getLobby();
	    }
	    
	    
	    
	    
	    public void initializeGame(){
	    	
	    	insertPlayers();
	    	System.out.println("player inserted");
	    	setMatches();
	    	System.out.println("matches created");
	    	sendModelToClients();
	    	System.out.println("model sent");

	    	//TODO wait form SAM
	    	sendGlobalVariablesToClients();	    	
	    	System.out.println("global variables sent");
	    	
	    	startGame();


	    	
	    	
	    }
	    
	    
	    private void startGame() {
	    	ClientHandler initialPlayer = this.matchClientHandler.get(this.board.getPlayersID().get(0));
	    	changePhases(initialPlayer);
	    	
		}




		//TODO to decommenti, wait for SAM
	    private void sendGlobalVariablesToClients() {
	    	for(ClientHandler clientHandler : this.clientHandlers){
    			clientHandler.sendObject(this.globalVariables);
    		}		
		}




		private void sendModelToClients() {
	    		for(ClientHandler clientHandler : this.clientHandlers){
	    			clientHandler.sendObject(this.board);
	    		}			
		}



	// MATCH PLAYER WITH CLIENT HANDLERS
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
	    	GlobalVariables globalVariables= new GlobalVariables();
	    	
	    	System.out.println("Initializing globalVariables");

	    	GlobalVariablesInit.readGlobalVariables(globalVariables);
	    	
	    	
	    	this.globalVariables=globalVariables;
	    	
	    	System.out.println("Creating board");

	    	this.board=new Board(playerIDs);
	    	System.out.println("Initializing game board");
	    	new InitGame(board);
	    	
	    	
	    	
	    }
	    
		
		
		
	//GET CLIENT HANDLER OF TURN    
	    private ClientHandler getPlayerOfTurn(){
	    	String playerID=this.board.getPhasePlayer();
	    	return this.matchClientHandler.get(playerID);
	    	
	    }
	    
//TODO AGGIUNGI BOOLEANO PER VEDERE SE PLAYER CONNESSO O NO;
	    
	    public synchronized void submitClientAction(Action action){
	    	
	    	System.out.println("\nclient Action received from client "+action.getPlayerID());
	    	
	    	boolean actionResult=false;
	    	actionResult= submit(action);
	    	
	    	System.out.println("\n Action successfully submitted");

	    	
	    	
	    	if(actionResult){
		    	
	    		System.out.println("\n Action successfully completed, broadcasting notifications and calling for next player phase");

	    		playersLobby.broadcastMsg(action.toString(), this.getPlayerOfTurn());
	    		for(ClientHandler clientHandler: this.clientHandlers){
	    			clientHandler.sendObject(this.board);
		    	}
	    		
	    		nextPlayerPhase();
	    		
	    	}
		    
	    	
	    	else{
	    		this.getPlayerOfTurn().sendMsgTo("\nAction could not be performed, please try again\n");
	    	}
	    
	    }
	    
	    
	    //MANAGING PHASE OF PLAYERS
	    
	    private void nextPlayerPhase() {
	    	
			this.board.nextPlayerPhase();
			ClientHandler playerOfTurn=this.matchClientHandler.get(this.board.getPhasePlayer());
			System.out.println("\nChanging phases of players");
			changePhases(playerOfTurn);
			System.out.println("\nNext turn logic ended successfully");

	    }



	    private void changePhases(ClientHandler playerOfTurn) {
	    	for(ClientHandler client: this.clientHandlers){
				if(client.getUsername().equals(playerOfTurn.getUsername()))
					client.setMyturn(true);
				else
					client.setMyturn(false);
			}			
		}




		//CALL submitClientAction
		private boolean submit(Action action){

			System.out.println("\nAttempting to match player on server to perform action");

	    	action.setPlayer(this.matchPlayer.get(action.getPlayer().getPlayerName()));
	    	int actionID= action.getActionID();
	    	boolean result;
	    	
			System.out.println("\nAttempting to perform the action submitted");

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
	    		case 5:
	    			TowerAction towerAction=(TowerAction) action;
	    			TowerActionPerformerRoutine towerActionImpl= new TowerActionPerformerRoutine(towerAction, this.board);
	    			result = towerActionImpl.performAction();
	    			return result;
	    			
	    		
	    		default:
	    			return false;
	    	}


	    }


}
