package it.polimi.ingsw.GC_43.controller;

import java.util.ArrayList;

public class Lobby implements Runnable{

	private ClientHandler admin;
	private ArrayList<ClientHandler> players;
	public int ID;

	public Lobby(ClientHandler lobbyAdmin,Integer ID) {
		this.admin=lobbyAdmin;
		this.players=new ArrayList<>();
		this.players.add(lobbyAdmin);
		lobbyAdmin.setLobby(this);
		this.ID=ID;
		System.out.println("SONO LA LOBBY "+ID+" E SON VIVA!");
	}

	public boolean addPlayer(ClientHandler cH) {
		//DARIO mettere il limite di controllo
		this.players.add(cH);
		cH.setLobby(this);
		System.out.println("added "+cH.toString());
		cH.sendMsgTo("sei stato aggiunto alla lobby");
		return true;
	}

	
	public void run() {
		while(true){
			System.out.println("---------------------------");
			System.out.println("nella lobby "+ID+" ci sono");
			players.stream().forEach(p->System.out.println(p.toString()));
			System.out.println("---------------------------");

			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
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
			return true;
		}
		else{
			return false;
		}
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
