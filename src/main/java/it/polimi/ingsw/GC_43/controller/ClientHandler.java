package it.polimi.ingsw.GC_43.controller;

import java.io.Serializable;
import java.rmi.RemoteException;

public interface ClientHandler extends Serializable{

	public  void setLobby(Lobby lobby)  throws RemoteException;

	public void sendMsgTo(String string) throws RemoteException;

	public void changeName(String string) throws RemoteException;

	public String getUsername() throws RemoteException;
	
	public void sendObject(Object o) throws RemoteException;

	public void setGame(boolean b) throws RemoteException;
	
	public Lobby getLobby() throws RemoteException;

	public void setMyturn(boolean b) throws RemoteException;

	public String readPassword() throws RemoteException;
	
	
}
