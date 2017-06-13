package it.polimi.ingsw.GC_43.view;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import it.polimi.ingsw.GC_43.controller.SimpleMessage;

public class ClientOutHandler implements Runnable {

	private ObjectOutputStream socketOut;
	private Client myClient;
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
		while(true){
			String msg=userIn.nextLine();
			sendMsgTo(msg);
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
