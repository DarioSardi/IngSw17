package it.polimi.ingsw.GC_43.controller;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.CopyOfGlobalVariables;
import it.polimi.ingsw.GC_43.model.GlobalVariables;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.actionPerforms.*;
import it.polimi.ingsw.GC_43.model.actions.*;
import it.polimi.ingsw.GC_43.model.initialization.GlobalVariablesInit;
import it.polimi.ingsw.GC_43.model.initialization.InitGame;

public class Controller implements IController {

	// MATCHING PLAYERS ON MODEL AND CLIENT HANDLERS
	private Map<String, Player> matchPlayer;
	private Map<String, ClientHandler> matchClientHandler;
	private Map<String, Boolean> matchClientHandlerStatus;

	private ArrayList<ClientHandler> clientHandlers;
	private Lobby playersLobby;

	// REFERENCE OF THE MODEL ON SERVER
	private Board board;
	private CopyOfGlobalVariables globalVariables;
	
	
	//DISCONNESSIONI
	private int playerDisconnected;

	public Controller(ArrayList<ClientHandler> clientHandlers) {
		this.clientHandlers = new ArrayList<ClientHandler>();
		this.clientHandlers = clientHandlers;
		this.matchPlayer = new HashMap<String, Player>();
		this.matchClientHandler = new HashMap<String, ClientHandler>();
		this.matchClientHandlerStatus= new HashMap<String,Boolean>();
		this.playersLobby = clientHandlers.get(0).getLobby();
		this.playerDisconnected=0;
	}

	public void initializeGame() {

		insertPlayers();
		System.out.println("player inserted");
		setMatches();
		System.out.println("matches created");
		sendModelToClients();
		System.out.println("model sent");

		// TODO wait form SAM
		sendGlobalVariablesToClients();
		System.out.println("global variables sent");

		startGame();

	}

	private void startGame() {
		System.out.println("start Game");
		ClientHandler initialPlayer = this.matchClientHandler.get(this.board.getPlayersID().get(0));
		String initialPlayerBroadcast="Initial phase goes to " + initialPlayer.getUsername();
		System.out.println(initialPlayerBroadcast);
		
	//SCOMMENTA DOPO DARIO PER AVERE BROADCAST MESAGE DA SYSTEM	
	//	this.playersLobby.broadcastMsg(initialPlayerBroadcast);
		

		changePhases(initialPlayer);
		System.out.println("changing phase finished");

	}

	// TODO to decommenti, wait for SAM
	private void sendGlobalVariablesToClients() {
		for (ClientHandler clientHandler : this.clientHandlers) {
			clientHandler.sendObject(this.globalVariables);
			System.out.println("numberOfFamliars " + this.globalVariables.numberOfFamilyMembers);
		}
	}

	private void sendModelToClients() {
		for (ClientHandler clientHandler : this.clientHandlers) {
			clientHandler.sendObject(this.board);
		}
	}

	// MATCH PLAYER WITH CLIENT HANDLERS
	private void setMatches() {
		int i = 0;
		for (ClientHandler clientHandler : this.clientHandlers) {

			this.matchClientHandler.put(clientHandler.getUsername(), clientHandler);
			this.matchClientHandlerStatus.put(clientHandler.getUsername(), true);
			this.matchPlayer.put(clientHandler.getUsername(), this.board.getPlayers().get(i));
			i++;

		}

	}
	
	//ROUTINES OF DISCONNECTIONS AND RECONNECTIONS OF PLAYERS
	
	public void playerInGameAgain(String playerID, ClientHandler clientHandler){
		if(this.matchClientHandlerStatus.get(playerID)!=null){
			switchPlayerStatus(playerID);
			this.matchClientHandler.put(playerID, clientHandler);
			playerDisconnected--;

			clientHandler.sendObject(this.globalVariables);
			clientHandler.sendObject(this.board);

		}
		
	}
	
	public void playerDisconnected(String playerID){
		switchPlayerStatus(playerID);
		playerDisconnected++;
	}

	private void switchPlayerStatus(String playerID) {
		if(matchClientHandlerStatus.get(playerID)==true)
			this.matchClientHandlerStatus.put(playerID, false);
		else
			this.matchClientHandlerStatus.put(playerID, true);

	}
	
	

	public void insertPlayers() {
		ArrayList<String> playerIDs = new ArrayList<String>();
		for (ClientHandler clientHandler : this.clientHandlers) {
			playerIDs.add(clientHandler.getUsername());

		}

		System.out.println("Initializing globalVariables");

		
	    GlobalVariablesInit.readGlobalVariables();
		  
		CopyOfGlobalVariables globalVariables= new CopyOfGlobalVariables();  
	    new GlobalVariables().createCopyGlobalVariables(globalVariables);
	    this.globalVariables=globalVariables;
		
	    
		System.out.println("Creating board");

		this.board = new Board(playerIDs);
		System.out.println("Initializing game board");
		new InitGame(board);
		this.board.initialize();


	}

	// GET CLIENT HANDLER OF TURN
	private ClientHandler getPlayerOfTurn() {
		String playerID = this.board.getPhasePlayer();

		return this.matchClientHandler.get(playerID);

	}
	
	public void clientTimeIsOver(String playerUsername){
		if(this.matchClientHandler.get(playerUsername)!=null){
			nextPlayerPhase();
			this.matchClientHandler.get(playerUsername).sendMsgTo("Phase has been skipped for inactivity");
		}
	}

	// TODO AGGIUNGI BOOLEANO PER VEDERE SE PLAYER CONNESSO O NO;

	public synchronized void submitClientAction(Action action) {

		System.out.println("\nclient Action received from client " + action.getPlayerID());

		boolean actionResult = false;
		actionResult = submit(action);

		System.out.println("\n Action submission = " + actionResult);

		if (actionResult) {

			System.out.println(
					"\nAction successfully completed, broadcasting notifications and calling for next player phase");

			playersLobby.broadcastMsg(action.toString(), this.getPlayerOfTurn());
			
			for (ClientHandler clientHandler : this.clientHandlers) {
				System.out.println("Sending updated board to client"+clientHandler.getUsername());
				clientHandler.sendObject(this.board);
			}

			nextPlayerPhase();

		}

		else {
			System.out.println("\n Action unsuccessfully submitted " + actionResult + "action to strng\n" + action.toString());
			System.out.println(this.getPlayerOfTurn().getUsername());
			this.getPlayerOfTurn().sendMsgTo("\nAction could not be performed, please try again\n");
			System.out.println("\nAction concluded !\n");

		}

	}

	// MANAGING PLAYERS PHASES

	private void nextPlayerPhase() {
		System.out.println("\n Attemping to get old phase player" + this.board.getPhasePlayer());
		
		if(this.playerDisconnected==this.clientHandlers.size())
			endGame();
		
		this.board.nextPhase();

		
		
	//CHECKING FOR EXCOMMUNICATION ROUND
		if(this.board.getPlayers().size()==this.board.getPhase()){
		/*	
			fkn+mldà
*/
		}
		
		
		
		
		
		
		while (!this.matchClientHandlerStatus.get(this.board.getPhasePlayer())){
			this.board.nextPhase();

		}
		System.out.println("\n Attemping Changing phases of players" + this.board.getPhase());
		System.out.println("\n Attemping match player name" + this.board.getPhasePlayer());

		System.out.println("\n Attemping match" + this.matchClientHandler.get(this.board.getPhasePlayer()));

		ClientHandler playerOfTurn = this.matchClientHandler.get(this.board.getPhasePlayer());
		System.out.println("\nChanging phases of players");
		changePhases(playerOfTurn);
		System.out.println("\nNext turn logic ended successfully");

	}
	
//TODO to implement
	
	private void endGame() {
		// TODO Auto-generated method stub
		
	}

	private void changePhases(ClientHandler playerOfTurn) {
		for (ClientHandler client : this.clientHandlers) {
			if (client.getUsername().equals(playerOfTurn.getUsername())) {
				client.setMyturn(true);
				System.out.println("ojfhbeuhgif player " + client.getUsername());

			} else {
				client.setMyturn(false);
				System.out.println("ojfhbeuhgif player not in turn " + client.getUsername());

			}
		}

	}

	// CALL submitClientAction
	private boolean submit(Action action) {

		System.out.println("\nAttempting to match player on server to perform action");

		action.setPlayer(this.matchPlayer.get(action.getPlayer().getPlayerName()));
		int actionID = action.getActionID();
		boolean result;

		System.out.println("\nAttempting to perform the action submitted "+action.toString()+"\n with ID: "+action.getActionID());

		switch (actionID) {
		case 1:
			ProductionAction productionAction = (ProductionAction) action;
			ProductionActionPerformerRoutine productionActionImpl = new ProductionActionPerformerRoutine(productionAction, this.board);
			result = productionActionImpl.performAction();
			return result;
		case 2:
			HarvestAction harvestAction = (HarvestAction) action;
			HarvestActionPerformerRoutine harvestActionImpl = new HarvestActionPerformerRoutine(harvestAction,this.board);
			result = harvestActionImpl.performAction();
			return result;

		case 3:
			CouncilPalaceAction councilPalaceAction = (CouncilPalaceAction) action;
			CouncilPalacePerformerRoutine councilPalaceActionImpl = new CouncilPalacePerformerRoutine(councilPalaceAction, this.board);
			result = councilPalaceActionImpl.performAction();
			return result;
		case 4:
			MarketAction marketAction = (MarketAction) action;
			MarketActionPerformerRoutine marketActionImpl = new MarketActionPerformerRoutine(marketAction, this.board);
			result = marketActionImpl.performAction();
			return result;
		case 5:
			TowerAction towerAction = (TowerAction) action;
			TowerActionPerformerRoutine towerActionImpl = new TowerActionPerformerRoutine(towerAction, this.board);
			result = towerActionImpl.performAction();
			return result;

		default:
			return false;
		}

	}

}
