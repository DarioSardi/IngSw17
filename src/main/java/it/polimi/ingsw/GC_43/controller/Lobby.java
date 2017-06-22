package it.polimi.ingsw.GC_43.controller;

import java.awt.SecondaryLoop;
import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.GC_43.view.Client;

public class Lobby implements Runnable{

	private ClientHandler admin;
	private ArrayList<ClientHandler> players;
	private HashMap<String,String> usernamePass;
	public int ID,maxPlayers;
	private Controller controller;
	private boolean exist,gameStarted;

	public Lobby(ClientHandler lobbyAdmin,Integer ID,Integer maxPlayers) {
		this.admin=lobbyAdmin;
		this.players=new ArrayList<>();
		this.players.add(lobbyAdmin);
		lobbyAdmin.setLobby(this);
		this.ID=ID;
		this.maxPlayers=maxPlayers;
		this.exist=true;
		this.gameStarted=false;
		System.out.println("SONO LA LOBBY "+ID+" E SON VIVA!");
	}

	public Integer addPlayer(ClientHandler cH) {
		if (players.size()<maxPlayers&&!gameStarted) {
			checkUsername(cH);
			if (!players.contains(cH)) {
				this.players.add(cH);
				cH.setLobby(this);
				System.out.println("added " + cH.toString());
				cH.sendMsgTo("added to the lobby!");
				return 1;
			} else if (players.contains(cH)) {
				cH.setLobby(this);
				System.out.println("re-entered " + cH.toString());
				cH.sendMsgTo("welcome back to the lobby");
				return 1;
			} 
			
		}
		else if(gameStarted){
			System.out.println("player trying to reconnect to the lobby "+this.toString());
			if(this.usernamePass.containsKey(cH.getUsername()) && !inGame(cH.getUsername())){ //qui?
				cH.sendMsgTo("if you are who i think, tell me your password!");
				return 2;
			}
		}
		else{
			cH.sendMsgTo("Lobby is full!");
			return 0;
		}
		System.out.println("unexpected return on lobby for addPlayer");
		return 0;
	}
	
	
	/**
	 * check if the username is already taken in the lobby
	 * @param username
	 * @return
	 */
	private boolean checkUsername(ClientHandler cH) {
		for(ClientHandler ch: this.players){
			if(ch.getUsername().equals(cH.getUsername())){
				cH.sendMsgTo("the username is taken!");
				cH.changeName();
				checkUsername(cH);
				return true;
			}
			
		}
		return true;
	}

	public boolean reconnectPlayer(ClientHandler cH, String password){
		if(this.usernamePass.get(cH.getUsername()).equals(password))
		{
			this.players.add(cH);
			cH.setLobby(this);
			System.out.println("reconnected " + cH.toString());
			cH.sendMsgTo("reconnected to the lobby, reinitializing the game...");
			cH.sendMsgTo("system_ingame_switch");
			this.controller.playerInGameAgain(cH.getUsername(), cH);
			
			
			return true;
		}
		return false;
	}
	
	
	public void disconnectPlayer(ClientHandler cH,String password){
		System.out.println("disconnected with username: "+cH.getUsername()+" password: "+password);
		this.usernamePass.put(cH.getUsername(), password);
		this.players.remove(cH);
	}
	
	
	/**
	 * to find if there is a clientHandler in the lobby with the same username (clientHandler==player is the game playing)
	 * @param username username of the player that we are searching
	 * @return 
	 */
	private boolean inGame(String username) {
		for(ClientHandler ch: this.players){
			if((ch.getUsername()).equals(username)){
				return true;
			}
		}
		return false;
	}

	public Controller getController(){
		return this.controller;
	}
	
	public void run() {
		while(exist){
			System.out.println("---------------------------");
			System.out.println("nella lobby "+ID+" ci sono");
			players.stream().forEach(p->System.out.println(p.toString()));
			System.out.println("---------------------------");

			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public String whoIsIn() {
		StringBuilder sb = new StringBuilder();
		sb.append("---------------------------\n");
		sb.append("nella lobby " + ID + " ci sono\n");
		players.stream().forEach(p -> {
			if (p.getUsername().equals(admin.getUsername())) {
				sb.append(p.getUsername() + "  ADMIN\n");
			} else {
				sb.append(p.getUsername());
			}
		});
		sb.append("\n---------------------------\n");
		return sb.toString();
	}

	@Override
	public String toString() {
		return "lobby number "+ID+", lobby admin is: "+admin.toString()+"\n";
	}

	public void broadcastMsg(String nextLine,ClientHandler cH) {
		players.stream().forEach(p->p.sendMsgTo("message from "+cH.getUsername()+": "+nextLine));
		
	}
	
	public void broadcastObject(Object o) {
		players.stream().forEach(p->p.sendObject(o));
		
	}
	
	public void broadcastSwitchMsg() {
		players.stream().forEach(p->
		{
			if(p!=admin){
				p.setGame(true);
				p.sendMsgTo("system_ingame_switch");}});
		
	}
	
	/**
	 * broadcast message sent by system
	 * @param nextLine text of the message
	 */
	private void lobbyMsg(String nextLine) {
		players.stream().forEach(p->p.sendMsgTo(nextLine));
	}

	public boolean startGame(ClientHandler clientHandler) {
		if(this.admin==clientHandler){
			//INIZIA GIOCO
			lobbyMsg("message from the lobby:il gioco si sta iniziando");
			this.controller=new Controller(players);
			lobbyMsg("message from the lobby:controller inizializzato");
			this.controller.initializeGame();
			lobbyMsg("message from the lobby:gioco inizializzato");
			this.gameStarted=true;
			initializeUserPass();
			broadcastSwitchMsg();
			return true;
		}
		else{
			return false;
		}
		
	}

	private void initializeUserPass() {
		this.usernamePass=new HashMap<>();
		this.players.forEach(ch->{
			this.usernamePass.put(ch.getUsername(), "null");
		});
		
		
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
