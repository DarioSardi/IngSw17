package it.polimi.ingsw.GC_43.model.actionCreations;

        import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;

        import it.polimi.ingsw.GC_43.model.FamilyMember;
        import it.polimi.ingsw.GC_43.model.Player;

public class CommonActionCreatorRoutine implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;




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

