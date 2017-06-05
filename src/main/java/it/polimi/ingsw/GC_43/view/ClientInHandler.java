package it.polimi.ingsw.GC_43.view;

import java.util.Scanner;

public class ClientInHandler implements Runnable {

	private Scanner socketIn;
	private Client myClient;
	private Boolean idSetted;

	public ClientInHandler(Scanner socketIn,Client myClient) {
		this.socketIn=socketIn;
		this.myClient=myClient;
		this.idSetted=false;
	}

	@Override
	public void run() {
		myClient.setID(socketIn.nextInt());
		System.out.println("client ID is now: " + myClient.getID());
		try {
			menu();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(true){
			String line=socketIn.nextLine();
			System.out.println(line);	
		}
	}

	private void menu() throws InterruptedException {
		System.out.println("\n\n\n\n");
		System.out.println("Menu di gioco");
		System.out.println("1. crea una nuova partita");
		System.out.println("2. seleziona una lobby");
		
		
	}
	
	
	@Override
	public String toString() {
		return "handler with ID: "+myClient.getID();
	}

}
