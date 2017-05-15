package model;

import java.awt.List;
import java.util.ArrayList;

public class FaithArea {
	private List victoryPositionPoints;
	private ArrayList<ExcommunicationTile> excommunicationTiles;
	
	
	public FaithArea(){
		this.excommunicationTiles= new ArrayList<ExcommunicationTile>();
	}
	public void fillExcommunicationTiles(ExcommunicationTile e){
		this.excommunicationTiles.add(e);
	}
}
