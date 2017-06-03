package it.polimi.ingsw.GC_43.view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
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
	private static InetAddress ipAddr;
	
    public Client() throws IOException{
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
	
	private void setID(int ID){
		this.ID=ID;
		System.out.println("connected with ID:"+this.ID);
	}


	private void connect() throws UnknownHostException, IOException {
    	System.out.println("tento di connettermi...");
    	socket = new Socket(address, port);
    	
    	//thread per gestione I/O
    	ExecutorService executor = Executors.newFixedThreadPool(2);
    	executor.submit(new ClientInHandler(new Scanner(socket.getInputStream())));
    	executor.submit(new ClientOutHandler(new PrintWriter(socket.getOutputStream())));
    	
        System.out.println("connesso!");
    }



	public void setup() throws IOException{
    	inKeyboard = new BufferedReader(new InputStreamReader(System.in));
        outVideo = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), true);
    	ipAddr=InetAddress.getLoopbackAddress();
        System.out.println("Benvenuto su Lorenzo il magnifico in JAVA");
    	System.out.println("seleziona l'indirizzo del server: ");
    	this.address=inKeyboard.readLine();
    	System.out.println("seleziona la porta del server: ");
    	this.port=Integer.parseInt(inKeyboard.readLine());
    	System.out.println("Username: ");
    	this.username=inKeyboard.readLine();
    	System.out.println("connetto a: "+address+"/"+port);
    }
	
	public static void main(String [] args) throws IOException {
		 Client c= new Client();
	 }

}
