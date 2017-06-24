package it.polimi.ingsw.GC_43.model.cards;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.GlobalVariables;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.actionSpace.Floor;
import it.polimi.ingsw.GC_43.model.effects.CostEffect;

public class CardHandler {

	private CardHandler() {
	}

	/**
	 * buy a card for the player,execute all istant bonus, add the card to the
	 * player hand and remove it from the tower
	 * 
	 * @param p
	 *            the player that want the card
	 * @param c
	 *            the card to buy
	 * @param f
	 *            floor where the card is
	 * @return true if the card can be bought
	 */
	public static boolean buyCard(Floor f, FamilyMember fam, Card c, boolean towerTax, boolean payedWithMilitary) {
		if (checkBuy(f, fam.getPlayer(), c, towerTax, payedWithMilitary)) {
			if (c.getCost() != null && !payedWithMilitary) {
				System.out.println("Message from CardHandler: managing cost of card..");
				CostEffect costEffect = (CostEffect) c.getCost();
				costEffect.executeEffect(fam.getPlayer(), c.getType());
			}
			c.getInstantBonus().stream().forEach(e -> e.executeEffect(fam));
			if (c instanceof CharacterCard) {
				c.getPermaBonus().stream().forEach(e -> e.executeEffect(fam));
			}

			return true;
		} else
			return false;
	}

	public static void addCardToPlayerPool(Card c, FamilyMember fam) {
		if (c instanceof BuildingCard) {
			System.out.println("Message from CardHandler: Adding building card " + c.getCardName()
					+ " to player building card pool");
			fam.getPlayer().getPlayerCards().addBuildingCard((BuildingCard) c);
			System.out.println("Message from CardHandler: ADDED building card "
					+ fam.getPlayer().getPlayerCards().toString());

		} else if (c instanceof CharacterCard) {
			System.out.println("Message from CardHandler: Adding character card " + c.getCardName()
					+ " to player character card pool");
			fam.getPlayer().getPlayerCards().addCharacterCard((CharacterCard) c);
		} else if (c instanceof TerritoryCard) {
			System.out.println("Message from CardHandler: Adding territory card " + c.getCardName()
					+ " to player territory card pool");
			fam.getPlayer().getPlayerCards().addTerritoryCard((TerritoryCard) c);
		} else if (c instanceof VentureCard) {
			System.out.println("Message from CardHandler: Adding venture card " + c.getCardName()
					+ " to player venture card pool");
			fam.getPlayer().getPlayerCards().addVentureCard((VentureCard) c);
		}
	}

	public static void actionsAfterBuy(Floor f, FamilyMember fam) {
		f.actionsAfterBuy(fam);
		System.out.println("adding card to player pool..");
		CardHandler.addCardToPlayerPool(f.getCard(), fam);
		f.removeCard();

	}

	// DARIO controllo quando non ho abbastanza spazi sulla plancia!!!

	public static boolean checkBuy(Floor f, Player p, Card c, boolean towerTax, boolean payedWithMilitary) {
		boolean checkResult = true;
		if ((c instanceof BuildingCard
				&& p.getPlayerCards().getArrayBuildingCards().size() < GlobalVariables.maxNumberPlayerCards)
				|| (c instanceof CharacterCard
						&& p.getPlayerCards().getArrayCharacterCards().size() < GlobalVariables.maxNumberPlayerCards)
				|| (c instanceof TerritoryCard
						&& p.getPlayerCards().getArrayTerritoryCards().size() < GlobalVariables.maxNumberPlayerCards
						&& GlobalVariables.militaryPointsRequired[p.getPlayerCards().getArrayBuildingCards().size()
								+ 1] <= p.getPlayerResource("militaryPoint"))
				|| (c instanceof VentureCard
						&& p.getPlayerCards().getArrayVentureCards().size() < GlobalVariables.maxNumberPlayerCards)) {
			System.out.println(
					"Message from CardHandler: Card could be placed among the other player cards, relative checks passed");
			// is all ok, do nothing
		} else {
			System.out.println(
					"Message from CardHandler: Maximum number of cards excedeed or military points of player unsufficient to place the card");
			return false; // something fails return false
		}
		if (towerTax) {

			System.out
					.println("Message from CardHandler: Tower already occupied, subtracting coins needed as tower tax");
			p.subResource("coin", GlobalVariables.towerTax);
		} // DARIO modificare il 3 in una globale
		if (!payedWithMilitary&&!(c.getCost()==null)) {
			System.out.println("Message from CardHandler: Attempting to check card cost..");
			if (c.getCost().check(p, c.getType())) {
				System.out.println("Message from CardHandler: Card cost check passed");
				checkResult = true;
			} else {
				System.out.println("Message from CardHandler: Card cost check NOT passed");
				checkResult = false;
			}
		}

		if (towerTax) {
			System.out.println("Message from CardHandler: Adding tower tax subtracted before");
			p.addResource("coin", GlobalVariables.towerTax);
		}

		return checkResult;
	}
}
