package it.polimi.ingsw.GC_43.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import it.polimi.ingsw.GC_43.controller.messages.ConnectionDataMsg;
import it.polimi.ingsw.GC_43.controller.messages.SimpleMsg;


public class ClientHandler implements Runnable{
	private Socket socket;
	private int ID;
	private Server myServer;
	private String username;
	private ObjectInputStream socketIn;
	private ObjectOutputStream socketOut;
	private Lobby lobby;

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
			reciveUserData();
			
			//MENU
			while (!exit) {
				sendMsgTo("sei nel menù,scegli!");
				
				//NEW LOBBY
				String choice=readChoice().getChoice();
				
				if (choice.equals("1")) {
					sendMsgTo("seleziona un numero di lobby");
					int lobbyNumber = Integer.parseInt(readChoice().getChoice());
					System.out.println("la scelta è "+lobbyNumber);
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
					Integer lobbyNumber=Integer.parseInt(readChoice().getChoice());
					if(myServer.joinLobby(lobbyNumber, this)){
					inLobby();
					}
					else{sendMsgTo("unable to join lobby");}
					
				
				//QUIT
				} else if (choice.equals("3")) {
					sendMsgTo("bb loser!");
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
			String command=readChoice().getChoice();
			if(command.equals("exit_lobby")){
				//DARIO rimuovi lobby
				inlobby=false;
				sendMsgTo("stai uscendo dalla lobby");
			}
			else if(command.equals("chat")){
				sendMsgTo("digita il messaggio da mandare a tutti gli altri");
				String msg=readChoice().getChoice();
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
		SimpleMsg serverAnswer=new SimpleMsg(string);
		try {
			socketOut.writeObject(serverAnswer);
			socketOut.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	@Override
	public String toString() {
		return username;
	}
	private SimpleMsg readChoice(){
		try {
			return (SimpleMsg)this.socketIn.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void reciveUserData(){
		try {
			ConnectionDataMsg askUsername;
			askUsername=(ConnectionDataMsg)socketIn.readObject();
			this.username=askUsername.getUsername();
			askUsername.setID(this.ID);
			System.out.println("username of the player is "+username);
			socketOut.writeObject(askUsername);
			socketOut.flush();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
