package it.polimi.ingsw.GC_43.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.actions.Action;

public class ClientHandlerSocket implements ClientHandler,Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5966200383345732473L;
	private Socket socket;
	private int ID;
	private Server myServer;
	private String username;
	private ObjectInputStream socketIn;
	private ObjectOutputStream socketOut;
	private Lobby lobby;
	private boolean Game;
	private boolean myturn;
	private boolean inLobby;
	private boolean exit;

	public ClientHandlerSocket(int ID,Server myServer){
		this.lobby=null;
		this.inLobby=false;
		this.Game=false;
		this.ID=ID;
		this.myServer=myServer;
	}
	
	public void setSocket(Socket socket) throws IOException{
		this.socket = socket;
		this.socketIn = new ObjectInputStream(this.socket.getInputStream());
		this.socketOut = new ObjectOutputStream(this.socket.getOutputStream());
	}
	
	public Lobby getLobby() {
		return lobby;
	}
	
	public void setLobby(Lobby lobby) {
		this.lobby = lobby;
	}

	public String getUsername() {
		return this.username;
	}

	@Override
	public void run() {
		this.exit = false;
		//SETUP
		readUsername();
		sendID();
		//MENU
		while (!this.exit) {
			try {
				
				mainMenuChoicesPrint();
				mainMenuChoicesRoutineSocket();
			} catch (RemoteException e) {
				System.err.println("client DC!");
			}
			
		 }
		//CLOSE CLIENT
		closeCLientHandler();
	}
	
	private void mainMenuChoicesRoutineSocket() throws RemoteException {
		//NEW LOBBY
		String choice=readMsg();
		if ("1".equals(choice)) {
			sendMsgTo("Lobbies on this server:");
			sendMsgTo(myServer.getLobbiesToString());
			sendMsgTo("select a number for the lobby");
			Integer lobbyNumber = Integer.parseInt(readMsg());
			Integer maxPlayers=0;
			while (!(maxPlayers<=5&&maxPlayers>=2)) {
				sendMsgTo("select the max number of players in the lobby/game (min 2 max 5)");
				maxPlayers = Integer.parseInt(readMsg());
			}
			String advancedMode=null;
			Boolean advChoice=false;
			while(!("yes".equals(advancedMode)||"no".equals(advancedMode))){
				sendMsgTo("do you want to play with the advanced mode?");
				advancedMode=readMsg();
			}
			if("yes".equals(advancedMode)){
				advChoice=true;
			}
			if (myServer.newLobby(this, lobbyNumber,maxPlayers,advChoice)) {
				sendMsgTo("you are in the lobby now!");
				inLobby();
			} else {
				sendMsgTo("lobby number unaviable");
			}
		
		//ENTER LOBBY
		} else if ("2".equals(choice)) {
			sendMsgTo("select a lobby to join!");
			sendMsgTo(myServer.getLobbiesToString());
			Integer lobbyNumber=Integer.parseInt(readMsg());
			if(myServer.joinLobby(lobbyNumber, this)){
				inLobby();
			}
			else{sendMsgTo("unable to join lobby");}
			
		
		//QUIT
		} else if ("3".equals(choice)) {
			this.exit = true;
		}
		
		//FKING MORON
		else{
			sendMsgTo("invalid choice");
		}
		
	}

	public void closeCLientHandler(){
		try {
			socketOut.close();
			socketIn.close();
			socket.close();
		} catch (Exception e) {
			
		}
	}
	
	public void mainMenuChoicesPrint() {
		sendMsgTo("\n"
				+ "MAIN MENU:\n"
				+ "1.Create new lobby!\n"
				+ "2.Join a lobby\n"
				+ "3.Exit the game\n");
	}

	public void sendID() {
		sendMsgTo(String.valueOf(this.ID));
	}

	public void readUsername() {
		this.username=readMsg();
		System.out.println("username of the player is "+this.username);
	}

	//IN LOBBY
	private void inLobby() throws RemoteException {
		inLobby=true;
		while(inLobby){
			if (!this.Game) {
				helpMsgLobby();
				String command = readMsg();
				if (command.equals("exit_lobby")) {
					inLobby = false;
					this.lobby.removePlayer(this);
					sendMsgTo("exiting the lobby");
				} else if ("chat".equals(command)) {
					sendMsgTo("write the message that you want to send!");
					String msg = readMsg();
					lobby.broadcastMsg(msg, this);
				} else if ("start_game".equals(command)) {
					if (lobby.startGame(this)) {
						this.Game=true;
						inGame();
					} else {
						sendMsgTo("you are not the admin...");
						continue;
					}
				} else if ("players".equals(command)) {
					sendMsgTo(lobby.whoIsIn());
				} else if ("help".equals(command)) {
					helpMsgLobby();
				} else {
					continue;

				} 
			}
			else{
				inGame();
			}
		}
		
	}
	
	public void helpMsgLobby() {
		sendMsgTo(
				"\nCOMANDI LOBBY:\n"
				+ "chat to chat with the other inLobby players\n" + "exit_lobby to quit the current lobby\n"
						+ "start_game to start the game if you are the admin\n" + "help to see this\n"
						+ "players if you want to see who is in the lobby\n");		
	}

	private void inGame() {
		sendMsgTo("You are now in game!");
		sendMsgTo("system_ingame_switch");
		while(this.Game){
			receive();
		}
		
		
	}

	private void receive() {
		try {
			Object o=socketIn.readObject();
			System.out.println(o.toString());
			if(o instanceof ChatMsg&&this.lobby!=null){
				ChatMsg msg=(ChatMsg) o;
				System.out.println("chat message :"+msg.getMsg());
				this.lobby.broadcastMsg(msg.getMsg(),this);
			}
			else if(o instanceof Action&&this.myturn){
				System.out.println("action received!"+ o.toString());
				Action action=(Action) o;
				this.lobby.getController().submitClientAction(action);
			}
			else if(o instanceof Action&&!this.myturn){
				this.lobby.broadcastMsg("\n\nthe player "+this.getUsername()+"is trying to cheat and think that the GM is a noob, cute!\n\n", this);
			}
			else if(o instanceof QuitMsg){
				String password=((QuitMsg) o).getPassword();
				this.lobby.disconnectPlayer(this, password);
				this.lobby=null;
				this.inLobby=false;
				this.Game=false;

			}
			else if(o instanceof DefaultBonusChoiceMessage){
				this.lobby.getController().submitDefaultBonusChoice((DefaultBonusChoiceMessage)o,this);
			}
			else if(o instanceof LeaderCardChoiceMessage){
				this.lobby.getController().submitLeaderCardChoice((LeaderCardChoiceMessage) o,this);
			}
			
			
			else if(o instanceof ExcommunicationChoiceMsg){
				Boolean answer=((ExcommunicationChoiceMsg) o).getChoice();
				this.lobby.getController().submitExcommunicationChoice(this.username, answer);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	public void sendObject(Object object){
		try {
			System.out.println("sent "+object);
			if(object instanceof Board){
				socketOut.reset();
			}
			socketOut.writeObject(object);
			socketOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
	public SimpleMessage receiveMsg(){
		try {
			Object o=socketIn.readObject();
			if(o instanceof SimpleMessage){
				return (SimpleMessage) o;
			}
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public String readMsg(){
		return receiveMsg().getMsg();
	}
	
	public String readPassword(){
		try {
			Object o=socketIn.readObject();
			return ((SimpleMessage)o).getMsg();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public String toString() {
		return username;
	}

	public void setMyturn(boolean myturn) {
		if(myturn){
			sendMsgTo("now_is_my_turn");
		}
		else{
		sendMsgTo("end_of_my_turn");
		}
		this.myturn = myturn;
	}

	public void changeName(String rmiUsername) {
			sendMsgTo("choose another username, " + this.getUsername() + " is already taken");
			String newUsername = receiveMsg().getMsg();
			this.username = newUsername;
			sendObject(new ChangeUsernameMessage(newUsername));
	}

	@Override
	public void setGame(boolean b) {
		this.Game=b;
		
	}

	
	
	
}
