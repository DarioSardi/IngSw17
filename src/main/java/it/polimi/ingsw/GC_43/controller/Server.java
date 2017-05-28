package it.polimi.ingsw.GC_43.controller;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ShutdownChannelGroupException;
import java.sql.ClientInfoStatus;

public class Server implements Runnable{
	Socket cSocket;


	public Server(Socket csocket) {
		this.cSocket = csocket;
	}

	@Override
	public void run() {
		try {
			PrintStream pstream = new PrintStream(cSocket.getOutputStream());
			pstream.println("hi Dario,i'm the server");
			pstream.close();
			cSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		boolean serverOnline=true;
		int port = 7777;
		ServerSocket sSocket = new ServerSocket(port);
		while(serverOnline){
															System.out.println("hi! I'm at port: "+port);
			Socket csocket= sSocket.accept();
															System.out.println("accepted client at socket: "+ csocket.toString());
			new Thread(new Server(csocket)).start();
		}
		 try {
			sSocket.close(); 
			 												System.out.println("Server Stopped");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	
}

