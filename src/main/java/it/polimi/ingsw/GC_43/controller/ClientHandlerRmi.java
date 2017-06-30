package it.polimi.ingsw.GC_43.controller;

import java.rmi.RemoteException;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.CopyOfGlobalVariables;
import it.polimi.ingsw.GC_43.model.actions.Action;
import it.polimi.ingsw.GC_43.view.UserRmiInterface;

public class ClientHandlerRmi implements ClientHandler{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7710077653703111598L;
	private Server myServer;
	private String username;
	private Lobby lobby;
	private Integer id;
	private UserRmiInterface client;
	
	protected ClientHandlerRmi(Server myServer) throws RemoteException {
		this.myServer=myServer;
	}

	public boolean tryToCreateLobby(Integer lobbyNumber,Integer maxPlayers,Boolean advancedMode){
		return myServer.newLobby(this, lobbyNumber,maxPlayers,advancedMode);
	}
	
	public boolean tryToJoinLobby(Integer lobbyNumber) throws RemoteException{
		return myServer.joinLobby(lobbyNumber, this);
	}
	
	@Override
	public void setLobby(Lobby lobby) {
		this.lobby=lobby;
	}

	@Override
	public void sendMsgTo(String string) throws RemoteException {
		SimpleMessage s=new SimpleMessage(string); 
		readMsg(s);
	}


	@Override
	public void changeName(String username) throws RemoteException {
		this.username = username;
		this.client.changeUsername(username);
	}

	@Override
	public void sendObject(Object o) throws RemoteException {
		if(o instanceof Board){
			this.client.updateBoard((Board)o);
		}
		else if(o instanceof SimpleMessage){
			 readMsg((SimpleMessage) o);
		}
		else if(o instanceof CopyOfGlobalVariables){
			this.client.updateGlobalVariables((CopyOfGlobalVariables)o);
		}
		else if(o instanceof DefaultBonusChoiceMessage){
			this.client.defaultBonusChoice((DefaultBonusChoiceMessage)o);
		}
		else if(o instanceof LeaderCardChoiceMessage){
			this.client.leaderDraftChoice((LeaderCardChoiceMessage)o);
		}
		
		
	}

	@Override
	public void setGame(boolean b) throws RemoteException {
		this.client.setInGame(b);
		
	}
	
	public void setId(Integer id) throws RemoteException{
		this.id=id;
	}

	@Override
	public Lobby getLobby() {
		return this.lobby;
	}

	@Override
	public void setMyturn(boolean b) throws RemoteException {
		this.client.setmyTurn(b);
		
	}

	
	public void readMsg(SimpleMessage s) throws RemoteException{
		String line=s.getMsg();
		if("system_ingame_switch".equals(line)){
				this.client.setInGame(true);
			}
		else if("system_outgame_switch".equals(line)){
			this.client.setInGame(false);
		}
		
		else if("now_is_my_turn".equals(line)){
			this.client.setmyTurn(true);
			}
		else if("end_of_my_turn".equals(line)){
			this.client.setmyTurn(false);
			}
		else if("excommunication_round".equals(line)){
			System.out.println("\n\n\nEXCOMMUNICATION ROUND PRESS ENTER OR FINISH THE MENU QUERY TO ANSWER THE CHOICE, YOU HAVE 2 MINUTES!\n\n");
			this.client.excomunicationRound();
		}
		else if("Paction_performed".equals(line)){
			this.client.setActionPerformed(true);
		}
		
		else{
			this.client.showMsg(line);
		}
	}



	public void setUsername(String username) throws RemoteException {
		this.username=username;
	}
	
	
	public String toString() {
		return this.username;
	}



	
	public String getUsername() throws RemoteException {
		return this.username;
	}



	
	



	
	public void chatMessage(String msg) {
		this.lobby.broadcastMsg(msg, this);
		
	}



	
	public boolean startGame() throws RemoteException {
		return this.lobby.startGame(this);
	}



	
	public String whoIsInLobby() throws RemoteException {
		return this.lobby.whoIsIn();
	}



	
	public void quitGame(String password) throws RemoteException {
		this.lobby.disconnectPlayer(this, password);
		this.lobby=null;
		
	}

	
	public void submitAction(Action action) throws RemoteException {
			this.lobby.getController().submitClientAction(action);
	}



	public boolean joinLobby(Integer lobbyNumber) throws RemoteException {
		return this.myServer.joinLobby(lobbyNumber,this);
	}



	
	public void connect(UserRmiInterface rmiView) throws RemoteException {
		this.client= rmiView;
	}



	
	public String readPassword() throws RemoteException {
		return this.client.insertPassword();
	}



	
	public void ping() throws RemoteException {
		this.client.showMsg("PONG!");
		
	}



	
	public int getID() throws RemoteException {
		return this.id;
	}

	public void leaveLobby() {
		this.lobby.removePlayer(this);
		
	}
	



	
}
