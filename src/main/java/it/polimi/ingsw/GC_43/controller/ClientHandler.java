package it.polimi.ingsw.GC_43.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable{
	private Socket socket;

	public ClientHandler(Socket socket) {
		super();
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			Scanner socketIn= new Scanner(socket.getInputStream());
			PrintWriter socketOut = new PrintWriter(socket.getOutputStream());
			while (true){ 
				
				String line = socketIn.nextLine();
				System.out.println(line);
				
				if (line.equals("quit")){
					break;
					}
				else{
					socketOut.println("messaggio al server: " + line);
				}
				}
			socketOut.close();
			socketIn.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
