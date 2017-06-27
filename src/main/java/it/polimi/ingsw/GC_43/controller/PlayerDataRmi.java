package it.polimi.ingsw.GC_43.controller;

import it.polimi.ingsw.GC_43.view.UserRmiInterface;

public class PlayerDataRmi {
	private String username;
	private Lobby lobby;
	private Integer id;
	private UserRmiInterface client;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Lobby getLobby() {
		return lobby;
	}
	public void setLobby(Lobby lobby) {
		this.lobby = lobby;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public UserRmiInterface getClient() {
		return client;
	}
	public void setClient(UserRmiInterface client) {
		this.client = client;
	}
	
	
}
