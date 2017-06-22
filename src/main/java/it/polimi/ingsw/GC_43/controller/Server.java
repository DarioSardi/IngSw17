package it.polimi.ingsw.GC_43.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketImpl;
import java.nio.channels.ShutdownChannelGroupException;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	private static final int PORT = 7777;
	private static ServerSocket sSocket;
	private static ExecutorService players;
	private int id;
	private ObjectInputStream socketIn;
	private ObjectOutputStream socketOut;
	private HashMap<Integer,Socket> clients;
	private HashMap<Integer,Lobby> lobbies;


	public Server() throws IOException {
		this.clients=new HashMap<>();
		this.lobbies=new HashMap<>();
		sSocket= new ServerSocket(PORT);
		System.out.println("Socket ready on Port:"+PORT);
		players= Executors.newCachedThreadPool();
		acceptConnectionsSocket();
		
	}



	private void closeServer() throws IOException {
		players.shutdown();
		sSocket.close();
		
	}


	private Runnable acceptConnectionsSocket() throws IOException {
		int numberOfClients=0;
		boolean online=true;
		while(online){
			Socket cSocket= sSocket.accept();
			System.out.println("connessione accettata! indirizzo Giocatore: "+cSocket.getInetAddress());
			ClientHandler ch=new ClientHandler(numberOfClients,this,false);
			ch.setSocket(cSocket);
			players.submit(ch);
			System.out.println("giocatore assegnato all'handler con ID: "+numberOfClients);
			clients.put(numberOfClients,cSocket);
			numberOfClients++;
			
		}
		return null;
	}
	
	private Socket getClient(int key){
		return clients.get(key);
	}

	public boolean newLobby(ClientHandler clientHandler,Integer lobbyNumber,Integer maxPlayers) {
		System.out.println("provo a creare la lobby numero "+lobbyNumber);
		if(!(this.lobbies.containsKey(lobbyNumber))){
			this.lobbies.put(lobbyNumber,new Lobby(clientHandler,lobbyNumber,maxPlayers));
			//lobbies.get(lobbyNumber).run();
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
	
	public boolean joinLobby(Integer lobbyNumber,ClientHandler cH){
		if (this.lobbies.containsKey(lobbyNumber)) {
			if (this.lobbies.get(lobbyNumber).addPlayer(cH)==1){
				return true;
			}
			else if(this.lobbies.get(lobbyNumber).addPlayer(cH)==2){
				cH.sendMsgTo("ENTER THE PASSWORD");
				Object o = cH.readObject();
				if (o instanceof SimpleMessage){
					SimpleMessage sm=(SimpleMessage) o;
					if(this.lobbies.get(lobbyNumber).reconnectPlayer(cH,sm.getMsg())){
						cH.setGame(true);
						return true;
					}
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
	public static void main(String[] args) throws IOException { 
		Server s=new Server();
	}

}

