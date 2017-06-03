package it.polimi.ingsw.GC_43.view;

import java.util.Scanner;

public class ClientInHandler implements Runnable {

	private Scanner socketIn;

	public ClientInHandler(Scanner socketIn) {
		this.socketIn=socketIn;
	}

	@Override
	public void run() {
		while(true){
			String line=socketIn.nextLine();
			System.out.println(line);
		}
	}

}
