package it.polimi.ingsw.GC_43.view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

import it.polimi.ingsw.GC_43.controller.SimpleMessage;

public class ClientInHandler implements Runnable {

	private ObjectInputStream socketIn;
	private Client myClient;
	private Boolean idSetted,inMenu,inGame;

	public ClientInHandler(ObjectInputStream socketIn,Client myClient) {
		this.socketIn=socketIn;
		this.myClient=myClient;
		this.idSetted=false;
		this.inMenu=true;
	}

	@Override
	public void run() {
		myClient.setID(Integer.parseInt(readMsg()));
		System.out.println("client ID is now: " + myClient.getID());
		try {
			menu();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(inMenu){
			String line=readMsg();
			System.out.println(line);
			if(line=="system_ingame_switch"){
				inGame=true;
				parseGameActions();
					
				}
			}
		}
	private void parseGameActions() {
		while(inGame){
			//DARIO parsing ingame
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

}
