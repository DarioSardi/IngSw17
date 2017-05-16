package model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    //CONFIGURATION SETTINGS
    public static final int numberOfTowers= 4;
    public static final int excommunicationRound=2;
    private int round;
    private int era;

    //CARDPOOLS
    private List territoryCardPool;
    private List buildingCardPool;
    private List characterCardPool;
    private List ventureCardPool;

    private List<Die> Dice;

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

        //FRANCESCO-DARIO Devo passarti il numero giocatori int per i costruttori delle aree per sapere cosa abilitare
        int numberOfPlayers= players.size();

        this.councilPalace = new CouncilPalace();
        this.market = new Market(numberOfPlayers);
        this.productionArea=new ProductionArea(numberOfPlayers);
        this.harvestArea= new HarvestArea(numberOfPlayers);
        this.faithArea = new FaithArea();

        createTowers();

        //maybe to set something form controller input

    }





    private void createTowers() {
        //FRANCESCO-DARIO da decidere se tower distinti da colori o da altro (passo intero al construttore, strebbe per colore)
        this.towers = new ArrayList<Tower>();
        for(int i=0 ; i < numberOfTowers; i++){
            this.getTowers().add(new Tower(i));

        }
    }



    //Controller ask for addPlayers giving ID as input
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
            players.add(new Player(playersID.get(i)));

        }
    }
    private void setTowerCards() {
        getCards();

    }
    private void getCards(){
    }

    private void createAndRollDice() {

    }
    public void nextTurn(){
        if(this.round == excommunicationRound){

        }

    }












    //getters and setters

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

