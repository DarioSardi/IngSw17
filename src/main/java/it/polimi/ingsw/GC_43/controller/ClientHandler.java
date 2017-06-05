package it.polimi.ingsw.GC_43.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable{
	private Socket socket;
	private int ID;
	private Server myServer;
	private String username;

	public ClientHandler(Socket socket,int ID,Server myServer) {
		super();
		this.socket = socket;
		this.ID=ID;
		this.myServer=myServer;
	}

	@Override
	public void run() {
		try {
			boolean exit = false;
			
			//SETUP
			System.out.println("ClientHandler starting up!");
			Scanner socketIn = new Scanner(socket.getInputStream());
			PrintWriter socketOut = new PrintWriter(socket.getOutputStream());
			this.username=socketIn.nextLine();
			System.out.println("username of the player is "+username);
			socketOut.println(this.ID);
			socketOut.flush();
			
			//MENU
			while (!exit) {
				socketOut.flush();
				socketOut.println("sei nel menù,scegli!");
				socketOut.flush();
				//NEW LOBBY
				String choice=socketIn.nextLine();
				if (choice.equals("1")) {
					socketOut.println("seleziona un numero di lobby");
					socketOut.flush();
					Integer lobbyNumber = Integer.parseInt(socketIn.nextLine());
					if (myServer.newLobby(this, lobbyNumber)) {
						socketOut.println("sei nella lobby");
						socketOut.flush();
						boolean inlobby=true;
						while(inlobby){
							if(socketIn.nextLine().equals("exit_lobby")){
								//rimuovi lobby
								inlobby=false;
								socketOut.println("stai uscendo dalla lobby");
								socketOut.flush();
							}
						}
					} else {
						socketOut.println("lobby number unaviable");
						socketOut.flush();
					}
				
				//ENTER LOBBY
				} else if (choice.equals("2")) {
					socketOut.println("seleziona una lobby");
					socketOut.flush();
					socketOut.println(myServer.getLobbiesToString());
					socketOut.flush();
					Integer lobbyNumber=Integer.parseInt(socketIn.nextLine());
					myServer.joinLobby(lobbyNumber, this);
				
				//QUIT
				} else if (choice.equals("3")) {
					exit = true;
				}
				
				//FKING MORON
				else{
					socketOut.println("invalid choice");
					socketOut.flush();
				}
			}
			
			//CLOSE CLIENT
			socketOut.close();
			socketIn.close();
			socket.close();
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return username;
	}
}
