package it.polimi.ingsw.GC_43.view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

import it.polimi.ingsw.GC_43.controller.Lobby;
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
		SimpleMessage s;
		try {
			s = (SimpleMessage) socketIn.readObject();
			myClient.setID(Integer.parseInt(s.getMsg()));
			System.out.println("client ID is now: " + myClient.getID());
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(this.myClient.online){
			receiveMsg();
			}
		}
	private void parseGameUpdates() {
		//System.out.println("parser delgli update pronto!");
		System.out.println("press enter to continue");
		while(this.myClient.inGame){
			
			}
		}
		
	
	
	
	
	@Override
	public String toString() {
		return "handler with ID: "+myClient.getID();
	}
	
	public void receiveMsg(){
		try {
			Object o=socketIn.readObject();
			if(o instanceof SimpleMessage){
				readMsg((SimpleMessage) o);
			}
			else if(o instanceof Board){
				this.myClient.setBoard((Board) o);
			}
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		
	}
	
	public void readMsg(SimpleMessage s){
		String line=s.getMsg();
		if("system_ingame_switch".equals(line)){
			this.myClient.inGame=true;
			parseGameUpdates();
				
			}
		else if("now_is_my_turn".equals(line)){
			this.myClient.myTurn=true;
			}
		else if("end_of_my_turn".equals(line)){
			this.myClient.myTurn=false;
			}
		
		else{
			System.out.println(line);
		}
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
