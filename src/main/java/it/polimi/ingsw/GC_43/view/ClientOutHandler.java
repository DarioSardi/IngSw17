package it.polimi.ingsw.GC_43.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;

import it.polimi.ingsw.GC_43.controller.ChatMsg;
import it.polimi.ingsw.GC_43.controller.ExcommunicationChoiceMsg;
import it.polimi.ingsw.GC_43.controller.QuitMsg;
import it.polimi.ingsw.GC_43.controller.SimpleMessage;
import it.polimi.ingsw.GC_43.view.GUI.menuFrame.GameBoard;

public class ClientOutHandler implements Runnable {

	private ObjectOutputStream socketOut;
	private Client myClient;
	private int ID;

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
		this.myClient.excommunicationRound=false;
		BufferedReader userIn= this.myClient.inKeyboard;
		sendMsgTo(myClient.getUsername());
		while(!Thread.currentThread().isInterrupted()){
			if (this.myClient.online) {
				try {
					if (!this.myClient.inGame) {
						String msg = userIn.readLine().toString();
						if (msg!=null) {
							sendMsgTo(msg);
						}
					} else if (this.myClient.inGame) {
						if(!this.myClient.excommunicationRound){
							
							inGameParser(userIn);
							
						}
						if(this.myClient.excommunicationRound){
							excomunicationRound(userIn);
							this.myClient.excommunicationRound=false;
						}
					}
				} catch (IOException e) {
				
					e.printStackTrace();
					
				} 
			}
		}

	}
	
	private void inGameParser(BufferedReader userIn) throws NumberFormatException, IOException {
		
		System.out.println("switched to in-game commands");
		this.ID=this.myClient.getID();
		InGameMessageParser parser=new InGameMessageParser(userIn,ID,this.myClient);
		if(this.myClient.isAdvancedGame()){
			advGameSetupPhase(userIn);
		}
		while(this.myClient.inGame){
			this.myClient.printMyData();
			inGameCommandsPrint();
			try {
				String command=userIn.readLine().toString();
				if("help".equals(command)){
					inGameCommandsPrint();
				}
				else if("action".equals(command)&&this.myClient.myTurn){
					parser.mainMenuParser();				
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
				
				else if("quit".equals(command)){
					System.out.println("are you sure? write 'yes' to exit the game");
					String quitString=userIn.readLine().toString();
					if(quitString.equalsIgnoreCase("yes")){
						System.out.println("select a password to re-enter the game");
						String password=userIn.readLine().toString();
						System.out.print("now you are leaving the game!Press Enter to continue to the lobby");
						QuitMsg quit=new QuitMsg(password, this.myClient.getID());
						this.sendObj(quit);
						this.myClient.setInGame(false);
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
	 * @param userIn 
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	private void advGameSetupPhase(BufferedReader userIn) throws NumberFormatException, IOException {
		this.myClient.isInAdvSetupPhase=true;
		System.out.println("THIS IS A ADVANCED MODE GAME,ENTERING SETUP PHASE");
		while(this.myClient.isInAdvSetupPhase){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			if(this.myClient.getDefaultBonusChoice()!=null){
				System.out.println(this.myClient.getDefaultBonusChoice().toString());
				Integer choice=Integer.parseInt(userIn.readLine());
				if(choice>=0&&choice<this.myClient.getDefaultBonusChoice().getAdvDefBonus().size()){
					this.myClient.getDefaultBonusChoice().setChoice(choice);
					this.myClient.sendObj(this.myClient.getDefaultBonusChoice(), this.ID);
					this.myClient.setDefaultBonusChoice(null);
					System.out.println("choice sent");
				}
				else{
					System.out.println("wrong answer");
				}
			}
			if(this.myClient.getLeaderCardChoice()!=null){
				System.out.println(this.myClient.getLeaderCardChoice().toString());
				Integer choice=Integer.parseInt(userIn.readLine());
				if(choice>=0&&choice<this.myClient.getLeaderCardChoice().getLeaderCards().size()){
					this.myClient.getLeaderCardChoice().setChoice(choice);
					this.myClient.sendObj(this.myClient.getLeaderCardChoice(), this.ID);
					this.myClient.setLeaderCardChoice(null);
					System.out.println("choice sent");}
				else{
					System.out.println("wrong answer");
				}
			}
			
			
		}
		
	}


	public void excomunicationRound(BufferedReader userIn) {
		Boolean done=false;
		try {
			while (!done) {
				System.out.println("EXCOMUNICATION TIME! ALL PROCESS ABORTED");
				String choice = userIn.readLine();
				if ("yes".equals(choice)) {
					System.out.println("good boy");
					sendObj(new ExcommunicationChoiceMsg(true));
					done = true;

				} else if ("no".equals(choice)) {
					System.out.println("WTF DI U SAY ABOUT THE POPE?");
					sendObj(new ExcommunicationChoiceMsg(false));
					done = true;
				} else
					System.out.println("Shame! Not even a yes or no answer...retry!");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
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
