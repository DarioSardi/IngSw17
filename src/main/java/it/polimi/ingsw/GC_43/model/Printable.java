package it.polimi.ingsw.GC_43.model;

public abstract class Printable {
	public String toString(){
		String s = new String();
		return s;
	}

/*
	public String printPlayerFamilyMembers(Player player, FamilyMember familyMember){
		String s;
		s = "Player: " + player.getPlayerName() + '\n';
		for(int i = 0; i < player.getFamilyMembers().size(); i++){			
			s = s + "Family Member " + (i+1) + " : " + familyMember.getFamilyMemberPosition() + '\n'; 
		}
		return s;
	}
*/
}
