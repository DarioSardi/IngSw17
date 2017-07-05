package it.polimi.ingsw.GC_43.view.GUI.menuFrame.boardGamePanels;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import it.polimi.ingsw.GC_43.view.GUI.menuFrame.GameBoard;

import java.awt.Color;

public class GUIChat extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Create the panel.
	 */
	public GUIChat(GameBoard gameBoard) {
		setSize(gameBoard.getWidth() - gameBoard.getGUIActionSpaces().getWidth(), 290);
		setLocation(0, gameBoard.getPlayersStatistics().getHeight());
		setBackground(new Color(234, 199, 131));
		setLayout(null);
		
		ImageIcon chatIcon = new ImageIcon("src/main/java/it/polimi/ingsw/GC_43/view/images/chatIcons/chatIcon.jpeg");
		JLabel chatLabel = new JLabel("");
		chatLabel.setBounds(0, 21,gameBoard.getWidth() - gameBoard.getGUIActionSpaces().getWidth(), 47);
		add(chatLabel);
		chatLabel.setIcon(chatIcon);
		
		
	}
}
