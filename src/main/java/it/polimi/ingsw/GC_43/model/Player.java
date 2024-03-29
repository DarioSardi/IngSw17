package it.polimi.ingsw.GC_43.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import it.polimi.ingsw.GC_43.model.actions.Action;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class Player implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4171356456229890955L;
	private String playerName;
	private PlayerBonusMalus playerBounusMalus;
	private PlayerCards playerCards;
	private HashMap<String, Integer> playerResources;
	private ArrayList<FamilyMember> familyMembers;
	private ArrayList<Boolean> excommunications;
	private ArrayList<Action> extraActions;
	private ArrayList<Effect> personalProductionBonus;
	private ArrayList<Effect> personalHarvestBonus;
	
	
	public Player(String name, int initCoins){
		this.playerName = name;
    	this.excommunications = new ArrayList<Boolean>();
    	this.familyMembers = new ArrayList<FamilyMember>();  
    	this.playerBounusMalus= new PlayerBonusMalus();
    	this.playerCards = new PlayerCards();
    	this.personalProductionBonus = new ArrayList<>();
    	this.personalHarvestBonus = new ArrayList<>();
    	this.extraActions = new ArrayList<>();
    	
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
	
	public void setPlayerResource(String resource, int value) throws Exception{
		if ("coin".equals(resource)||"servant".equals(resource)||"stone".equals(resource)||"wood".equals(resource)||
			"victoryPoint".equals(resource)||"militaryPoint".equals(resource)||"faithPoint".equals(resource))
		this.playerResources.put(resource, value);
		else throw new Exception();
	}
	
	public int getPlayerResource(String resource){
		return this.playerResources.get(resource);
	}
	
	public void addResource(String resource, int value){
		try {
			if ("coin".equals(resource)||"servant".equals(resource)||"stone".equals(resource)||"wood".equals(resource)||
					"victoryPoint".equals(resource)||"militaryPoint".equals(resource)||"faithPoint".equals(resource))
			this.playerResources.put(resource, this.getPlayerResource(resource) + value);
		} catch (Exception e) {
			System.out.println("addResource: Resource not found.");
			e.printStackTrace();
		}
//Far controllare a Fra che i valori siano > 0
	}
	
	public void subResource(String resource, int value){
		try {
			if (
				"coin".equals(resource)||"servant".equals(resource)||"stone".equals(resource)||"wood".equals(resource)||
				"victoryPoint".equals(resource)||"militaryPoint".equals(resource)||"faithPoint".equals(resource))
					this.playerResources.put(resource, this.getPlayerResource(resource) - value);
		} catch (Exception e) {
			System.out.println("subResource: Resource not found.");
			e.printStackTrace();
		}
//Far controllare a Fra che i valori siano > 0
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
		
	public ArrayList<Effect> getPersonalProductionBonus() {
		return personalProductionBonus;
	}

	public void setPersonalProductionBonus(ArrayList<Effect> personalProductionBonus) {
		this.personalProductionBonus = personalProductionBonus;
	}

	public ArrayList<Effect> getPersonalHarvestBonus() {
		return personalHarvestBonus;
	}

	public void setPersonalHarvestBonus(ArrayList<Effect> personalHarvestBonus) {
		this.personalHarvestBonus = personalHarvestBonus;
	}

	/**
	 * Returns a boolean that tells if the player has the excommunication in the period
	 * @param period 
	 * @return if true, the player has an excommunication 
	 */
	
	public boolean getExcommunications(int period){
		if (period >= 0 && period <= GlobalVariables.totalNumberOfPeriods)
		return this.excommunications.get(period-1);
		else return false;
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
	
	public ArrayList<FamilyMember> getFamilyMembers() {
		return this.familyMembers;
	}
	
	public FamilyMember getFamilyMember(int numFamilyMember) {
		return this.familyMembers.get(numFamilyMember);
	}

	public void setFamilyMember(FamilyMember familyMember) {
		this.familyMembers.add(familyMember);
	}

	/**
	 * Set the family members position to null.
	 */
	public void reinitializeFamilyMemberPosition(){
		for(int i=0; i < GlobalVariables.numberOfFamilyMembers;  i ++){
			this.familyMembers.get(i).setFamilyMemberPosition(null);
		}
	}
	
	/**
	 * Set the value of the malus on die for each family member.
	 * 
	 * @param malusOnDie Value of the malus on die.
	 */
	public void assignsDieMalusToFamilyMembers(int malusOnDie){ //FRANCESCO-SAMUEL Metodo per settare il malus
		for(int i = 0; i < this.familyMembers.size(); i++){
			this.familyMembers.get(i).setMalusOnDie(malusOnDie);
		}
	}	

	/**
	 * @return free Returns an ArrayList of family members not yet positioned
	 */
	public ArrayList<FamilyMember> getFreeFamilyMembers(){
		ArrayList<FamilyMember> free = new ArrayList<FamilyMember>();
		for (int i=0; i < this.familyMembers.size(); i++)
			if(this.familyMembers.get(i).isAlreadyPlaced() == false)
				 free.add(this.familyMembers.get(i));
		return free;
	}	
	
	public ArrayList<Action> getExtraActions() {
		return extraActions;
	}

	public void setExtraActions(ArrayList<Action> extraActions) {
		this.extraActions = extraActions;
	}
	
	/**
	 * @param color 
	 * @return Returns the familyMember with the required color.
	 */
	public FamilyMember findFamilyMemberByColor(int color){
		int i=0;
		while (this.familyMembers.get(i).getColor()!=color){i++;}
		return this.familyMembers.get(i);
	}
	
	public String printFamilyMembers(){
		String s="Family Members:\n";
		for (int i=0; i < this.familyMembers.size(); i++)
			s = s + i +") " +'\n' + this.familyMembers.get(i).toString()+"\n\n";
		return s;
	}
	
	public String printFreeFamilyMembers(){
		String s="Family Members:\n";
		for (int i=0; i < this.familyMembers.size(); i++)
			if(this.familyMembers.get(i).getFamilyMemberPosition() == null)
				s = s + i +") " +'\n' + this.familyMembers.get(i).toString()+"\n\n";
		return s;
	}
	
	@Override
	//Player resources
	public String toString(){
		String s="Player: " + this.getPlayerName() + '\n';	  
		for (Map.Entry<String, Integer> entry : this.playerResources.entrySet()) {
			  s = s + entry.getKey() + ": " + entry.getValue() + '\n' ;
		}
		return s;	
	}
}

