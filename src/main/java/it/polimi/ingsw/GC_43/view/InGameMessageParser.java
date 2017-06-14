package it.polimi.ingsw.GC_43.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import it.polimi.ingsw.GC_43.model.actionSpace.MarketActionSpace;

public class InGameMessageParser {

	private Scanner userIn;
	private Client myClient;

	public InGameMessageParser(Scanner userIn, Client myClient) {
		this.userIn=userIn;
		this.myClient=myClient;
	}

	public void actionMenu(Scanner userIn,Client myclient) {
		//TIMER ALL'INGRESSO DI QUESTA SEZIONE DA RESETTARE SE ESCO.
		while (!myclient.actionPerformed) {
			printActionsMenu();
			System.out.println(myclient.getBoard().toString());
			String actionChoice = userIn.nextLine();
			if ("1".equals(actionChoice)) {
				ArrayList<MarketActionSpace> markets = this.myClient.getBoard().getMarket().getMarketActionSpaces();
				while (!marketSubMenu(markets));
			} 
			
		}
		
		
	}
	
	public void printActionsMenu(){
		System.out.println("MENU AZIONE!");
		System.out.println("Seleziona l'area a cui vuoi accedere:");
		System.out.println("1) mercato");
		System.out.println("2) area produzione");
		System.out.println("3) area raccolto");
		System.out.println("4) palazzo del consiglio");
		System.out.println("5) torri");
		System.out.println("6) annulla azione");
	}
	
	public boolean marketSubMenu(List<MarketActionSpace> markets){
		int i=0;
		for (i = 0;  i< markets.size(); i++) {
			System.out.println(i + ") per ricevere: " + markets.get(i).getBonus().toString());
		}
		System.out.println(i+") per annullare");
		System.out.println("seleziona uno spazio del mercato dove andare");
		String actionChoice=this.userIn.nextLine();
		if(0>=Integer.parseInt(actionChoice)||i<Integer.parseInt(actionChoice)){
			System.out.println("eseguo azione sullo spazio numero "+ Integer.parseInt(actionChoice));
			//AZIONE MERCATO 
			//se viene eseguita
			//this.myClient.actionPerformed=true;
			return true;
		}
		else if(Integer.parseInt(actionChoice)==i){
			return true;
		}
		else{
			System.out.println("not a valid number!");
			return false;
		}
	}

}