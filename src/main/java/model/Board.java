package model;

import java.util.ArrayList;
import java.util.List;
import model.actionSpace.*;

public class Board {

    //CONFIGURATION SETTINGS

   private static int round;
   private static int turnNumber;
   private static int era;

    //CARDPOOLS
    private List territoryCardPool;
    private List buildingCardPool;
    private List characterCardPool;
    private List ventureCardPool;

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


public Board(){
        createPlayers(); //magari implementeremo la scelta bonus Base Risorse e Leader Card piu avanti da passare in parametro

        int numberOfPlayers= players.size();

        this.councilPalace = new CouncilPalace();
        this.market = new Market(numberOfPlayers);
        this.productionArea=new ProductionArea(numberOfPlayers);
        this.harvestArea= new HarvestArea(numberOfPlayers);
        this.faithArea = new FaithArea();
        this.dice= new ArrayList<Die>();

        createTowers();
        createCards();
        //maybe to set something from controller input

    }





    private void createCards() {
    	this.buildingCardPool = new ArrayList<Card>();
    	this.ventureCardPool = new ArrayList<Card>();
    	this.territoryCardPool= new ArrayList<Card>();
    	this.characterCardPool = new ArrayList<Card>();
    	for(int i=0; i<totalNumberOfCardsPerSet;i++){
        //FRANCESCO bordello in creazione carte,come le ricevo e elaboro? da capire !!!!

        	this.buildingCardPool.add(new BuildingCard(null,this.getEra())); 
        	this.ventureCardPool.add(new VentureCard(null, this.getEra()));
        	this.territoryCardPool.add(new TerritoryCard(null, this.getEra()));
        	this.characterCardPool.add(new CharacterCard(null, this.getEra()));
    	}
    }





	private void createTowers() {
		
        this.towers = new ArrayList<Tower>();
        for(int i=0 ; i < numberOfTowers; i++){
            this.getTowers().add(new Tower(i));

        }
    }



    // REQUIREMENTS : Controller ask for addPlayers giving ID as input
    public void addPlayerID(String playerID){
        if (playersID == null)
            playersID= new ArrayList <String>();
        playersID.add(playerID);


    }


    public void initialize(){
    	
    	
        setTowerCards();
        
//		setExcommunicationCards();



//		askPlayersForDefaultBonus(Controller c);
//		askPlayersForLeaderCards(Controller c);


        createAndRollDice();

    }



    private void createPlayers() {
        if(players==null){
            players = new ArrayList<Player>();
        }
        for (int i=0; i<this.playersID.size(); i++){
        	//SAMUEL-FRANCESCO Da modificare costruttore di player con input dati;
            players.add(new Player(playersID.get(i)));

        }
    }
    
    //SET CARDS READY IN THE TOWERS, REQUIREMENTS: CARDS ORDERED BY ERA FROM BOTTOM TO TOP
    
    private void setTowerCards() {
    //    getCards();
    	int startingCardToDraw = this.round*GlobalVariables.towerCardsPerRound;
        for(int index=0 ; index < GlobalVariables.towerCardsPerRound; index++)
            this.getTowers().get(0).getFloors().get(index).setCard(this.territoryCardPool.get(startingCardToDraw+index));
        for(int index=0 ; index < GlobalVariables.towerCardsPerRound; index++)
            this.getTowers().get(0).getFloors().get(index).setCard(this.buildingCardPool.get(startingCardToDraw+index));
        for(int index=0 ; index < GlobalVariables.towerCardsPerRound; index++)
            this.getTowers().get(0).getFloors().get(index).setCard(this.territoryCardPool.get(startingCardToDraw+index));
        for(int index=0 ; index < GlobalVariables.towerCardsPerRound; index++)
            this.getTowers().get(0).getFloors().get(index).setCard(this.territoryCardPool.get(startingCardToDraw+index));   
    }


    private void createAndRollDice() {
    	for(int index=0; index<numberOfDice; index++)
    		this.dice.add(new Die(index));		

    }
    private void RollDice() {
    	for(int index=0; index<numberOfDice; index++)
    		this.dice.get(index).rollDie();		
    }
    
    
    
    public void nextTurn(){
        if(this.round == excommunicationRound){

        }

    }







    //GETTERS AND SETTERS

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getEra() {
        return era;
    }

    public void setEra(int era) {
        this.era = era;
    }

    public List<Die> getDice() {
        return Dice;
    }

    public void setDice(List<Die> dice) {
        Dice = dice;
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