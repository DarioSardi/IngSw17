package it.polimi.ingsw.GC_43.model;

public abstract class Printable {
//	private Board board;
	public String toString(){
		String s = new String();
		return s;
	}
/*
	public void printPlayersResources(){
		for (int i = 0; i < this.board.getPlayers().size(); i++){
			System.out.println(this.board.getPlayers().get(i).toString);
		}
	}
	
	public void printFamilyMembers(){
		for (int i = 0; i < GlobalVariable.maxNumPlayer; i++){
			System.out.println(this.board.getPlayers().get(1).getFamilyMember(i).toString());
		}
	}
*/
}
