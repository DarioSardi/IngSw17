package it.polimi.ingsw.GC_43.model.effects;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.resources.*;

public class MultipleChoiceEffect extends Effect {
		/**
	 * 
	 */
	private static final long serialVersionUID = 7770326772860231768L;

		private ArrayList<ChoiceEffect> choices;
		
		public String toString(){
			String toString="Multiple one to one exchange effect\n";
			int number =0;
			for(ChoiceEffect choiceEffect:this.choices){
				toString=toString+"choice number "+number+" -> \n   "+choiceEffect.toString()+"\n";
				number++;
			}
			
			return toString;
		}
		
		public MultipleChoiceEffect(ArrayList<ChoiceEffect> choices){
				this.choices=new ArrayList<ChoiceEffect>();
				this.choices=choices;
			}
		
		
		public void executeEffect(FamilyMember familyMember){
		
		}

		public void executeEffect(FamilyMember familyMember, int choiceNumber){
			this.choices.get(choiceNumber).executeEffect(familyMember);
			
		}



		private boolean checkChoice(int choiceNumber, Player player) {		
			return this.choices.get(choiceNumber).check(player);
			
		}

		public ArrayList<ChoiceEffect> getChoices() {
			return choices;
		}

		public void setChoices(ArrayList<ChoiceEffect> choices) {
			this.choices = choices;
		}

		
		
			

		
		
}


