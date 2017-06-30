package it.polimi.ingsw.GC_43.view;

import java.io.IOException;
import java.io.ObjectInputStream;

import it.polimi.ingsw.GC_43.controller.ChangeUsernameMessage;
import it.polimi.ingsw.GC_43.controller.DefaultBonusChoiceMessage;
import it.polimi.ingsw.GC_43.controller.LeaderCardChoiceMessage;
import it.polimi.ingsw.GC_43.controller.SimpleMessage;
import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.CopyOfGlobalVariables;
import it.polimi.ingsw.GC_43.model.actionCreations.CommonActionCreatorRoutine;

public class ClientInHandler implements Runnable {

	private ObjectInputStream socketIn;
	private Client myClient;

	public ClientInHandler(ObjectInputStream socketIn,Client myClient) {
		this.socketIn=socketIn;
		this.myClient=myClient;
		this.myClient.inMenu=true;
	}

	@Override
	public void run() {
		SimpleMessage s;
		try {
			s = (SimpleMessage) socketIn.readObject();
			myClient.setID(Integer.parseInt(s.getMsg()));
			System.out.println("client ID is now: " + myClient.getID());
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		while(this.myClient.online){
			receiveMsg();
			}
		}
	private void parseGameUpdates() {
		//System.out.println("parser delgli update pronto!");
		System.out.println("press enter to continue");
		while(this.myClient.inGame){
			receiveMsg();
			}
		}
		
	
	
	
	
	@Override
	public String toString() {
		return "handler with ID: "+myClient.getID();
	}
	
	public void receiveMsg(){
		try {
			Object o=socketIn.readObject();
			if(o instanceof SimpleMessage){
				readMsg((SimpleMessage) o);
			}
			else if(o instanceof Board){
				this.myClient.setBoard((Board) o);
			}
			else if(o instanceof CopyOfGlobalVariables){
				this.myClient.setGameGlobalVariables((CopyOfGlobalVariables) o);
			}
			else if(o instanceof ChangeUsernameMessage){
				this.myClient.changeUsername(((ChangeUsernameMessage)o).getMsg());
			}
			else if(o instanceof DefaultBonusChoiceMessage){
				DefaultBonusChoiceMessage dbm=(DefaultBonusChoiceMessage)o;
				System.out.println("time to choose your personal default bonus.");
				new CommonActionCreatorRoutine();
				dbm.setChoice(CommonActionCreatorRoutine.askForSingleChoice(dbm.toString(), 0, dbm.getAdvDefBonus().size()));
				this.myClient.sendObj(dbm, this.myClient.getID());
			}
			else if(o instanceof LeaderCardChoiceMessage){
				System.out.println("Draft time:choose the leader card you want to keep");
				LeaderCardChoiceMessage l=(LeaderCardChoiceMessage)o;
				new CommonActionCreatorRoutine();
				l.setChoice(CommonActionCreatorRoutine.askForSingleChoice(o.toString(), 0, l.getLeaderCards().size()));
				this.myClient.sendObj(l,this.myClient.getID());
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		
	}
	
	public void readMsg(SimpleMessage s){
		String line=s.getMsg();
		if("system_ingame_switch".equals(line)){
			this.myClient.inGame=true;
			parseGameUpdates();
				
			}
		if("system_outgame_switch".equals(line)){
			this.myClient.inGame=false;
			
		}
		
		else if("now_is_my_turn".equals(line)){
			System.out.println("IS NOW MY TURN!");
			this.myClient.myTurn=true;
			this.myClient.setActionPerformed(false);
			}
		else if("end_of_my_turn".equals(line)){
			System.out.println("END OF MY TURN!");
			this.myClient.myTurn=false;
			}
		else if("excommunication_round".equals(line)){
			System.out.println("\n\n\nEXCOMMUNICATION ROUND PRESS ENTER OR FINISH THE MENU QUERY TO ANSWER THE CHOICE, YOU HAVE 2 MINUTES!\n\n");
			this.myClient.excommunicationRound=true;
		}
		else if("Paction_performed".equals(line)){
			this.myClient.setActionPerformed(true);
		}
		else if("advChoices_ended".equals(line)){
			this.myClient.isInAdvSetupPhase=false;
		}
		
		else{
			System.out.println(line);
		}
	}
	
	public Object receiveObj(){
		try {
			return this.socketIn.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
