package it.polimi.ingsw.GC_43.controller;

import java.rmi.Remote;
import java.rmi.RemoteException;

import it.polimi.ingsw.GC_43.model.actions.Action;
import it.polimi.ingsw.GC_43.view.UserRmiInterface;

public interface ClientaHandlerRmInterface  extends Remote{

	public String mainMenuChoicesPrint() throws RemoteException;
	public String printLobbyes() throws RemoteException;
	public boolean tryToCreateLobby(Integer ID,Integer lobbyNumber,Integer maxPlayers,Boolean advancedMode) throws RemoteException;
	public void setUsername(Integer ID,String username) throws RemoteException;
	public String helpMsgLobby() throws RemoteException;
	public void chatMessage(Integer ID,String msg) throws RemoteException;
	public boolean startGame(Integer ID)throws RemoteException;
	public String whoIsInLobby(Integer ID)throws RemoteException;
	public void quitGame(Integer ID,String password) throws RemoteException;
	void submitAction(Integer ID,Action action) throws RemoteException;
	public boolean joinLobby(Integer ID,Integer lobbyNumber) throws RemoteException;
	public void sendMsgTo(Integer ID,String s) throws RemoteException;
	public Integer connect(UserRmiInterface rmiView) throws RemoteException;
	public void ping(Integer ID) throws RemoteException;
	public void exitLobby(int id) throws RemoteException;
	public void submitDefaultBonus(int id, DefaultBonusChoiceMessage o) throws RemoteException;
	public void submitLeaderCardChoice(int id, LeaderCardChoiceMessage o)throws RemoteException;
	public void submitEexommChoice(int id,Boolean answer)throws RemoteException;
	
}
