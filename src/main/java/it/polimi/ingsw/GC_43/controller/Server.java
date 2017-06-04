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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	private static final int PORT = 7777;
	private static ServerSocket sSocket;
	private static ExecutorService players;
	private int id;
	private ObjectInputStream socketIn;
	private ObjectOutputStream socketOut;


	public Server() throws IOException {
		sSocket= new ServerSocket(PORT);
		System.out.println("Socket ready on Port:"+PORT);
		players= Executors.newCachedThreadPool();
		acceptConnections();
		closeServer();
	}


	private void closeServer() throws IOException {
		players.shutdown();
		sSocket.close();
		
	}


	private void acceptConnections() throws IOException {
		while(true){
			Socket cSocket= sSocket.accept();
			System.out.println("connessione accettata! indirizzo Giocatore: "+cSocket.getInetAddress());
			players.submit(new ClientHandler(cSocket));
		}
	}

	public static void main(String[] args) throws IOException { 
		Server s=new Server();
	}
	
}

