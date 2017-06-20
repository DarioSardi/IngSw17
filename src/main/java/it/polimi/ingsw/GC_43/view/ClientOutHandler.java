package it.polimi.ingsw.GC_43.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import it.polimi.ingsw.GC_43.controller.ChatMsg;
import it.polimi.ingsw.GC_43.controller.Lobby;
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
		
		System.out.println("switched to in-game commands");
		
		InGameMessageParser parser=new InGameMessageParser(userIn,this);
		while(this.myClient.inGame){
			inGameCommandsPrint();
			try {
				String command=userIn.readLine().toString();
				if("help".equals(command)){
					inGameCommandsPrint();
				}
				else if("action".equals(command)&&this.myClient.myTurn){
					parser.actionMenu();				
				}
				else if("action".equals(command)&&!this.myClient.myTurn){
					System.out.println("this is not your turn!");
				}
				else if("chat".equals(command)){
					System.out.println("write the message and then press enter, to abort write 'exit_chat'");
					String msg=userIn.readLine();
					if(!"exit_chat".equals(msg)){
						this.socketOut.writeObject(new ChatMsg(msg));
						this.socketOut.flush();
					}
				}
				else if("info".equals(command)){
					System.out.println("1) to see personal data");
					System.out.println("2) to see the board infos");
					System.out.println("3) to see the list of the players with their resources");
					String subchoice=userIn.readLine().toString();
					if("1".equals(subchoice)){
						System.out.println(this.myClient.getMyPlayer().toString());
					}
					else if("2".equals(subchoice)){
						System.out.println(this.myClient.getBoard().toString());
					}
					else if("3".equals(subchoice)){
						this.myClient.getBoard().getPlayers().stream().forEach(p->System.out.println(p.toString()));
						
					}
					else{
						System.out.println("not a valid choice, returning to game menu");
					}
					
				}
				else{
					System.out.println("command "+command+" is not a valid command");
				}
			} catch (IOException e) {
			
				e.printStackTrace();
			}

		}
	}
	
	public void inGameCommandsPrint(){
		System.out.println("You are in the Game-mode menu;your commands are:");
		System.out.println("help - to see this message");
		System.out.println("action - to show the list of actions");
		System.out.println("chat - for sending a message to all the players in the game");
		System.out.println("info- to see the list of possible infos");
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

	public Client getMyClient() {
		return myClient;
	}
	
	

}
