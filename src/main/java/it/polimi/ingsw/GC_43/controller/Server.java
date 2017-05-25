package it.polimi.ingsw.GC_43.controller;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

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
		int clientNumber=0;
		int maxNumber=4;
		int port = 7777;
		ServerSocket sSocket = new ServerSocket(port);
		while(clientNumber<=maxNumber){
			System.out.println("hi! I'm at port: "+port);
			Socket csocket= sSocket.accept();
			System.out.println("accepted client at socket: "+ csocket.toString());
			clientNumber++;
			new Thread(new Server(csocket)).start();
		}
	}

	
}

