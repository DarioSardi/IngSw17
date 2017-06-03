package it.polimi.ingsw.GC_43.view;

import java.io.PrintWriter;
import java.util.Scanner;

public class ClientOutHandler implements Runnable {

	private PrintWriter socketOut;
	/**
	 * handle the message sent to the server
	 * @param socketOut
	 */
	public ClientOutHandler(PrintWriter socketOut) {
		this.socketOut=socketOut;
	}

	@Override
	public void run() {
		
		Scanner userIn= new Scanner(System.in);
		while(true){
			String msg=userIn.nextLine();
			socketOut.println(msg);
			socketOut.flush();
		}

	}

}
