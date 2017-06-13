package it.polimi.ingsw.GC_43.controller.messages;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.controller.Lobby;

public class LobbyCreationMsg extends ChoiceMessage {
	
	private ArrayList<Integer> lobbies;

	public LobbyCreationMsg(ArrayList<Integer> lobbies){
		this.lobbies=lobbies;
	}

	@Override
	public void choose() throws Exception{
		if(s.equals(null)){
			System.out.println("no scanner!");
			throw new Exception("no scanner found");
		}
		//DARIO e se non ci sono lobby disponibili?
		System.out.println("chose a number for the lobby you will create between 0 an 10");
		System.out.println("number already chosen are:");
		StringBuilder s = new StringBuilder();
		if (lobbies.size() != 0) {
			lobbies.stream().forEach(number -> s.append(number + ", "));
			System.out.println(s.toString());
		}
		else{
			System.out.println("no lobbies on this server!");
		}
		Boolean chosing=true;
		while(chosing==true){
		Integer choice=Integer.parseInt(this.s.nextLine());
			if(choice<=10&&choice>=0){
				if(lobbies.stream().anyMatch(number->number.equals(choice))){
					System.out.println("the number chosen is already taken");
					continue;
				}
				System.out.println("ook i will try to create a lobby");
				this.answer=choice;
				chosing=false;	
			}
			else
			{
				System.out.println("the nuber is not between 1 and 10!");
			}
		}
		
		
	}

}
