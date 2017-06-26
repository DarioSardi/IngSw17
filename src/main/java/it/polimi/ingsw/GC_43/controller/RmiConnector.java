package it.polimi.ingsw.GC_43.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RmiConnector  implements LoginInterface{

	private volatile Server myServer;
	protected RmiConnector(Server server) throws RemoteException {
		this.myServer=server;
	}

	@Override
	public ClientaHandlerRmInterface login(String username) throws RemoteException {
		return new ClientHandlerRmi(this);
				
	}

	public Server getServer() {
		return this.myServer;
	}

}
