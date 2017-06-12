package it.polimi.ingsw.GC_43.view;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import it.polimi.ingsw.GC_43.controller.QuerySolver;
import it.polimi.ingsw.GC_43.controller.messages.ConnectionDataMsg;
import it.polimi.ingsw.GC_43.controller.messages.SimpleMsg;

public class ClientOutHandler implements Runnable {

	private ObjectOutputStream socketOut;
	private Client myClient;
	private QuerySolver solver;
	/**
	 * handle the message sent to the server
	 * @param socketOut
	 */
	public ClientOutHandler(ObjectOutputStream socketOut,Client myClient) {
		this.socketOut=socketOut;
		this.myClient=myClient;
	}

	@Override
	public void run() {
		
		Scanner userIn= new Scanner(System.in);
		try {
			socketOut.writeObject(new ConnectionDataMsg(myClient.getUsername()));
			socketOut.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//DARIO modificare con variabile "busy" per non far inviare input se sto eseguendo un messaggio
		while(true){
			//String msg=userIn.nextLine();
			//sendClientMsg(msg);
			
		}

	}
	
	private void sendClientMsg(String msg){
		SimpleMsg messageObj=new SimpleMsg(msg);
		try {
			socketOut.writeObject(messageObj);
			socketOut.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
