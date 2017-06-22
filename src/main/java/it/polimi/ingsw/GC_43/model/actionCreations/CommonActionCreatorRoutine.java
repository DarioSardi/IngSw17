package it.polimi.ingsw.GC_43.model.actionCreations;

        import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

        import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.GlobalVariables;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.effects.ChoiceEffect;
import it.polimi.ingsw.GC_43.model.effects.MultipleChoiceEffect;
import it.polimi.ingsw.GC_43.model.effects.MultipleCouncilPrivileges;
import it.polimi.ingsw.GC_43.model.resources.Coin;
import it.polimi.ingsw.GC_43.model.resources.FaithPoint;
import it.polimi.ingsw.GC_43.model.resources.MilitaryPoint;
import it.polimi.ingsw.GC_43.model.resources.Resource;
import it.polimi.ingsw.GC_43.model.resources.Servant;
import it.polimi.ingsw.GC_43.model.resources.Stone;
import it.polimi.ingsw.GC_43.model.resources.VictoryPoint;
import it.polimi.ingsw.GC_43.model.resources.Wood;

public class CommonActionCreatorRoutine implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = -4995255312527494508L;





	public static MultipleCouncilPrivileges copyMultiplePrivileges(int numberOfCopies){
		MultipleCouncilPrivileges multipleCopy= new MultipleCouncilPrivileges(numberOfCopies);
		ArrayList<ChoiceEffect> choiceEffects= new ArrayList<ChoiceEffect>();
		for(ChoiceEffect choiceEffect: GlobalVariables.councilPrivilegeEffect.getChoices()){
			System.out.println("In loop choice");

			ArrayList<Resource> costs= new ArrayList<Resource>();
			ArrayList<Resource> gains= new ArrayList<Resource>();

			for(Resource resource: choiceEffect.getGains()){
				gains.add(createCorrespondingResource(resource));					
			}
			choiceEffects.add(new ChoiceEffect(costs,gains));
			

	}
	multipleCopy.setPrivilegeChoices(new MultipleChoiceEffect(choiceEffects));
//	System.out.println("\nOut of loop, multiple choice result: \n"+multipleCopy.toString());

	return multipleCopy;
}

	private static Resource createCorrespondingResource(Resource resource) {
		System.out.println("create resource costs");

		if(resource.getResourceType().equals("coin"))
			return new Coin(resource.getValue());
		if(resource.getResourceType().equals("stone"))
			return new Stone(resource.getValue());
		if(resource.getResourceType().equals("wood"))
			return new Wood(resource.getValue());
		if(resource.getResourceType().equals("faithPoint"))
			return new FaithPoint(resource.getValue());
		if(resource.getResourceType().equals("servant"))
			return new Servant(resource.getValue());
		if(resource.getResourceType().equals("victoryPoint"))
			return new VictoryPoint(resource.getValue());
		if(resource.getResourceType().equals("militaryPoint"))
			return new MilitaryPoint(resource.getValue());
		
		return null;
	}

	public static HashMap<String,Integer> copyPlayerResources(Player player){
        HashMap<String,Integer> copyOfPlayerResources= new HashMap<String,Integer> ();
        copyOfPlayerResources.put("coin", player.getPlayerResource("coin"));
        copyOfPlayerResources.put("servant", player.getPlayerResource("servant"));
        copyOfPlayerResources.put("stone", player.getPlayerResource("stone"));
        copyOfPlayerResources.put("wood", player.getPlayerResource("wood"));
        copyOfPlayerResources.put("victoryPoint", player.getPlayerResource("victoryPoint"));
        copyOfPlayerResources.put("militaryPoint", player.getPlayerResource("militaryPoint"));
        copyOfPlayerResources.put("faithPoint", player.getPlayerResource("faithPoint"));
        return copyOfPlayerResources;

    }
    
    

    public static FamilyMember askForFamilyMemberChoice(Player player) {
        String question="Please select the familiar you want to use to perform the action:\n"+player.printFreeFamilyMembers();
        int choice=askForSingleChoice(question,0,player.getFamilyMembers().size());

        return player.getFamilyMember(choice);
    }
    
    

    public static int askForServantsUsage(Player player, int currentDieValue) {
        int numberOfServants=player.getPlayerResource("servant");
        int choice=0;
        boolean isTwoServantsCountAsOne=player.getPlayerBounusMalus().isTwoServantsCountAsOne();
        if(numberOfServants>0){
            String question= "Please insert how many servants you want to use to increase your familiar current die value of "+currentDieValue;
            question= question +"\n You have a maximum of "+numberOfServants+ " servants available and isTwoServantsCountAsOne malus is set "+isTwoServantsCountAsOne;
            choice=askForSingleChoice(question,0,numberOfServants+1);

            if(isTwoServantsCountAsOne)
                choice=choice/2;

        }
        
        return choice;

    }
    
    
    
    
    public static int askForSingleChoice(String question,int minRange, int maxRange) {
        boolean choiceOk=false;
        int choiceTaken=0;
        while(!choiceOk){
            Scanner reader= new Scanner(System.in);

            System.out.println("\n"+question+"\n");

            try{
                int choice=reader.nextInt();
                if(choice>=minRange&&choice<maxRange){
                	System.out.println("Choice ok");
                    choiceOk=true;
                    choiceTaken=choice;
                }
                else{
                    System.out.println("\ninput choice not valid");

                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

        return choiceTaken;
    }

}

