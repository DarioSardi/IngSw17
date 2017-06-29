package it.polimi.ingsw.GC_43.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_43.model.actionSpace.CouncilPalace;
import it.polimi.ingsw.GC_43.model.actionSpace.HarvestArea;
import it.polimi.ingsw.GC_43.model.actionSpace.Market;
import it.polimi.ingsw.GC_43.model.actionSpace.ProductionArea;
import it.polimi.ingsw.GC_43.model.actionSpace.Tower;
import it.polimi.ingsw.GC_43.model.cards.BuildingCard;
import it.polimi.ingsw.GC_43.model.cards.CharacterCard;
import it.polimi.ingsw.GC_43.model.cards.LeaderCard;
import it.polimi.ingsw.GC_43.model.cards.TerritoryCard;
import it.polimi.ingsw.GC_43.model.cards.VentureCard;
import it.polimi.ingsw.GC_43.model.effects.Effect;

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
	private ArrayList<LeaderCard> leaderCardPool;

	// LEADER CARDS PLAYER IN MEMORY
	private ArrayList<LeaderCard> leaderCardsPlayed;

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
	
	
	//ADVANCED RULES
	
	private boolean advancedGame;
	private ArrayList<PlayerPersonalBonus> playerPersonalBonus;
	private PlayerPersonalBonus defaultPersonalBonus;

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
		System.out.println("\nInitializing board to get ready for the game");

		rollDice();
		System.out.println("Dice rolled..");
		setPlayersFamilyMembersValue();
		System.out.println("All familiar die value setted..");
		setTowerCards();
		System.out.println("Tower cards setted..");

		System.out.println("Board initialization ended successfully\n");
	}

	private void setPlayersFamilyMembersValue() {
		int j = 0;
		int i = 0;
		for (j = 0; j < this.getPlayers().size(); j++) {

			for (i = 1; i < this.getPlayers().get(j).getFamilyMembers().size(); i++) {
				if (!this.getPlayers().get(j).getFamilyMember(i).isLeaderDieBonusFixed()) {
					this.getPlayers().get(j).getFamilyMember(i)
							.setDieToFamilyMember(this.getDice().get(i - 1).getDieValue());
				}
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
		System.out.println("\nTotal players in game is " + this.getPlayersID().size() +"\n");

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
		System.out.println("Setting card on tower starting from card: " + startingCardToDraw);
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
		System.out.println("\nOngoing next round logic on board:\n");

		System.out.println("Establishing new player order");
		establishNewPlayerOrder();

		System.out.println("Resetting board areas");
		resetAreas();

		System.out.println("Resetting tower cards");
		setTowerCards();

		System.out.println("Rolling dices");
		rollDice();

		System.out.println("Setting new die value to familyMembers");
		setPlayersFamilyMembersValue();

	}

	private void resetAreas() {
		this.resetTowers();
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
		System.out.println("Phase number on board is " + this.phase);
		System.out.println("player of phase on board"
				+ this.getPlayers().get(this.phase % this.getPlayers().size()).getPlayerName());

		return this.getPlayers().get(this.phase % this.getPlayers().size()).getPlayerName();
	}

	// NEW PLAYER ORDER EACH ROUND
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

	public void nextRound() {
		this.round++;
	}

	public void nextPeriod() {
		this.period++;
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

	public String leaderCardsToString() {
		String toString = "";
		int i = 0;
		for (LeaderCard leaderCard : this.getLeaderCardPool()) {
			toString = i + ")" + leaderCard.toString() + "\n";
			i++;
		}
		return toString;
	}

	public String leaderCardsPlayedToString() {
		String toString = "";

		int i = 0;
		if (!this.getLeaderCardsPlayed().isEmpty()) {
			for (LeaderCard leaderCard : this.getLeaderCardsPlayed()) {
				toString = i + ")" + leaderCard.toString() + "\n";
				i++;
			}
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

	public ArrayList<String> getPlayersID() {
		return this.playersID;
	}

	public void setPlayersID(ArrayList<String> playersID) {
		this.playersID = playersID;
	}

	public ArrayList<Player> getPlayers() {
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

	public ArrayList<LeaderCard> getLeaderCardPool() {
		return leaderCardPool;
	}

	public void setLeaderCardPool(ArrayList<LeaderCard> leaderCardPool) {
		this.leaderCardPool = leaderCardPool;
	}

	public ArrayList<LeaderCard> getLeaderCardsPlayed() {
		return leaderCardsPlayed;
	}

	public void setLeaderCardsPlayed(ArrayList<LeaderCard> leaderCardsPlayed) {
		this.leaderCardsPlayed = leaderCardsPlayed;
	}
	
	

	public boolean isAdvancedGame() {
		return advancedGame;
	}

	public void setAdvancedGame(boolean advancedGame) {
		this.advancedGame = advancedGame;
	}

	
	
	
	public ArrayList<PlayerPersonalBonus> getPlayerPersonalBonus() {
		return playerPersonalBonus;
	}

	public void setPlayerPersonalBonus(ArrayList<PlayerPersonalBonus> playerPersonalBonus) {
		this.playerPersonalBonus = playerPersonalBonus;
	}

	public PlayerPersonalBonus getDefaultPersonalBonus() {
		return defaultPersonalBonus;
	}

	public void setDefaultPersonalBonus(PlayerPersonalBonus defaultPersonalBonus) {
		this.defaultPersonalBonus = defaultPersonalBonus;
	}

	public void excommunicatePlayer(Player player) {
		int relativePeriod = this.getPeriod();
		System.out.println("Message from board: Excommunicating player " + player.getPlayerName() + " during period "
				+ relativePeriod);
		// DEBUGGING TO BE CANCELLED

		if (this.getExcommunicationTiles().get(relativePeriod) != null) {
			System.out.println("Message from board: Attempting to get "
					+ this.getExcommunicationTiles().get(relativePeriod).toString());

			this.getExcommunicationTiles().get(relativePeriod).executeEffect(player.findFamilyMemberByColor(1));
		}
		if (this.getExcommunicationTiles().get(relativePeriod + 1) != null) {
			System.out.println("Message from board: Attempting to get "
					+ this.getExcommunicationTiles().get(relativePeriod + 1).toString());

			this.getExcommunicationTiles().get(relativePeriod + 1).executeEffect(player.findFamilyMemberByColor(1));
		}
	}

	public void satisfyTheChurch(Player player) {
		System.out.println("Player " + player.getPlayerName() + " is about to gain "
				+ this.faithVictoryPoints[player.getPlayerResource("faithPoint")] + " satisfying the church");
		player.addResource("victoryPoint", this.faithVictoryPoints[player.getPlayerResource("faithPoint")]
				+ player.getPlayerBounusMalus().getVictoryPointsAvoidingExcommunication());
		System.out.println("Resetting his faith points to 0");
		player.getPlayerResources().put("faithPoint", 0);

	}

	public String establishWinner() {
		FinalCalculationVictoryPoints victoryPointsDecider = new FinalCalculationVictoryPoints();
		Player player = victoryPointsDecider.decideForWinner(this.getPlayers());
		return player.getPlayerName();
	}

}