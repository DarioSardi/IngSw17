package it.polimi.ingsw.GC_43.model.effects;

import it.polimi.ingsw.GC_43.model.FamilyMember;

public class AdditionalDieValueAndResourceDiscountToTower extends Effect {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5232001138766919311L;


	private String cardType;
	private int extraValue;
	private String resourceType;
	private int resourceValue;
	
	
	//FOR SAM aggiungi "CharacterCard" o "VentureCard" o "BuildingCard" o "TerritoryCard"
	//
	public AdditionalDieValueAndResourceDiscountToTower(String cardType,int extraValue, int coinValue) {
		this.cardType=cardType;
		this.extraValue=extraValue;
		this.resourceValue=coinValue;
		this.resourceType="coin";
		
	}
    public String toString(){
        String toString = "Player will receve an additional die value of: "+this.extraValue+" on: "+this.cardType+"\n"+"with an additional discount on: "+resourceType+" of: "+this.resourceValue; 
        return toString;

    }
	
	
	public void executeEffect(FamilyMember familyMember){
		System.out.println("Executing AdditionalDieValueAndResourceDiscountToTower Effect");
		if(cardType.equals("VentureCard")){
			int oldValue=familyMember.getPlayer().getPlayerBounusMalus().getBonusDiceVentureTower();
			familyMember.getPlayer().getPlayerBounusMalus().setBonusDiceVentureTower(oldValue+this.extraValue);
			int oldDiscountValue=familyMember.getPlayer().getPlayerBounusMalus().getBonusCoinsOnBuyInTowers().get(this.cardType);
			familyMember.getPlayer().getPlayerBounusMalus().getBonusCoinsOnBuyInTowers().put(resourceType,oldDiscountValue+this.resourceValue);
		}
		else if(cardType.equals("TerritoryCard")){
			int oldValue=familyMember.getPlayer().getPlayerBounusMalus().getBonusDiceTerritoryTower();
			familyMember.getPlayer().getPlayerBounusMalus().setBonusDiceTerritoryTower(oldValue+this.extraValue);
			int oldDiscountValue=familyMember.getPlayer().getPlayerBounusMalus().getBonusCoinsOnBuyInTowers().get(this.cardType);
			familyMember.getPlayer().getPlayerBounusMalus().getBonusCoinsOnBuyInTowers().put(this.resourceType,oldDiscountValue+this.resourceValue);
		}
		else if(cardType.equals("BuildingCard")){
			int oldValue=familyMember.getPlayer().getPlayerBounusMalus().getBonusDiceBuildingTower();
			familyMember.getPlayer().getPlayerBounusMalus().setBonusDiceBuildingTower(oldValue+this.extraValue);
			int oldDiscountValue=familyMember.getPlayer().getPlayerBounusMalus().getBonusCoinsOnBuyInTowers().get(this.cardType);
			familyMember.getPlayer().getPlayerBounusMalus().getBonusCoinsOnBuyInTowers().put(this.resourceType,oldDiscountValue+this.resourceValue);
					
		}
		else if(cardType.equals("CharacterCard")){
			int oldValue=familyMember.getPlayer().getPlayerBounusMalus().getBonusDiceCharacterTower();
			familyMember.getPlayer().getPlayerBounusMalus().setBonusDiceCharacterTower(oldValue+this.extraValue);
			int oldDiscountValue=familyMember.getPlayer().getPlayerBounusMalus().getBonusCoinsOnBuyInTowers().get(this.cardType);
			familyMember.getPlayer().getPlayerBounusMalus().getBonusCoinsOnBuyInTowers().put(this.resourceType,oldDiscountValue+this.resourceValue);
		}
	}

}
