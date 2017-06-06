package it.polimi.ingsw.GC_43.model;
import it.polimi.ingsw.GC_43.model.actionSpace.*;



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
import it.polimi.ingsw.GC_43.model.actionSpace.TowerColors;


public class Board {

    //CONFIGURATION SETTINGS
   private static int phase;
   private static int round;
   private static int period;
   private ArrayList<Integer> victoryPositionPoints;

    //CARDPOOLS
    private List territoryCardPool;
    private List buildingCardPool;
    private List characterCardPool;
    private List ventureCardPool;
    private ArrayList <ExcommunicationTile> excommunicationTiles;
    
    //DICE
    private List<Die> dice;
    
    
    //AREAS
    private Market market;
    private CouncilPalace councilPalace;
    private HarvestArea harvestArea;
    private ProductionArea productionArea;
    private List <Tower> towers;
    private FaithArea faithArea;

    //PLAYERS
    private List<String> playersID;
    private List<Player> players;



    
    
    //SAM-FRANCESCO: Remember to call board.initalize() only after creation of board and
    //and initial settings of all stuff, delete this comment afterwards ;)
    
public Board(ArrayList playersID){
	
	
		this.round=1;
		this.phase=1;
		this.period=1;
	
		this.playersID=new ArrayList<String>();
		this.playersID=playersID;
		this.victoryPositionPoints= new ArrayList<Integer>();

        
		GlobalVariables.numberOfPlayers=playersID.size();
        
        createPlayers();
        
        
        createDice();
                
     
        createCards();
        

        createTowers();
        
        
        

    }





    private void createCards() {
    	this.buildingCardPool = new ArrayList<BuildingCard>();
    	this.ventureCardPool = new ArrayList<VentureCard>();
    	this.territoryCardPool= new ArrayList<TerritoryCard>();
    	this.characterCardPool = new ArrayList<CharacterCard>();
    	this.excommunicationTiles= new ArrayList<ExcommunicationTile>();

    	}
    


	private void createTowers() {
		
        this.towers = new ArrayList<Tower>();
        

        this.towers.add(new Tower(TowerColors.TERRITORIES_TOWER, GlobalVariables.floorsPerTower));
        this.towers.add(new Tower(TowerColors.BUILDINGS_TOWER, GlobalVariables.floorsPerTower));
        this.towers.add(new Tower(TowerColors.VENTURES_TOWER, GlobalVariables.floorsPerTower));
        this.towers.add(new Tower(TowerColors.CHARACTERS_TOWER, GlobalVariables.floorsPerTower));

        }
    



    public void addPlayerID(String playerID){
        if (playersID == null)
            playersID= new ArrayList <String>();
        playersID.add(playerID);
    }


    public void initialize(){
    	    	
        rollDice();
        
        setPlayersFamilyMembersValue();

        setTowerCards();


    }

    
private void setPlayersFamilyMembersValue() {		
	for (Player player: this.getPlayers()){
		for(int i=0; i<player.getFamilyMembers().size();i++){
			player.getFamilyMember(i).setDieToFamilyMember(this.getDice().get(i).getDieValue());
			
		}
	}
		
}





//create Players assigning incremental money to receive
//No rolling dice to establish the first initial order of players.
    private void createPlayers() {
        if(players==null){
            players = new ArrayList<Player>();
        }
        int initialCoinsToReceive=GlobalVariables.initialFirstPlayerCoins;
        for (int i=0; i<this.playersID.size(); i++){
        	players.add(new Player(playersID.get(i),initialCoinsToReceive));
        	initialCoinsToReceive++;
        }
    }
    
    
    
  //SET CARDS READY IN THE TOWERS, @require : CARDS ORDERED BY ERA FROM BOTTOM TO TOP
    
    private void setTowerCards() {
    	int startingCardToDraw = this.round*GlobalVariables.towerCardsPerRound;
        for(int index=0 ; index < GlobalVariables.towerCardsPerRound; index++)
            this.getTowers().get(0).getFloors().get(index).setCard((TerritoryCard)this.territoryCardPool.get(startingCardToDraw+index));
        for(int index=0 ; index < GlobalVariables.towerCardsPerRound; index++)
            this.getTowers().get(1).getFloors().get(index).setCard((BuildingCard)this.buildingCardPool.get(startingCardToDraw+index));
        for(int index=0 ; index < GlobalVariables.towerCardsPerRound; index++)
            this.getTowers().get(2).getFloors().get(index).setCard((VentureCard)this.ventureCardPool.get(startingCardToDraw+index));
        for(int index=0 ; index < GlobalVariables.towerCardsPerRound; index++)
            this.getTowers().get(3).getFloors().get(index).setCard((CharacterCard)this.characterCardPool.get(startingCardToDraw+index));   
    }


    private void createDice() {
        this.dice= new ArrayList<Die>();

    	for(int color=0; color<GlobalVariables.numberOfDice;color++)
    		this.dice.add(new Die(color));		
    }
    private void rollDice() {
    	for(int color=0; color<GlobalVariables.numberOfDice; color++)
    		this.dice.get(color).rollDie();		
    }
    
    
    
    public void nextTurn(){
        this.round++;

        if(this.round % GlobalVariables.excommunicationRound==0){
//TODO 	to decide whether to implement it on controller, it requires interaction
        	//        	checkPlayerExcommunication();
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
		for(int i=0; i<this.getTowers().size();i++)
			this.getTowers().get(i).resetArea();
	}
	
	
	public void nextPlayerPhase(){
		this.phase++;
	}
    private String getPhasePlayer(){
    	return this.getPlayers().get(this.phase%4).getPlayerName();
    }

    
    
	
	//TODO TO BE COMPLETED AFTER DARIO
  private void establishNewPlayerOrder() {
    	if(!this.getCouncilPalace().entryOrder().isEmpty()){
        	ArrayList <Player> newPlayerOrder=new ArrayList<Player>();
        	newPlayerOrder=this.getCouncilPalace().entryOrder();
        	
        	for(Player player: newPlayerOrder){
        		if(this.getPlayers().contains(player)){
        			this.getPlayers().remove(player);
        		}
        	}
        	for(Player player: this.getPlayers()){
        		newPlayerOrder.add(player);
        	}
        	this.setPlayers(newPlayerOrder);

    	}
    	
	} 
    






	//GETTERS AND SETTERS


    public List<Die> getDice() {
        return this.dice;
    }

    public void setDice(List<Die> dice) {
        this.dice = dice;
    }

    public List getTerritoryCardPool() {
        return territoryCardPool;
    }

    public void setTerritoryCardPool(List territoryCardPool) {
        this.territoryCardPool = territoryCardPool;
    }

    public List getBuildingCardPool() {
        return buildingCardPool;
    }

    public void setBuildingCardPool(List buildingCardPool) {
        this.buildingCardPool = buildingCardPool;
    }

    public List getCharacterCardPool() {
        return characterCardPool;
    }

    public void setCharacterCardPool(List characterCardPool) {
        this.characterCardPool = characterCardPool;
    }

    public List getVentureCardPool() {
        return ventureCardPool;
    }

    public void setVentureCardPool(List ventureCardPool) {
        this.ventureCardPool = ventureCardPool;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public CouncilPalace getCouncilPalace() {
        return councilPalace;
    }

    public void setCouncilPalace(CouncilPalace councilPalace) {
        this.councilPalace = councilPalace;
    }

    public HarvestArea getHarvestArea() {
        return harvestArea;
    }

    public void setHarvestArea(HarvestArea harvestArea) {
        this.harvestArea = harvestArea;
    }

    public ProductionArea getProductionArea() {
        return productionArea;
    }

    public void setProductionArea(ProductionArea productionArea) {
        this.productionArea = productionArea;
    }

    public List<Tower> getTowers() {
        return towers;
    }

    public void setTowers(List<Tower> towers) {
        this.towers = towers;
    }

    public FaithArea getFaithArea() {
        return faithArea;
    }

    public void setFaithArea(FaithArea faithArea) {
        this.faithArea = faithArea;
    }

    public List<String> getPlayersID() {
        return playersID;
    }

    public void setPlayersID(List<String> playersID) {
        this.playersID = playersID;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

	public static int getPhase() {
		return phase;
	}

	public static void setPhase(int phase) {
		Board.phase = phase;
	}
	
	public static int getRound() {
		return round;
	}

	public static void setRound(int round) {
		Board.round = round;
	}

	public static int getPeriod() {
		return period;
	}

	public static void setPeriod(int period) {
		Board.period = period;
	}

	public ArrayList<Integer> getVictoryPositionPoints() {
		return victoryPositionPoints;
	}

	public void setVictoryPositionPoints(ArrayList<Integer> victoryPositionPoints) {
		this.victoryPositionPoints = victoryPositionPoints;
	}

	public ArrayList<ExcommunicationTile> getExcommunicationTiles() {
		return excommunicationTiles;
	}

	public void setExcommunicationTiles(ArrayList<ExcommunicationTile> excommunicationTiles) {
		this.excommunicationTiles = excommunicationTiles;
	}
    
    
    
}