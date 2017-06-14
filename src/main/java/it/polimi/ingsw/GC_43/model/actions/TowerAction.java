package it.polimi.ingsw.GC_43.model.actions;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.Player;

public class TowerAction extends Action {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Integer> towerChoices;

	    public TowerAction(String playerID, Player player){
	    	super(playerID,player);
	        this.towerChoices=new ArrayList<Integer>();
	        this.setActionID(5);
	    }

		public ArrayList<Integer> getTowerChoices() {
			return towerChoices;
		}

		public void setTowerChoices(ArrayList<Integer> towerChoices) {
			this.towerChoices = towerChoices;
		}
	    
	    

	    


}
