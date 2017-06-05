package it.polimi.ingsw.GC_43.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable{
	private Socket socket;
	private int ID;
	private Server myServer;
	private String username;
	private Scanner socketIn;
	private PrintWriter socketOut;
	private Lobby lobby;

	public ClientHandler(Socket socket,int ID,Server myServer) throws IOException {
		super();
		this.socket = socket;
		this.ID=ID;
		this.myServer=myServer;
		this.socketIn = new Scanner(this.socket.getInputStream());
		this.socketOut = new PrintWriter(this.socket.getOutputStream());
	}
	
	public Lobby getLobby() {
		return lobby;
	}



	public void setLobby(Lobby lobby) {
		this.lobby = lobby;
	}



	public String getUsername() {
		return username;
	}

	@Override
	public void run() {
		try {
			boolean exit = false;
			
			//SETUP
			System.out.println("ClientHandler starting up!");
			this.username=socketIn.nextLine();
			System.out.println("username of the player is "+username);
			socketOut.println(this.ID);
			socketOut.flush();
			
			//MENU
			while (!exit) {
				sendMsgTo("sei nel menù,scegli!");
				
				//NEW LOBBY
				String choice=socketIn.nextLine();
				if (choice.equals("1")) {
					sendMsgTo("seleziona un numero di lobby");
					Integer lobbyNumber = Integer.parseInt(socketIn.nextLine());
					if (myServer.newLobby(this, lobbyNumber)) {
						sendMsgTo("sei nella lobby");
						inLobby();
					} else {
						sendMsgTo("lobby number unaviable");
					}
				
				//ENTER LOBBY
				} else if (choice.equals("2")) {
					sendMsgTo("seleziona una lobby");
					sendMsgTo(myServer.getLobbiesToString());
					Integer lobbyNumber=Integer.parseInt(socketIn.nextLine());
					if(myServer.joinLobby(lobbyNumber, this)){
					inLobby();
					}
					else{sendMsgTo("unable to join lobby");}
					
				
				//QUIT
				} else if (choice.equals("3")) {
					exit = true;
				}
				
				//FKING MORON
				else{
					sendMsgTo("invalid choice");
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
	
	private void inLobby() {
		sendMsgTo("sei entrato nella lobby");
		boolean inlobby=true;
		while(inlobby){
			String command=socketIn.nextLine();
			if(command.equals("exit_lobby")){
				//DARIO rimuovi lobby
				inlobby=false;
				sendMsgTo("stai uscendo dalla lobby");
			}
			else if(command.equals("chat")){
				sendMsgTo("digita il messaggio da mandare a tutti gli altri");
				String msg=socketIn.nextLine();
				lobby.broadcastMsg(msg,this);
			}
			else if(command.equals("start_game")){
				if(lobby.startGame(this)){
					inGame();
				}
				else{
					sendMsgTo("you are not the admin...");
					continue;
				}
			}
			else{
				sendMsgTo("nulla di che...");
				continue;}
		}
		
	}

	private void inGame() {
		// TODO Auto-generated method stub
		
	}

	public void sendMsgTo(String string){
		socketOut.println(string);
		socketOut.flush();
	}
	
	@Override
	public String toString() {
		return username;
	}
}
