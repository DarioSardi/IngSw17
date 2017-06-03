package it.polimi.ingsw.GC_43.model.effects;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.Resource;

public class MultipleChoiceEffect extends Effect {
		private ArrayList<ChoiceEffect> choices;
		
		public String toString(){
			String toString="Multiple one to one exchange effect ";
			int number =0;
			for(ChoiceEffect choiceEffect:this.choices){
				toString=toString+"choice number "+number+": "+choiceEffect.toString()+"\n";
				number++;
			}
			
			return toString;
		}
		
		public MultipleChoiceEffect(ArrayList<ChoiceEffect> choices){
				this.choices=new ArrayList<ChoiceEffect>();
				this.choices=choices;
			}
		
		
			
		public void executeEffect(FamilyMember familyMember, int choiceNumber){
			this.choices.get(choiceNumber).executeEffect(familyMember);
			
		}



		private boolean checkChoice(int choiceNumber, Player player) {		
			return this.choices.get(choiceNumber).check(player);
			
		}

		
			

		
		
}

