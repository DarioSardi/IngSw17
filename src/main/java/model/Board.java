package model;

import java.util.ArrayList;
import java.util.List;

public class Board {
	final int excommunicationRound=2;
	private int round;
	private int era;
	private List<Die> Dice;
	private List territoryCardPool;
	private List buildingCardPool;
	private List characterCardPool;
	private List ventureCardPool;
	private Market market;
	private CouncilPalace councilPalace;
	private HarvestArea harvestArea;
	private ProductionArea productionArea;
	private Tower tower;
	private FaithArea faithArea;
	private List<Player> players;
	
//TODO Controller class	
//	private Controller c;
	
//TODO aspetta a generare getters e setters vien casino senno
	
	public Board(){
		this.players= new ArrayList<Player>();
		setPlayers();
		//TODO
		
	}
	
	
	public void initialize(){
		createPlayerBoards();
		setTowerCards();
		setExcommunicationCards();
		createMarket();
		
		
//		askPlayersForDefaultBonus(Controller c);
//		askPlayersForLeaderCards(Controller c);

		
		createAndRollDice();
		
		
	}
	private void setExcommunicationCards() {
		// TODO Auto-generated method stub
		
	}



	private void createPlayerBoards() {
		// TODO Auto-generated method stub
		
	}
	private void setTowerCards() {
		getCards();
		// TODO Auto-generated method stub
		
	}
	private void getCards(){
	//TODO shuffe cards and prepare all decks of cards
	}
	
	private void createAndRollDice() {
		// TODO Auto-generated method stub
		
	}
	public void nextTurn(){
		if(this.round == excommunicationRound){
			
			//TODO to implement scomunica
		}
		
		
	}
	


	

}
