package it.polimi.ingsw.GC_43.model.effects;

import java.io.Serializable;
import java.util.List;

import it.polimi.ingsw.GC_43.model.FamilyMember;

public abstract class Effect implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3322020192223760014L;

	/**
	 * 
	 */

	public String toString(){
		String toString=null;
		return toString;
	}
	public void executeEffect(FamilyMember familyMember){
		System.out.println("Executing GENERAL EFFECT Effect");

		
	}
	
	public boolean check(FamilyMember familyMember){
		return true;
	}

}
