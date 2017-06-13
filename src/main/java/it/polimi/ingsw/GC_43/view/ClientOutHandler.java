package it.polimi.ingsw.GC_43.view;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import it.polimi.ingsw.GC_43.controller.QuerySolver;
import it.polimi.ingsw.GC_43.controller.messages.ConnectionDataMsg;
import it.polimi.ingsw.GC_43.controller.messages.LobbyCmd;
import it.polimi.ingsw.GC_43.controller.messages.SimpleMsg;

public class ClientOutHandler implements Runnable {

	private ObjectOutputStream socketOut;
	private Client myClient;
	private QuerySolver solver;
	private ClientInputParser parser;
	/**
	 * handle the message sent to the server
	 * @param socketOut
	 */
	public ClientOutHandler(ObjectOutputStream socketOut,Client myClient) {
		this.socketOut=socketOut;
		this.myClient=myClient;
		this.parser=new ClientInputParser();
		
	}

	@Override
	public void run() {
		
		Scanner userIn= new Scanner(System.in);
		try {
			socketOut.writeObject(new ConnectionDataMsg(myClient.getUsername()));
			socketOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Boolean online=true;
		while(online){
			
			//in lobby parse
			if(this.myClient.inLobby&&!this.myClient.busy){
				String input=userIn.nextLine();
				LobbyCmd lc= parser.lobbyParser(input);
				try {
					this.myClient.busy=true;
					lc.choose();
					this.myClient.busy=false;
					sendObject(lc);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//in game parse
			else if(this.myClient.inGame&&!this.myClient.busy){
				String input=userIn.nextLine();
			}
			else{
				
			}
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
	
	public void sendObject(Object o){
		try {
			
			socketOut.writeObject(o);
			socketOut.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
