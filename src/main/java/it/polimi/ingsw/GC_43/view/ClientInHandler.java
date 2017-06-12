package it.polimi.ingsw.GC_43.view;

import java.io.IOException;
import java.io.ObjectInputStream;

import it.polimi.ingsw.GC_43.controller.messages.ConnectionDataMsg;
import it.polimi.ingsw.GC_43.controller.messages.SimpleMsg;

public class ClientInHandler implements Runnable {

	private ObjectInputStream socketIn;
	private Client myClient;
	private Boolean idSetted;

	public ClientInHandler(ObjectInputStream socketIn,Client myClient) {
		this.socketIn=socketIn;
		this.myClient=myClient;
		this.idSetted=false;
	}

	@Override
	public void run() {
		String line;
		//Set ID of user
		myClient.setID(readID().getID());
		System.out.println("client ID is now: " + myClient.getID());
		//print menu
		menu();
		//read while
		while(true){
			line = readSimpleIn().getChoice();
			System.out.println(line);	}
		}
	
	
	private void menu(){
		System.out.println("\n\n\n\n");
		System.out.println("Menu di gioco");
		System.out.println("1. crea una nuova partita");
		System.out.println("2. seleziona una lobby");	
		System.out.println("3. esci");	
		
	}
	
	
	@Override
	public String toString() {
		return "handler with ID: "+myClient.getID();
	}
	
	public ConnectionDataMsg readID(){
		try {
			return (ConnectionDataMsg)this.socketIn.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	private SimpleMsg readSimpleIn() {
		try {
			return (SimpleMsg) this.socketIn.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}


}
