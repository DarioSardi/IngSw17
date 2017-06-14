package it.polimi.ingsw.GC_43.controller;

import java.util.ArrayList;

public class Lobby implements Runnable{

	private ClientHandler admin;
	private ArrayList<ClientHandler> players;
	public int ID;
	private Controller controller;
	private boolean exist;

	public Lobby(ClientHandler lobbyAdmin,Integer ID) {
		this.admin=lobbyAdmin;
		this.players=new ArrayList<>();
		this.players.add(lobbyAdmin);
		lobbyAdmin.setLobby(this);
		this.ID=ID;
		this.exist=true;
		System.out.println("SONO LA LOBBY "+ID+" E SON VIVA!");
	}

	public boolean addPlayer(ClientHandler cH) {
		if (players.size()<4) {
			if (!players.contains(cH)) {
				this.players.add(cH);
				cH.setLobby(this);
				System.out.println("added " + cH.toString());
				cH.sendMsgTo("added to the lobby!");
				return true;
			} else {
				cH.setLobby(this);
				System.out.println("re-entered " + cH.toString());
				cH.sendMsgTo("welcome back to the lobby");
				return true;
			} 
		}
		else{
			cH.sendMsgTo("Lobby is full!");
			return false;
		}
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
	
	@Override
	public String toString() {
		return "lobby number "+ID+", lobby admin is: "+admin.toString()+"\n";
	}

	public void broadcastMsg(String nextLine,ClientHandler cH) {
		players.stream().forEach(p->p.sendMsgTo("message from "+cH.getUsername()+": "+nextLine));
		
	}
	
	private void lobbyMsg(String nextLine) {
		players.stream().forEach(p->p.sendMsgTo("message from the LOBBY: "+nextLine));
	}

	public boolean startGame(ClientHandler clientHandler) {
		if(this.admin==clientHandler){
			//INIZIA GIOCO
			lobbyMsg("il gioco sta iniziando");
			this.controller=new Controller(players);
			lobbyMsg("controller inizializzato");
			controller.initializeGame();
			lobbyMsg("gioco inizializzato");
			return true;
		}
		else{
			return false;
		}
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
