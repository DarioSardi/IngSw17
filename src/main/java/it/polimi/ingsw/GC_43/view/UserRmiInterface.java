package it.polimi.ingsw.GC_43.view;

import java.rmi.Remote;
import java.rmi.RemoteException;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.CopyOfGlobalVariables;

public interface UserRmiInterface extends Remote{

	
	public void showMsg(String s) throws RemoteException; 
	public void changeUsername(String s) throws RemoteException; 
	public void updateBoard(Board b) throws RemoteException; 
	public String insertPassword() throws RemoteException;
	public void setInGame(Boolean inGame) throws RemoteException;
	public void setmyTurn(Boolean inGame) throws RemoteException;
	public void excomunicationRound() throws RemoteException;
	public void updateGlobalVariables(CopyOfGlobalVariables o) throws RemoteException;
	public String getUsername() throws RemoteException;
	public Integer getID()throws RemoteException;
	public void setActionPerformed(boolean b)throws RemoteException;
	
}
