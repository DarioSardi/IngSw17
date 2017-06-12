package it.polimi.ingsw.GC_43.controller;

import java.io.IOException;

import it.polimi.ingsw.GC_43.controller.messages.LobbyCreationMsg;
import it.polimi.ingsw.GC_43.controller.messages.LobbyJoiningMsg;
import it.polimi.ingsw.GC_43.controller.messages.MainMenuMsg;

public class QuerySolver {

	ClientHandler cH;

	public QuerySolver(ClientHandler cH) {
		this.cH= cH;
	}
	
	public void messageAnalyzer(Object message){
		
		//main menu messages
		if(message instanceof MainMenuMsg)
		{
			MainMenuMsg m=(MainMenuMsg) message;
			int answer=m.getAnswer();
			if(answer==1){
				LobbyCreationMsg lobby=new LobbyCreationMsg();
				cH.sendChoiceTo(lobby);
			}
			if(answer==2){
				LobbyJoiningMsg lobby=new LobbyJoiningMsg();
				cH.sendChoiceTo(lobby);
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
		
		//
	}
}
