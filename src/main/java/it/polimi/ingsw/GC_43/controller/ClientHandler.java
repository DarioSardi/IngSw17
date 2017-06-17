package it.polimi.ingsw.GC_43.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable{
	private Socket socket;
	private int ID;
	private Server myServer;
	private String username;
	private ObjectInputStream socketIn;
	private ObjectOutputStream socketOut;
	private Lobby lobby;
	private boolean Game;

	public ClientHandler(Socket socket,int ID,Server myServer) throws IOException {
		super();
		this.socket = socket;
		this.ID=ID;
		this.myServer=myServer;
		this.socketIn = new ObjectInputStream(this.socket.getInputStream());
		this.socketOut = new ObjectOutputStream(this.socket.getOutputStream());
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
			this.username=readMsg();
			System.out.println("username of the player is "+username);
			sendMsgTo(String.valueOf(this.ID));
			socketOut.flush();
			
			//MENU
			while (!exit) {
				sendMsgTo("\n"
						+ "Menu di gioco\n"
						+ "1.crea nuova lobby\n"
						+ "2.unisciti alla lobby\n"
						+ "3.esci dal gioco\n");
				
				//NEW LOBBY
				String choice=readMsg();
				if (choice.equals("1")) {
					sendMsgTo("seleziona un numero di lobby");
					Integer lobbyNumber = Integer.parseInt(readMsg());
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
					Integer lobbyNumber=Integer.parseInt(readMsg());
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
	
	//IN LOBBY
	private void inLobby() {
		sendMsgTo("sei entrato nella lobby, digita 'help' per la lista dei comandi");
		boolean inlobby=true;
		Game=false;
		while(inlobby){
			if (!Game) {
				String command = readMsg();
				if (command.equals("exit_lobby")) {
					//DARIO rimuovi lobby
					inlobby = false;
					sendMsgTo("stai uscendo dalla lobby");
				} else if (command.equals("chat")) {
					sendMsgTo("digita il messaggio da mandare a tutti gli altri");
					String msg = readMsg();
					lobby.broadcastMsg(msg, this);
				} else if (command.equals("start_game")) {
					if (lobby.startGame(this)) {
						inGame();
					} else {
						sendMsgTo("you are not the admin...");
						continue;
					}
				} else if ("players".equals(command)) {
					sendMsgTo(lobby.whoIsIn());
				} else if ("help".equals(command)) {
					sendMsgTo(
							"\nchat to chat with the other inLobby players\n" + "exit_lobby to quit the current lobby\n"
									+ "start_game to start the game if you are the admin\n" + "help to see this\n"
									+ "players if you want to see who is in the lobby\n");
				} else {
					sendMsgTo("nulla di che...");
					continue;
				} 
			}
		}
		
	}
	
	public void setGame(){
		this.Game=true;
	}

	private void inGame() {
		sendMsgTo("You are now in game!");
		sendMsgTo("system_ingame_switch");
		while(Game){
			
		}
		// TODO Auto-generated method stub
		
	}

	public void sendObject(Object object){
		try {
			System.out.println("sent "+object);
			socketOut.writeObject(object);
			socketOut.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendMsgTo(String string){
		SimpleMessage sm=new SimpleMessage(string);
		try {
			socketOut.writeObject(sm);
			socketOut.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public SimpleMessage receiveMsg(){
		try {
			Object o=socketIn.readObject();
			if(o instanceof SimpleMessage){
				return (SimpleMessage) o;
			}
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public String readMsg(){
		return receiveMsg().getMsg();
	}
	
	
	@Override
	public String toString() {
		return username;
	}
}
