package it.polimi.ingsw.GC_43.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Scanner;

import it.polimi.ingsw.GC_43.controller.ChatMsg;
import it.polimi.ingsw.GC_43.controller.ClientaHandlerRmInterface;
import it.polimi.ingsw.GC_43.controller.QuitMsg;

public class RmiView implements Runnable{

	private static boolean isInGame = false;
	private ClientaHandlerRmInterface handler;
	private BufferedReader input;
	private Boolean online;
	private Client myclient;
	private boolean inLobby;

	public RmiView(Client c,ClientaHandlerRmInterface handler, BufferedReader inKeyboard) {
		this.handler=handler;
		this.input=inKeyboard;
		this.myclient=c;
	}

	@Override
	public void run() {
		System.out.println("RMI menu!");
		try {
			this.handler.setUsername(this.myclient.getUsername());
		} catch (RemoteException e1) {
			
			e1.printStackTrace();
		}
		online=true;
		while (online) {
			try {
				System.out.println(this.handler.mainMenuChoicesPrint());
				String command = input.readLine();
				if("1".equals(command)){
					System.out.println("Lobbies on this server:");
					this.handler.printLobbyes();
					System.out.println("select a number for the lobby");
					Integer lobbyNumber = Integer.parseInt(input.readLine());
					Integer maxPlayers=0;
					while (!(maxPlayers<=4&&maxPlayers>=2)) {
						System.out.println("select the max number of players in the lobby/game (min 2 max 4)");
						maxPlayers = Integer.parseInt(input.readLine());
					}
					if (handler.tryToCreateLobby(lobbyNumber,maxPlayers)) {
						System.out.println("you are in the lobby now!");
						inLobby();
				} else {
						System.out.println("lobby number unaviable");
					}
				}
				
			 else if ("2".equals(command)) {
				System.out.println("select a lobby to join!");
				System.out.println(handler.printLobbyes());
				Integer lobbyNumber=Integer.parseInt(input.readLine());
				if(handler.joinLobby(lobbyNumber)){
					inLobby();
				}
				else{System.out.println("unable to join lobby");}
				
			
			//QUIT
			} else if ("3".equals(command)) {
				this.online = true;
			}
			
			//FKING MORON
			else{
				System.out.println("invalid choice");
			}

			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		
	}

	private void inLobby() throws IOException {
		inLobby=true;
		while(inLobby){
			if (!this.isInGame) {
				System.out.println(handler.helpMsgLobby());;
				String command = input.readLine();
				if (command.equals("exit_lobby")) {
					inLobby = false;
					System.out.println("exiting the lobby");
				} else if ("chat".equals(command)) {
					System.out.println("write the message that you want to send!");
					String msg = input.readLine();
					handler.chatMessage(msg);
				} else if ("start_game".equals(command)) {
					if (handler.startGame()) {
						this.isInGame=true;
						inGame();
					} else {
						System.out.println("you are not the admin...");
						continue;
					}
				} else if ("players".equals(command)) {
					System.out.println(handler.whoIsInLobby());
				} else if ("help".equals(command)) {
					helpMsgLobby();
				} else {
					System.out.println("command not found");
					continue;

				} 
			}
			else{
				inGame();
			}
		}
		
	}

	private void helpMsgLobby() {
		System.out.println("\nCOMANDI LOBBY:\n"
				+ "chat to chat with the other inLobby players\n" + "exit_lobby to quit the current lobby\n"
						+ "start_game to start the game if you are the admin\n" + "help to see this\n"
						+ "players if you want to see who is in the lobby\n");
		
	}

	private void inGame() {
		System.out.println("you are in game");
		while(this.isInGame){
			System.out.println("switched to in-game commands");
			InGameMessageParser parser=new InGameMessageParser(input);
			while(this.myclient.inGame){
				inGameCommandsPrint();
				try {
					String command=input.readLine().toString();
					if("help".equals(command)){
						inGameCommandsPrint();
					}
					else if("action".equals(command)&&this.myclient.myTurn){
						parser.mainMenuParser();				
					}
					else if("action".equals(command)&&!this.myclient.myTurn){
						System.out.println("this is not your turn!");
					}
					else if("chat".equals(command)){
						System.out.println("write the message and then press enter, to abort write 'exit_chat'");
						String msg=input.readLine();
						if(!"exit_chat".equals(msg)){
							handler.chatMessage(msg);
						}
					}
					else if("info".equals(command)){
						System.out.println("1) to see personal data");
						System.out.println("2) to see the board infos");
						System.out.println("3) to see the list of the players with their resources");
						String subchoice=input.readLine().toString();
						if("1".equals(subchoice)){
							System.out.println(this.myclient.getMyPlayer().toString());
						}
						else if("2".equals(subchoice)){
							System.out.println(this.myclient.getBoard().toString());
						}
						else if("3".equals(subchoice)){
							this.myclient.getBoard().getPlayers().stream().forEach(p->System.out.println(p.toString()));
							
						}
						else{
							System.out.println("not a valid choice, returning to game menu");
						}
						
					}
					
					else if("quit".equals(command)){
						System.out.println("are you sure? write 'yes' to exit the game");
						String quitString=input.readLine().toString();
						if(quitString.equalsIgnoreCase("yes")){
							System.out.println("select a password to re-enter the game");
							String password=input.readLine().toString();
							System.out.print("now you are leaving the game!Press Enter to continue to the lobby");
							handler.quitGame(password);
							this.myclient.setInGame(false);
						}
						else{
							System.out.println("...so you don't want to leave? good! returning to the game.");
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
		}
		
	public void inGameCommandsPrint(){
		System.out.println("You are in the Game-mode menu;"
				+ "\nyour commands are:");
		System.out.println("help - to see this message");
		System.out.println("action - to show the list of actions");
		System.out.println("chat - for sending a message to all the players in the game");
		System.out.println("info- to see the list of possible infos");
		System.out.println("quit- to exit the game!");
	}
	
	
		
	}


