package it.polimi.ingsw.GC_43.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Player {
	private String playerName;
	private PlayerBonusMalus playerBounusMalus;
	private HashMap<String, Integer> playerResources;
	private List<FamilyMember> familyMembers;
	private ArrayList<Boolean> excommunications;
	
	public Player(String name, int initCoins){
		this.playerName = name;
    	this.excommunications = new ArrayList<Boolean>();
    	this.familyMembers = new ArrayList<FamilyMember>();  
    	this.playerBounusMalus= new PlayerBonusMalus();
    	generateHashmapResources(initCoins);
    	initializeFamilyMembers();
    		}
	
	private void generateHashmapResources(int coins){
		this.playerResources= new HashMap<String, Integer>();
		this.playerResources.put("coin", coins);
		this.playerResources.put("servant", GlobalVariables.initialServants);
		this.playerResources.put("stone", GlobalVariables.initialStones);
		this.playerResources.put("wood", GlobalVariables.initialWoods);
		this.playerResources.put("victoryPoint", GlobalVariables.initialVictoryPoints);
		this.playerResources.put("militaryPoint", GlobalVariables.initialMilitaryPoints);
		this.playerResources.put("faithPoint", GlobalVariables.initialFaithPoints);
		
		//SAMUEL Gestione delle monete iniziali
	}
	
	
	
	public HashMap<String, Integer> getPlayerResources() {
		return playerResources;
	}
	
	public String getNamePlayer(){
		return this.playerName;
	}

	public void setPlayerResources(HashMap<String, Integer> resources) {
		this.playerResources = resources;
	}

	public int getPlayerResource(String resource){
		return this.playerResources.get(resource);					//FRANCESCO-SAMUEL Come mai era in un blocco di commento?
	}
	
	public void setPlayerResource(String resource, int value){
		this.playerResources.put(resource, value);
	}
	
	public boolean getExcommunications(int period){
		return this.excommunications.get(period-1);
	}
	
	public void setExcommunications(int period, boolean control){
		this.excommunications.set(period-1, control);
	}
	
	
	public PlayerBonusMalus getPlayerBounusMalus() {
		return playerBounusMalus;
	}

	public void setPlayerBounusMalus(PlayerBonusMalus playerBounusMalus) {
		this.playerBounusMalus = playerBounusMalus;
	}

	public void initializeExcommunication(){
		for(int i=0; i < GlobalVariables.totalNumberOfPeriods; i++){
			this.excommunications.add(false);
		}
	}
	
	private void initializeFamilyMembers(){
		for(int i=0; i < GlobalVariables.numberOfFamilyMembers; i++){
			this.familyMembers.add(new FamilyMember(this, i));
		}
	}
	
	//SAMUEL pensare al reinitialize dei familiari
	public void callToReinitializeFamilyMember(){		//SAMUEL per mettere posizioni a null
		for(int i=0; i < GlobalVariables.numberOfFamilyMembers;  i ++){
			this.familyMembers.get(i).reinitializeFamilyMember(); //SAMUEL creare metodo in FamilyMember
		}
	}

	public void printFamiliars(){
		//SAMUEL da fare printFamiliars
	}
	
	@Override
	public String toString(){
		//SAMUEL toString per stampare le risorse
		return "";
	}
}