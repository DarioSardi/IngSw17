package it.polimi.ingsw.GC_43.model.actions;

        import java.util.ArrayList;
        import it.polimi.ingsw.GC_43.model.FamilyMember;
        import it.polimi.ingsw.GC_43.model.Player;



public class ProductionAction extends Action {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Integer> productionChoices;
    private boolean primaryCellChosen;
	private boolean defaultFamilyMember;

    public ProductionAction(String playerID, Player player){
    	super(playerID,player);
        this.productionChoices=new ArrayList<>();
        this.setActionID(1);
    }



    public ArrayList<Integer> getProductionChoices() {
        return productionChoices;
    }
    public void setProductionChoices(ArrayList<Integer> productionChoices) {
        this.productionChoices = productionChoices;
    }



    public boolean isPrimaryCellChosen() {
        return primaryCellChosen;
    }


    public void setPrimaryCellChosen(boolean primaryCellChosen) {
        this.primaryCellChosen = primaryCellChosen;
    }



	public boolean isDefaultFamilyMember() {
		return defaultFamilyMember;
	}



	public void setDefaultFamilyMember(boolean defaultFamilyMember) {
		this.defaultFamilyMember = defaultFamilyMember;
	}
    
    


}

