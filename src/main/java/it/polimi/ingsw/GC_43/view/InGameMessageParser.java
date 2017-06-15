package it.polimi.ingsw.GC_43.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.actionCreations.ProductionActionCreationRoutine;
import it.polimi.ingsw.GC_43.model.actionSpace.MarketActionSpace;
import it.polimi.ingsw.GC_43.model.actionSpace.Space;

public class InGameMessageParser {

	private Scanner userIn;
	private Client myClient;

	public InGameMessageParser(Scanner userIn, Client myClient) {
		this.userIn=userIn;
		this.myClient=myClient;
	}

	public InGameMessageParser(BufferedReader userIn2, Client myClient2) {
		// TODO Auto-generated constructor stub
	}

	public void actionMenu(BufferedReader userIn,Client myclient) {
		//TIMER ALL'INGRESSO DI QUESTA SEZIONE DA RESETTARE SE ESCO.
		while (!myclient.actionPerformed) {
			try {
				printActionsMenu();
				String actionChoice = userIn.readLine().toString();
				if ("1".equals(actionChoice)) {
					/*if(this.myClient.getBoard().getMarket().getMarketActionSpaces()!=null){
					ArrayList<MarketActionSpace> markets = this.myClient.getBoard().getMarket().getMarketActionSpaces();
					while (!marketSubMenu(markets));
					}
					else{
						System.out.println("nessun market ancora presente?!");
					}*/
					System.out.println("\nda inizializzare bene, prova un altra opzione\n");
				}
				
				else if("2".equals(actionChoice)){
					if(this.myClient.getBoard().getProductionArea().getSpaces()!=null){
						Space primaryProduction = this.myClient.getBoard().getProductionArea().getPrimarySpace();
						Space secondaryProduction = this.myClient.getBoard().getProductionArea().getSecondarySpace();
						while (!productionAreaSubMenu(primaryProduction,secondaryProduction));
						}
						else{
							System.out.println("nessun market ancora presente?!");
						}
				}
				
				
				
				
				
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
			
		}
		
		
	}
	

	private boolean productionAreaSubMenu(Space primaryProduction, Space secondaryProduction) {
		Boolean skipSecondary=false;
		System.out.println("hai deciso di entrare nell'area di produzione");
		if(!primaryProduction.isOccupied()){
			System.out.println("1) entra nello spazio primario (LIBERO)");
		}
		else{
			System.out.println("1) entra nello spazio primario (OCCUPATO)");
		}
		if (secondaryProduction!=null) {
			if (!secondaryProduction.isOccupied()) {
				System.out.println("2) entra nello spazio secondario con malus in ingresso (LIBERO)");
			} else {
				System.out.println("2) entra nello spazio secondario con malus in ingresso (OCCUPATO)");
			} 
		}
		else{
			skipSecondary=true;
			System.out.println("2) spazio secondario non disponibile");
		}
		System.out.println("3) annulla");
		String actionChoice=this.userIn.nextLine();
		if(Integer.parseInt(actionChoice)==1){
			//ProductionActionCreationRoutine pa= new ProductionActionCreationRoutine(this.myClient.getID(), player, board)
			return true;
		}
		return false;
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
