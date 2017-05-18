package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Player {
	private String playerName;
	private PlayerBonusMalus playerBounusMalus;
	private int color;
	private PlayerBonusMalus playerBounusMalus;
	private HashMap<String, Integer> resources;
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
		this.resources= new HashMap<String, Integer>();
		this.resources.put("coins", coins);
		this.resources.put("servants", GlobalVariables.initialServants);
		this.resources.put("stones", GlobalVariables.initialStones);
		this.resources.put("woods", GlobalVariables.initialWoods);
		this.resources.put("victoryPoints", GlobalVariables.initialWoods);
		this.resources.put("militaryPoints", GlobalVariables.initialWoods);
		this.resources.put("faithPoints", GlobalVariables.initialWoods);
		
		//SAMUEL Gestione delle monete iniziali
	}
	
	public int getPlayerResource(String resource){
		this.resources.get(resource);
	}
	
	public void setPlayerResource(String resource, int value){
		this.resources.put(resource, value);
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
