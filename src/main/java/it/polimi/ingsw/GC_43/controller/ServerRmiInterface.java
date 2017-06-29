package it.polimi.ingsw.GC_43.controller;

import java.rmi.RemoteException;
import java.util.HashMap;

import it.polimi.ingsw.GC_43.model.actions.Action;
import it.polimi.ingsw.GC_43.view.UserRmiInterface;

public class ServerRmiInterface implements ClientaHandlerRmInterface{
	
	private HashMap<Integer, ClientHandlerRmi> clientHandlers;
	private Server myServer;
	
	

	public ServerRmiInterface(Server myServer) {
		this.clientHandlers=new HashMap<>();
		this.myServer = myServer;
	}

	@Override
	public String mainMenuChoicesPrint() throws RemoteException {
		return  "MAIN MENU:\n"
				+ "1.Create new lobby!\n"
				+ "2.Join a lobby\n"
				+ "3.Exit the game\n";
		
	}

	@Override
	public String printLobbyes(){
		return myServer.getLobbiesToString();
	}

	@Override
	public boolean tryToCreateLobby(Integer ID, Integer lobbyNumber, Integer maxPlayers,Boolean advancedMode) throws RemoteException {
		return this.clientHandlers.get(ID).tryToCreateLobby(lobbyNumber, maxPlayers,advancedMode);
	}

	@Override
	public void setUsername(Integer ID, String username) throws RemoteException {
		this.clientHandlers.get(ID).changeName(username);
		
	}

	@Override
	public String helpMsgLobby() throws RemoteException {
		return "\nCOMANDI LOBBY:\n"
				+ "chat to chat with the other inLobby players\n" + "exit_lobby to quit the current lobby\n"
				+ "start_game to start the game if you are the admin\n" + "help to see this\n"
				+ "players if you want to see who is in the lobby\n";
		
	}

	@Override
	public void chatMessage(Integer ID, String msg) throws RemoteException {
		this.clientHandlers.get(ID).chatMessage(msg);
		
	}

	@Override
	public boolean startGame(Integer ID) throws RemoteException {
		return this.clientHandlers.get(ID).startGame();
	}

	@Override
	public String whoIsInLobby(Integer ID) throws RemoteException {
		return this.clientHandlers.get(ID).whoIsInLobby();
	}

	@Override
	public void quitGame(Integer ID, String password) throws RemoteException {
		this.clientHandlers.get(ID).quitGame(password);
	}

	@Override
	public void submitAction(Integer ID, Action action) throws RemoteException {
		this.clientHandlers.get(ID).submitAction(action);
		
	}

	@Override
	public boolean joinLobby(Integer ID, Integer lobbyNumber) throws RemoteException {
		return this.clientHandlers.get(ID).joinLobby(lobbyNumber);
	}

	@Override
	public void sendMsgTo(Integer ID, String s) throws RemoteException {
		this.clientHandlers.get(ID).sendMsgTo(s);
		
	}

	@Override
	public Integer connect(UserRmiInterface rmiView) throws RemoteException {
		ClientHandlerRmi cH=new ClientHandlerRmi(this.myServer);
		Integer ID=this.myServer.addClient(cH);
		cH.setId(ID);
		cH.setUsername(rmiView.getUsername());
		System.out.println("player "+cH.getUsername()+"added to server with ID "+ID);
		this.clientHandlers.put(ID,cH);
		this.clientHandlers.get(ID).connect(rmiView);
		return ID;
	}

	@Override
	public void ping(Integer ID) throws RemoteException {
		this.clientHandlers.get(ID).ping();
		
	}

	@Override
	public void exitLobby(int id) throws RemoteException {
		this.clientHandlers.get(id).leaveLobby();
		
	}

}
