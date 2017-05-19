package it.polimi.ingsw.GC_43.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Player {
	private String playerName;
	private PlayerBonusMalus playerBounusMalus;
	private PlayerCards playerCards;
	private HashMap<String, Integer> playerResources;
	private List<FamilyMember> familyMembers;
	private ArrayList<Boolean> excommunications;
	
	public Player(String name, int initCoins){
		this.playerName = name;
    	this.excommunications = new ArrayList<Boolean>();
    	this.familyMembers = new ArrayList<FamilyMember>();  
    	this.playerBounusMalus= new PlayerBonusMalus();
    	this.playerCards = new PlayerCards();
    	generateHashmapResources(initCoins);
<<<<<<< HEAD
    		}
=======
    	initializeFamilyMembers();
    	initializeExcommunication();
    }
	
>>>>>>> branch 'master' of https://github.com/DarioSardi/IngSw17.git
	
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
		return this.playerResources;
	}

	public void setPlayerResources(HashMap<String, Integer> resources) {
		this.playerResources = resources;
	}
<<<<<<< HEAD

	public int getPlayerResource(String resource){
		this.playerResources.get(resource);					//FRANCESCO-SAMUEL Come mai era in un blocco di commento?
	}
=======
>>>>>>> branch 'master' of https://github.com/DarioSardi/IngSw17.git
	
	public void setPlayerResource(String resource, int value){
		this.playerResources.put(resource, value);
	}
	
	public int getPlayerResource(String resource){
		return this.playerResources.get(resource);
	}
	
	public PlayerCards getPlayerCards() {
		return playerCards;
	}

	public void setPlayerCards(PlayerCards playerCards) {
		this.playerCards = playerCards;
	}

	public void setExcommunications(int period, boolean control){
		this.excommunications.set(period-1, control);
	}
	
	public boolean getExcommunications(int period){
		return this.excommunications.get(period-1);
	}
		
	public PlayerBonusMalus getPlayerBounusMalus() {
		return playerBounusMalus;
	}

	public void setPlayerBounusMalus(PlayerBonusMalus playerBounusMalus) {
		this.playerBounusMalus = playerBounusMalus;
	}

<<<<<<< HEAD
	public void initializeExcommunication(){
		int i=0;
		while(i<GlobalVariables.totalNumberOfPeriods){
=======
	private void initializeExcommunication(){
		for(int i=0; i < GlobalVariables.totalNumberOfPeriods; i++){
>>>>>>> branch 'master' of https://github.com/DarioSardi/IngSw17.git
			this.excommunications.add(false);
		}
	}
	
	private void initializeFamilyMembers(){
		int i=0;
		while(i < GlobalVariables.numberOfFamilyMembers){
			this.familyMembers.add(new FamilyMember(this, i));
			i++;
		}
	}
	
	public void printFamiliars(){
		//SAMUEL da fare printFamiliars
	}
	
	//SAMUEL pensare al reinitialize dei familiari
	public void callToReinitializeFamilyMember(){		//SAMUEL per mettere posizioni a null
<<<<<<< HEAD
		int i=0;
		while(i < GlobalVariables.numberOfFamilyMembers){
			this.familyMembers.get(i).reinitializeFamilyMember(); //SAMUEL creare metodo in FamilyMember
			i++;
=======
		for(int i=0; i < GlobalVariables.numberOfFamilyMembers;  i ++){
			this.familyMembers.get(i).setFamilyMemberPosition(null); //SAMUEL creare metodo in FamilyMember
>>>>>>> branch 'master' of https://github.com/DarioSardi/IngSw17.git
		}
	}
	
	@Override
	public String toString(){
		//SAMUEL toString per stampare le risorse
		return "";
	}
}
