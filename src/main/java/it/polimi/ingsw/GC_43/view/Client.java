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
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
	private int port,ID;
	private Socket socket;
    private BufferedReader inSocket;
    private PrintWriter outSocket;
    private BufferedReader inKeyboard;
    private PrintWriter outVideo;
	private String address,username;
	private ClientOutHandler outStream;
	private ClientInHandler inStream;
	private static InetAddress ipAddr;
	boolean inLobby,busy,inGame;
	
    public Client() throws IOException{
    	this.inLobby=false;
    	this.inGame=false;
    	this.busy=false;
    	setup();
    	connect();
    	startToPlay();
    	//closeGame();
    }
    
    
    
    private void closeGame() {
		try {
			inSocket.close();
			outSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}



	private void startToPlay() {
		// TODO Auto-generated method stub
		
	}
	
	public void setID(int ID){
		this.ID=ID;
		
	}
	
	public int getID(){
		return ID;
	}
	

	public String getUsername() {
		return username;
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
    	while(true){
    	System.out.println("scegli configurazione: manual o auto?");
    	String answer=inKeyboard.readLine().toString();
    	
    	if(answer.equals("manual")){
	    	System.out.println("seleziona l'indirizzo del server: ");
	    	this.address=inKeyboard.readLine();
	    	System.out.println("seleziona la porta del server: ");
	    	this.port=Integer.parseInt(inKeyboard.readLine());
	    	System.out.println("Username: ");
	    	this.username=inKeyboard.readLine();
	    	break;
        }
        
    	else if(answer.equals("auto")){
        	this.address="127.0.0.1";
        	this.port=7777;
        	this.username="PanDario7";
        	break;
        }
    	else{System.out.println("scelta non valida, riproviamo...");}
    	}
    	
    	System.out.println("connetto a: "+address+"/"+port);
    }
	
	public static void main(String [] args) throws IOException {
		 Client c= new Client();
	 }
	
	public void sendObject(Object obj){
		this.outStream.sendObject(obj);
	}

}
