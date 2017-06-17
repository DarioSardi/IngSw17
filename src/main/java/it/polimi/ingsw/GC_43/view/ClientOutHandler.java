package it.polimi.ingsw.GC_43.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import it.polimi.ingsw.GC_43.controller.SimpleMessage;

public class ClientOutHandler implements Runnable {

	private ObjectOutputStream socketOut;
	private Client myClient;
	private InGameMessageParser gameParser;
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
		
		BufferedReader userIn= this.myClient.inKeyboard;
		sendMsgTo(myClient.getUsername());
		while(this.myClient.online){
			try {
				if(!this.myClient.inGame){
				String msg=userIn.readLine().toString();
				sendMsgTo(msg);
				}
				else if(this.myClient.inGame){
					inGameParser(userIn);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	private void inGameParser(BufferedReader userIn) {
		
		System.out.println("ho switchato ai comandi in gioco");
		System.out.println("digita help per la lista dei comandi in gioco");
		InGameMessageParser parser=new InGameMessageParser(userIn,this.myClient);
		while(this.myClient.inGame){
			try {
				String command=userIn.readLine().toString();
				if("help".equals(command)){
					System.out.println("i tuoi comandi sono:");
					System.out.println("help - per visualizzare la lista dei comandi");
					System.out.println("action - per visualizzare la lista delle azioni");
					System.out.println("chat - per inviare un messaggio ai giocatori");
				}
				else if("action".equals(command)&&this.myClient.myTurn){
					parser.actionMenu(userIn,this.myClient);				
				}
				else if("action".equals(command)&&!this.myClient.myTurn){
					System.out.println("this is not your turn!");
				}
			} catch (IOException e) {
			
				e.printStackTrace();
			}

		}
	}

	public void sendMsgTo(String string){
		SimpleMessage sm=new SimpleMessage(string);
		try {
			socketOut.writeObject(sm);
			socketOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendObj(Object o){
		try {
			socketOut.writeObject(o);
			socketOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
