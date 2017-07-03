package it.polimi.ingsw.GC_43.view.GUI.menuFrame;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.initialization.GlobalVariablesInit;
import it.polimi.ingsw.GC_43.model.initialization.InitGame;
import it.polimi.ingsw.GC_43.view.Client;
import it.polimi.ingsw.GC_43.view.GUI.menuFrame.menuPanels.CreateLobby;
import it.polimi.ingsw.GC_43.view.GUI.menuFrame.menuPanels.JoinLobby;
import it.polimi.ingsw.GC_43.view.GUI.menuFrame.menuPanels.MainMenuPanel;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class GameBoard extends JFrame {

	private MainMenuPanel mainMenuPanel;
	private JoinLobby joinLobbyPanel;
	private CreateLobby createLobbyPanel;
	private Board board;
	private Client client;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {	
					GameBoard frame = new GameBoard();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					
					ArrayList<String> nomi = new ArrayList<>();
					nomi.add("Samuel");
					nomi.add("Sasa");
					new GlobalVariablesInit().readGlobalVariables();
					this.board = new Board(nomi);
					new InitGame(this.board);
					this.board.initialize();	
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		/*
	}

	/**
	 * Create the frame.
	 */
	public GameBoard() {
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 750);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(51, 153, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon harvestIcon = new ImageIcon("src/main/java/it/polimi/ingsw/GC_43/view/images/spacesIcons/harvestIcon.jpeg");
		ImageIcon productionIcon = new ImageIcon("src/main/java/it/polimi/ingsw/GC_43/view/images/spacesIcons/productionIcon.jpeg");
		ImageIcon councilPalaceIcon = new ImageIcon("src/main/java/it/polimi/ingsw/GC_43/view/images/spacesIcons/councilPalaceIcon.jpeg");
		ImageIcon[] marketIcons = new ImageIcon[4];
		marketIcons[0] = new ImageIcon("src/main/java/it/polimi/ingsw/GC_43/view/images/spacesIcons/market1.jpeg");
		marketIcons[1] = new ImageIcon("src/main/java/it/polimi/ingsw/GC_43/view/images/spacesIcons/market2.jpeg");
		marketIcons[2] = new ImageIcon("src/main/java/it/polimi/ingsw/GC_43/view/images/spacesIcons/market3.jpeg");
		marketIcons[3] = new ImageIcon("src/main/java/it/polimi/ingsw/GC_43/view/images/spacesIcons/market4.jpeg");
		
		ImageIcon[] developmentCardsIcons = new ImageIcon[16];
		initDevelopmentCards(developmentCardsIcons);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, this.getWidth(), 21);
		contentPane.add(menuBar);
		
		JMenu menuOptions = new JMenu("Options");
		menuBar.add(menuOptions);
		
		JMenu menuMusic = new JMenu("Music");
		menuMusic.setPreferredSize(new Dimension(64, 22));
		menuOptions.add(menuMusic);
		
		JCheckBoxMenuItem chckbxmntmNewCheckItem = new JCheckBoxMenuItem("Volume");
		chckbxmntmNewCheckItem.setPreferredSize(new Dimension(85, 21));
		chckbxmntmNewCheckItem.setHorizontalAlignment(SwingConstants.RIGHT);
		chckbxmntmNewCheckItem.setSelected(true);
		menuMusic.add(chckbxmntmNewCheckItem);
		
		JMenuItem menuExit = new JMenuItem("Exit");
		menuExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menuOptions.add(menuExit);
		menuExit.setPreferredSize(new Dimension(64, 22));
		
		JLabel zoomLabel = new JLabel("");
		zoomLabel.setBounds(492, 22, 205, 305);
		contentPane.add(zoomLabel);
		
		JButton tower1floor4 = new JButton("");
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
		contentPane.add(tower1floor4);
		
		JButton tower1floor3 = new JButton("");
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
		contentPane.add(tower1floor3);
		
		JButton tower1floor2 = new JButton("");
		tower1floor2.setBounds(10, 259, 64, 100);
		contentPane.add(tower1floor2);
		
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
		tower1floor1.setBounds(10, 369, 64, 100);
		contentPane.add(tower1floor1);
		
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
		tower2floor4.setBounds(128, 39, 64, 100);
		contentPane.add(tower2floor4);
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
		tower2floor3.setBounds(128, 149, 64, 100);
		contentPane.add(tower2floor3);
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
		tower2floor2.setBounds(128, 259, 64, 100);
		contentPane.add(tower2floor2);
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
		tower2floor1.setBounds(128, 369, 64, 100);
		contentPane.add(tower2floor1);
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
		tower3floor4.setBounds(246, 39, 64, 100);
		contentPane.add(tower3floor4);
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
		tower3floor3.setBounds(246, 149, 64, 100);
		contentPane.add(tower3floor3);
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
		tower3floor2.setBounds(246, 259, 64, 100);
		contentPane.add(tower3floor2);
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
		tower3floor1.setBounds(246, 369, 64, 100);
		contentPane.add(tower3floor1);
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
		tower4floor4.setBounds(364, 39, 64, 100);
		contentPane.add(tower4floor4);
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
		tower4floor3.setBounds(364, 149, 64, 100);
		contentPane.add(tower4floor3);
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
		tower4floor2.setBounds(364, 259, 64, 100);
		contentPane.add(tower4floor2);
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
		tower4floor1.setBounds(364, 369, 64, 100);
		contentPane.add(tower4floor1);
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
		productionButton.setOpaque(false);
		productionButton.setContentAreaFilled(false);
		productionButton.setBounds(7, 604, 45, 54);
		contentPane.add(productionButton);
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
		harvestButton.setOpaque(false);
		harvestButton.setContentAreaFilled(false);
		harvestButton.setBounds(7, 660, 45, 54);
		contentPane.add(harvestButton);
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
		councilPalaceButton.setOpaque(false);
		councilPalaceButton.setContentAreaFilled(false);
		councilPalaceButton.setBounds(252, 474, 154, 70);
		contentPane.add(councilPalaceButton);
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
		market1Button.setOpaque(false);
		market1Button.setContentAreaFilled(false);
		market1Button.setBounds(295, 590, 45, 54);
		contentPane.add(market1Button);
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
		market2Button.setOpaque(false);
		market2Button.setContentAreaFilled(false);
		market2Button.setBounds(350, 590, 45, 54);
		contentPane.add(market2Button);
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
		market3Button.setOpaque(false);
		market3Button.setContentAreaFilled(false);
		market3Button.setBounds(399, 604, 45, 54);
		contentPane.add(market3Button);
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
		market4Button.setOpaque(false);
		market4Button.setContentAreaFilled(false);
		market4Button.setBounds(438, 646, 45, 54);
		contentPane.add(market4Button);
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
		councilPalaceButton.setOpaque(false);
		councilPalaceButton.setContentAreaFilled(false);
		backgroundImage.setSize(491, 700);
		backgroundImage.setLocation(0, 22);
		
		contentPane.add(backgroundImage);
		backgroundImage.setAlignmentX(Component.CENTER_ALIGNMENT);
		backgroundImage.setIcon(new ImageIcon("src/main/java/it/polimi/ingsw/GC_43/view/images/gameboard.jpeg"));
				
		tower1floor4.setIcon(new ImageIcon(resizeCards(tower1floor4, developmentCardsIcons[0])));
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
	
	public MainMenuPanel getMainMenuPanel(){
		return this.mainMenuPanel;
	}
	public CreateLobby getCreateLobbyPanel() {
		return this.createLobbyPanel;
	}
	public JoinLobby getJoinLobbyPanel() {
		return this.joinLobbyPanel;
	}	
	public Client getClient(){
		return this.client;
	}
	
	public void setClient(Client client){
		this.client = client;
	}
	public Board getBoard(){
		return this.board;
	}
	public void initDevelopmentCards(ImageIcon[] developmentCardsIcons){
		developmentCardsIcons[0] = new ImageIcon(board.getTowers().get(0).getFloors().get(3).getCard().getCardIcon());
		developmentCardsIcons[1] = new ImageIcon(board.getTowers().get(0).getFloors().get(2).getCard().getCardIcon());
		developmentCardsIcons[2] = new ImageIcon(board.getTowers().get(0).getFloors().get(1).getCard().getCardIcon());
		developmentCardsIcons[3] = new ImageIcon(board.getTowers().get(0).getFloors().get(0).getCard().getCardIcon());
		developmentCardsIcons[4] = new ImageIcon(board.getTowers().get(1).getFloors().get(3).getCard().getCardIcon());
		developmentCardsIcons[5] = new ImageIcon(board.getTowers().get(1).getFloors().get(2).getCard().getCardIcon());
		developmentCardsIcons[6] = new ImageIcon(board.getTowers().get(1).getFloors().get(1).getCard().getCardIcon());
		developmentCardsIcons[7] = new ImageIcon(board.getTowers().get(1).getFloors().get(0).getCard().getCardIcon());
		developmentCardsIcons[8] = new ImageIcon(board.getTowers().get(2).getFloors().get(3).getCard().getCardIcon());
		developmentCardsIcons[9] = new ImageIcon(board.getTowers().get(2).getFloors().get(2).getCard().getCardIcon());
		developmentCardsIcons[10] = new ImageIcon(board.getTowers().get(2).getFloors().get(1).getCard().getCardIcon());
		developmentCardsIcons[11] = new ImageIcon(board.getTowers().get(2).getFloors().get(0).getCard().getCardIcon());
		developmentCardsIcons[12] = new ImageIcon(board.getTowers().get(3).getFloors().get(3).getCard().getCardIcon());
		developmentCardsIcons[13] = new ImageIcon(board.getTowers().get(3).getFloors().get(2).getCard().getCardIcon());
		developmentCardsIcons[14] = new ImageIcon(board.getTowers().get(3).getFloors().get(1).getCard().getCardIcon());
		developmentCardsIcons[15] = new ImageIcon(board.getTowers().get(3).getFloors().get(0).getCard().getCardIcon());
	}
}
