package it.polimi.ingsw.GC_43.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.CopyOfGlobalVariables;
import it.polimi.ingsw.GC_43.model.GlobalVariables;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.actionPerforms.*;
import it.polimi.ingsw.GC_43.model.actions.*;
import it.polimi.ingsw.GC_43.model.cards.LeaderCard;
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

	// DISCONNESSIONI
	private int playerDisconnected;
	private int excommunicationSubmission;
	private boolean isExcommunicationTime;
	private boolean advancedGame;
	private ArrayList<Player> playerSkippedFirstRound;

	public Controller(ArrayList<ClientHandler> clientHandlers) throws RemoteException {
		this.clientHandlers = new ArrayList<ClientHandler>();
		this.clientHandlers = clientHandlers;
		this.matchPlayer = new HashMap<String, Player>();
		this.matchClientHandler = new HashMap<String, ClientHandler>();
		this.matchClientHandlerStatus = new HashMap<String, Boolean>();
		this.playersLobby = clientHandlers.get(0).getLobby();
		this.playerDisconnected = 0;
		this.excommunicationSubmission = 0;
		this.isExcommunicationTime = false;
		this.playerSkippedFirstRound = new ArrayList<Player>();

	}

	public void initializeGame(boolean advancedGame) throws RemoteException {
		
		
		this.advancedGame=advancedGame;
		
		insertPlayers();
		System.out.println("player inserted");
		setMatches();
		System.out.println("matches created");
		sendModelToClients();
		System.out.println("model sent");
		sendGlobalVariablesToClients();
		System.out.println("global variables sent");
		
		if(advancedGame){
			System.out.println("Advanced game settings selected..");
			advancedGameRoutine();
		}

		startGame();

	}

	private void advancedGameRoutine() {
		System.out.println("Attempting to ask for default player bonus");
		askForDefaultBonus();
		askForLeaderCards();
	}

	private void askForLeaderCards() {
		ArrayList<LeaderCard> firstPool= new ArrayList<LeaderCard>();

		ArrayList<LeaderCard> secondPool= new ArrayList<LeaderCard>();
	
		ArrayList<LeaderCard> thirdPool= new ArrayList<LeaderCard>();

		ArrayList<LeaderCard> fourthPool= new ArrayList<LeaderCard>();
		
		
		
	}
	
	public void submitLeaderCardChoice(){
		
	}

	private void askForDefaultBonus() {
		ArrayList<Player> inversePlayerOrder=new ArrayList<Player>();
		int playerSize=this.board.getPlayers().size()-1;
		//INVERSE ORDER PHASE PLAYERS
		while(playerSize>=0){
			inversePlayerOrder.add(this.board.getPlayers().get(playerSize));
			playerSize--;
		}
		//this.matchClientHandler.get(inversePlayerOrder).sendMsgTo("Please select for default harvest and production bonus");
		
	}

	private void startGame() throws RemoteException {
		System.out.println("start Game");
		ClientHandler initialPlayer = this.matchClientHandler.get(this.board.getPlayersID().get(0));
		String initialPlayerBroadcast = "Initial phase goes to " + initialPlayer.getUsername();
		System.out.println(initialPlayerBroadcast);

		// NOTIFYING OF FIRST PLAYER PHASE
		this.playersLobby.lobbyMsg(initialPlayerBroadcast);

		try {
			changePhases(initialPlayer);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		System.out.println("changing phase finished");

	}

	private void sendGlobalVariablesToClients() throws RemoteException {
		for (ClientHandler clientHandler : this.clientHandlers) {
			clientHandler.sendObject(this.globalVariables);
			System.out.println("numberOfFamliars " + this.globalVariables.numberOfFamilyMembers);
		}
	}

	private void sendModelToClients() throws RemoteException {
		for (ClientHandler clientHandler : this.clientHandlers) {
			clientHandler.sendObject(this.board);
		}
	}

	// MATCH PLAYER WITH CLIENT HANDLERS
	private void setMatches() throws RemoteException {
		int i = 0;
		for (ClientHandler clientHandler : this.clientHandlers) {

			this.matchClientHandler.put(clientHandler.getUsername(), clientHandler);
			this.matchClientHandlerStatus.put(clientHandler.getUsername(), true);
			this.matchPlayer.put(clientHandler.getUsername(), this.board.getPlayers().get(i));
			i++;

		}

	}

	// ROUTINE OF RECONNECTIONS OF PLAYERS

	public void playerInGameAgain(String playerUsername, ClientHandler clientHandler) throws RemoteException {
		System.out.println("Attempting to reconnect " + playerUsername);
		if (this.matchClientHandlerStatus.get(playerUsername) != null) {
			switchPlayerStatus(playerUsername);
			System.out.println("Switching his status in Game again ");

			this.matchClientHandler.put(playerUsername, clientHandler);
			System.out.println("added to client Handlers " + this.matchClientHandler.get(playerUsername));

			playerDisconnected--;
			System.out.println("Decrementing number of disconnected players");

			System.out.println("Sending global variables and model to the reconnected player");

			clientHandler.sendObject(this.globalVariables);
			clientHandler.sendObject(this.board);

		}

	}

	// ROUTINE OF DISCONNECTIONS OF PLAYERS

	public void playerDisconnected(String playerUsername) {
		switchPlayerStatus(playerUsername);
		System.out.println("Incrementing number of disconnected players");
		playerDisconnected++;
	}

	private void switchPlayerStatus(String playerUsername) {
		if (matchClientHandlerStatus.get(playerUsername) == true)
			this.matchClientHandlerStatus.put(playerUsername, false);
		else
			this.matchClientHandlerStatus.put(playerUsername, true);

	}

	public void insertPlayers() throws RemoteException {
		ArrayList<String> playerUsername = new ArrayList<String>();
		for (ClientHandler clientHandler : this.clientHandlers) {
			playerUsername.add(clientHandler.getUsername());

		}

		System.out.println("Initializing globalVariables");

		GlobalVariablesInit.readGlobalVariables();

		// SAM IF PLAYER 5 CAMBIA TAX TOWER CAMBIA MALUS PRODUCTIONE HARVEST
		// SECONDARY CELL TUTTI A 2/-2
		// AGGIUNGO QUI NO ?

		CopyOfGlobalVariables globalVariables = new CopyOfGlobalVariables();
		new GlobalVariables().createCopyGlobalVariables(globalVariables);
		this.globalVariables = globalVariables;

		System.out.println("Creating board");

		this.board = new Board(playerUsername);
		System.out.println("Initializing game board");
		new InitGame(board);
		this.board.initialize();

	}

	// GET CLIENT HANDLER OF TURN
	private ClientHandler getPlayerOfTurn() {

		String playerID = this.board.getPhasePlayer();
		System.out.println("player of turn is " + playerID);

		return this.matchClientHandler.get(playerID);

	}

	public void clientTimeIsOver(String playerUsername) throws RemoteException {
		if (this.matchClientHandler.get(playerUsername) != null) {
			nextPlayerPhase();
			this.matchClientHandler.get(playerUsername).sendMsgTo("Phase has been skipped for inactivity");
		}
	}

	// TODO AGGIUNGI BOOLEANO PER VEDERE SE PLAYER CONNESSO O NO;

	public synchronized void submitClientAction(Action action) throws RemoteException {
		if (!this.isExcommunicationTime) {
			System.out.println("\nclient Action received from client " + action.getPlayerID());

			boolean actionResult = true;
			actionResult = submit(action);

			System.out.println("\n Action submission = " + actionResult);

			if (actionResult) {
				System.out.println(
						"\nAction successfully completed, broadcasting notifications and calling for next player phase");

				playersLobby.broadcastMsg(action.toString(), this.getPlayerOfTurn());

				for (ClientHandler clientHandler : this.clientHandlers) {
					System.out.println("Sending updated board to client" + clientHandler.getUsername());
					clientHandler.sendObject(this.board);
				}

				if (this.matchPlayer.get(this.board.getPhasePlayer()).getExtraActions().isEmpty()){
					if (action.getActionID() != 6){
						//NON VA BENE STO IF PERCHè SE NO DOPO EXTRA ACTION SE C'è PASSA IL TURNO CI VUOLE UN BOOLEANO DA METTERE
					//	jjokplpj
						nextPlayerPhase();
					}
					else{
						System.out.println("Leader card action completed, that 's still "+this.getPlayerOfTurn().getUsername()+" phase");
					}
				}
				else
					askForExtraAction();

			}

			else {
				System.out.println("\nAction unsuccessfully submitted " + actionResult + " action to string\n"
						+ action.toString());
				System.out.println(this.getPlayerOfTurn().getUsername());
				this.getPlayerOfTurn().sendMsgTo("\nAction could not be performed, please try again\n");
				System.out.println("\nAction concluded !\n");

			}
		}

		else {
			System.out.println("Player " + action.getPlayerID() + " submitted an action during excommunication time");
		}

	}

	// EXTRA ACTION COMMUNICATION LOGIC
	private void askForExtraAction() {
		System.out.println("Detected extra action possibility: asking player for it");
		ExtraAction extraAction = new ExtraAction(
				this.matchPlayer.get(this.board.getPhasePlayer()).getExtraActions().get(0));
		try {
			this.getPlayerOfTurn().sendObject(extraAction);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		// removing extra action after sending it
		this.matchPlayer.get(this.board.getPhasePlayer()).getExtraActions().remove(0);
	}

	private void getBackInitialTurn() {

		System.out.println("Giving back phase action to players who had malus..");
		try {
			changePhases(this.matchClientHandler.get(this.playerSkippedFirstRound.get(0).getPlayerName()));
			this.playerSkippedFirstRound.remove(0);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// MANAGING PLAYERS PHASES

	private void nextPlayerPhase() throws RemoteException {
		System.out.println("\n\nNEXT PLAYER PHASE LOGIC STARTING\n");

		System.out.println("Attemping to get old phase player " + this.board.getPhasePlayer());

		if (this.playerDisconnected == this.clientHandlers.size()) {
			System.out.println("No players in game, game over");
			endGame();
		}

		// CHECKING IF SOME PLAYER GOT MALUS OF SKIPPING FIRST ROUND AND GET IT
		// BACK AT THE END
		if (this.board.getPhase() + 1 % this.board.getPlayers().size() == 0 && !this.playerSkippedFirstRound.isEmpty()
				&& this.board.getRound() + 1 % GlobalVariables.numberOfFamilyMembers == 0) {

			getBackInitialTurn();

		}

		else {
			this.board.nextPhase();

			// CHECKING FOR EXCOMMUNICATION ROUND AND END GAME
			if (this.board.getPhase() % this.board.getPlayers().size() == 0) {

				System.out.println("Ongoing next round logic, round was number " + this.board.getRound());

				this.board.nextRound();

				// CHECKING EXCOMMUNICATION
				if (this.board.getRound() % GlobalVariables.excommunicationRound == 0) {
					System.out.println("Excommunication time on round " + this.board.getRound() + " and period "
							+ this.board.getPeriod());
					this.isExcommunicationTime = true;
					askPlayersForExcommunication();
					// waitForAllResponses()
				}

				// END GAME
				if (this.board.getPeriod() == GlobalVariables.totalNumberOfPeriods
						&& this.board.getRound() % this.board.getPlayers().size() == 0) {
					System.out.println("Game is finished!\n Period= " + this.board.getPeriod() + "\nRound= "
							+ this.board.getRound() + "\nPhase= " + this.board.getPhase());
					endGame();
				}
				System.out
						.println("Resetting board spaces, geting ready for next round number" + this.board.getRound());
				nextRoundLogic();

			}

			if (!this.matchClientHandlerStatus.get(this.board.getPhasePlayer())) {
				nextPlayerPhase();
				return;
			}

			System.out.println("\n Attemping match player name" + this.board.getPhasePlayer());

			// CHECKING MALUS SKIP FIRST ROUND PHASE

			ClientHandler playerOfTurn = this.matchClientHandler.get(this.board.getPhasePlayer());
			if (this.board.getRound() == 0 && this.matchPlayer.get(this.board.getPhasePlayer()).getPlayerBounusMalus()
					.isSkipFirstFamiliarMoveAndGetItBackAtTheEnd()) {
				while (this.board.getRound() == 0 && this.matchPlayer.get(this.board.getPhasePlayer())
						.getPlayerBounusMalus().isSkipFirstFamiliarMoveAndGetItBackAtTheEnd()) {
					this.playerSkippedFirstRound.add(this.matchPlayer.get(this.board.getPhasePlayer()));
					nextPlayerPhase();
					return;

				}

			}

			else {
				System.out.println("\nChanging phases of players");
				changePhases(playerOfTurn);
				System.out.println("\nNext turn logic ended successfully");
			}
		}
	}

	// TODO to be completed nextRoundLogic, askPlayersForExcommunication,
	// endGame

	private void nextRoundLogic() {
		this.board.nextTurn();
	}

	public void submitExcommunicationChoice(String playerUsername, boolean decision) {
		if (decision == false) {
			System.out.println("Player " + playerUsername + " decided to be excommunicated");
			this.board.excommunicatePlayer(this.matchPlayer.get(playerUsername));
		} else {
			System.out.println(
					"Player " + playerUsername + " decided to NOT be excommunicated and to satisfy the church");
			this.board.satisfyTheChurch(matchPlayer.get(playerUsername));

		}
		this.excommunicationSubmission++;

		if (this.excommunicationSubmission == this.board.getPlayers().size()) {
			this.board.setPhase(0);
			System.out.println("Excommunication round finished, going for next player phase: " + this.board.getPhase()
					+ " round" + this.board.getRound() + " period" + this.board.getPeriod());
			this.isExcommunicationTime = false;
			try {
				nextPlayerPhase();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void askPlayersForExcommunication() throws RemoteException {
		System.out.println("Entered in Excommunication logic function");
		this.board.nextPeriod();

		for (ClientHandler clientHandler : this.clientHandlers) {
			if (this.matchClientHandlerStatus.get(clientHandler.getUsername())
					&& checkExcommunicationFaithPoints(clientHandler.getUsername())) {
				clientHandler.sendMsgTo("excommunication_round");
				// DARIO non devono poter fare azioni di altro tipo in questo
				// periodo di scelta
				clientHandler.setMyturn(true);
			}

		}
		if (this.excommunicationSubmission == this.board.getPlayers().size()) {
			this.board.setPhase(0);
			System.out.println("Excommunication round finished, going for next player phase: " + this.board.getPhase()
					+ " round" + this.board.getRound() + " period" + this.board.getPeriod());
			this.isExcommunicationTime = false;
			nextPlayerPhase();
		}
	}

	private boolean checkExcommunicationFaithPoints(String playerUsername) {
		boolean result = true;
		System.out.println("Entered in Excommunication logic, minimum faith points to avoid excommunication is: "
				+ globalVariables.faithPointExcomRequired[this.board.getPeriod() - 1]);
		if (!(this.matchPlayer.get(playerUsername).getPlayerResource(
				"faithPoint") >= globalVariables.faithPointExcomRequired[this.board.getPeriod() - 1])) {
			result = false;

			System.out.println("Excommunicating player who has "
					+ this.matchPlayer.get(playerUsername).getPlayerResource("faithPoint") + " fatih points");

			this.board.excommunicatePlayer(this.matchPlayer.get(playerUsername));
			this.excommunicationSubmission++;

			// NOTIFYING ALL PLAYER OF EXCOMMUNICATED PLAYER
			this.playersLobby.lobbyMsg("Player " + playerUsername + " has been excommunicated");
			;
		}
		return result;
	}

	private void endGame() {
		if (playerDisconnected < this.board.getPlayers().size()) {
			String winnerIs = this.board.establishWinner();
			this.playersLobby.lobbyMsg("\n\nAND THE WINNER IS " + winnerIs + " with a total amount of "
					+ this.matchPlayer.get(winnerIs).getPlayerResource("victoryPoint") + " victory points!!");
		} else {
			System.out.println("All players are disconnected, end of the game");
		}
		// TODO decide how to terminate the game
	}

	private void changePhases(ClientHandler playerOfTurn) throws RemoteException {
		for (ClientHandler client : this.clientHandlers) {
			if (client.getUsername().equals(playerOfTurn.getUsername())) {
				client.setMyturn(true);
				System.out.println("Player of turn now is " + client.getUsername());

			} else {
				client.setMyturn(false);
				System.out.println("Player not in turn " + client.getUsername());

			}
		}

	}

	// called by submitClientAction
	private boolean submit(Action action) {

		System.out.println("\nAttempting to match player on server to perform action");

		action.setPlayer(this.matchPlayer.get(action.getPlayer().getPlayerName()));
		int actionID = action.getActionID();
		boolean result;

		System.out.println("\nAttempting to perform the action submitted with ID: " + action.getActionID() + "\n"
				+ action.toString());

		switch (actionID) {
		case 1:
			ProductionAction productionAction = (ProductionAction) action;
			ProductionActionPerformerRoutine productionActionImpl = new ProductionActionPerformerRoutine(
					productionAction, this.board);
			result = productionActionImpl.performAction();
			return result;
		case 2:
			HarvestAction harvestAction = (HarvestAction) action;
			HarvestActionPerformerRoutine harvestActionImpl = new HarvestActionPerformerRoutine(harvestAction,
					this.board);
			result = harvestActionImpl.performAction();
			return result;

		case 3:
			CouncilPalaceAction councilPalaceAction = (CouncilPalaceAction) action;
			CouncilPalacePerformerRoutine councilPalaceActionImpl = new CouncilPalacePerformerRoutine(
					councilPalaceAction, this.board);
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
		case 6:
			LeaderCardAction leaderCardAction = (LeaderCardAction) action;
			LeaderCardActionPerformerRoutine leaderActionImpl = new LeaderCardActionPerformerRoutine(leaderCardAction,
					this.board);
			result = leaderActionImpl.performAction();
			return result;

		default:
			return false;
		}

	}

}
