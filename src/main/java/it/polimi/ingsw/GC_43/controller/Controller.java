package it.polimi.ingsw.GC_43.controller;

import java.util.HashMap;
import java.util.Map;

import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.playerActions.*;

public class Controller implements IController {
	    private final Map<String, String> userPassword = new HashMap<String, String>();
	    private final Map<String, Player> matchPlayer= new HashMap<String, Player>();
	    
	    
	    public Controller(){
	    	
	    	
	    }
	    
	    
	    public boolean login(String username, String password) {
	        String pw = userPassword.get(username);

	        if (pw == null) {
	            userPassword.put(username, password);
	            return true;
	        }

	        return password.equals(pw);
	    }
	    
	    
	    
	    
	    public boolean submit(Action action){
	    	int actionID= action.getActionID();
	    	switch(actionID){
	    		case 0:
	    			ProductionAction productionAction=(ProductionAction) action;
	    			ProductionActionPerformer productionActionImpl= new ProductionActionPerformer(productionAction);
	    			boolean result = productionActionImpl.performAction();
	    			return result;
	    		case 1:
	    			
	    			
	    		case 2:
	    			
	    		
	    		default:
	    			return false;
	    	}
	    }

}
