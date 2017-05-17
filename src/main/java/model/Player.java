package model;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private String playerName;
	private PlayerBonusMalus playerBounusMalus;
	private int color;
	private ArrayList<Resource> playerResouces;
	private int victoryPoints;
	private int militaryPoints;
	private int faithPoints;
	private List<FamilyMember> familyMembers;
	private ArrayList<Boolean> excommunications;
	
	public Player(String name){
		this.playerName = name;
    	this.excommunications = new ArrayList<Boolean>();
    	this.familyMembers = new ArrayList<FamilyMember>();   
    	this.playerBounusMalus= new PlayerBonusMalus();
    	
	}
	
	public void setPlayerResources(){
		//SAMUEL setPlayerResources Pensare all'hashmap
	}
	
	public int getVictoryPoints(){
		return this.victoryPoints;
	}
	
	public void setVictoryPoints(int points){
		this.victoryPoints = points;
	}
	
	public int getMilitaryPoints(){
		return this.militaryPoints;
	}
	
	public void setMilitaryPoints(int points){
		this.militaryPoints = points;
	}
	
	public int getFaithPoints(){
		return this.faithPoints;
	}
	
	public void setFaithPoints(int points){
		this.faithPoints = points;
	}	
	
	public boolean getExcommunications(int era){
		return this.excommunications.get(era-1);
	}
	
	public void setExcommunications(int era, boolean control){
		this.excommunications.set(era-1, control);
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
	
	@Override
	public String toString(){
		//SAMUEL toString per stampare le risorse
		return "";
	}
	
	//SAMUEL pensare al reinitialize dei familiari
	public void callToReinitializeFamilyMember(){		//SAMUEL per mettere posizioni a null
		int i=0;
		while(i < GlobalVariables.numberOfFamilyMembers){
			this.familyMembers.get(i).reinitializeFamilyMember();
			i++;
		}
	}
	
}
