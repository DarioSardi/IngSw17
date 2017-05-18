package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Player {
	private String playerName;
	private PlayerBonusMalus playerBounusMalus;
	private int color;
	private HashMap<String, Integer> playerResources;
	private int victoryPoints;
	private int militaryPoints;
	private int faithPoints;
	private List<FamilyMember> familyMembers;
	private ArrayList<Boolean> excommunications;
	
	public Player(String name, int initCoins){
		this.playerName = name;
    	this.excommunications = new ArrayList<Boolean>();
    	this.familyMembers = new ArrayList<FamilyMember>();  
    	this.playerBounusMalus= new PlayerBonusMalus();
    	generateHashmapResources(initCoins);
    	

	}
	
	public void generateHashmapResources(int coins){
		this.playerResources= new HashMap<String, Integer>();
		this.playerResources.put("coin", coins);
		this.playerResources.put("servant", GlobalVariables.initialServants);
		this.playerResources.put("stone", GlobalVariables.initialStones);
		this.playerResources.put("wood", GlobalVariables.initialWoods);
		this.playerResources.put("victoryPoint", 0);
		this.playerResources.put("militaryPoint", 0);
		this.playerResources.put("faithPoint", 0);
		
		//SAMUEL Gestione delle monete iniziali
	}
	
	
	
	public HashMap<String, Integer> getPlayerResources() {
		return playerResources;
	}

	public void setPlayerResources(HashMap<String, Integer> resources) {
		this.playerResources = resources;
	}

/*	public int getPlayerResources(String resource){
		this.playerResources.get(resource);
	}*/
	
	public void setPlayerResource(String resource, int value){
		this.playerResources.put(resource, value);
	}
	
	public boolean getExcommunications(int era){
		return this.excommunications.get(era-1);
	}
	
	public void setExcommunications(int era, boolean control){
		this.excommunications.set(era-1, control);
	}
	
	
	public PlayerBonusMalus getPlayerBounusMalus() {
		return playerBounusMalus;
	}

	public void setPlayerBounusMalus(PlayerBonusMalus playerBounusMalus) {
		this.playerBounusMalus = playerBounusMalus;
	}

	public void initializeExcommunication(){
		int i=0;
		while(i<GlobalVariables.totalNumberOfEras){
			this.excommunications.add(false);
		}
	}
	
	public void initializeFamilyMembers(){
		int i=0;
		while(i < GlobalVariables.numberOfFamilyMembers){
			this.familyMembers.add(new FamilyMember(this, this.color));
		}
	}
	
	public void printFamiliars(){
		//SAMUEL da fare printFamiliars
	}
	
	//SAMUEL pensare al reinitialize dei familiari
	public void callToReinitializeFamilyMember(){		//SAMUEL per mettere posizioni a null
		int i=0;
		while(i < GlobalVariables.numberOfFamilyMembers){
			this.familyMembers.get(i).reinitializeFamilyMember(); //SAMUEL creare metodo in FamilyMember
			i++;
		}
	}
	
	@Override
	public String toString(){
		//SAMUEL toString per stampare le risorse
		return "";
	}
}
