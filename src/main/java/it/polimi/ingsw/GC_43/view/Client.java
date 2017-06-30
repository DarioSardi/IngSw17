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
	private Integer port, ID;
	private Socket socket;
	private ObjectInputStream inSocket;
	private ObjectOutputStream outSocket;
	BufferedReader inKeyboard;
	private String address, username;
	private ClientOutHandler outStream;
	private ClientInHandler inStream;
	private Board board;
	private Lobby lobby;
	Boolean idSetted, inMenu, inGame, online, myTurn, excommunicationRound, rmi;
	private Player myPlayer;
	private ExecutorService executor;
	public ReentrantLock locker;
	private RmiView rmiView;
	ClientaHandlerRmInterface handler;
	private boolean actionPerformed;
	private boolean advancedGame;
	public boolean isInAdvSetupPhase;

	public Client() throws IOException, NotBoundException {
		initBools();
		setup();
		
		if (!rmi) {
			connect();
		} else
			connectRMI();
		
	}
	
	
	private void initBools(){
		this.rmi = false;
		this.lobby = null;
		this.online = true;
		this.inMenu = true;
		this.inGame = false;
		this.myTurn = false;
		this.ID = 0;
		isInAdvSetupPhase=false;
		this.actionPerformed=true;
	}

	private void connectRMI() throws RemoteException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry(InetAddress.getLoopbackAddress().getHostAddress(), this.port);
		handler = (ClientaHandlerRmInterface) registry.lookup("COF");
		executor = Executors.newFixedThreadPool(1);
		this.rmiView = new RmiView(this, handler, inKeyboard);
		executor.submit(rmiView);

	}

	private void connect() throws UnknownHostException, IOException {
		socket = new Socket(address, port);
		// thread per gestione I/O
		executor = Executors.newFixedThreadPool(2);
		this.outStream = new ClientOutHandler(new ObjectOutputStream(socket.getOutputStream()), this);
		this.inStream = new ClientInHandler(new ObjectInputStream(socket.getInputStream()), this);
		executor.submit(inStream);
		executor.submit(outStream);

	}

	public void setup() throws IOException {
		inKeyboard = new BufferedReader(new InputStreamReader(System.in));
		Boolean correctAnswer = false;
		while (!correctAnswer) {
			System.out.println("Welcome on Lorenzo il magnifico in JAVA");
			System.out.println("select the configuration type: manual /socket/rmi");
			String answer = inKeyboard.readLine().toString();
			if (answer.equals("manual")) {
				System.out.println("select connection type RMI/SOCKET:");
				String connectionType = inKeyboard.readLine();
				Boolean rmiChoice;
				if ("rmi".equalsIgnoreCase(connectionType)) {
					rmiChoice = true;
				} else if ("socket".equalsIgnoreCase(connectionType)) {
					rmiChoice = false;
				} else {
					System.out.println("invalid choice");
					continue;
				}
				System.out.println("seleziona l'indirizzo del server (localhost by default settings) : ");
				String addressChoice = inKeyboard.readLine();
				System.out.println("seleziona la porta del server(SOCKET: 7777 RMI:7077):");
				Integer portChoice = Integer.parseInt(inKeyboard.readLine());
				System.out.println("Select your username: ");
				String usernameChoice = inKeyboard.readLine();
				setConfigFields(addressChoice, portChoice, usernameChoice, rmiChoice);
				correctAnswer = true;

			}

			else if (answer.equals("socket")) {
				String addressChoice = "127.0.0.1";
				Integer portChoice = 7777;
				System.out.println("Select your username: ");
				String usernameChoice = inKeyboard.readLine();
				Boolean rmiChoice = false;
				setConfigFields(addressChoice, portChoice, usernameChoice, rmiChoice);
				correctAnswer = true;
			}

			else if (answer.equals("rmi")) {
				String addressChoice = "127.0.0.1";
				Integer portChoice = 7077;
				System.out.println("Select your username: ");
				String usernameChoice = inKeyboard.readLine();
				Boolean rmiChoice = true;
				setConfigFields(addressChoice, portChoice, usernameChoice, rmiChoice);
				correctAnswer = true;
			}

			else {
				System.out.println("damit, this is the first one, good luck on playing the game with CLI");
			}
		}
		System.out.println("connetto a: " + address + "/" + port + " RMI:" + rmi);
	}

	private void setConfigFields(String addressChoice, Integer portChoice, String usernameChoice, Boolean rmiChoice) {
		this.address = addressChoice;
		this.port = portChoice;
		this.username = usernameChoice;
		this.rmi = rmiChoice;

	}

	public void sendObj(Object o, Integer ID) throws RemoteException {
		if (!rmi) {
			this.outStream.sendObj(o);
		} else if (rmi) {
			if (o instanceof Action) {
				handler.submitAction(ID, (Action) o);
			}

		} else
			System.out.println("ehm...why you are here?");
	}

	public void setGameGlobalVariables(CopyOfGlobalVariables copy) {
		GlobalVariables.maxNumberOfPlayers = copy.maxNumberPlayerCards;
		GlobalVariables.numberOfFamilyMembers = copy.numberOfFamilyMembers;
		GlobalVariables.numberOfTowers = copy.numberOfTowers;
		GlobalVariables.numberOfDice = copy.numberOfDice;
		GlobalVariables.excommunicationRound = copy.excommunicationRound;
		GlobalVariables.totalNumberOfCardsPerSet = copy.totalNumberOfCardsPerSet;
		GlobalVariables.towerCardsPerRound = copy.towerCardsPerRound;
		GlobalVariables.towerCardsPerPeriod = copy.towerCardsPerPeriod;
		GlobalVariables.floorsPerTower = copy.floorsPerTower;
		GlobalVariables.totalNumberOfPeriods = copy.totalNumberOfPeriods;
		GlobalVariables.maxNumberPlayerCards = copy.maxNumberPlayerCards;
		GlobalVariables.initialWoods = copy.initialWoods;
		GlobalVariables.initialStones = copy.initialStones;
		GlobalVariables.initialServants = copy.initialServants;
		GlobalVariables.initialFirstPlayerCoins = copy.initialFirstPlayerCoins;
		GlobalVariables.initialSecondPlayerCoins = copy.initialSecondPlayerCoins;
		GlobalVariables.initialThirdPlayerCoins = copy.initialThirdPlayerCoins;
		GlobalVariables.initialFourthPlayerCoins = copy.initialFourthPlayerCoins;
		GlobalVariables.initialVictoryPoints = copy.initialVictoryPoints;
		GlobalVariables.initialMilitaryPoints = copy.initialMilitaryPoints;
		GlobalVariables.initialFaithPoints = copy.initialFaithPoints;
		GlobalVariables.minDiceFirstHarvestArea = copy.minDiceFirstHarvestArea;
		GlobalVariables.minDiceSecondHarvestArea = copy.minDiceSecondHarvestArea;
		GlobalVariables.minDiceFirstProductionArea = copy.minDiceFirstProductionArea;
		GlobalVariables.minDiceSecondProductionArea = copy.minDiceSecondProductionArea;
		GlobalVariables.minDiceValueCouncilPalace = copy.minDiceValueCouncilPalace;
		GlobalVariables.malusOnSecondHarvestArea = copy.malusOnSecondHarvestArea;
		GlobalVariables.malusOnSecondProductionArea = copy.malusOnSecondProductionArea;
		GlobalVariables.towerTax = copy.towerTax;
		GlobalVariables.numberOfPlayers = copy.numberOfPlayers;
		GlobalVariables.victoryPointsFirstMilitaryPower = copy.victoryPointsFirstMilitaryPower;
		GlobalVariables.victoryPointsSecondMilitaryPower = copy.victoryPointsSecondMilitaryPower;
		GlobalVariables.maxVictoryPoints = copy.maxVictoryPoints;
		GlobalVariables.maxMilitaryPoints = copy.maxMilitaryPoints;
		GlobalVariables.maxFaithPoints = copy.maxFaithPoints;
		GlobalVariables.councilPrivilegeEffect = copy.councilPrivilegeEffect;
		GlobalVariables.malusUnlimitedCells = copy.malusUnlimitedCells;
		GlobalVariables.militaryPointsRequired = copy.militaryPointsRequired;
		GlobalVariables.faithPointExcomRequired = copy.faithPointExcomRequired;
		GlobalVariables.endResourcesToVictoryPoint = copy.endResourcesToVictoryPoint;
		GlobalVariables.endCharacterVictoryPoints = copy.endCharacterVictoryPoints;
		GlobalVariables.endTerritoryVictoryPoints = copy.endTerritoryVictoryPoints;
	}

	public void closeGame() {
		try {
			this.online = false;
			inSocket.close();
			outSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Player getMyPlayer() {
		return this.myPlayer;
	}

	public Lobby getLobby() {
		return this.lobby;
	}

	public void setInGame(Boolean inG) {
		this.inGame = inG;
	}

	public void setMyTurn(Boolean myT) {
		this.myTurn = myT;
	}

	public void setID(int ID) {
		this.ID = ID;

	}

	public int getID() {
		return this.ID;
	}

	public String getUsername() {
		return this.username;
	}

	public Board getBoard() {
		return this.board;
	}

	public void changeUsername(String newUsername) {
		System.out.println("changed your username in " + newUsername);
		this.username = newUsername;
	}

	public void setBoard(Board board) {
		this.board = board;
		this.advancedGame=board.isAdvancedGame();
		this.board.getPlayers().stream().forEach(p -> {
			if (p.getPlayerName().equals(this.username)) {
				this.myPlayer = p;
			}
		});
		System.out.println("\nI TUOI DATI:\n" + this.myPlayer.toString());
	}

	public static void main(String[] args) throws IOException, NotBoundException {
		@SuppressWarnings("unused")
		Client c = new Client();
	}

	public boolean getActionPerformed() {
		return this.actionPerformed;
	}
	
	public void setActionPerformed(Boolean b){
		this.actionPerformed=b;
	}


	public boolean isAdvancedGame() {
		return advancedGame;
	}

}