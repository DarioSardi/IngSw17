package it.polimi.ingsw.GC_43.model;

import java.awt.List;
import java.util.ArrayList;

public class FaithArea {
	private ArrayList<Integer> victoryPositionPoints;
	private ArrayList<ExcommunicationTile> excommunicationTiles;
	

	public FaithArea(){
		this.excommunicationTiles= new ArrayList<ExcommunicationTile>();
		this.victoryPositionPoints= new ArrayList<Integer>();
		generateVictoryPositionPoints();
	}
	public void fillExcommunicationTiles(ExcommunicationTile e){
		this.excommunicationTiles.add(e);
	}
	public ArrayList<Integer> getVictoryPositionPoints() {
		return victoryPositionPoints;
	}
	public void setVictoryPositionPoints(ArrayList<Integer> victoryPositionPoints) {
		this.victoryPositionPoints = victoryPositionPoints;
	}
	public ArrayList<ExcommunicationTile> getExcommunicationTiles() {
		return excommunicationTiles;
	}
	public void setExcommunicationTiles(ArrayList<ExcommunicationTile> excommunicationTiles) {
		this.excommunicationTiles = excommunicationTiles;
	}
	
	private void generateVictoryPositionPoints(){
		int victoryPoints=0;
		int index=0;
		while(index<=5){
			this.victoryPositionPoints.add(victoryPoints);
			victoryPoints++;
			index++;
		}
		victoryPoints++;
		while(index<=12){
			this.victoryPositionPoints.add(victoryPoints);
			index++;
			victoryPoints+=2;
		}
		
		this.victoryPositionPoints.add(22);
		this.victoryPositionPoints.add(25);
		this.victoryPositionPoints.add(30);	
		}
	

	
}
