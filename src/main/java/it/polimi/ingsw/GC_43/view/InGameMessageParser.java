package it.polimi.ingsw.GC_43.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.rules.Timeout;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.actionCreations.CouncilPalaceActionCreationRoutine;
import it.polimi.ingsw.GC_43.model.actionCreations.HarvestActionCreationRoutine;
import it.polimi.ingsw.GC_43.model.actionCreations.MarketActionCreationRoutine;
import it.polimi.ingsw.GC_43.model.actionCreations.ProductionActionCreationRoutine;
import it.polimi.ingsw.GC_43.model.actionCreations.TowerActionCreationRoutine;

public class InGameMessageParser {

	private BufferedReader userIn;
	private ClientOutHandler clientHandler;
	private long timeoutLimit;

	public InGameMessageParser(BufferedReader userIn, ClientOutHandler clientOutHandler) {
		this.userIn=userIn;
		this.clientHandler=clientOutHandler;
		this.timeoutLimit=1000*60*1;
	}


	public void actionMenu() {
		Client myClient=this.clientHandler.getMyClient();
		Player myp=myClient.getMyPlayer();
		Board b=myClient.getBoard();
		long startTime = System.currentTimeMillis();
					while (System.currentTimeMillis()-startTime<=timeoutLimit&&myClient.myTurn) {
		    				printActionsMenu();
		    				try {
								String actionChoice = userIn.readLine().toString();
								if ("1".equals(actionChoice)&&myClient.myTurn) {
									startTime = System.currentTimeMillis();
									b.getDice().get(1).setDieValue(5);;
									myp.getFamilyMember(1).setDieToFamilyMember(1);
									MarketActionCreationRoutine ta=new MarketActionCreationRoutine(myp.getPlayerName(),myp,myClient.getBoard());
									if(ta.prepareAction()){
										myClient.sendObj(ta.getMarketAction());
										
									}
									
								}
								
								else if("2".equals(actionChoice)&&myClient.myTurn){
									startTime = System.currentTimeMillis();
									ProductionActionCreationRoutine pa=new ProductionActionCreationRoutine(myp.getPlayerName(),myp,myClient.getBoard());
									if(pa.prepareAction()){myClient.sendObj(pa.getProductionAction());}
									
								}
								else if("3".equals(actionChoice)&&myClient.myTurn){
									startTime = System.currentTimeMillis();
									HarvestActionCreationRoutine ha=new HarvestActionCreationRoutine(myp.getPlayerName(),myp,myClient.getBoard());
									if(ha.prepareAction()){myClient.sendObj(ha.getHarvestAction());}
									
								}
								else if("4".equals(actionChoice)&&myClient.myTurn){
									startTime = System.currentTimeMillis();
									CouncilPalaceActionCreationRoutine ca=new CouncilPalaceActionCreationRoutine(myp.getPlayerName(),myp,myClient.getBoard());
									if(ca.prepareAction()){myClient.sendObj(ca.getCouncilPalaceAction());}
									
								}
								else if("5".equals(actionChoice)&&myClient.myTurn){
									startTime = System.currentTimeMillis();
									TowerActionCreationRoutine ta=new TowerActionCreationRoutine(myp.getPlayerName(),myp,myClient.getBoard());
									if(ta.prepareAction()){myClient.sendObj(ta.getTowerAction());}
								}
								else if("6".equals(actionChoice)){
									startTime=0;
								}
							} catch (IOException e) {
								e.printStackTrace();
							}}
					if(System.currentTimeMillis()-startTime>=timeoutLimit&&startTime!=0){
						System.out.println("time is over!");
					}
					else{
						System.out.println("returning to main menu");
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
}
	
