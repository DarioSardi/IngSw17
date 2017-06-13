package it.polimi.ingsw.GC_43.view;

import java.awt.Choice;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

import it.polimi.ingsw.GC_43.controller.Lobby;
import it.polimi.ingsw.GC_43.controller.messages.ChoiceMessage;
import it.polimi.ingsw.GC_43.controller.messages.ConnectionDataMsg;
import it.polimi.ingsw.GC_43.controller.messages.SimpleMsg;
import it.polimi.ingsw.GC_43.controller.messages.StateChangeMsg;

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
		Boolean online=true;
		while(online){
			try {
				readInObject();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	}
		}
	
	
	private void readInObject() throws Exception {
		try {
			Object msg=this.socketIn.readObject();
			this.myClient.busy=true;
			//choice
			if(msg instanceof ChoiceMessage){
				ChoiceMessage c= (ChoiceMessage) msg;
				choiceMsgRead(c);
			}
			//msg
			else if(msg instanceof SimpleMsg)
			{
				simpleMsgRead((SimpleMsg) msg);
			}
			//status change
			else if(msg instanceof StateChangeMsg)
			{
				StateChangeMsg c= (StateChangeMsg) msg;
				if(c.getInLobby()!=null){
					this.myClient.inLobby=c.getInLobby();
					System.out.println("stato di inLobby cambiato a "+this.myClient.inLobby);
				}
				if(c.getInGame()!=null){
					this.myClient.inGame=c.getInGame();
				}
			}
			//uknown
			else if (msg instanceof Object){
				System.out.println("formato sconosciuto");
			}
			else
			{throw new Exception("non so cosa sia successo");}
			this.myClient.busy=false;
		
		
		
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void simpleMsgRead(SimpleMsg msg) {
		System.out.println("è arrivato un messaggioh!");
		System.out.println(msg.getMsg());
	}

	private void choiceMsgRead(ChoiceMessage msg) {
		msg.setScanner(new Scanner(System.in));
		try {
			msg.choose();
			msg.setScanner(null);
			this.myClient.sendObject(msg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	

	

}
