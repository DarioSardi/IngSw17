package it.polimi.ingsw.GC_43.controller.messages;

import java.util.Scanner;

public class MainMenuMsg extends ChoiceMessage {


	/**
	 * 
	 */
	private static final long serialVersionUID = -8991875641686943483L;
	
	public MainMenuMsg() {
		this.answer=-1;
	}
	

	@Override
	public void choose() {
		System.out.println("Menu di gioco");
		System.out.println("1. crea una nuova partita");
		System.out.println("2. seleziona una lobby");	
		System.out.println("3. esci");	
		
		while(this.answer==-1){
		System.out.println("sei nel menù,scegli!");
		
		//NEW LOBBY
		String choice=s.nextLine();
		
		if (choice.equals("1")) {
			this.answer=1;
			
			
			
		
		
		//ENTER LOBBY
		} else if (choice.equals("2")) {
			this.answer=2;
			
		
		//QUIT
		} else if (choice.equals("3")) {
			System.out.println("bb loser!");
			this.answer=3;
		}
		
		//FKING MORON
		else{
			System.out.println("invalid choice");
		}
		}	
	}
}
	
	

