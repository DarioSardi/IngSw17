package it.polimi.ingsw.GC_43.model;

import it.polimi.ingsw.GC_43.model.actionSpace.*;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_43.model.actionSpace.CouncilPalace;
import it.polimi.ingsw.GC_43.model.actionSpace.HarvestArea;
import it.polimi.ingsw.GC_43.model.actionSpace.Market;
import it.polimi.ingsw.GC_43.model.actionSpace.ProductionArea;
import it.polimi.ingsw.GC_43.model.actionSpace.Tower;
import it.polimi.ingsw.GC_43.model.cards.BuildingCard;
import it.polimi.ingsw.GC_43.model.cards.Card;
import it.polimi.ingsw.GC_43.model.cards.CharacterCard;
import it.polimi.ingsw.GC_43.model.cards.TerritoryCard;
import it.polimi.ingsw.GC_43.model.cards.VentureCard;
import it.polimi.ingsw.GC_43.model.effects.Effect;
import it.polimi.ingsw.GC_43.model.actionSpace.TowerColors;

public class Board implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6362079383828595044L;

	
	// CONFIGURATION SETTINGS
	private int phase;
	private int round;
	private int period;
	private int[] faithVictoryPoints;

	// CARDPOOLS
	private ArrayList territoryCardPool;
	private ArrayList buildingCardPool;
	private ArrayList characterCardPool;
	private ArrayList ventureCardPool;
	private ArrayList<Effect> excommunicationTilesEffects;

	// DICE
	private ArrayList<Die> dice;

	// AREAS
	private Market market;
	private CouncilPalace councilPalace;
	private HarvestArea harvestArea;
	private ProductionArea productionArea;
	private ArrayList<Tower> towers;

	// PLAYERS
	private ArrayList<String> playersID;
	private ArrayList<Player> players;

	// SAM-FRANCESCO: Remember to call board.initalize() only after creation of
	// board and
	// and initial settings of all stuff, delete this comment afterwards ;)

	public Board(ArrayList<String> playersID) {

		/*
		 * this.round=1; this.phase=1; this.period=1;
		 */

		this.playersID = new ArrayList<String>();
		this.playersID = playersID;
		
		this.setPeriod(0);
		this.setPhase(0);
		this.setRound(0);

		GlobalVariables.numberOfPlayers = playersID.size();

		createPlayers();

		createDice();

		createCards();

		createSpaces();

	}

	private void createCards() {
		this.buildingCardPool = new ArrayList<BuildingCard>();
		this.ventureCardPool = new ArrayList<VentureCard>();
		this.territoryCardPool = new ArrayList<TerritoryCard>();
		this.characterCardPool = new ArrayList<CharacterCard>();
		this.excommunicationTilesEffects = new ArrayList<Effect>();

	}

	private void createSpaces() {

		this.towers = new ArrayList<Tower>();
		this.harvestArea = new HarvestArea();
		this.productionArea = new ProductionArea();

	}

	public void addPlayerID(String playerID) {
		if (this.playersID == null)
			this.playersID = new ArrayList<String>();
		this.playersID.add(playerID);
	}

	public void initialize() {

		rollDice();
		System.out.println("\ndice rolled\n");
		setPlayersFamilyMembersValue();
		System.out.println("\nfamiliar ok\n");

		setTowerCards();
		System.out.println("\tower ok\n");

	}

	private void setPlayersFamilyMembersValue() {
		int j = 0;
		int i = 0;
		for (j = 0; j < this.getPlayers().size(); j++) {

			for (i = 1; i < this.getPlayers().get(j).getFamilyMembers().size(); i++) {
				this.getPlayers().get(j).getFamilyMember(i)
						.setDieToFamilyMember(this.getDice().get(i - 1).getDieValue());

			}
		}

	}

	/**
	 * create Players assigning incremental money to receive, No rolling dice to
	 * establish the first initial order of players.
	 */

	private void createPlayers() {
		if (this.players == null) {
			this.players = new ArrayList<Player>();
		}
		System.out.println("\n\nplayerss are " + this.getPlayersID().size() + "\n\n");

		try {
			int index = 0;
			this.getPlayers().add(new Player(this.getPlayersID().get(index), GlobalVariables.initialFirstPlayerCoins));
			index++;
			this.getPlayers().add(new Player(this.getPlayersID().get(index), GlobalVariables.initialSecondPlayerCoins));

			index++;
			if (index < this.playersID.size()) {
				this.getPlayers()
						.add(new Player(this.getPlayersID().get(index), GlobalVariables.initialThirdPlayerCoins));
				index++;

				if (index < this.playersID.size())
					this.getPlayers()
							.add(new Player(this.getPlayersID().get(index), GlobalVariables.initialFourthPlayerCoins));

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// SET CARDS READY IN THE TOWERS, @require : CARDS ORDERED BY ERA FROM
	// BOTTOM TO TOP

	private void setTowerCards() {

		int startingCardToDraw = this.round * GlobalVariables.towerCardsPerRound;
		System.out.println("Setting card on tower starting from card: "+startingCardToDraw);
		for (int index = 0; index < GlobalVariables.towerCardsPerRound; index++)
			this.getTowers().get(0).getFloors().get(index)
					.setCard((TerritoryCard) this.territoryCardPool.get(startingCardToDraw + index));
		for (int index = 0; index < GlobalVariables.towerCardsPerRound; index++)
			this.getTowers().get(1).getFloors().get(index)
					.setCard((CharacterCard) this.characterCardPool.get(startingCardToDraw + index));
		for (int index = 0; index < GlobalVariables.towerCardsPerRound; index++)
			this.getTowers().get(2).getFloors().get(index)
					.setCard((BuildingCard) this.buildingCardPool.get(startingCardToDraw + index));
		for (int index = 0; index < GlobalVariables.towerCardsPerRound; index++)
			this.getTowers().get(3).getFloors().get(index)
					.setCard((VentureCard) this.ventureCardPool.get(startingCardToDraw + index));
	}

	private void createDice() {
		this.dice = new ArrayList<Die>();

		for (int color = 0; color < GlobalVariables.numberOfDice; color++)
			this.dice.add(new Die(color));
	}

	private void rollDice() {
		for (int color = 0; color < GlobalVariables.numberOfDice; color++)
			this.dice.get(color).rollDie();
	}

	public void nextTurn() {
		this.round++;

		if (this.round % GlobalVariables.excommunicationRound == 0) {
			// TODO to decide whether to implement it on controller, it requires
			// interaction
			// checkPlayerExcommunication();
			this.period++;
		}

		establishNewPlayerOrder();

		resetAreas();

		setTowerCards();

		rollDice();

		setPlayersFamilyMembersValue();

	}

	private void resetAreas() {
		resetTowers();
		this.harvestArea.resetArea();
		this.productionArea.resetArea();
		this.councilPalace.resetArea();
		this.market.resetArea();

	}

	private void resetTowers() {
		for (int i = 0; i < this.getTowers().size(); i++)
			this.getTowers().get(i).resetArea();
	}

	public void nextPhase() {
		this.phase++;
	}

	public String getPhasePlayer() {
		System.out.println("\nplayer of phase on board"+this.phase);
		System.out.println("\nplayer of phase on board"+this.getPlayers().get(this.phase % this.getPlayers().size()).getPlayerName());

		return this.getPlayers().get(this.phase % this.getPlayers().size()).getPlayerName();
	}

	private void establishNewPlayerOrder() {
		if (!this.getCouncilPalace().entryOrder().isEmpty()) {
			ArrayList<Player> newPlayerOrder = new ArrayList<Player>();
			newPlayerOrder = this.getCouncilPalace().entryOrder();

			for (Player player : newPlayerOrder) {
				if (this.getPlayers().contains(player)) {
					this.getPlayers().remove(player);
				}
			}
			for (Player player : this.getPlayers()) {
				newPlayerOrder.add(player);
			}
			this.setPlayers(newPlayerOrder);

		}

	}

	public String towersToString() {
		String toString = "\nTowers in game are :\n\n";
		int number = 0;
		for (Tower tower : this.getTowers()) {
			toString = toString + number + ") " + tower.toStringAll() + "\n\n";
			number++;
		}
		return toString;
	}

	// GETTERS AND SETTERS

	public List<Die> getDice() {
		return this.dice;
	}

	public void setDice(ArrayList<Die> dice) {
		this.dice = dice;
	}

	public ArrayList getTerritoryCardPool() {
		return this.territoryCardPool;
	}

	public void setTerritoryCardPool(ArrayList territoryCardPool) {
		this.territoryCardPool = territoryCardPool;
	}

	public ArrayList getBuildingCardPool() {
		return this.buildingCardPool;
	}

	public void setBuildingCardPool(ArrayList buildingCardPool) {
		this.buildingCardPool = buildingCardPool;
	}

	public ArrayList getCharacterCardPool() {
		return this.characterCardPool;
	}

	public void setCharacterCardPool(ArrayList characterCardPool) {
		this.characterCardPool = characterCardPool;
	}

	public ArrayList getVentureCardPool() {
		return this.ventureCardPool;
	}

	public void setVentureCardPool(ArrayList ventureCardPool) {
		this.ventureCardPool = ventureCardPool;
	}

	public Market getMarket() {
		return this.market;
	}

	public void setMarket(Market market) {
		this.market = market;
	}

	public CouncilPalace getCouncilPalace() {
		return this.councilPalace;
	}

	public void setCouncilPalace(CouncilPalace councilPalace) {
		this.councilPalace = councilPalace;
	}

	public HarvestArea getHarvestArea() {
		return this.harvestArea;
	}

	public void setHarvestArea(HarvestArea harvestArea) {
		this.harvestArea = harvestArea;
	}

	public ProductionArea getProductionArea() {
		return this.productionArea;
	}

	public void setProductionArea(ProductionArea productionArea) {
		this.productionArea = productionArea;
	}

	public ArrayList<Tower> getTowers() {
		return this.towers;
	}

	public void setTowers(ArrayList<Tower> towers) {
		this.towers = towers;
	}

	public List<String> getPlayersID() {
		return this.playersID;
	}

	public void setPlayersID(ArrayList<String> playersID) {
		this.playersID = playersID;
	}

	public List<Player> getPlayers() {
		return this.players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public int getPhase() {
		return this.phase;
	}

	public void setPhase(int phase) {
		this.phase = phase;
	}

	public int getRound() {
		return this.round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public int getPeriod() {
		return this.period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public ArrayList<Effect> getExcommunicationTiles() {
		return this.excommunicationTilesEffects;
	}

	public void setExcommunicationTiles(ArrayList<Effect> excommunicationTilesEffects) {
		this.excommunicationTilesEffects = excommunicationTilesEffects;
	}

	public int[] getFaithVictoryPoints() {
		return this.faithVictoryPoints;
	}

	public void setFaithVictoryPoints(int[] faithVictoryPoints) {
		this.faithVictoryPoints = faithVictoryPoints;
	}

}