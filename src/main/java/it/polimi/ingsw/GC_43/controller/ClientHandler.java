package it.polimi.ingsw.GC_43.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import it.polimi.ingsw.GC_43.controller.messages.ChoiceMessage;
import it.polimi.ingsw.GC_43.controller.messages.ConnectionDataMsg;
import it.polimi.ingsw.GC_43.controller.messages.MainMenuMsg;
import it.polimi.ingsw.GC_43.controller.messages.SimpleMsg;
import it.polimi.ingsw.GC_43.controller.messages.StateChangeMsg;


public class ClientHandler implements Runnable{
	Socket socket;
	private int ID;
	private Server myServer;
	private String username;
	ObjectInputStream socketIn;
	ObjectOutputStream socketOut;
	private Lobby lobby;
	private QuerySolver qs;

	public ClientHandler(Socket socket,int ID,Server myServer) throws IOException {
		super();
		this.socket = socket;
		this.ID=ID;
		this.myServer=myServer;
		this.socketIn = new ObjectInputStream(this.socket.getInputStream());
		this.socketOut = new ObjectOutputStream(this.socket.getOutputStream());
		this.qs=new QuerySolver(this);
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
		boolean exit = false;
		
		//SETUP
		System.out.println("ClientHandler starting up!");
		reciveUserData();
		MainMenuMsg menu=new MainMenuMsg();
		this.sendObject(menu);
		
		boolean running=true;
		while(running){
			this.readChoice();
		}
	}
	


	private void inLobby() {
		sendStringTo("sei entrato nella lobby");
		boolean inlobby=true;
		while(inlobby){
			String command=readChoice().getMsg();
			if(command.equals("exit_lobby")){
				//DARIO rimuovi lobby
				inlobby=false;
				sendStringTo("stai uscendo dalla lobby");
			}
			else if(command.equals("chat")){
				sendStringTo("digita il messaggio da mandare a tutti gli altri");
				String msg=readChoice().getMsg();
				lobby.broadcastMsg(msg,this);
			}
			else if(command.equals("start_game")){
				if(lobby.startGame(this)){
					inGame();
				}
				else{
					sendStringTo("you are not the admin...");
					continue;
				}
			}
			else{
				sendStringTo("nulla di che...");
				continue;}
		}
		
	}

	private void inGame() {
		// TODO Auto-generated method stub
		
	}

	public void sendStringTo(String string){
		System.out.println("scrivo un messaggio in sendStringTo nel clientHandler");
		SimpleMsg serverAnswer=new SimpleMsg(string);
		try {
			socketOut.writeObject(serverAnswer);
			socketOut.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public void sendObject(Object msg){
		try {
			socketOut.writeObject(msg);
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
			qs.messageAnalyzer(this.socketIn.readObject());
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
	public Server getMyServer(){
		return this.myServer;
	}
	
}
