package it.polimi.ingsw.GC_43.controller;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LoginInterface extends Remote{
	public ClientaHandlerRmInterface login(String username) throws RemoteException;
}
