package it.polimi.ingsw.GC_43.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import it.polimi.ingsw.GC_43.controller.ClientaHandlerRmInterface;
import it.polimi.ingsw.GC_43.controller.DefaultBonusChoiceMessage;
import it.polimi.ingsw.GC_43.controller.LeaderCardChoiceMessage;
import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.CopyOfGlobalVariables;
import it.polimi.ingsw.GC_43.model.PlayerPersonalBonus;
import it.polimi.ingsw.GC_43.model.actionCreations.CommonActionCreatorRoutine;

public class RmiView extends UnicastRemoteObject implements Serializable,UserRmiInterface,Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7468308935097128257L;
	private ClientaHandlerRmInterface handler;
	private volatile BufferedReader input;
	private Boolean online;
	private Client myclient;
	private boolean inLobby;
	private int ID;
	

	public RmiView(Client c,ClientaHandlerRmInterface handler, BufferedReader inKeyboard)  throws RemoteException{
		this.handler=handler;
		this.input=inKeyboard;
		this.myclient=c;
	}
	
	
	/**
	 * main run method, while the rmi client is online this should run.
	 */
	@Override
	public void run() {
		System.out.println("RMI menu!");
		try {
			this.myclient.setID(handler.connect(this));
		} catch (RemoteException e2) {
			e2.printStackTrace();
		}
		this.ID=myclient.getID();
		try {
			System.out.println("setting username");
			this.handler.setUsername(this.ID,this.myclient.getUsername());
		} catch (RemoteException e1) {
			
			e1.printStackTrace();
		}
		online=true;
		
		while (online) {
			try {
				System.out.println(this.handler.mainMenuChoicesPrint());
				String command = input.readLine();
				
				//NEW LOBBY
				if("1".equals(command)){
					System.out.println("Lobbies on this server:");
					this.handler.printLobbyes();
					System.out.println("select a number for the lobby");
					Integer lobbyNumber = Integer.parseInt(input.readLine());
					Integer maxPlayers=0;
					while (!(maxPlayers<=5&&maxPlayers>=2)) {
						System.out.println("select the max number of players in the lobby/game (min 2 max 5)");
						maxPlayers = Integer.parseInt(input.readLine());
					}
					String advancedMode=null;
					Boolean advChoice=false;
					while  (!("yes".equals(advancedMode)||"no".equals(advancedMode))) {
						System.out.println("do you want to play with the advanced mode?");
						advancedMode=input.readLine();
					}
					if("yes".equals(advancedMode)){
						advChoice=true;
					}
					if (handler.tryToCreateLobby(this.ID,lobbyNumber,maxPlayers,advChoice)) {
						System.out.println("you are in the lobby now!");
						inLobby();
				} else {
						System.out.println("lobby number unaviable");
					}
				}
			
			 //JOIN LOBBY	
			 else if ("2".equals(command)) {
				System.out.println("select a lobby to join!");
				System.out.println(handler.printLobbyes());
				Integer lobbyNumber=Integer.parseInt(input.readLine());
				if(handler.joinLobby(this.ID,lobbyNumber)){
					inLobby();
				}
				else{System.out.println("unable to join lobby");}
			 }
			
			//QUIT
			else if ("3".equals(command)) {
				this.online = false;
				this.myclient.closeGame();
			}
			
			//SUPERSECRET PING COMMAND	
			else if ("ping".equals(command)) {
				System.out.println("pinging server...");
				handler.ping(this.ID);
			}
			
			
			//YOU MORON
			else{
				System.out.println("invalid choice");
			}

			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		
	}

	/**
	 * in lobby running method go give player restricted access only to in-lobby commands
	 * @throws IOException in case of DC;
	 */
	private void inLobby() throws IOException {
		inLobby=true;
		while(inLobby){
			if (!this.myclient.inGame) {
				System.out.println(handler.helpMsgLobby());;
				String command = input.readLine();
				if (command.equals("exit_lobby")) {
					inLobby = false;
					System.out.println("exiting the lobby");
					this.handler.exitLobby(this.ID);
				} else if ("chat".equals(command)) {
					System.out.println("write the message that you want to send!");
					String msg = input.readLine();
					handler.chatMessage(this.ID,msg);
				} else if ("start_game".equals(command)) {
					if (handler.startGame(this.ID)) {
						this.myclient.inGame=true;
						inGame();
					} else {
						System.out.println("you are not the admin...");
						continue;
					}
				} else if ("players".equals(command)) {
					System.out.println(handler.whoIsInLobby(this.ID));
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

	/**
	 * in Game running method.
	 */
	private void inGame() {
		System.out.println("YOU ARE NOW IN GAME!");
		InGameMessageParser parser=new InGameMessageParser(input,this.ID,this.myclient);
		if(this.myclient.isAdvancedGame()){
			advGameSetupPhase();
		}	
		while(this.myclient.inGame){
				inGameCommandsPrint();
				try {
					String command=input.readLine().toString();
					if	   ("help".equals(command)){
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
							handler.chatMessage(this.ID,msg);
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
							handler.quitGame(this.ID,password);
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
		
	/**
	 * in case of advanced Rules the client should enter this endless loop until the setup phase is finished.
	 */
	private void advGameSetupPhase() {
		this.myclient.isInAdvSetupPhase=true;
		System.out.println("THIS IS A ADVANCED MODE GAME,ENTERING SETUP PHASE");
		while(this.myclient.isInAdvSetupPhase){
			
		}
		
	}
	
	
	private void helpMsgLobby() {
		System.out.println("\nCOMANDI LOBBY:\n"
				+ "chat to chat with the other inLobby players\n" + "exit_lobby to quit the current lobby\n"
						+ "start_game to start the game if you are the admin\n" + "help to see this\n"
						+ "players if you want to see who is in the lobby\n");
		
	}
	
	public void inGameCommandsPrint(){
		System.out.println("You are in the Game-mode menu;"
				+ "\nyour commands are:"
				+ "\nhelp - to see this message"
				+ "\naction - to show the list of actions"
				+ "\nchat - for sending a message to all the players in the game"
				+ "\ninfo- to see the list of possible infos"
				+ "\nquit- to exit the game!");
	}

	@Override
	public void showMsg(String s) throws RemoteException {
		System.out.println(s);
		
	}

	@Override
	public void changeUsername(String s) throws RemoteException {
		this.myclient.changeUsername(s);
	}

	@Override
	public void updateBoard(Board b) throws RemoteException {
		System.out.println("board received");
		this.myclient.setBoard(b);
		
	}


	public String insertPassword() {
		System.out.println("ENTER THE PASSWORD");
		String pass=null;
		try {
			pass = input.readLine();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return pass;
	}

	@Override
	public void setInGame(Boolean inGame) throws RemoteException {
		this.myclient.setInGame(inGame);
		
	}

	@Override
	public void setmyTurn(Boolean myT) throws RemoteException {
		this.myclient.setMyTurn(myT);
		if(myT){
			this.myclient.setActionPerformed(false);
		}
		
	}

	@Override
	public void excomunicationRound() throws RemoteException {
		// DARIO Auto-generated method stub
		
	}

	@Override
	public void updateGlobalVariables(CopyOfGlobalVariables o) {
		this.myclient.setGameGlobalVariables(o);
		
	}

	@Override
	public String getUsername() throws RemoteException {
		return this.myclient.getUsername();
	}

	@Override
	public Integer getID() throws RemoteException {
		return this.myclient.getID();
	}

	@Override
	public void setActionPerformed(boolean b) throws RemoteException {
		this.myclient.setActionPerformed(b);
		
	}

	@Override
	public void defaultBonusChoice(DefaultBonusChoiceMessage o) throws RemoteException {
		System.out.println("time to choose your personal default bonus.");
		new CommonActionCreatorRoutine();
		o.setChoice(CommonActionCreatorRoutine.askForSingleChoice(o.toString(), 0, o.getAdvDefBonus().size()));
		handler.submitDefaultBonus(this.ID,o);
	}

	@Override
	public void leaderDraftChoice(LeaderCardChoiceMessage o) throws RemoteException {
		System.out.println("Draft time:choose the leader card you want to keep");
		new CommonActionCreatorRoutine();
		o.setChoice(CommonActionCreatorRoutine.askForSingleChoice(o.toString(), 0, o.getLeaderCards().size()));
		handler.submitLeaderCardChoice(this.ID,o);
		
	}


	@Override
	public void setInAdvSetupPhase(boolean b) throws RemoteException {
		this.myclient.isInAdvSetupPhase=b;
		
	}
	
	
	
	
		
	}


