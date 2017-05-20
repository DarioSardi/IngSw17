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
    	initializeFamilyMembers();
    	initializeExcommunication();
    }
	
	/**
	 * Generete the initial resources for the player
	 * @param coins number of coins according to the turn order
	 */
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
	
	public String getPlayerName(){
		return this.playerName;
	}

	public void setPlayerResources(HashMap<String, Integer> resources) {
		this.playerResources = resources;
	}
	
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
	
	/**
	 * Returns, for the period selected, if the player has the excommunication
	 * @param period 
	 * @return if true, the player has an excommunication 
	 */
	
	public boolean getExcommunications(int period){
		return this.excommunications.get(period-1);
	}
		
	public PlayerBonusMalus getPlayerBounusMalus() {
		return playerBounusMalus;
	}

	public void setPlayerBounusMalus(PlayerBonusMalus playerBounusMalus) {
		this.playerBounusMalus = playerBounusMalus;
	}

	private void initializeExcommunication(){
		for(int i=0; i < GlobalVariables.totalNumberOfPeriods; i++){
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
	
	public FamilyMember getFamilyMember(int numFamilyMember) {
		return this.familyMembers.get(numFamilyMember);
	}

	public void setFamilyMember(FamilyMember familyMember) {
		this.familyMembers.add(familyMember);
	}

	public void printFamiliars(){
		//SAMUEL da fare printFamiliars
	}

	public void reinitializeFamilyMemberPosition(){
		for(int i=0; i < GlobalVariables.numberOfFamilyMembers;  i ++){
			this.familyMembers.get(i).setFamilyMemberPosition(null);
		}
	}
	
	public void assignsDieMalusToFamilyMembers(int malusOnDie){ //FRANCESCO-SAMUEL Metodo per settare il malus
		for(int i = 0; i < this.familyMembers.size(); i++){
			this.familyMembers.get(i).setMalusOnDie(malusOnDie);
		}
	}
	
	@Override
	public String toString(){
		//SAMUEL toString per stampare le risorse
		return "";
	}
}
