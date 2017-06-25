package it.polimi.ingsw.GC_43.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

import it.polimi.ingsw.GC_43.controller.ClientaHandlerRmInterface;
import it.polimi.ingsw.GC_43.controller.Lobby;
import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.CopyOfGlobalVariables;
import it.polimi.ingsw.GC_43.model.GlobalVariables;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.actions.Action;

public class Client {
	private Integer port,ID;
	private Socket socket;
    private ObjectInputStream inSocket;
    private ObjectOutputStream outSocket;
    BufferedReader inKeyboard;
	private String address,username;
	private ClientOutHandler outStream;
	private ClientInHandler inStream;
	private Board board;
	private Lobby lobby;
	Boolean idSetted,inMenu,inGame,online,myTurn,excommunicationRound,rmi;
	private Player myPlayer;
	private ExecutorService executor;
	public ReentrantLock locker;
	private RmiView rmiView;
	ClientaHandlerRmInterface handler;

    public Client() throws IOException, NotBoundException{
    	this.rmi=false;
    	setup();
    	this.lobby=null;
    	this.online=true;
    	this.inMenu=true;
    	this.inGame=false;
    	this.myTurn=false;
    	this.ID=0;
    	if (!rmi) {
			connect();
		}
    	else connectRMI();
    }
    
    
    
    private void connectRMI() throws RemoteException, NotBoundException {
    	Registry registry =LocateRegistry.getRegistry(InetAddress.getLoopbackAddress().getHostAddress(), this.port);
		handler=(ClientaHandlerRmInterface)registry.lookup("COF");
		executor = Executors.newFixedThreadPool(1);
		this.rmiView=new RmiView(this,handler,inKeyboard);
		handler.connect(this.rmiView);
		executor.submit(rmiView);
		
		
		
	}



	public void closeGame() {
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
    
    public void setMyTurn(Boolean myT){
    	this.myTurn=myT;
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
		this.board.getPlayers().stream().forEach(p->{
			if(p.getPlayerName().equals(this.username)){
				this.myPlayer=p;
			}
		});
		System.out.println("\nI TUOI DATI:\n"+this.myPlayer.toString());
	}



	private void connect() throws UnknownHostException, IOException {
    	socket = new Socket(address, port);
    	//thread per gestione I/O
    	executor = Executors.newFixedThreadPool(2);
    	this.outStream=new ClientOutHandler(new ObjectOutputStream(socket.getOutputStream()),this);
    	this.inStream=new ClientInHandler(new ObjectInputStream(socket.getInputStream()),this);
    	executor.submit(inStream);
    	executor.submit(outStream);
      
        
    }



	public void setup() throws IOException{
    	inKeyboard = new BufferedReader(new InputStreamReader(System.in));
    	Boolean correctAnswer=false;
    	while (!correctAnswer) {
			System.out.println("Welcome on Lorenzo il magnifico in JAVA");
			System.out.println("select the configuration type: manual / auto_socket / auto_rmi");
			String answer = inKeyboard.readLine().toString();
			if (answer.equals("manual")) {
				System.out.println("select connection type RMI/SOCKET:");
				String connectionType= inKeyboard.readLine();
				if("rmi".equalsIgnoreCase(connectionType)){
					this.rmi=true;
				}
				else if("socket".equalsIgnoreCase(connectionType)){
					this.rmi=false;
				}
				else {
					System.out.println("invalid input");
					continue;}
				
				System.out.println("seleziona l'indirizzo del server (localhost by default settings) : ");
				this.address = inKeyboard.readLine();
				System.out.println("seleziona la porta del server(SOCKET: 7777 RMI:7077):");
				this.port = Integer.parseInt(inKeyboard.readLine());
				System.out.println("Select your username: ");
				this.username = inKeyboard.readLine();
				correctAnswer=true;
			}

			else if (answer.equals("auto_socket")) {
				this.address = "127.0.0.1";
				this.port = 7777;
				System.out.println("Select your username: ");
				this.username = inKeyboard.readLine();
				this.rmi=false;
				correctAnswer=true;
			}

			else if (answer.equals("auto_rmi")) {
				this.address = "127.0.0.1";
				this.port = 7077;
				System.out.println("Select your username: ");
				this.username = inKeyboard.readLine();
				this.rmi=true;
				correctAnswer=true;
			} 
			
			
			else {
				System.out.println("damit, this is the first one, good luck on playing the game with CLI");
			} 
		}
		System.out.println("connetto a: "+address+"/"+port+" RMI:"+rmi);
    }
	
	
	
	public void sendObj(Object o) throws RemoteException{
		if (!rmi) {
			System.out.println("invio oggetto " + o.toString());
			this.outStream.sendObj(o);
		}
		else if(rmi){
			if(o instanceof Action){
				handler.submitAction((Action)o);
			}
			
		}
		else System.out.println("ehm...why you are here?");
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
	
	public static void main(String [] args) throws IOException, NotBoundException {
	 @SuppressWarnings("unused")
		Client c= new Client();
	 }
	
	

}