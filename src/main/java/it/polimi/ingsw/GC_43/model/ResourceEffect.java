package it.polimi.ingsw.GC_43.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResourceEffect extends Effect {
	
	private List<Resource> resourcesToGet;
	
	public ResourceEffect(){
		resourcesToGet= new ArrayList<Resource>();
		
	}
	
	
	
	public void addResources(HashMap<String,Integer> playerResources){
		for( Resource resource: resourcesToGet){
			int newValue=playerResources.get(resource.getTipoRisorsa())+ resource.getValue();
			playerResources.put(resource.getTipoRisorsa(), newValue);
			
		}
	}
	
	public void ExecuteEffect(FamilyMember familyMember){
		addResources(familyMember.getPlayer().getPlayerResources());
			
		}

	
//If you want to test it, try it out
/*
public static void main(String [] args){
	Player p = new Player("ciao", 2);
	ResourceEffect re = new ResourceEffect();
	re.resourcesToGet.add(new Wood(3));
	re.resourcesToGet.add(new Wood(3));
	re.resourcesToGet.add(new Wood(3));
	re.resourcesToGet.add(new Servant(3));
	re.resourcesToGet.add(new FaithPoint(3));


	
	re.addResources(p.getPlayerResources());
	System.out.println(p.getPlayerResources().get("wood"));	
	System.out.println(p.getPlayerResources().get("servant"));	
	System.out.println(p.getPlayerResources().get("faithPoint"));	


	}*/
}
	
	

