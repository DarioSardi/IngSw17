package it.polimi.ingsw.GC_43.controller;

import java.rmi.RemoteException;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.actions.Action;

public class ClientHandlerRmi implements ClientaHandlerRmInterface{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7710077653703111598L;
	private int ID;
	private Server myServer;
	private String username;
	private Lobby lobby;
	private boolean Game;
	private boolean myturn;
	private boolean inLobby;
	private boolean exit;
	
	protected ClientHandlerRmi(Server myServer) throws RemoteException {
		this.myServer=myServer;
	}

	
	
	public String printLobbyes(){
		return myServer.getLobbiesToString();
	}
	
	public boolean tryToCreateLobby(Integer lobbyNumber,Integer maxPlayers){
		return myServer.newLobby(this, lobbyNumber,maxPlayers);
	}
	
	public boolean tryToJoinLobby(Integer lobbyNumber) throws RemoteException{
		return myServer.joinLobby(lobbyNumber, this);
	}
	
	@Override
	public void setLobby(Lobby lobby) {
		this.lobby=lobby;
	}

	@Override
	public void sendMsgTo(String string) {
		// TODO Auto-generated method stub
	}


	@Override
	public void changeName(String username) {
		this.username = username;
		//set username on client side
	}

	@Override
	public void sendObject(Object o) {
		if(o instanceof Board){
			//update board on client side
		}
		if(o instanceof SimpleMessage){
			 readMsg((SimpleMessage) o);
		}
		
		
	}

	@Override
	public void setGame(boolean b) {
		this.Game=b;
		
	}

	@Override
	public Lobby getLobby() {
		return this.lobby;
	}

	@Override
	public void setMyturn(boolean b) {
		this.myturn=b;
		
	}

	@Override
	public String readPassword() {
		// lancia routine di password su client e ritorna il suo string
		return null;
	}
	
	public void readMsg(SimpleMessage s){
		String line=s.getMsg();
		if("system_ingame_switch".equals(line)){
				//vedremo quanto servono
			}
		if("system_outgame_switch".equals(line)){
			//vedremo quanto servono
		}
		
		else if("now_is_my_turn".equals(line)){
			//setTurn sul client
			}
		else if("end_of_my_turn".equals(line)){
			//setTurn sul client
			}
		else if("excommunication_round".equals(line)){
			System.out.println("\n\n\nEXCOMMUNICATION ROUND PRESS ENTER OR FINISH THE MENU QUERY TO ANSWER THE CHOICE, YOU HAVE 2 MINUTES!\n\n");
			// myClient.excommunicationRound=true;
		}
		
		else{
			System.out.println(line);
		}
	}





	@Override
	public String mainMenuChoicesPrint() throws RemoteException {
		return "\n"
				+ "MAIN MENU:\n"
				+ "1.Create new lobby!\n"
				+ "2.Join a lobby\n"
				+ "3.Exit the game\n";
		
	}



	@Override
	public void setUsername(String username) throws RemoteException {
		this.username=username;
	}
	
	@Override
	public String toString() {
		return this.username;
	}



	@Override
	public String getUsername() throws RemoteException {
		return this.username;
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
		this.inLobby=false;
		this.Game=false;
		
	}

	@Override
	public void submitAction(Action action) throws RemoteException {
			this.lobby.getController().submitClientAction(action);
	}



	@Override
	public boolean joinLobby(Integer lobbyNumber) throws RemoteException {
		return this.myServer.joinLobby(lobbyNumber,this);
	}
	



	
}
