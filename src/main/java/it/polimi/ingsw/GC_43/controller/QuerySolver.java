package it.polimi.ingsw.GC_43.controller;

import java.io.IOException;

import it.polimi.ingsw.GC_43.controller.messages.LobbyCmd;
import it.polimi.ingsw.GC_43.controller.messages.LobbyCreationMsg;
import it.polimi.ingsw.GC_43.controller.messages.LobbyJoiningMsg;
import it.polimi.ingsw.GC_43.controller.messages.MainMenuMsg;

public class QuerySolver {

	ClientHandler cH;

	public QuerySolver(ClientHandler cH) {
		this.cH= cH;
	}
	
	public void messageAnalyzer(Object message){
		System.out.println("oggetto ricevuto!");
		//main menu messages
		if(message instanceof MainMenuMsg)
		{
			MainMenuMsg m=(MainMenuMsg) message;
			int answer=m.getAnswer();
			if(answer==1){
				
				LobbyCreationMsg lobby=new LobbyCreationMsg(cH.getMyServer().getLobbiesNumbers());
				cH.sendObject(lobby);
			}
			if(answer==2){
				LobbyJoiningMsg lobby=new LobbyJoiningMsg();
				cH.sendObject(lobby);
			}
			if(answer==3){
				try {
					cH.socketOut.close();
					cH.socketIn.close();
					cH.socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			
			}
		}
		
		//lobby creation
		else if(message instanceof LobbyCreationMsg)
		{
			LobbyCreationMsg l=(LobbyCreationMsg) message;
			int answer=l.answer;
			this.cH.getMyServer().newLobby(cH, answer);
		}
		
		//lobby joining
		else if(message instanceof LobbyJoiningMsg){
			LobbyJoiningMsg lj=(LobbyJoiningMsg) message;
			//DARIO aggiungere!
			
		}
		
		
		//lobby commands
		else if(message instanceof LobbyCmd){
			LobbyCmd lc=(LobbyCmd) message;
			String command=lc.cmd;
			if(command.equals("exit_lobby")){
				//DARIO rimuovi lobby
				//inlobby=false;
				//sendStringTo("stai uscendo dalla lobby");
			}
			else if(command.equals("chat")){
				cH.getLobby().broadcastMsg(lc.msg, cH);
			}
			else if(command.equals("start_game")){
				if(cH.getLobby().startGame(cH)){
					//status change in game
				}
				else{
					this.cH.sendStringTo("you are not the admin...");
					
				}
			}
			else{
				this.cH.sendStringTo("nulla di che...");
				}
		}
	
		else{
			System.out.println("non ho idea di cosa mi sia arrivato...");
		}
	}
}
