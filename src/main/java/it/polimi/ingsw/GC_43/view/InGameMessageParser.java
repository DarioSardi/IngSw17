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

	public InGameMessageParser(BufferedReader userIn, ClientOutHandler clientOutHandler) {
		this.userIn=userIn;
		this.clientHandler=clientOutHandler;
	}


	public void actionMenu() {
		ExecutorService service = Executors.newSingleThreadExecutor();
		Client myClient=this.clientHandler.getMyClient();
		Player myp=myClient.getMyPlayer();
		Board b=myClient.getBoard();
		try {
		    Runnable r = new Runnable() {
		        @Override
		        public void run() {
		        	
		    		while (!myClient.actionPerformed) {
		    			try {
		    				printActionsMenu();
		    				String actionChoice = userIn.readLine().toString();
		    				if ("1".equals(actionChoice)) {
		    					b.getDice().get(1).setDieValue(5);;
		    					myp.getFamilyMember(1).setDieToFamilyMember(1);
		    					MarketActionCreationRoutine ta=new MarketActionCreationRoutine(myp.getPlayerName(),myp,myClient.getBoard());
		    					if(ta.prepareAction()){myClient.sendObj(ta.getMarketAction());}
		    					
		    				}
		    				
		    				else if("2".equals(actionChoice)){
		    					ProductionActionCreationRoutine pa=new ProductionActionCreationRoutine(myp.getPlayerName(),myp,myClient.getBoard());
		    					if(pa.prepareAction()){myClient.sendObj(pa.getProductionAction());}
		    					
		    				}
		    				else if("3".equals(actionChoice)){
		    					HarvestActionCreationRoutine ha=new HarvestActionCreationRoutine(myp.getPlayerName(),myp,myClient.getBoard());
		    					if(ha.prepareAction()){myClient.sendObj(ha.getHarvestAction());}
		    					
		    				}
		    				else if("4".equals(actionChoice)){
		    					CouncilPalaceActionCreationRoutine ca=new CouncilPalaceActionCreationRoutine(myp.getPlayerName(),myp,myClient.getBoard());
		    					if(ca.prepareAction()){myClient.sendObj(ca.getCouncilPalaceAction());}
		    					
		    				}
		    				else if("5".equals(actionChoice)){
		    					TowerActionCreationRoutine ta=new TowerActionCreationRoutine(myp.getPlayerName(),myp,myClient.getBoard());
		    					if(ta.prepareAction()){myClient.sendObj(ta.getTowerAction());}
		    				}
		    				
		    				
		    				
		    				
		    				
		    				
		    			} catch (IOException e) {
		    				e.printStackTrace();
		    			} 
		    			
		    		}
		        }
		    };

		    Future<?> f = service.submit(r);

		    f.get(1, TimeUnit.MINUTES);     // attempt the task for two minutes
		}
		catch (final InterruptedException e) {
		    // The thread was interrupted during sleep, wait or join
		}
		catch (final TimeoutException e) {
		    System.out.println("time is over, turn passed");
		}
		catch (final ExecutionException e) {
		    // An exception from within the Runnable task
		}
		finally {
		    service.shutdown();
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
	
