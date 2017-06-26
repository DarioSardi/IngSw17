package it.polimi.ingsw.GC_43.model.actions;

import java.io.Serializable;

public class ExtraAction implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5342556355376156658L;
	private Action extraAction;
	
	public ExtraAction(Action extraAction){
		this.extraAction=extraAction;
	}
}
