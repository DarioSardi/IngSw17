package it.polimi.ingsw.GC_43.view.GUI.menuFrame.boardGamePanels;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.cards.*;
import it.polimi.ingsw.GC_43.view.GUI.menuFrame.GameBoard;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUIPlayerBoard extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1574159887828749188L;
	private int player;
	private Board board;
	private ArrayList<VentureCard> ventureCards;
	private ArrayList<CharacterCard> characterCards;
	private ArrayList<BuildingCard> buildingCards;
	private ArrayList<TerritoryCard> territoryCards;
	private ImageIcon[] ventureCardsIcons;
	private ImageIcon[] characterCardsIcons;
	private ImageIcon[] buildingCardsIcons;
	private ImageIcon[] territoryCardsIcons;
	
	private JLabel venture1Label;
	private JLabel venture2Label;
	private JLabel venture3Label;
	private JLabel venture4Label;
	private JLabel venture5Label;
	private JLabel venture6Label;
	private JLabel character1Label;
	private JLabel character2Label;
	private JLabel character3Label;
	private JLabel character4Label;
	private JLabel character5Label;
	private JLabel character6Label;
	private JLabel building1Label;
	private JLabel building2Label;
	private JLabel building3Label;
	private JLabel building4Label;
	private JLabel building5Label;
	private JLabel building6Label;
	private JLabel territory1Label;
	private JLabel territory2Label;
	private JLabel territory3Label;
	private JLabel territory4Label;
	private JLabel territory5Label;
	private JLabel territory6Label;
	
	public GUIPlayerBoard(GameBoard gameBoard) {
		this.board = gameBoard.getBoard();
		setSize(1250, 750);
		setLocation(0, 6);
		setBackground(new Color(234, 199, 131));
		setLayout(null);
		
		ventureCards = new ArrayList<>();
		characterCards = new ArrayList<>();
		buildingCards = new ArrayList<>();
		territoryCards = new ArrayList<>();
		
		ventureCardsIcons = new ImageIcon[6];
		characterCardsIcons = new ImageIcon[6];
		buildingCardsIcons = new ImageIcon[6];
		territoryCardsIcons = new ImageIcon[6];
		
		venture1Label = new JLabel("");
		venture1Label.setBounds(107, 20, 118, 123);
		add(venture1Label);
		venture2Label = new JLabel("");
		venture2Label.setBounds(300, 20, 118, 123);
		add(venture2Label);
		venture3Label = new JLabel("");
		venture3Label.setBounds(492, 20, 118, 123);
		add(venture3Label);
		venture4Label = new JLabel("");
		venture4Label.setBounds(682, 20, 118, 123);
		add(venture4Label);
		venture5Label = new JLabel("");
		venture5Label.setBounds(872, 20, 118, 123);
		add(venture5Label);
		venture6Label = new JLabel("");
		venture6Label.setBounds(1062, 20, 118, 123);
		add(venture6Label);
		
		character1Label = new JLabel("");
		character1Label.setBounds(107, 147, 118, 123);
		add(character1Label);
		character2Label = new JLabel("");
		character2Label.setBounds(300, 147, 118, 123);
		add(character2Label);
		character3Label = new JLabel("");
		character3Label.setBounds(492, 147, 118, 123);
		add(character3Label);
		character4Label = new JLabel("");
		character4Label.setBounds(682, 147, 118, 123);
		add(character4Label);
		character5Label = new JLabel("");
		character5Label.setBounds(872, 1, 118, 123);
		add(character5Label);
		character6Label = new JLabel("");
		character6Label.setBounds(1062, 150, 118, 123);
		add(character6Label);
		
		building1Label = new JLabel("");
		building1Label.setBounds(107, 278, 118, 123);
		add(building1Label);
		building2Label = new JLabel("");
		building2Label.setBounds(300, 278, 118, 123);
		add(building2Label);
		building3Label = new JLabel("");
		building3Label.setBounds(492, 278, 118, 123);
		add(building3Label);
		building4Label = new JLabel("");
		building4Label.setBounds(682, 278, 118, 123);
		add(building4Label);
		building5Label = new JLabel("");
		building5Label.setBounds(872, 278, 118, 123);
		add(building5Label);
		building6Label = new JLabel("");
		building6Label.setBounds(1062, 278, 118, 123);
		add(building6Label);
		
		territory1Label = new JLabel("");
		territory1Label.setBounds(107, 445, 118, 123);
		add(territory1Label);
		territory2Label = new JLabel("");
		territory2Label.setBounds(300, 445, 118, 123);
		add(territory2Label);
		territory3Label = new JLabel("");
		territory3Label.setBounds(492, 445, 118, 123);
		add(territory3Label);
		territory4Label = new JLabel("");
		territory4Label.setBounds(682, 445, 118, 123);
		add(territory4Label);
		territory5Label = new JLabel("");
		territory5Label.setBounds(872, 445, 118, 123);
		add(territory5Label);
		territory6Label = new JLabel("");
		territory6Label.setBounds(1062, 445, 118, 123);
		add(territory6Label);
		
		
		ImageIcon playersBoardIcon = new ImageIcon("src/main/java/it/polimi/ingsw/GC_43/view/images/playerBoard/playerBoardIcon.png");
		
		JButton btnReturnToBoard = new JButton("RETURN TO BOARD");
		btnReturnToBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				gameBoard.getPlayersStatistics().setVisible(true);
				gameBoard.getChat().setVisible(true);
				gameBoard.getGUIActionSpaces().setVisible(true);
				
			}
		});
		btnReturnToBoard.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnReturnToBoard.setBounds(124, 654, 215, 46);
		add(btnReturnToBoard);
		JLabel playerBoardLabel = new JLabel("");
		playerBoardLabel.setBounds(0, 0,1250, 729);
		add(playerBoardLabel);
		playerBoardLabel.setIcon(playersBoardIcon);
		playerBoardLabel.setIcon(new ImageIcon(playersBoardIcon.getImage().getScaledInstance( gameBoard.getWidth(), gameBoard.getHeight()-50,  java.awt.Image.SCALE_SMOOTH )));
	}
	
	public void updateCards(int player){
		this.ventureCards = this.board.getPlayers().get(player).getPlayerCards().getArrayVentureCards();
		this.characterCards = this.board.getPlayers().get(player).getPlayerCards().getArrayCharacterCards();
		this.buildingCards = this.board.getPlayers().get(player).getPlayerCards().getArrayBuildingCards();
		this.territoryCards = this.board.getPlayers().get(player).getPlayerCards().getArrayTerritoryCards();
		
		venture1Label.setIcon(null);
		venture2Label.setIcon(null);
		venture3Label.setIcon(null);
		venture4Label.setIcon(null);
		venture5Label.setIcon(null);
		venture6Label.setIcon(null);
		character1Label.setIcon(null);
		character2Label.setIcon(null);
		character3Label.setIcon(null);
		character4Label.setIcon(null);
		character5Label.setIcon(null);
		character6Label.setIcon(null);
		building1Label.setIcon(null);
		building2Label.setIcon(null);
		building3Label.setIcon(null);
		building4Label.setIcon(null);
		building5Label.setIcon(null);
		building6Label.setIcon(null);
		territory1Label.setIcon(null);
		territory2Label.setIcon(null);
		territory3Label.setIcon(null);
		territory4Label.setIcon(null);
		territory5Label.setIcon(null);
		territory6Label.setIcon(null);
		
		for (int i=0; i < this.ventureCards.size(); i++){
			ventureCardsIcons[i] = new ImageIcon(this.ventureCards.get(i).getCardIcon());
			if(i==0) venture1Label.setIcon(new ImageIcon(resizeCards(venture1Label, ventureCardsIcons[i])));
			if(i==1) venture2Label.setIcon(new ImageIcon(resizeCards(venture2Label, ventureCardsIcons[i])));
			if(i==2) venture3Label.setIcon(new ImageIcon(resizeCards(venture3Label, ventureCardsIcons[i])));
			if(i==3) venture4Label.setIcon(new ImageIcon(resizeCards(venture4Label, ventureCardsIcons[i])));
			if(i==4) venture5Label.setIcon(new ImageIcon(resizeCards(venture5Label, ventureCardsIcons[i])));
			if(i==5) venture6Label.setIcon(new ImageIcon(resizeCards(venture6Label, ventureCardsIcons[i])));
		}
		for (int i=0; i < this.characterCards.size(); i++){
			characterCardsIcons[i] = new ImageIcon(this.characterCards.get(i).getCardIcon());
			if(i==0) character1Label.setIcon(new ImageIcon(resizeCards(character1Label, characterCardsIcons[i])));
			if(i==1) character2Label.setIcon(new ImageIcon(resizeCards(character2Label, characterCardsIcons[i])));
			if(i==2) character3Label.setIcon(new ImageIcon(resizeCards(character3Label, characterCardsIcons[i])));
			if(i==3) character4Label.setIcon(new ImageIcon(resizeCards(character4Label, characterCardsIcons[i])));
			if(i==4) character5Label.setIcon(new ImageIcon(resizeCards(character5Label, characterCardsIcons[i])));
			if(i==5) character6Label.setIcon(new ImageIcon(resizeCards(character6Label, characterCardsIcons[i])));
		}
		for (int i=0; i < this.buildingCards.size(); i++){
			buildingCardsIcons[i] = new ImageIcon(this.buildingCards.get(i).getCardIcon());
			if(i==0) building1Label.setIcon(new ImageIcon(resizeCards(building1Label, buildingCardsIcons[i])));
			if(i==1) building2Label.setIcon(new ImageIcon(resizeCards(building2Label, buildingCardsIcons[i])));
			if(i==2) building3Label.setIcon(new ImageIcon(resizeCards(building3Label, buildingCardsIcons[i])));
			if(i==3) building4Label.setIcon(new ImageIcon(resizeCards(building4Label, buildingCardsIcons[i])));
			if(i==4) building5Label.setIcon(new ImageIcon(resizeCards(building5Label, buildingCardsIcons[i])));
			if(i==5) building6Label.setIcon(new ImageIcon(resizeCards(building6Label, buildingCardsIcons[i])));
		}
		for (int i=0; i < this.territoryCards.size(); i++){
			territoryCardsIcons[i] = new ImageIcon(this.territoryCards.get(i).getCardIcon());
			if(i==0) territory1Label.setIcon(new ImageIcon(resizeCards(territory1Label, territoryCardsIcons[i])));
			if(i==1) territory2Label.setIcon(new ImageIcon(resizeCards(territory2Label, territoryCardsIcons[i])));
			if(i==2) territory3Label.setIcon(new ImageIcon(resizeCards(territory3Label, territoryCardsIcons[i])));
			if(i==3) territory4Label.setIcon(new ImageIcon(resizeCards(territory4Label, territoryCardsIcons[i])));
			if(i==4) territory5Label.setIcon(new ImageIcon(resizeCards(territory5Label, territoryCardsIcons[i])));
			if(i==5) territory6Label.setIcon(new ImageIcon(resizeCards(territory6Label, territoryCardsIcons[i])));
		}
	}
	
	private Image resizeCards(JLabel label, ImageIcon image){
		return image.getImage().getScaledInstance( label.getWidth(), label.getHeight(),  java.awt.Image.SCALE_SMOOTH ) ;
	}
}
