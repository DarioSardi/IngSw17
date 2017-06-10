package it.polimi.ingsw.GC_43.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.actionPerforms.ProductionActionPerformer;
import it.polimi.ingsw.GC_43.model.actions.Action;
import it.polimi.ingsw.GC_43.model.actions.ProductionAction;
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
	    //	new InitGame(board);
	    	
	    	
	    }
	    
//TODO AGGIUNGI BOOLEANO PER VEDERE SE PLAYER CONNESSO O NO;
	    
	    
	    
	    public boolean submit(Action action){
	    	int actionID= action.getActionID();
	    	switch(actionID){
	    		case 0:
	    			ProductionAction productionAction=(ProductionAction) action;
	    			ProductionActionPerformer productionActionImpl= new ProductionActionPerformer(productionAction, this.board);
	    			boolean result = productionActionImpl.performAction();
	    			return result;
	    		case 1:
	    			
	    			
	    		case 2:
	    			
	    		
	    		default:
	    			return false;
	    	}
	    }

		@Override
		public boolean login(String username, String Password) {
			// TODO Auto-generated method stub
			return false;
		}


}
