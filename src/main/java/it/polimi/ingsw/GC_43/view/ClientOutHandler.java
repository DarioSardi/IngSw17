package it.polimi.ingsw.GC_43.view;

import java.io.PrintWriter;
import java.util.Scanner;

public class ClientOutHandler implements Runnable {

	private PrintWriter socketOut;
	private Client myClient;
	/**
	 * handle the message sent to the server
	 * @param socketOut
	 */
	public ClientOutHandler(PrintWriter socketOut,Client myClient) {
		this.socketOut=socketOut;
		this.myClient=myClient;
	}

	@Override
	public void run() {
		
		Scanner userIn= new Scanner(System.in);
		socketOut.println(myClient.getUsername());
		socketOut.flush();
		while(true){
			String msg=userIn.nextLine();
			socketOut.println(msg);
			socketOut.flush();
		}

	}

}
