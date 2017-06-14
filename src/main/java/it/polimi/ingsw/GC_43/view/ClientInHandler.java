package it.polimi.ingsw.GC_43.view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

import it.polimi.ingsw.GC_43.controller.SimpleMessage;
import it.polimi.ingsw.GC_43.model.Board;

public class ClientInHandler implements Runnable {

	private ObjectInputStream socketIn;
	private Client myClient;
	private Boolean idSetted;

	public ClientInHandler(ObjectInputStream socketIn,Client myClient) {
		this.socketIn=socketIn;
		this.myClient=myClient;
		this.idSetted=false;
		this.myClient.inMenu=true;
	}

	@Override
	public void run() {
		myClient.setID(Integer.parseInt(readMsg()));
		System.out.println("client ID is now: " + myClient.getID());
		while(this.myClient.online){
			String line=readMsg();
			
			if("system_ingame_switch".equals(line)){
				this.myClient.inGame=true;
				parseGameUpdates();
					
				}
			else{
				System.out.println(line);
			}
			}
		}
	private void parseGameUpdates() {
		System.out.println("parser delgli update pronto!");
		while(this.myClient.inGame){
			Object o=receiveMsg();
			if(o instanceof Board){
				Board b=(Board) o;
				this.myClient.setBoard(b);
			}
		}
		
	}
	

	private void menu() throws InterruptedException {
		
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
	
	public Object receiveObj(){
		try {
			return this.socketIn.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
