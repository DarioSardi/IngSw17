package it.polimi.ingsw.GC_43.model.effects;

import it.polimi.ingsw.GC_43.model.FamilyMember;

public class MalusOnFinalVictoryPoints extends Effect {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String malusTypeOnVictoryPoint;
	
	public MalusOnFinalVictoryPoints(String malusType){
		this.malusTypeOnVictoryPoint=malusType;
		
	}
	
	public String toString(){
		String toString;
		if(this.malusTypeOnVictoryPoint.equals("victoryPoint"))
			toString="The player ,right before the final calculation of victory Points, will loose an amount of victory points equal to one fifth the actual ones";
		else if(this.malusTypeOnVictoryPoint.equals("militaryPoint")){
			toString="The player will loose an amount of victory points equal to his final military force in final calculation of victory Points";
		}
		else if(this.malusTypeOnVictoryPoint.equals("resource")){
			toString="The player will loose an amount of victory points equal to the number of resource he has in the final calculation of victory Points";
		}
		
		else if(this.malusTypeOnVictoryPoint.equals("buildingCardCost")){
			toString="The player will loose an amount of victory points equal to the total amount of wood and stone present in the cost of his building cards";
		}
		else{
			toString="Player will not receive final victory Points according to the number of "+this.malusTypeOnVictoryPoint;
			
		}
				
			
		return toString;
		
	}
	/*this.malusOnFinalVictoryPoints.put("victoryPoint",false);
		this.malusOnFinalVictoryPoints.put("militaryPoint",false);
		this.malusOnFinalVictoryPoints.put("resource",false);
		this.malusOnFinalVictoryPoints.put("buildingCardCost",false);
		this.malusOnFinalVictoryPoints.put("ventureCard",false);
		this.malusOnFinalVictoryPoints.put("territoryCard",false);
		this.malusOnFinalVictoryPoints.put("characterCard",false);*/
	public void executeEffect(FamilyMember familyMember){
		familyMember.getPlayer().getPlayerBounusMalus().getMalusOnFinalVictoryPoints().put(this.malusTypeOnVictoryPoint,true);
		
	}

}
