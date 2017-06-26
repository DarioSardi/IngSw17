package it.polimi.ingsw.GC_43.controller;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.CopyOfGlobalVariables;
import it.polimi.ingsw.GC_43.model.actions.Action;
import it.polimi.ingsw.GC_43.view.UserRmiInterface;

public class ClientHandlerRmi  implements ClientaHandlerRmInterface{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7710077653703111598L;
	private volatile Server myServer;
	private Lobby lobby;
	private Integer id;
	private UserRmiInterface client;
	
	protected ClientHandlerRmi(RmiConnector rmiConnector) throws RemoteException {
		this.myServer=rmiConnector.getServer();
	}

	
	
	public String printLobbyes(){
		return myServer.getLobbiesToString();
	}
	
	public boolean tryToCreateLobby(Integer lobbyNumber,Integer maxPlayers){
		return myServer.newLobby(this, lobbyNumber,maxPlayers);
	}
	
	public boolean tryToJoinLobby(Integer lobbyNumber) throws IOException{
		return myServer.joinLobby(lobbyNumber, this);
	}
	
	@Override
	public void setLobby(Lobby lobby) {
		this.lobby=lobby;
	}

	@Override
	public void sendMsgTo(String string) throws RemoteException {
		this.client.showMsg(string);
	}


	@Override
	public void changeName() throws IOException {
		this.client.chooseNewUsername();
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
		
		
	}

	@Override
	public void setGame(boolean b) throws RemoteException {
		this.client.setInGame(b);
		
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
		if("system_outgame_switch".equals(line)){
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
		
		else{
			System.out.println(line);
		}
	}





	@Override
	public String mainMenuChoicesPrint() throws RemoteException {
		return  "MAIN MENU:\n"
				+ "1.Create new lobby!\n"
				+ "2.Join a lobby\n"
				+ "3.Exit the game\n";
		
	}



	@Override
	public void setUsername(String username) throws RemoteException {
		this.client.changeUsername(username);
	}
	
	@Override
	public String toString() {
		try {
			return this.client.getUsername();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}



	@Override
	public String getUsername() throws RemoteException {
		return this.client.getUsername();
	}



	@Override
	public String helpMsgLobby() throws RemoteException {
		return "\nCOMANDI LOBBY:\n"
				+ "chat to chat with the other inLobby players\n" + "exit_lobby to quit the current lobby\n"
				+ "start_game to start the game if you are the admin\n" + "help to see this\n"
				+ "players if you want to see who is in the lobby\n";
		
	}



	@Override
	public void chatMessage(String msg) {
		this.lobby.broadcastMsg(msg, this);
		
	}



	@Override
	public boolean startGame() throws RemoteException {
		return this.lobby.startGame(this);
	}



	@Override
	public String whoIsInLobby() throws RemoteException {
		return this.lobby.whoIsIn();
	}



	@Override
	public void quitGame(String password) throws RemoteException {
		this.lobby.disconnectPlayer(this, password);
		this.lobby=null;
		
	}

	@Override
	public void submitAction(Action action) throws RemoteException {
			this.lobby.getController().submitClientAction(action);
	}



	@Override
	public boolean joinLobby(Integer lobbyNumber) throws IOException {
		return this.myServer.joinLobby(lobbyNumber,this);
	}



	@Override
	public void connect(UserRmiInterface rmiView) throws RemoteException {
		this.client= rmiView;
		this.id=this.myServer.addClient(this);
		this.client.setId(this.id);
		System.out.println("connected player with username: "+this.client.getUsername()+" and ID :"+String.valueOf(this.client.getID()));
	}



	@Override
	public String readPassword() throws RemoteException {
		return this.client.insertPassword();
	}



	@Override
	public void ping() throws RemoteException {
		this.client.showMsg("PONG! you are in lobby "+this.getLobby().toString());
		
	}



	


	@Override
	public void exitLobby() throws RemoteException {
		this.lobby=null;
		this.client.exitLobby();
		
	}
	



	
}
