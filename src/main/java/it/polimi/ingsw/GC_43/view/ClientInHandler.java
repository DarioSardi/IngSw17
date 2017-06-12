package it.polimi.ingsw.GC_43.view;

import java.awt.Choice;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

import it.polimi.ingsw.GC_43.controller.messages.ChoiceMessage;
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
		while(true){
			try {
				readInObject();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	}
		}
	
	
	private void readInObject() throws Exception {
		try {
			System.out.println("provo a leggere");
			Object msg=this.socketIn.readObject();
			//choice
			if(msg instanceof ChoiceMessage){
				ChoiceMessage c= (ChoiceMessage) msg;
				System.out.println("è un messaggio choice");
				choiceMsgRead(c);
			}
			//msg
			else if(msg instanceof SimpleMsg)
			{
				simpleMsgRead((SimpleMsg) msg);
			}
			else if (msg instanceof Object){
				System.out.println("formato sconosciuto");
			}
			else
			{throw new Exception("non so cosa sia successo");}
		
		
		
		
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void simpleMsgRead(SimpleMsg msg) {
		System.out.println(msg.getMsg());
	}

	private void choiceMsgRead(ChoiceMessage msg) {
		msg.setScanner(new Scanner(System.in));
		msg.choose();
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
