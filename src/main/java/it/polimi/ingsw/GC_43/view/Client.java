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

import it.polimi.ingsw.GC_43.model.Board;

public class Client {
	private int port,ID;
	private Socket socket;
    private ObjectInputStream inSocket;
    private ObjectOutputStream outSocket;
    private BufferedReader inKeyboard;
    private PrintWriter outVideo;
	private String address,username;
	private ClientOutHandler outStream;
	private ClientInHandler inStream;
	private static InetAddress ipAddr;
	private Board board;
	Boolean idSetted,inMenu,inGame,online,actionPerformed,myTurn;

    public Client() throws IOException{
    	setup();
    	this.online=true;
    	this.inMenu=true;
    	this.inGame=false;
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


    //DARIO temporaneo per il test
	private void createBoardTest() {
		ArrayList<String> playersID = new ArrayList<>();
		playersID.add("0");
		this.board=new Board(playersID);
		this.board.toString();
		this.board.getTowers().toString();
		
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
		System.out.println("board settata");
		System.out.println(this.board.toString());
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
    	else{System.out.println("ma che cazz...");}
    	
    	System.out.println("connetto a: "+address+"/"+port);
    }
	
	public static void main(String [] args) throws IOException {
		 Client c= new Client();
	 }

}
