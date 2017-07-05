package it.polimi.ingsw.GC_43.view.GUI.menuFrame.boardGamePanels;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.polimi.ingsw.GC_43.view.GUI.menuFrame.GameBoard;

import java.awt.Color;

public class GUIPlayersStatistics extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Create the panel.
	 */
	public GUIPlayersStatistics(GameBoard gameBoard) {
		setSize(gameBoard.getWidth() - gameBoard.getGUIActionSpaces().getWidth(), gameBoard.getHeight()-290);
		setLocation(0, 21);
		setBackground(new Color(234, 199, 131));
		setLayout(null);
		
		ImageIcon playersStatsIcon = new ImageIcon("src/main/java/it/polimi/ingsw/GC_43/view/images/playerStatsIcons/playerStatsIcon.jpeg");
		ImageIcon player1Icon = new ImageIcon("src/main/java/it/polimi/ingsw/GC_43/view/images/playerStatsIcons/player1Icon.jpeg");
		ImageIcon player2Icon = new ImageIcon("src/main/java/it/polimi/ingsw/GC_43/view/images/playerStatsIcons/player2Icon.jpeg");
		ImageIcon player3Icon = new ImageIcon("src/main/java/it/polimi/ingsw/GC_43/view/images/playerStatsIcons/player3Icon.jpeg");
		ImageIcon player4Icon = new ImageIcon("src/main/java/it/polimi/ingsw/GC_43/view/images/playerStatsIcons/player4Icon.jpeg");
		JLabel playersStatsLabel = new JLabel("");
		playersStatsLabel.setBounds(0, 0,this.getWidth(), 47);
		add(playersStatsLabel);
		playersStatsLabel.setIcon(playersStatsIcon);
		
		
		JLabel player1StatsLabel = new JLabel("");
		player1StatsLabel.setBounds(0, 48, this.getWidth(), 102);
		add(player1StatsLabel);
		player1StatsLabel.setIcon(player1Icon);

		JLabel player2StatsLabel = new JLabel("");
		player2StatsLabel.setBounds(0, 151, this.getWidth(), 102);
		add(player2StatsLabel);
		player2StatsLabel.setIcon(player2Icon);

		JLabel player3StatsLabel = new JLabel("");
		player3StatsLabel.setBounds(0, 254, this.getWidth(), 102);
		add(player3StatsLabel);
		player3StatsLabel.setIcon(player3Icon);   

		JLabel player4StatsLabel = new JLabel("");
		player4StatsLabel.setBounds(0, 357, this.getWidth(), 102);
		add(player4StatsLabel);
		player4StatsLabel.setIcon(player4Icon);
	}
}
