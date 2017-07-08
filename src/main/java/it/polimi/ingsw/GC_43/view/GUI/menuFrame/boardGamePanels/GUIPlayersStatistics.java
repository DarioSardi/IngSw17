package it.polimi.ingsw.GC_43.view.GUI.menuFrame.boardGamePanels;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.view.GUI.menuFrame.GameBoard;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;

public class GUIPlayersStatistics extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1541386250726612835L;

	private GameBoard gameBoard;
	private Board board;
	private JLabel coin1;
	private JLabel coin2;
	private JLabel coin3;
	private JLabel coin4;
	private JLabel servant1;
	private JLabel servant2;
	private JLabel servant3;
	private JLabel servant4;
	private JLabel stone1;
	private JLabel stone2;
	private JLabel stone3;
	private JLabel stone4;
	private JLabel wood1;
	private JLabel wood2;
	private JLabel wood3;
	private JLabel wood4;
	private JLabel victoryPoint1;
	private JLabel victoryPoint2;
	private JLabel victoryPoint3;
	private JLabel victoryPoint4;
	private JLabel faithPoint1;
	private JLabel faithPoint2;
	private JLabel faithPoint3;
	private JLabel faithPoint4;
	private JLabel militaryPoint1;
	private JLabel militaryPoint2;
	private JLabel militaryPoint3;
	private JLabel militaryPoint4;
	
	public GUIPlayersStatistics(GameBoard gameBoard) {
		this.gameBoard = gameBoard;
		this.board = gameBoard.getBoard();
		setSize(gameBoard.getWidth()-gameBoard.getGUIActionSpaces().getWidth(), gameBoard.getHeight()-290);
		System.out.println(getSize());
		setLocation(0, 21);
		setBackground(new Color(234, 199, 131));
		setLayout(null);
		
		ImageIcon playersStatsIcon = new ImageIcon("src/main/java/it/polimi/ingsw/GC_43/view/images/playerStatsIcons/playerStatsIcon.jpeg");
		ImageIcon player1Icon = new ImageIcon("src/main/java/it/polimi/ingsw/GC_43/view/images/playerStatsIcons/player1Icon.jpeg");
		ImageIcon player2Icon = new ImageIcon("src/main/java/it/polimi/ingsw/GC_43/view/images/playerStatsIcons/player2Icon.jpeg");
		ImageIcon player3Icon = new ImageIcon("src/main/java/it/polimi/ingsw/GC_43/view/images/playerStatsIcons/player3Icon.jpeg");
		ImageIcon player4Icon = new ImageIcon("src/main/java/it/polimi/ingsw/GC_43/view/images/playerStatsIcons/player4Icon.jpeg");
		
		JLabel playersStatsLabel = new JLabel("");
		playersStatsLabel.setBounds(0, 0,550, 47);
		add(playersStatsLabel);
		playersStatsLabel.setIcon(playersStatsIcon);
		
		
		this.coin1 = new JLabel("");
		coin1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		coin1.setHorizontalAlignment(SwingConstants.CENTER);
		this.coin1.setBounds(142, 112, 35, 28);
		add(coin1);
		
		this.servant1 = new JLabel("");
		servant1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		servant1.setHorizontalAlignment(SwingConstants.CENTER);
		servant1.setBounds(199, 112, 35, 28);
		add(servant1);
		
		this.stone1 = new JLabel("");
		stone1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		stone1.setHorizontalAlignment(SwingConstants.CENTER);
		stone1.setBounds(255, 112, 35, 28);
		add(stone1);
		
		this.wood1 = new JLabel("");
		wood1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		wood1.setHorizontalAlignment(SwingConstants.CENTER);
		wood1.setBounds(313, 112, 35, 28);
		add(wood1);
		
		this.victoryPoint1 = new JLabel("");
		victoryPoint1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		victoryPoint1.setHorizontalAlignment(SwingConstants.CENTER);
		victoryPoint1.setBounds(370, 112, 35, 28);
		add(victoryPoint1);
		
		this.faithPoint1 = new JLabel("");
		faithPoint1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		faithPoint1.setHorizontalAlignment(SwingConstants.CENTER);
		faithPoint1.setBounds(426, 112, 35, 28);
		add(faithPoint1);
		
		this.militaryPoint1 = new JLabel("");
		militaryPoint1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		militaryPoint1.setHorizontalAlignment(SwingConstants.CENTER);
		militaryPoint1.setBounds(483, 112, 35, 28);
		add(militaryPoint1);
		
		this.coin2 = new JLabel("");
		coin2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		coin2.setHorizontalAlignment(SwingConstants.CENTER);
		coin2.setBounds(142, 215, 35, 28);
		add(coin2);
		
		this.servant2 = new JLabel("");
		servant2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		servant2.setHorizontalAlignment(SwingConstants.CENTER);
		servant2.setBounds(199, 215, 35, 28);
		add(servant2);
		
		this.stone2 = new JLabel("");
		stone2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		stone2.setHorizontalAlignment(SwingConstants.CENTER);
		stone2.setBounds(255, 215, 35, 28);
		add(stone2);
		
		this.wood2 = new JLabel("");
		wood2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		wood2.setHorizontalAlignment(SwingConstants.CENTER);
		wood2.setBounds(313, 215, 35, 28);
		add(wood2);
		
		this.victoryPoint2 = new JLabel("");
		victoryPoint2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		victoryPoint2.setHorizontalAlignment(SwingConstants.CENTER);
		victoryPoint2.setBounds(370, 215, 35, 28);
		add(victoryPoint2);
		
		this.faithPoint2 = new JLabel("");
		faithPoint2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		faithPoint2.setHorizontalAlignment(SwingConstants.CENTER);
		faithPoint2.setBounds(426, 215, 35, 28);
		add(faithPoint2);
		
		this.militaryPoint2 = new JLabel("");
		militaryPoint2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		militaryPoint2.setHorizontalAlignment(SwingConstants.CENTER);
		militaryPoint2.setBounds(483, 215, 35, 28);
		add(militaryPoint2);
		
		this.coin3 = new JLabel("");
		coin3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		coin3.setHorizontalAlignment(SwingConstants.CENTER);
		coin3.setBounds(142, 318, 35, 28);
		add(coin3);
		
		this.servant3 = new JLabel("");
		servant3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		servant3.setHorizontalAlignment(SwingConstants.CENTER);
		servant3.setBounds(199, 318, 35, 28);
		add(servant3);
		
		this.stone3 = new JLabel("");
		stone3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		stone3.setHorizontalAlignment(SwingConstants.CENTER);
		stone3.setBounds(255, 318, 35, 28);
		add(stone3);
		
		this.wood3 = new JLabel("");
		wood3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		wood3.setHorizontalAlignment(SwingConstants.CENTER);
		wood3.setBounds(313, 318, 35, 28);
		add(wood3);
		
		this.victoryPoint3 = new JLabel("");
		victoryPoint3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		victoryPoint3.setHorizontalAlignment(SwingConstants.CENTER);
		victoryPoint3.setBounds(370, 318, 35, 28);
		add(victoryPoint3);
		
		this.faithPoint3 = new JLabel("");
		faithPoint3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		faithPoint3.setHorizontalAlignment(SwingConstants.CENTER);
		faithPoint3.setBounds(426, 318, 35, 28);
		add(faithPoint3);
		
		this.militaryPoint3 = new JLabel("");
		militaryPoint3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		militaryPoint3.setHorizontalAlignment(SwingConstants.CENTER);
		militaryPoint3.setBounds(483, 318, 35, 28);
		add(militaryPoint3);
		
		this.coin4 = new JLabel("");
		coin4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		coin4.setHorizontalAlignment(SwingConstants.CENTER);
		coin4.setBounds(142, 421, 35, 28);
		add(coin4);
		
		this.servant4 = new JLabel("");
		servant4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		servant4.setHorizontalAlignment(SwingConstants.CENTER);
		servant4.setBounds(199, 421, 35, 28);
		add(servant4);
		
		this.stone4 = new JLabel("");
		stone4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		stone4.setHorizontalAlignment(SwingConstants.CENTER);
		stone4.setBounds(255, 421, 35, 28);
		add(stone4);
		
		this.wood4 = new JLabel("");
		wood4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		wood4.setHorizontalAlignment(SwingConstants.CENTER);
		wood4.setBounds(313, 421, 35, 28);
		add(wood4);
		
		this.victoryPoint4 = new JLabel("");
		victoryPoint4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		victoryPoint4.setHorizontalAlignment(SwingConstants.CENTER);
		victoryPoint4.setBounds(370, 421, 35, 28);
		add(victoryPoint4);
		
		this.faithPoint4 = new JLabel("");
		faithPoint4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		faithPoint4.setHorizontalAlignment(SwingConstants.CENTER);
		faithPoint4.setBounds(426, 421, 35, 28);
		add(faithPoint4);
		
		this.militaryPoint4 = new JLabel("");
		militaryPoint4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		militaryPoint4.setHorizontalAlignment(SwingConstants.CENTER);
		militaryPoint4.setBounds(483, 421, 35, 28);
		add(militaryPoint4);
		
		
		JButton player1Button = new JButton("");
		player1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameBoard.getPlayerBoard().updateCards(0);
				gameBoard.getPlayerBoard().setVisible(true);
				setVisible(false);
				gameBoard.getGUIActionSpaces().setVisible(false);
				gameBoard.getChat().setVisible(false);
			}
		});
		player1Button.setOpaque(false);
		player1Button.setContentAreaFilled(false);
		player1Button.setBounds(0, 48, 550, 102);
		add(player1Button);		
		
		JLabel player1StatsLabel = new JLabel("");
		player1StatsLabel.setBounds(0, 48, 550, 102);
		add(player1StatsLabel);
		player1StatsLabel.setIcon(player1Icon);
		
		JButton player2Button = new JButton("");
		player2Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameBoard.getPlayerBoard().updateCards(1);
				gameBoard.getPlayerBoard().setVisible(true);
				setVisible(false);
				gameBoard.getGUIActionSpaces().setVisible(false);
				gameBoard.getChat().setVisible(false);
			}
		});
		player2Button.setOpaque(false);
		player2Button.setContentAreaFilled(false);
		player2Button.setBounds(0, 151, this.getWidth(), 102);
		add(player2Button);

		JLabel player2StatsLabel = new JLabel("");
		player2StatsLabel.setBounds(0, 151, 550, 102);
		add(player2StatsLabel);
		player2StatsLabel.setIcon(player2Icon);
		
		if (this.board.getPlayers().size() > 2){
			JButton player3Button = new JButton("");
			player3Button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gameBoard.getPlayerBoard().updateCards(2);
					gameBoard.getPlayerBoard().setVisible(true);
					setVisible(false);
					gameBoard.getGUIActionSpaces().setVisible(false);
					gameBoard.getChat().setVisible(false);
				}
			});
			player3Button.setOpaque(false);
			player3Button.setContentAreaFilled(false);
			player3Button.setBounds(0, 254, this.getWidth(), 102);
			add(player3Button);
			
			JLabel player3StatsLabel = new JLabel("");
			player3StatsLabel.setBounds(0, 254, 550, 102);
			add(player3StatsLabel);
			player3StatsLabel.setIcon(player3Icon);
		}
		
		if (this.board.getPlayers().size() > 3){
			JButton player4Button = new JButton("");
			player4Button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gameBoard.getPlayerBoard().updateCards(3);
					gameBoard.getPlayerBoard().setVisible(true);
					setVisible(false);
					gameBoard.getGUIActionSpaces().setVisible(false);
					gameBoard.getChat().setVisible(false);
				}
			});
			player4Button.setOpaque(false);
			player4Button.setContentAreaFilled(false);
			player4Button.setBounds(0, 357, this.getWidth(), 102);
			add(player4Button);
	
			JLabel player4StatsLabel = new JLabel("");
			player4StatsLabel.setBounds(0, 357, 550, 102);
			add(player4StatsLabel);
			player4StatsLabel.setIcon(player4Icon);
		}
		setPlayerResources();
	}
	
	
	public void setPlayerResources(){
		coin1.setText(""+board.getPlayers().get(0).getPlayerResource("coin"));
		servant1.setText(""+board.getPlayers().get(0).getPlayerResource("servant"));
		stone1.setText(""+board.getPlayers().get(0).getPlayerResource("stone"));
		wood1.setText(""+board.getPlayers().get(0).getPlayerResource("wood"));
		victoryPoint1.setText(""+board.getPlayers().get(0).getPlayerResource("victoryPoint"));
		faithPoint1.setText(""+board.getPlayers().get(0).getPlayerResource("faithPoint"));
		militaryPoint1.setText(""+board.getPlayers().get(0).getPlayerResource("militaryPoint"));

		coin2.setText(""+board.getPlayers().get(1).getPlayerResource("coin"));
		servant2.setText(""+board.getPlayers().get(1).getPlayerResource("servant"));
		stone2.setText(""+board.getPlayers().get(1).getPlayerResource("stone"));
		wood2.setText(""+board.getPlayers().get(1).getPlayerResource("wood"));
		victoryPoint2.setText(""+board.getPlayers().get(1).getPlayerResource("victoryPoint"));
		faithPoint2.setText(""+board.getPlayers().get(1).getPlayerResource("faithPoint"));
		militaryPoint2.setText(""+board.getPlayers().get(1).getPlayerResource("militaryPoint"));
		
		if (this.board.getPlayers().size() > 2){
			coin3.setText(""+board.getPlayers().get(2).getPlayerResource("coin"));
			servant3.setText(""+board.getPlayers().get(2).getPlayerResource("servant"));
			stone3.setText(""+board.getPlayers().get(2).getPlayerResource("stone"));
			wood3.setText(""+board.getPlayers().get(2).getPlayerResource("wood"));
			victoryPoint3.setText(""+board.getPlayers().get(2).getPlayerResource("victoryPoint"));
			faithPoint3.setText(""+board.getPlayers().get(2).getPlayerResource("faithPoint"));
			militaryPoint3.setText(""+board.getPlayers().get(2).getPlayerResource("militaryPoint"));
		}
		if (this.board.getPlayers().size() > 3){
			coin4.setText(""+board.getPlayers().get(3).getPlayerResource("coin"));
			servant4.setText(""+board.getPlayers().get(3).getPlayerResource("servant"));
			stone4.setText(""+board.getPlayers().get(3).getPlayerResource("stone"));
			wood4.setText(""+board.getPlayers().get(3).getPlayerResource("wood"));
			victoryPoint4.setText(""+board.getPlayers().get(3).getPlayerResource("victoryPoint"));
			faithPoint4.setText(""+board.getPlayers().get(3).getPlayerResource("faithPoint"));
			militaryPoint4.setText(""+board.getPlayers().get(3).getPlayerResource("militaryPoint"));
		}
	}
}
