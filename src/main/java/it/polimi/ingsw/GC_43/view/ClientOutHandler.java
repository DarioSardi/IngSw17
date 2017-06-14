package it.polimi.ingsw.GC_43.view;

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
		
		Scanner userIn= new Scanner(System.in);
		sendMsgTo(myClient.getUsername());
		while(this.myClient.online){
			if(!this.myClient.inGame){
			String msg=userIn.nextLine();
			sendMsgTo(msg);
			}
			else if(this.myClient.inGame){
				inGameParser(userIn);
			}
		}

	}
	
	private void inGameParser(Scanner userIn) {
		System.out.println("ho switchato ai comandi in gioco");
		System.out.println("digita help per la lista dei comandi in gioco");
		InGameMessageParser parser=new InGameMessageParser(userIn,this.myClient);
		while(this.myClient.inGame){
			String command=userIn.nextLine();
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

		}
	}

	public void sendMsgTo(String string){
		SimpleMessage sm=new SimpleMessage(string);
		try {
			socketOut.writeObject(sm);
			socketOut.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
