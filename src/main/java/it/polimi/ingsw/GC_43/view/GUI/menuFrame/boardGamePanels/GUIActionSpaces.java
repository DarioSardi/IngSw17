package it.polimi.ingsw.GC_43.view.GUI.menuFrame.boardGamePanels;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.cards.BuildingCard;
import it.polimi.ingsw.GC_43.model.cards.CharacterCard;
import it.polimi.ingsw.GC_43.model.cards.TerritoryCard;
import it.polimi.ingsw.GC_43.model.cards.VentureCard;
import it.polimi.ingsw.GC_43.view.GUI.menuFrame.GameBoard;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUIActionSpaces extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Board board;
	private ArrayList<JButton> buttonGroup = new ArrayList<>();
	public GUIActionSpaces(GameBoard gameBoard, Board board) {
		this.board = board;
		setSize(700, gameBoard.getHeight());
		setLocation(gameBoard.getWidth() - this.getWidth(), 0);
		setLayout(null);
		
		
		ImageIcon harvestIcon = new ImageIcon("src/main/java/it/polimi/ingsw/GC_43/view/images/spacesIcons/harvestIcon.jpeg");
		ImageIcon productionIcon = new ImageIcon("src/main/java/it/polimi/ingsw/GC_43/view/images/spacesIcons/productionIcon.jpeg");
		ImageIcon councilPalaceIcon = new ImageIcon("src/main/java/it/polimi/ingsw/GC_43/view/images/spacesIcons/councilPalaceIcon.jpeg");
		ImageIcon[] marketIcons = new ImageIcon[4];
		marketIcons[0] = new ImageIcon("src/main/java/it/polimi/ingsw/GC_43/view/images/spacesIcons/market1.jpeg");
		marketIcons[1] = new ImageIcon("src/main/java/it/polimi/ingsw/GC_43/view/images/spacesIcons/market2.jpeg");
		marketIcons[2] = new ImageIcon("src/main/java/it/polimi/ingsw/GC_43/view/images/spacesIcons/market3.jpeg");
		marketIcons[3] = new ImageIcon("src/main/java/it/polimi/ingsw/GC_43/view/images/spacesIcons/market4.jpeg");
		
		ImageIcon tower1Bonus1Icon = new ImageIcon("src/main/java/it/polimi/ingsw/GC_43/view/images/spacesIcons/tower1bonus1.jpeg");
		ImageIcon tower1Bonus2Icon = new ImageIcon("src/main/java/it/polimi/ingsw/GC_43/view/images/spacesIcons/tower1bonus2.jpeg");
		ImageIcon tower2Bonus1Icon = new ImageIcon("src/main/java/it/polimi/ingsw/GC_43/view/images/spacesIcons/tower2bonus1.jpeg");
		ImageIcon tower2Bonus2Icon = new ImageIcon("src/main/java/it/polimi/ingsw/GC_43/view/images/spacesIcons/tower2bonus2.jpeg");
		ImageIcon tower3Bonus1Icon = new ImageIcon("src/main/java/it/polimi/ingsw/GC_43/view/images/spacesIcons/tower3bonus1.jpeg");
		ImageIcon tower3Bonus2Icon = new ImageIcon("src/main/java/it/polimi/ingsw/GC_43/view/images/spacesIcons/tower3bonus2.jpeg");
		ImageIcon tower4Bonus1Icon = new ImageIcon("src/main/java/it/polimi/ingsw/GC_43/view/images/spacesIcons/tower4bonus1.jpeg");
		ImageIcon tower4Bonus2Icon = new ImageIcon("src/main/java/it/polimi/ingsw/GC_43/view/images/spacesIcons/tower4bonus2.jpeg");

		ImageIcon[] developmentCardsIcons = new ImageIcon[16];
		initDevelopmentCards(developmentCardsIcons);
		
		JLabel zoomLabel = new JLabel("");
		zoomLabel.setBounds(492, 22, 205, 305);
		add(zoomLabel);
		
		JButton tower1floor4 = new JButton("");
		buttonGroup.add(tower1floor4);
		tower1floor4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tower1floor4.setIcon(null);
				tower1floor4.setVisible(false);
				board.getPlayers().get(0).getPlayerCards().addTerritoryCard((TerritoryCard)board.getTowers().get(0).getFloors().get(3).getCard());
				disableAllButons(buttonGroup);
			}
		});
		tower1floor4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				zoomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				zoomLabel.setIcon(new ImageIcon(zoomCards(zoomLabel, developmentCardsIcons[0])));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				zoomLabel.setIcon(null);
			}
		});
		
		tower1floor4.setBounds(10, 39, 64, 100);
		add(tower1floor4);
		
		tower1floor4.setIcon(new ImageIcon(resizeCards(tower1floor4, developmentCardsIcons[0])));
		
		JButton tower1floor3 = new JButton("");
		buttonGroup.add(tower1floor3);
		tower1floor3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tower1floor3.setIcon(null);
				tower1floor3.setVisible(false);
				board.getPlayers().get(0).getPlayerCards().addTerritoryCard((TerritoryCard)board.getTowers().get(0).getFloors().get(2).getCard());
				disableAllButons(buttonGroup);
			}
		});
		tower1floor3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				zoomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				zoomLabel.setIcon(new ImageIcon(zoomCards(zoomLabel, developmentCardsIcons[1])));
				}
			@Override
			public void mouseExited(MouseEvent e) {
				zoomLabel.setIcon(null);
			}
		});
		tower1floor3.setBounds(10, 149, 64, 100);
		add(tower1floor3);
		
		JButton tower1floor2 = new JButton("");
		buttonGroup.add(tower1floor2);
		tower1floor2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tower1floor2.setIcon(null);
				tower1floor2.setVisible(false);
				board.getPlayers().get(0).getPlayerCards().addTerritoryCard((TerritoryCard)board.getTowers().get(0).getFloors().get(1).getCard());
				disableAllButons(buttonGroup);
			}
		});
		tower1floor2.setBounds(10, 259, 64, 100);
		add(tower1floor2);
		
		tower1floor2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				zoomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				zoomLabel.setIcon(new ImageIcon(zoomCards(zoomLabel, developmentCardsIcons[2])));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				zoomLabel.setIcon(null);
			}
		});
		
		JButton tower1floor1 = new JButton("");
		buttonGroup.add(tower1floor1);
		tower1floor1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tower1floor1.setIcon(null);
				tower1floor1.setVisible(false);
				tower1floor1.setVisible(false);
				board.getPlayers().get(0).getPlayerCards().addTerritoryCard((TerritoryCard)board.getTowers().get(0).getFloors().get(0).getCard());
				disableAllButons(buttonGroup);
			}
		});
		tower1floor1.setBounds(10, 369, 64, 100);
		add(tower1floor1);
		
		tower1floor1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				zoomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				zoomLabel.setIcon(new ImageIcon(zoomCards(zoomLabel, developmentCardsIcons[3])));	
			}
			@Override
			public void mouseExited(MouseEvent e) {
				zoomLabel.setIcon(null);
			}
		});
		JButton tower2floor4 = new JButton("");
		buttonGroup.add(tower2floor4);
		tower2floor4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tower2floor4.setIcon(null);
				tower2floor4.setVisible(false);
				board.getPlayers().get(0).getPlayerCards().addCharacterCard((CharacterCard)board.getTowers().get(1).getFloors().get(3).getCard());
				disableAllButons(buttonGroup);
			}
		});
		tower2floor4.setBounds(128, 39, 64, 100);
		add(tower2floor4);
		tower2floor4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				zoomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				zoomLabel.setIcon(new ImageIcon(zoomCards(zoomLabel, developmentCardsIcons[4])));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				zoomLabel.setIcon(null);
			}
		});
		JButton tower2floor3 = new JButton("");
		buttonGroup.add(tower2floor3);
		tower2floor3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tower2floor3.setIcon(null);
				tower2floor3.setVisible(false);
				board.getPlayers().get(0).getPlayerCards().addCharacterCard((CharacterCard)board.getTowers().get(1).getFloors().get(2).getCard());
				disableAllButons(buttonGroup);
			}
		});
		tower2floor3.setBounds(128, 149, 64, 100);
		add(tower2floor3);
		tower2floor3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				zoomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				zoomLabel.setIcon(new ImageIcon(zoomCards(zoomLabel, developmentCardsIcons[5])));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				zoomLabel.setIcon(null);
			}
		});
		
		JButton tower2floor2 = new JButton("");
		buttonGroup.add(tower2floor2);
		tower2floor2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tower2floor2.setIcon(null);
				tower2floor2.setVisible(false);
				board.getPlayers().get(0).getPlayerCards().addCharacterCard((CharacterCard)board.getTowers().get(1).getFloors().get(1).getCard());
				disableAllButons(buttonGroup);
			}
		});
		tower2floor2.setBounds(128, 259, 64, 100);
		add(tower2floor2);
		tower2floor2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				zoomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				zoomLabel.setIcon(new ImageIcon(zoomCards(zoomLabel, developmentCardsIcons[6])));
				}
			@Override
			public void mouseExited(MouseEvent e) {
				zoomLabel.setIcon(null);
			}
		});
		
		JButton tower2floor1 = new JButton("");
		buttonGroup.add(tower2floor1);
		tower2floor1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tower2floor1.setIcon(null);
				tower2floor1.setVisible(false);
				board.getPlayers().get(0).getPlayerCards().addCharacterCard((CharacterCard)board.getTowers().get(1).getFloors().get(0).getCard());
				disableAllButons(buttonGroup);
			}
		});
		tower2floor1.setBounds(128, 369, 64, 100);
		add(tower2floor1);
		tower2floor1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				zoomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				zoomLabel.setIcon(new ImageIcon(zoomCards(zoomLabel, developmentCardsIcons[7])));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				zoomLabel.setIcon(null);
			}
		});
		
		JButton tower3floor4 = new JButton("");
		buttonGroup.add(tower3floor4);
		tower3floor4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tower3floor4.setIcon(null);
				tower3floor4.setVisible(false);
				board.getPlayers().get(0).getPlayerCards().addBuildingCard((BuildingCard)board.getTowers().get(2).getFloors().get(3).getCard());
				disableAllButons(buttonGroup);
			}
		});
		tower3floor4.setBounds(246, 39, 64, 100);
		add(tower3floor4);
		tower3floor4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				zoomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				zoomLabel.setIcon(new ImageIcon(zoomCards(zoomLabel, developmentCardsIcons[8])));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				zoomLabel.setIcon(null);
			}
		});
		
		JButton tower3floor3 = new JButton("");
		buttonGroup.add(tower3floor3);
		tower3floor3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tower3floor3.setIcon(null);
				tower3floor3.setVisible(false);
				board.getPlayers().get(0).getPlayerCards().addBuildingCard((BuildingCard)board.getTowers().get(2).getFloors().get(2).getCard());
				disableAllButons(buttonGroup);
			}
		});
		tower3floor3.setBounds(246, 149, 64, 100);
		add(tower3floor3);
		tower3floor3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				zoomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				zoomLabel.setIcon(new ImageIcon(zoomCards(zoomLabel, developmentCardsIcons[9])));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				zoomLabel.setIcon(null);
			}
		});
		
		JButton tower3floor2 = new JButton("");
		buttonGroup.add(tower3floor2);
		tower3floor2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tower3floor2.setIcon(null);
				tower3floor2.setVisible(false);
				board.getPlayers().get(0).getPlayerCards().addBuildingCard((BuildingCard)board.getTowers().get(2).getFloors().get(1).getCard());
				disableAllButons(buttonGroup);
			}
		});
		tower3floor2.setBounds(246, 259, 64, 100);
		add(tower3floor2);
		tower3floor2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				zoomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				zoomLabel.setIcon(new ImageIcon(zoomCards(zoomLabel, developmentCardsIcons[10])));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				zoomLabel.setIcon(null);
			}
		});
		
		JButton tower3floor1 = new JButton("");
		buttonGroup.add(tower3floor1);
		tower3floor1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tower3floor1.setIcon(null);
				tower3floor1.setVisible(false);
				board.getPlayers().get(0).getPlayerCards().addBuildingCard((BuildingCard)board.getTowers().get(2).getFloors().get(0).getCard());
				disableAllButons(buttonGroup);
			}
		});
		tower3floor1.setBounds(246, 369, 64, 100);
		add(tower3floor1);
		tower3floor1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				zoomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				zoomLabel.setIcon(new ImageIcon(zoomCards(zoomLabel, developmentCardsIcons[11])));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				zoomLabel.setIcon(null);
			}
		});
		
		JButton tower4floor4 = new JButton("");
		buttonGroup.add(tower4floor4);
		tower4floor4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tower4floor4.setIcon(null);
				tower4floor4.setVisible(false);
				board.getPlayers().get(0).getPlayerCards().addVentureCard((VentureCard)board.getTowers().get(3).getFloors().get(3).getCard());
				disableAllButons(buttonGroup);
			}
		});
		tower4floor4.setBounds(364, 39, 64, 100);
		add(tower4floor4);
		tower4floor4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				zoomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				zoomLabel.setIcon(new ImageIcon(zoomCards(zoomLabel, developmentCardsIcons[12])));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				zoomLabel.setIcon(null);
			}
		});
		
		JButton tower4floor3 = new JButton("");
		buttonGroup.add(tower4floor3);
		tower4floor3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tower4floor3.setIcon(null);
				tower4floor3.setVisible(false);
				board.getPlayers().get(0).getPlayerCards().addVentureCard((VentureCard)board.getTowers().get(3).getFloors().get(2).getCard());
				disableAllButons(buttonGroup);
			}
		});
		tower4floor3.setBounds(364, 149, 64, 100);
		add(tower4floor3);
		tower4floor3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				zoomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				zoomLabel.setIcon(new ImageIcon(zoomCards(zoomLabel, developmentCardsIcons[13])));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				zoomLabel.setIcon(null);
			}
		});
		
		JButton tower4floor2 = new JButton("");
		buttonGroup.add(tower4floor2);
		tower4floor2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tower4floor2.setIcon(null);
				tower4floor2.setVisible(false);
				board.getPlayers().get(0).getPlayerCards().addVentureCard((VentureCard)board.getTowers().get(3).getFloors().get(1).getCard());
				disableAllButons(buttonGroup);
			}
		});
		tower4floor2.setBounds(364, 259, 64, 100);
		add(tower4floor2);
		tower4floor2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				zoomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				zoomLabel.setIcon(new ImageIcon(zoomCards(zoomLabel, developmentCardsIcons[14])));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				zoomLabel.setIcon(null);
			}
		});
		
		JButton tower4floor1 = new JButton("");
		buttonGroup.add(tower4floor1);
		tower4floor1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tower4floor1.setIcon(null);
				tower4floor1.setVisible(false);
				board.getPlayers().get(0).getPlayerCards().addVentureCard((VentureCard)board.getTowers().get(3).getFloors().get(0).getCard());
				disableAllButons(buttonGroup);
			}
		});
		tower4floor1.setBounds(364, 369, 64, 100);
		add(tower4floor1);
		tower4floor1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				zoomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				zoomLabel.setIcon(new ImageIcon(zoomCards(zoomLabel, developmentCardsIcons[15])));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				zoomLabel.setIcon(null);
			}
		});
		
		JButton productionButton = new JButton("");
		buttonGroup.add(productionButton);
		productionButton.setOpaque(false);
		productionButton.setContentAreaFilled(false);
		productionButton.setBounds(7, 604, 45, 54);
		add(productionButton);
		productionButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				zoomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				zoomLabel.setIcon(new ImageIcon(zoomCards(zoomLabel, productionIcon)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				zoomLabel.setIcon(null);
			}
		});
		
		JButton harvestButton = new JButton("");
		buttonGroup.add(harvestButton);
		harvestButton.setOpaque(false);
		harvestButton.setContentAreaFilled(false);
		harvestButton.setBounds(7, 660, 45, 54);
		add(harvestButton);
		harvestButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				zoomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				zoomLabel.setIcon(new ImageIcon(zoomCards(zoomLabel, harvestIcon)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				zoomLabel.setIcon(null);
			}
		});
		
		JButton councilPalaceButton = new JButton("");
		buttonGroup.add(councilPalaceButton);
		councilPalaceButton.setOpaque(false);
		councilPalaceButton.setContentAreaFilled(false);
		councilPalaceButton.setBounds(252, 474, 154, 70);
		add(councilPalaceButton);
		councilPalaceButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				zoomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				zoomLabel.setIcon(new ImageIcon(zoomCards(zoomLabel,councilPalaceIcon)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				zoomLabel.setIcon(null);
			}
		});
		
		JButton market1Button = new JButton("");
		buttonGroup.add(market1Button);
		market1Button.setOpaque(false);
		market1Button.setContentAreaFilled(false);
		market1Button.setBounds(295, 590, 45, 54);
		add(market1Button);
		market1Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				zoomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				zoomLabel.setIcon(new ImageIcon(zoomCards(zoomLabel, marketIcons[0])));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				zoomLabel.setIcon(null);
			}
		});
		
		JButton market2Button = new JButton("");
		buttonGroup.add(market2Button);
		market2Button.setOpaque(false);
		market2Button.setContentAreaFilled(false);
		market2Button.setBounds(350, 590, 45, 54);
		add(market2Button);
		market2Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				zoomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				zoomLabel.setIcon(new ImageIcon(zoomCards(zoomLabel, marketIcons[1])));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				zoomLabel.setIcon(null);
			}
		});
		
		JButton market3Button = new JButton("");
		buttonGroup.add(market3Button);
		market3Button.setOpaque(false);
		market3Button.setContentAreaFilled(false);
		market3Button.setBounds(401, 604, 45, 54);
		add(market3Button);
		market3Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				zoomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				zoomLabel.setIcon(new ImageIcon(zoomCards(zoomLabel, marketIcons[2])));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				zoomLabel.setIcon(null);
			}
		});
		
		JButton market4Button = new JButton("");
		buttonGroup.add(market4Button);
		market4Button.setOpaque(false);
		market4Button.setContentAreaFilled(false);
		market4Button.setBounds(441, 646, 45, 54);
		add(market4Button);
		market4Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				zoomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				zoomLabel.setIcon(new ImageIcon(zoomCards(zoomLabel, marketIcons[3])));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				zoomLabel.setIcon(null);
			}
		});
		
		JLabel backgroundImage = new JLabel("");
		backgroundImage.setSize(491, 700);
		backgroundImage.setLocation(0, 22);
		
		add(backgroundImage);
		backgroundImage.setAlignmentX(Component.CENTER_ALIGNMENT);
		backgroundImage.setIcon(new ImageIcon("src/main/java/it/polimi/ingsw/GC_43/view/images/gameboard.jpeg"));
		
		JLabel tower1Bonus1Label = new JLabel("");
		tower1Bonus1Label.setBounds(76, 63, 47, 54);
		add(tower1Bonus1Label);
		tower1Bonus1Label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				zoomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				zoomLabel.setIcon(new ImageIcon(zoomCards(zoomLabel, tower1Bonus1Icon)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				zoomLabel.setIcon(null);
			}
		});
		
		JLabel tower1Bonus2Label = new JLabel("");
		tower1Bonus2Label.setBounds(76, 173, 47, 54);
		add(tower1Bonus2Label);
		tower1Bonus2Label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				zoomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				zoomLabel.setIcon(new ImageIcon(zoomCards(zoomLabel, tower1Bonus2Icon)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				zoomLabel.setIcon(null);
			}
		});
		
		JLabel tower2Bonus1Label = new JLabel("");
		tower2Bonus1Label.setBounds(194, 63, 47, 54);
		add(tower2Bonus1Label);
		tower2Bonus1Label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				zoomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				zoomLabel.setIcon(new ImageIcon(zoomCards(zoomLabel, tower2Bonus1Icon)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				zoomLabel.setIcon(null);
			}
		});
		
		JLabel tower2Bonus2Label = new JLabel("");
		tower2Bonus2Label.setBounds(194, 173, 47, 54);
		add(tower2Bonus2Label);
		tower2Bonus2Label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				zoomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				zoomLabel.setIcon(new ImageIcon(zoomCards(zoomLabel, tower2Bonus2Icon)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				zoomLabel.setIcon(null);
			}
		});
		
		JLabel tower3Bonus1Label = new JLabel("");
		tower3Bonus1Label.setBounds(312, 63, 47, 54);
		add(tower3Bonus1Label);
		tower3Bonus1Label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				zoomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				zoomLabel.setIcon(new ImageIcon(zoomCards(zoomLabel, tower3Bonus1Icon)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				zoomLabel.setIcon(null);
			}
		});
		
		JLabel tower3Bonus2Label = new JLabel("");
		tower3Bonus2Label.setBounds(312, 173, 47, 54);
		add(tower3Bonus2Label);
		tower3Bonus2Label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				zoomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				zoomLabel.setIcon(new ImageIcon(zoomCards(zoomLabel, tower3Bonus2Icon)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				zoomLabel.setIcon(null);
			}
		});
		
		JLabel tower4Bonus1Label = new JLabel("");
		tower4Bonus1Label.setBounds(430, 63, 47, 54);
		add(tower4Bonus1Label);
		tower4Bonus1Label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				zoomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				zoomLabel.setIcon(new ImageIcon(zoomCards(zoomLabel, tower4Bonus1Icon)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				zoomLabel.setIcon(null);
			}
		});
		
		JLabel tower4Bonus2Label = new JLabel("");
		tower4Bonus2Label.setBounds(430, 173, 47, 54);
		add(tower4Bonus2Label);
		tower4Bonus2Label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				zoomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				zoomLabel.setIcon(new ImageIcon(zoomCards(zoomLabel, tower4Bonus2Icon)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				zoomLabel.setIcon(null);
			}
		});
		tower1floor3.setIcon(new ImageIcon(resizeCards(tower1floor3, developmentCardsIcons[1])));
		tower1floor2.setIcon(new ImageIcon(resizeCards(tower1floor3, developmentCardsIcons[2])));
		tower1floor1.setIcon(new ImageIcon(resizeCards(tower1floor3, developmentCardsIcons[3])));
		tower2floor4.setIcon(new ImageIcon(resizeCards(tower1floor3, developmentCardsIcons[4])));
		tower2floor3.setIcon(new ImageIcon(resizeCards(tower1floor3, developmentCardsIcons[5])));
		tower2floor2.setIcon(new ImageIcon(resizeCards(tower1floor3, developmentCardsIcons[6])));
		tower2floor1.setIcon(new ImageIcon(resizeCards(tower1floor3, developmentCardsIcons[7])));
		tower3floor4.setIcon(new ImageIcon(resizeCards(tower1floor3, developmentCardsIcons[8])));
		tower3floor3.setIcon(new ImageIcon(resizeCards(tower1floor3, developmentCardsIcons[9])));
		tower3floor2.setIcon(new ImageIcon(resizeCards(tower1floor3, developmentCardsIcons[10])));
		tower3floor1.setIcon(new ImageIcon(resizeCards(tower1floor3, developmentCardsIcons[11])));
		tower4floor4.setIcon(new ImageIcon(resizeCards(tower1floor3, developmentCardsIcons[12])));
		tower4floor3.setIcon(new ImageIcon(resizeCards(tower1floor3, developmentCardsIcons[13])));
		tower4floor2.setIcon(new ImageIcon(resizeCards(tower1floor3, developmentCardsIcons[14])));
		tower4floor1.setIcon(new ImageIcon(resizeCards(tower1floor3, developmentCardsIcons[15])));
		

		
		
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	private Image resizeCards(JButton button, ImageIcon image){
		return image.getImage().getScaledInstance( button.getWidth(), button.getHeight(),  java.awt.Image.SCALE_SMOOTH ) ;
	}
	private Image zoomCards(JLabel label, ImageIcon image){
		return image.getImage().getScaledInstance( label.getWidth(), label.getHeight(),  java.awt.Image.SCALE_SMOOTH ) ;
	}
	
	public void initDevelopmentCards(ImageIcon[] developmentCardsIcons){
		developmentCardsIcons[0] = new ImageIcon(this.board.getTowers().get(0).getFloors().get(3).getCard().getCardIcon());
		developmentCardsIcons[1] = new ImageIcon(this.board.getTowers().get(0).getFloors().get(2).getCard().getCardIcon());
		developmentCardsIcons[2] = new ImageIcon(this.board.getTowers().get(0).getFloors().get(1).getCard().getCardIcon());
		developmentCardsIcons[3] = new ImageIcon(this.board.getTowers().get(0).getFloors().get(0).getCard().getCardIcon());
		developmentCardsIcons[4] = new ImageIcon(this.board.getTowers().get(1).getFloors().get(3).getCard().getCardIcon());
		developmentCardsIcons[5] = new ImageIcon(this.board.getTowers().get(1).getFloors().get(2).getCard().getCardIcon());
		developmentCardsIcons[6] = new ImageIcon(this.board.getTowers().get(1).getFloors().get(1).getCard().getCardIcon());
		developmentCardsIcons[7] = new ImageIcon(this.board.getTowers().get(1).getFloors().get(0).getCard().getCardIcon());
		developmentCardsIcons[8] = new ImageIcon(this.board.getTowers().get(2).getFloors().get(3).getCard().getCardIcon());
		developmentCardsIcons[9] = new ImageIcon(this.board.getTowers().get(2).getFloors().get(2).getCard().getCardIcon());
		developmentCardsIcons[10] = new ImageIcon(this.board.getTowers().get(2).getFloors().get(1).getCard().getCardIcon());
		developmentCardsIcons[11] = new ImageIcon(this.board.getTowers().get(2).getFloors().get(0).getCard().getCardIcon());
		developmentCardsIcons[12] = new ImageIcon(this.board.getTowers().get(3).getFloors().get(3).getCard().getCardIcon());
		developmentCardsIcons[13] = new ImageIcon(this.board.getTowers().get(3).getFloors().get(2).getCard().getCardIcon());
		developmentCardsIcons[14] = new ImageIcon(this.board.getTowers().get(3).getFloors().get(1).getCard().getCardIcon());
		developmentCardsIcons[15] = new ImageIcon(this.board.getTowers().get(3).getFloors().get(0).getCard().getCardIcon());
	}
	
	private void disableAllButons(ArrayList<JButton> buttonGroup){
//		for (JButton b : buttonGroup) {
//		    b.setEnabled(false);
//		}
	}
}
