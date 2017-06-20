package it.polimi.ingsw.GC_43.view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.polimi.ingsw.GC_43.controller.Lobby;
import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.GlobalVariables;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.actionCreations.MarketActionCreationRoutine;
import it.polimi.ingsw.GC_43.model.actionCreations.TowerActionCreationRoutine;
import it.polimi.ingsw.GC_43.model.initialization.GlobalVariablesInit;

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
	Boolean idSetted,inMenu,inGame,online,myTurn;
	private Player myPlayer;
	private GlobalVariables gameGlobalVariables;

    public Client() throws IOException{
    	setup();
    	this.lobby=null;
    	this.online=true;
    	this.inMenu=true;
    	this.inGame=false;
    	this.gameGlobalVariables=new GlobalVariables();
    	//DARIO test,da settare col controller
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
    
    
	public GlobalVariables getGameGlobalVariables() {
		return this.gameGlobalVariables;
	}



	public void setGameGlobalVariables(GlobalVariables gameGlobalVariables) {
		this.gameGlobalVariables = gameGlobalVariables;
	    GlobalVariablesInit.readGlobalVariables();
	    System.out.println("familiars: "+ this.gameGlobalVariables.numberOfFamilyMembers);
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
    	ExecutorService executor = Executors.newFixedThreadPool(2);
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
    	System.out.println("Benvenuto su Lorenzo il magnifico in JAVA");
    	System.out.println("scegli configurazione: manual o auto?");
    	String answer=inKeyboard.readLine().toString();
    	
    	if(answer.equals("manual")){
    	System.out.println("seleziona l'indirizzo del server: ");
    	this.address=inKeyboard.readLine();
    	System.out.println("seleziona la porta del server: ");
    	this.port=Integer.parseInt(inKeyboard.readLine());
    	System.out.println("Username: ");
    	this.username=inKeyboard.readLine();
        }
        
    	else if(answer.equals("auto")){
        	this.address="127.0.0.1";
        	this.port=7777;
        	this.username="PanDario7";
        }
    	
    	else if(answer.equals("auto1")){
        	this.address="127.0.0.1";
        	this.port=7777;
        	this.username="Dario";
        }
    	else{System.out.println("ma che cazz...");}
    	
    	System.out.println("connetto a: "+address+"/"+port);
    }
	
	public static void main(String [] args) throws IOException {
		 Client c= new Client();
	 }
	
	public void sendObj(Object o){
		System.out.println("invio oggetto "+o.toString());
		this.outStream.sendObj(o);
	}

}
