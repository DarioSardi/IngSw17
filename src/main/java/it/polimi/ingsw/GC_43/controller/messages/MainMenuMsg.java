package it.polimi.ingsw.GC_43.controller.messages;

import java.util.Scanner;

public class MainMenuMsg extends ChoiceMessage {

	Scanner s;
	Integer subChoice;
	
	public MainMenuMsg() {
		this.s = new Scanner(System.in);
		this.answer=-1;
		this.subChoice=-1;
	}

	@Override
	public void choose() {
		
		while(this.answer==-1){
		System.out.println("sei nel menù,scegli!");
		
		//NEW LOBBY
		String choice=s.nextLine();
		
		if (choice.equals("1")) {
			System.out.println("seleziona un numero di lobby");
			this.subChoice = Integer.parseInt(s.nextLine());
			System.out.println("la scelta è "+this.subChoice);
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
	
	

