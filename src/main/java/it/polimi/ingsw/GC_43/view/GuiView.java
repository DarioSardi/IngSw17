package it.polimi.ingsw.GC_43.view;

import java.io.Serializable;
import java.rmi.RemoteException;

import it.polimi.ingsw.GC_43.controller.ClientaHandlerRmInterface;
import it.polimi.ingsw.GC_43.controller.DefaultBonusChoiceMessage;
import it.polimi.ingsw.GC_43.controller.LeaderCardChoiceMessage;
import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.CopyOfGlobalVariables;

public class GuiView  implements Serializable,UserRmiInterface,Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1887417908557856229L;
	private Client myClient;
	private ClientaHandlerRmInterface handler;
	private int ID;

	public GuiView(Client client, ClientaHandlerRmInterface handler) {
		this.myClient=client;
		this.handler=handler;
		this.ID=myClient.getID();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showMsg(String s) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeUsername(String s) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBoard(Board b) throws RemoteException {
		// TODO Auto-generated method stub	
	}

	@Override
	public String insertPassword() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInGame(Boolean inGame) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setmyTurn(Boolean inGame) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excomunicationRound() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateGlobalVariables(CopyOfGlobalVariables o) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getUsername() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getID() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setActionPerformed(boolean b) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void defaultBonusChoice(DefaultBonusChoiceMessage o) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void leaderDraftChoice(LeaderCardChoiceMessage o) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setInAdvSetupPhase(boolean b) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
	public boolean createLobby(Integer lobbyNumber,Integer maxPlayers) throws RemoteException{
		return this.handler.tryToCreateLobby(ID, lobbyNumber, maxPlayers, false);
	}

}
