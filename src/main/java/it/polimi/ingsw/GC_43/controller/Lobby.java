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
		this.ID=ID;
		System.out.println("SONO LA LOBBY "+ID+" E SON VIVA!");
	}

	public void addPlayer(ClientHandler cH) {
		//DARIO mettere il limite di controllo
		this.players.add(cH);
		
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

}
