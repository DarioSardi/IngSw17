package it.polimi.ingsw.GC_43.model.actionPerforms;

import it.polimi.ingsw.GC_43.model.actions.Action;

//this interface and classes which will implement it will be on server only
public interface ActionPerformer {
	
	boolean performAction(Action action);

}
