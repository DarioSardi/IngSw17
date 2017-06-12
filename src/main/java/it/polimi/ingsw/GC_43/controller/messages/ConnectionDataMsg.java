package it.polimi.ingsw.GC_43.controller.messages;

public class ConnectionDataMsg extends Message{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6764704479040936827L;
	private String username;
	private int ID;

	public ConnectionDataMsg(String username) {
		this.username = username;
		this.ID=-1;
	}
	
	public void setID(int iD){
		this.ID=iD;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getID() {
		return ID;
	}

	

}
