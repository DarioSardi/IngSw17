package it.polimi.ingsw.GC_43.model.effects;
import it.polimi.ingsw.GC_43.model.FamilyMember;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.resources.*;
/*
public class ResourceEffect extends Effect {
	
	private List<Resource> resourcesToGet;
	
	public ResourceEffect(){
		resourcesToGet= new ArrayList<Resource>();
		
	}
	
	public String toString(){
		String resourcesToString = new String();
		for(Resource resource: resourcesToGet){
			resourcesToString= resourcesToString+this.resourcesToGet.toString();
		}
		return resourcesToString;
		
	}
	
	
	
	public void addResources(Player player){
		for( Resource resource: resourcesToGet){
			int newValue=player.getPlayerResources().get(resource.getResourceType())+ resource.getValue()+player.getPlayerBounusMalus().getMalusOnAcquiringResources().get(resource.getResourceType());
			player.getPlayerResources().put(resource.getResourceType(), newValue);
			
		}
	}
	
	public void executeEffect(FamilyMember familyMember){
		addResources(familyMember.getPlayer());
			
		}

	
//If you want to test it, try it out

public static void main(String [] args){
	Player p = new Player("ciao", 2);
	ResourceEffect re = new ResourceEffect();
	re.resourcesToGet.add(new Wood(3));
	re.resourcesToGet.add(new Wood(3));
	re.resourcesToGet.add(new Wood(3));
	re.resourcesToGet.add(new Servant(3));
	re.resourcesToGet.add(new FaithPoint(3));
	ArrayList <Effect> effects= new ArrayList <Effect>();
	effects.add(re);
	effects.add(new MalusOnFinalVictoryPoints("victoryPoint"));


	
	re.addResources(p);
	System.out.println(p.getPlayerResources().get("wood"));	
	System.out.println(p.getPlayerResources().get("servant"));	
	System.out.println(p.getPlayerResources().get("faithPoint"));	


	}
}*/




/**
 * Created by FrancescoSaverio on 30/05/2017.
 */
public class ResourceEffect extends Effect {

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private Resource resource;

        public ResourceEffect(Resource resource){
            
        	this.resource=resource;

        }

        public String toString(){
            String toString = "  "+this.resource.toString()+"\n"; 
            return toString;

        }



       public void addResources(Player player){
                int newValue=resource.getValue()+player.getPlayerBounusMalus().getMalusOnAcquiringResources().get(resource.getResourceType());
                player.addResource(resource.getResourceType(), newValue);
            }
        

        public void executeEffect(FamilyMember familyMember){
            addResources(familyMember.getPlayer());
            
        }


}

	

