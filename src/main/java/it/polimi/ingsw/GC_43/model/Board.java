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

	// minor edit
    //CONFIGURATION SETTINGS

   private static int round;
   private static int turnNumber;
   private static int era;

    //CARDPOOLS
    private List territoryCardPool;
    private List buildingCardPool;
    private List characterCardPool;
    private List ventureCardPool;
    private ArrayList <ExcommunicationTile> excommunicationTiles;

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

//  Generate controller class (see addPlayer function)
//	private Controller c;


public Board(ArrayList players){
	
	
		this.round=1;
		this.turnNumber=1;
		this.era=1;
	
		this.players=new ArrayList<Player>();
		this.players=players;
        GlobalVariables.numberOfPlayers=players.size();

        createPlayers();
        createDice();
        rollDice();
        
        // io metterei l'effect in global variable
        this.councilPalace = new CouncilPalace(null);
        
        this.market = new Market();
        
        // io metterei l'effect in global variable
        this.productionArea=new ProductionArea(null);
        
        // io metterei l'effect in global variable
        this.harvestArea= new HarvestArea(null);
     
        createCards();
      
        

        createTowers();
        //maybe to set something from controller input
        this.faithArea = new FaithArea(this.excommunicationTiles);
        
        initialize();


    }





    private void createCards() {
    	this.buildingCardPool = new ArrayList<Card>();
    	this.ventureCardPool = new ArrayList<Card>();
    	this.territoryCardPool= new ArrayList<Card>();
    	this.characterCardPool = new ArrayList<Card>();
    	this.excommunicationTiles= new ArrayList<ExcommunicationTile>();

        //TODO FRANCESCO  fatti passare arraylist di carte tipo da SAM!!!!


    	}
    





	private void createTowers() {
		
        this.towers = new ArrayList<Tower>();
        

        
        this.towers.add(new Tower(TowerColors.TERRITORIES_TOWER, GlobalVariables.floorsPerTower));
        this.towers.add(new Tower(TowerColors.BUILDINGS_TOWER, GlobalVariables.floorsPerTower));
        this.towers.add(new Tower(TowerColors.VENTURES_TOWER, GlobalVariables.floorsPerTower));
        this.towers.add(new Tower(TowerColors.CHARACTERS_TOWER, GlobalVariables.floorsPerTower));

        }
    



    // REQUIREMENTS : Controller ask for addPlayers giving ID as input
    public void addPlayerID(String playerID){
        if (playersID == null)
            playersID= new ArrayList <String>();
        playersID.add(playerID);


    }


    public void initialize(){
    	    	
        setTowerCards();


    }

    
//create Players assigning incremental money to receive
//No rolling dice to establish the first initial order of players.
    private void createPlayers() {
        if(players==null){
            players = new ArrayList<Player>();
        }
        int initialCoinsToReceive=5;
        for (int i=0; i<this.playersID.size(); i++){
        	players.add(new Player(playersID.get(i),initialCoinsToReceive));
        	initialCoinsToReceive++;
        }
    }
    
    
    
    //SET CARDS READY IN THE TOWERS, REQUIREMENTS: CARDS ORDERED BY ERA FROM BOTTOM TO TOP
    
    private void setTowerCards() {
   /* 	int startingCardToDraw = this.round*GlobalVariables.towerCardsPerRound;
        for(int index=0 ; index < GlobalVariables.towerCardsPerRound; index++)
            this.getTowers().get(0).getFloors().get(index).setCard(this.territoryCardPool.get(startingCardToDraw+index));
        for(int index=0 ; index < GlobalVariables.towerCardsPerRound; index++)
            this.getTowers().get(1).getFloors().get(index).setCard(this.buildingCardPool.get(startingCardToDraw+index));
        for(int index=0 ; index < GlobalVariables.towerCardsPerRound; index++)
            this.getTowers().get(2).getFloors().get(index).setCard(this.ventureCardPool.get(startingCardToDraw+index));
        for(int index=0 ; index < GlobalVariables.towerCardsPerRound; index++)
            this.getTowers().get(3).getFloors().get(index).setCard(this.characterCardPool.get(startingCardToDraw+index));   
    */}



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
        if(this.round % GlobalVariables.excommunicationRound==0){
//TODO 	to decide whether to implement it on controller, it requires interaction
        	//        	checkPlayerExcommunication();
        }
        setTowerCards();
        rollDice();
        establishNewPlayerOrder();

    }


    public String nextPlayerRound(){
    	//TODO to implement
    	return null;
    }

    private void establishNewPlayerOrder() {
    	
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
}