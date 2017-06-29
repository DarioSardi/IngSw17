package it.polimi.ingsw.GC_43.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Remote{
	
	private static final int PORT = 7777;
	private static final int PORTRMI = 7077;
	private static ServerSocket sSocket;
	private static ExecutorService players;
	private int id;
	private ObjectInputStream socketIn;
	private ObjectOutputStream socketOut;
	private HashMap<Integer,ClientHandler> clients;
	private HashMap<Integer,Lobby> lobbies;
	private Registry registry;
	private Integer numberOfClients;


	public Server() throws IOException, AlreadyBoundException {
		this.clients=new HashMap<>();
		this.lobbies=new HashMap<>();
		sSocket= new ServerSocket(PORT);
		//SOCKET
		System.out.println("Socket ready on Port:"+PORT);
		players= Executors.newCachedThreadPool();
		//RMI
		registry = LocateRegistry.createRegistry(PORTRMI);
		System.out.println("RMI registry ready on Port:"+PORTRMI);
		numberOfClients=0;
		startRMI();
		acceptConnectionsSocket();
		
		
	}



	private void startRMI() throws RemoteException, AlreadyBoundException {
		LoginInterface login= new RmiConnector(this);
		LoginInterface remoteLogin=(LoginInterface) UnicastRemoteObject.exportObject(login,PORTRMI);
		registry.bind("COF", remoteLogin);
		System.out.println("Waiting for players on RMI...");
	}



	private Runnable acceptConnectionsSocket() throws IOException {
		
		boolean online=true;
		System.out.println("Waiting for connection on socket...");
		while(online){
			Socket cSocket= sSocket.accept();
			System.out.println("connessione accettata! indirizzo Giocatore: "+cSocket.getInetAddress());
			ClientHandlerSocket ch=new ClientHandlerSocket(this.numberOfClients,this);
			ch.setSocket(cSocket);
			players.submit(ch);
			System.out.println("giocatore assegnato all'handler con ID: "+this.numberOfClients);
			addClient(ch);
			
		}
		return null;
	}
	
	public Integer getNumberOfClients(){
		return this.numberOfClients;
	}
	
	public Integer addClient(ClientHandler cH){
		Integer thisID=this.numberOfClients;
		if(this.clients.containsKey(cH)){
			System.out.println("esiste gia lo stesso clientHandler");
		}
		clients.put(this.numberOfClients,cH);
		this.numberOfClients++;
		return thisID;
	}
	
	public ClientHandler getClient(int key){
		return clients.get(key);
	}

	public boolean newLobby(ClientHandler clientHandler,Integer lobbyNumber,Integer maxPlayers,Boolean advancedRules) {
		System.out.println("provo a creare la lobby numero "+lobbyNumber);
		if(!(this.lobbies.containsKey(lobbyNumber))){
			try {
				this.lobbies.put(lobbyNumber,new Lobby(clientHandler,lobbyNumber,maxPlayers,advancedRules));
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			System.out.println("added lobby number "+this.lobbies.size());	
			return true;
		}
		else{
			System.out.println("nop,ho fallito!");
			return false;}
	}
	
	public void removeLobby(Lobby closedLobby){
		this.lobbies.remove(closedLobby);
	}
	
	public boolean joinLobby(Integer lobbyNumber,ClientHandler cH) throws IOException{
		if (this.lobbies.containsKey(lobbyNumber)) {
			if (this.lobbies.get(lobbyNumber).addPlayer(cH)==1){
				return true;
			}
			else if(this.lobbies.get(lobbyNumber).addPlayer(cH)==2){
				cH.sendMsgTo("ENTER THE PASSWORD");
				String password = null;
				if (cH instanceof ClientHandlerSocket) {
					Object o = cH.readPassword();
					if (o instanceof SimpleMessage) {
						SimpleMessage sm = (SimpleMessage) o;
						password = sm.getMsg();
					}
					else{
						cH.sendMsgTo("error on pass validation");
						return false;
					}
				}
				else if(cH instanceof ClientHandlerRmi){
					password=cH.readPassword();
				}
				if(this.lobbies.get(lobbyNumber).reconnectPlayer(cH,password)){
					cH.setGame(true);
					return true;
					
				}
				else{
					cH.sendMsgTo("autentication failed!");
					return false;
				}
			}
			else return false;
		}
		return false;
	}



	public String getLobbiesToString() {
		StringBuilder sb= new StringBuilder();
		if (this.lobbies.size()!=0) {
			this.lobbies.entrySet().stream().forEach(lobby -> sb.append(lobby.toString()));
			System.out.println(sb.toString());
			return sb.toString();
		}
		else{
			return "there are no lobbies on this server!";
		}
	}
	
	//MAIN
	public static void main(String[] args) throws IOException, AlreadyBoundException { 

			@SuppressWarnings("unused")
			Server s=new Server();
		
		
		
	}

}

