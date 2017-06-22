package it.polimi.ingsw.GC_43.view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

import it.polimi.ingsw.GC_43.controller.Lobby;
import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.CopyOfGlobalVariables;
import it.polimi.ingsw.GC_43.model.GlobalVariables;
import it.polimi.ingsw.GC_43.model.Player;

public class Client {
	private int port,ID;
	private Socket socket;
    private ObjectInputStream inSocket;
    private ObjectOutputStream outSocket;
    BufferedReader inKeyboard;
    private PrintWriter outVideo;
	private String address,username;
	private ClientOutHandler outStream;
	private ClientInHandler inStream;
	private static InetAddress ipAddr;
	private Board board;
	private Lobby lobby;
	Boolean idSetted,inMenu,inGame,online,myTurn,excommunicationRound;
	private Player myPlayer;
	private ExecutorService executor;
	public ReentrantLock locker;

    public Client() throws IOException{
    	setup();
    	this.lobby=null;
    	this.online=true;
    	this.inMenu=true;
    	this.inGame=false;
    	this.myTurn=false;
    	
    	
    	connect();
    	//closeGame();
    }
    
    
    
    private void closeGame() {
		try {
			this.online=false;
			inSocket.close();
			outSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}
    
    public Player getMyPlayer(){
    	return this.myPlayer;
    }
    
    public Lobby getLobby(){
    	return this.lobby;
    }
    
    public void setInGame(Boolean inG){
    	this.inGame=inG;
    }
    
	public void setID(int ID){
		this.ID=ID;
		
	}
	
	public int getID(){
		return this.ID;
	}
	

	public String getUsername() {
		return this.username;
	}
	

	public Board getBoard() {
		return this.board;
	}
	
	public void changeUsername(String newUsername){
		System.out.println("changed your username in " + newUsername);
		this.username=newUsername;
	}



	public void setBoard(Board board) {
		this.board = board;
		System.out.println("hai ricevuto la board di gioco!");
		this.board.getPlayers().stream().forEach(p->{
			if(p.getPlayerName().equals(this.username)){
				this.myPlayer=p;
			}
		});
		System.out.println("\nI TUOI DATI:\n"+this.myPlayer.toString());
	}



	private void connect() throws UnknownHostException, IOException {
    	System.out.println("tento di connettermi...");
    	socket = new Socket(address, port);
    	//thread per gestione I/O
    	executor = Executors.newFixedThreadPool(2);
    	this.outStream=new ClientOutHandler(new ObjectOutputStream(socket.getOutputStream()),this);
    	this.inStream=new ClientInHandler(new ObjectInputStream(socket.getInputStream()),this);
    	executor.submit(inStream);
    	executor.submit(outStream);
        System.out.println("connesso!");
      
        
    }



	public void setup() throws IOException{
    	inKeyboard = new BufferedReader(new InputStreamReader(System.in));
        outVideo = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), true);
    	ipAddr=InetAddress.getLoopbackAddress();
    	Boolean correctAnswer=false;
    	while (!correctAnswer) {
			System.out.println("Benvenuto su Lorenzo il magnifico in JAVA");
			System.out.println("scegli configurazione: manual o auto?");
			String answer = inKeyboard.readLine().toString();
			if (answer.equals("manual")) {
				System.out.println("seleziona l'indirizzo del server: ");
				this.address = inKeyboard.readLine();
				System.out.println("seleziona la porta del server: ");
				this.port = Integer.parseInt(inKeyboard.readLine());
				System.out.println("Username: ");
				this.username = inKeyboard.readLine();
				correctAnswer=true;
			}

			else if (answer.equals("auto")) {
				this.address = "127.0.0.1";
				this.port = 7777;
				this.username = "PanDario7";
				correctAnswer=true;
			}

			else if (answer.equals("auto1")) {
				this.address = "127.0.0.1";
				this.port = 7777;
				this.username = "Dario";
				correctAnswer=true;
			} else {
				System.out.println("ma che cazz...");
			} 
		}
		System.out.println("connetto a: "+address+"/"+port);
    }
	
	public static void main(String [] args) throws IOException {
		 Client c= new Client();
	 }
	
	public void sendObj(Object o){
		System.out.println("invio oggetto "+o.toString());
		this.outStream.sendObj(o);
	}
	
	public void setGameGlobalVariables(CopyOfGlobalVariables o) {
		   GlobalVariables.maxNumberOfPlayers = o.maxNumberPlayerCards;
		   GlobalVariables.numberOfFamilyMembers = o.numberOfFamilyMembers;
		   GlobalVariables.numberOfTowers = o.numberOfTowers;
		   GlobalVariables.numberOfDice = o.numberOfDice;
		   GlobalVariables.excommunicationRound = o.excommunicationRound;
		   GlobalVariables.totalNumberOfCardsPerSet = o.totalNumberOfCardsPerSet;
		   GlobalVariables.towerCardsPerRound = o.towerCardsPerRound;
		   GlobalVariables.towerCardsPerPeriod = o.towerCardsPerPeriod;
		   GlobalVariables.floorsPerTower = o.floorsPerTower;
		   GlobalVariables.totalNumberOfPeriods = o.totalNumberOfPeriods;
		   GlobalVariables.maxNumberPlayerCards = o.maxNumberPlayerCards;
		   GlobalVariables.initialWoods = o.initialWoods;
		   GlobalVariables.initialStones = o.initialStones;
		   GlobalVariables.initialServants = o.initialServants;
		   GlobalVariables.initialFirstPlayerCoins = o.initialFirstPlayerCoins;
		   GlobalVariables.initialSecondPlayerCoins = o.initialSecondPlayerCoins;	
		   GlobalVariables.initialThirdPlayerCoins = o.initialThirdPlayerCoins;	
		   GlobalVariables.initialFourthPlayerCoins = o.initialFourthPlayerCoins;	
		   GlobalVariables.initialVictoryPoints = o.initialVictoryPoints;
		   GlobalVariables.initialMilitaryPoints = o.initialMilitaryPoints;
		   GlobalVariables.initialFaithPoints = o.initialFaithPoints;
		   GlobalVariables.minDiceFirstHarvestArea = o.minDiceFirstHarvestArea;
		   GlobalVariables.minDiceSecondHarvestArea = o.minDiceSecondHarvestArea;
		   GlobalVariables.minDiceFirstProductionArea = o.minDiceFirstProductionArea;
		   GlobalVariables.minDiceSecondProductionArea = o.minDiceSecondProductionArea;
		   GlobalVariables.minDiceValueCouncilPalace = o.minDiceValueCouncilPalace;
		   GlobalVariables.towerTax = o.towerTax;
		   GlobalVariables.numberOfPlayers = o.numberOfPlayers;
		   GlobalVariables.victoryPointsFirstMilitaryPower = o.victoryPointsFirstMilitaryPower;
		   GlobalVariables.victoryPointsSecondMilitaryPower = o.victoryPointsSecondMilitaryPower;
		   GlobalVariables.maxVictoryPoints = o.maxVictoryPoints;
		   GlobalVariables.maxMilitaryPoints = o.maxMilitaryPoints;
		   GlobalVariables.maxFaithPoints = o.maxFaithPoints;
		   GlobalVariables.faithPointExcomRequired=o.faithPointExcomRequired;
	}
	
	
	

}