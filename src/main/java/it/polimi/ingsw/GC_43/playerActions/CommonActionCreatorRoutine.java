package it.polimi.ingsw.GC_43.playerActions;

import java.util.Scanner;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.GlobalVariables;
import it.polimi.ingsw.GC_43.model.Player;

public class CommonActionCreatorRoutine {
	
	

	
	public static FamilyMember askForFamilyMemberChoice(Player player) {
		String question="Please select the familiar you want to use to perform the action:\n"+player.printFreeFamilyMembers();
		int choice=askForSingleChoice(question,0,GlobalVariables.numberOfFamilyMembers);
		
		return player.getFamilyMember(choice);
}

	public static int askForServantsUsage(Player player, int currentDieValue) {
		int numberOfServants=player.getPlayerResource("servant");
		int choice=0;
		boolean isTwoServantsCountAsOne=player.getPlayerBounusMalus().isTwoServantsCountAsOne();
		if(numberOfServants>0){
			String question= "Please insert how many servants you want to use to increase your familiar current die value of "+currentDieValue;
			question= question +"\n You have a maximum of "+numberOfServants+ " servants available and isTwoServantsCountAsOne malus is set "+isTwoServantsCountAsOne;
			choice=askForSingleChoice(question,0,numberOfServants);
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

				System.out.println("Please select the familiar you want to use to perform the action:\n");
			
				try{
					int choice=reader.nextInt();
					if(choice>=minRange&&choice<=maxRange){
						choiceOk=true;
						choiceTaken=choice;
					}
				}
				catch(Exception e){
					e.printStackTrace();
					System.out.println("\ninput choice not valid");
				}
			}
			return choiceTaken;
		}

}
