package it.polimi.ingsw.GC_43.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.rmi.RemoteException;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.actionCreations.CouncilPalaceActionCreationRoutine;
import it.polimi.ingsw.GC_43.model.actionCreations.HarvestActionCreationRoutine;
import it.polimi.ingsw.GC_43.model.actionCreations.LeaderCardActionCreationRoutine;
import it.polimi.ingsw.GC_43.model.actionCreations.MarketActionCreationRoutine;
import it.polimi.ingsw.GC_43.model.actionCreations.ProductionActionCreationRoutine;
import it.polimi.ingsw.GC_43.model.actionCreations.TowerActionCreationRoutine;
import it.polimi.ingsw.GC_43.model.actions.EndPhaseAction;

public class InGameMessageParser {

	private BufferedReader userIn;
	// private ClientOutHandler clientHandler;
	private long timeoutLimit;
	private Integer ID;
	private Client myClient;

	public InGameMessageParser(BufferedReader userIn, Integer ID, Client myclient) {
		this.userIn = userIn;
		this.ID = ID;
		this.timeoutLimit = (long) 1000 * 60 * 1;
		this.myClient = myclient;
	}

	public void mainMenuParser() throws RemoteException {

		Player myp = myClient.getMyPlayer();
		Board b = myClient.getBoard();
		long startTime = System.currentTimeMillis();
		while (System.currentTimeMillis() - startTime <= timeoutLimit && myClient.myTurn) {
			printActionsMenu();
			try {
				String actionChoice = userIn.readLine();
				if ("1".equals(actionChoice) && myClient.myTurn&&!myClient.getActionPerformed()) {
					startTime = System.currentTimeMillis();
					b.getDice().get(1).setDieValue(5);
					;
					myp.getFamilyMember(1).setDieToFamilyMember(1);
					MarketActionCreationRoutine ta = new MarketActionCreationRoutine(myp.getPlayerName(), myp,
							myClient.getBoard());
					if (ta.prepareAction()) {
						myClient.sendObj(ta.getMarketAction(), this.ID);

					}

				}

				else if ("2".equals(actionChoice) && myClient.myTurn&&!myClient.getActionPerformed()) {
					startTime = System.currentTimeMillis();
					ProductionActionCreationRoutine pa = new ProductionActionCreationRoutine(myp.getPlayerName(), myp,
							myClient.getBoard());
					if (pa.prepareAction()) {
						myClient.sendObj(pa.getProductionAction(), this.ID);
					}

				} else if ("3".equals(actionChoice) && myClient.myTurn&&!myClient.getActionPerformed()) {
					startTime = System.currentTimeMillis();
					HarvestActionCreationRoutine ha = new HarvestActionCreationRoutine(myp.getPlayerName(), myp,
							myClient.getBoard());
					if (ha.prepareAction()) {
						myClient.sendObj(ha.getHarvestAction(), this.ID);
					}

				} else if ("4".equals(actionChoice) && myClient.myTurn&&!myClient.getActionPerformed()) {
					startTime = System.currentTimeMillis();
					CouncilPalaceActionCreationRoutine ca = new CouncilPalaceActionCreationRoutine(myp.getPlayerName(),
							myp, myClient.getBoard());
					if (ca.prepareAction()) {
						myClient.sendObj(ca.getCouncilPalaceAction(), this.ID);
					}

				} else if ("5".equals(actionChoice) && myClient.myTurn&&!myClient.getActionPerformed()) {
					startTime = System.currentTimeMillis();
					TowerActionCreationRoutine ta = new TowerActionCreationRoutine(myp.getPlayerName(), myp,
							myClient.getBoard());
					if (ta.prepareAction()) {
						myClient.sendObj(ta.getTowerAction(), this.ID);
					}
				} else if ("6".equals(actionChoice)) {
					startTime = System.currentTimeMillis();
					EndPhaseAction pass = new EndPhaseAction(String.valueOf(this.ID), myp);
					this.myClient.submitAction(pass, this.ID);
					this.myClient.myTurn=false;

				} else if ("7".equals(actionChoice)) {
					startTime = 0;
				} else if ("8".equals(actionChoice) && myClient.myTurn) {
					startTime = System.currentTimeMillis();
					LeaderCardActionCreationRoutine leader = new LeaderCardActionCreationRoutine(myp.getPlayerName(), myp,
							myClient.getBoard());
					if (leader.prepareAction()) {
						myClient.sendObj(leader.getLeaderCardAction(), this.ID);
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (System.currentTimeMillis() - startTime >= timeoutLimit && startTime != 0) {
			System.out.println("time is over!");
			endTurn(myp);
		} else {
			System.out.println("returning to main menu");
		}
	}

	public void endTurn(Player myp) throws RemoteException {
		EndPhaseAction pass = new EndPhaseAction(String.valueOf(this.ID), myp);
		myClient.sendObj(pass, ID);
		this.myClient.myTurn=false;
	}

	public void printActionsMenu() {
		System.out.println("MENU AZIONE!");
		System.out.println("Seleziona l'area a cui vuoi accedere:");
		System.out.println("1) mercato");
		System.out.println("2) area produzione");
		System.out.println("3) area raccolto");
		System.out.println("4) palazzo del consiglio");
		System.out.println("5) torri");
		System.out.println("6) passa turno");
		System.out.println("7) annulla azione");
		System.out.println("8) azione leader card"); // DARIO da mettere con la
														// condizione di
														// modalità avanzata
	}
}
