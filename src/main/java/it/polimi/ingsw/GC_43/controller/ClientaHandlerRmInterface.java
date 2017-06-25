package it.polimi.ingsw.GC_43.controller;

import java.rmi.Remote;
import java.rmi.RemoteException;

import it.polimi.ingsw.GC_43.model.actions.Action;
import it.polimi.ingsw.GC_43.view.UserRmiInterface;

public interface ClientaHandlerRmInterface  extends ClientHandler,Remote{

	public String mainMenuChoicesPrint() throws RemoteException;
	public String printLobbyes() throws RemoteException;
	public boolean tryToCreateLobby(Integer lobbyNumber,Integer maxPlayers) throws RemoteException;
	public void setUsername(String username) throws RemoteException;
	public String helpMsgLobby() throws RemoteException;
	public void chatMessage(String msg) throws RemoteException;
	public boolean startGame()throws RemoteException;
	public String whoIsInLobby()throws RemoteException;
	public void quitGame(String password) throws RemoteException;
	void submitAction(Action action) throws RemoteException;
	public boolean joinLobby(Integer lobbyNumber) throws RemoteException;
	public void sendMsgTo(String s) throws RemoteException;
	public void connect(UserRmiInterface rmiView) throws RemoteException;
	public void ping() throws RemoteException;
	public int getID() throws RemoteException;
	
}
