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
import it.polimi.ingsw.GC_43.view.GUI.menuFrame.boardGamePanels.GUIActionSpaces;
import it.polimi.ingsw.GC_43.view.GUI.menuFrame.boardGamePanels.GUIChat;
import it.polimi.ingsw.GC_43.view.GUI.menuFrame.boardGamePanels.GUIPlayersStatistics;
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
	/**
	 * 
	 */
	private static final long serialVersionUID = -586979421093933352L;
	private Board board;
	private Client client;
	private JPanel contentPane;
	private GUIActionSpaces actionSpaces;
	private GUIPlayersStatistics playersStatistics;
	private GUIChat chatGUI;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {						
					ArrayList<String> nomi = new ArrayList<>();
					nomi.add("Samuel");
					nomi.add("Sasa");
					new GlobalVariablesInit().readGlobalVariables();
					Board board = new Board(nomi);
					new InitGame(board);
					board.initialize();	

					GameBoard frame = new GameBoard(board);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GameBoard(Board board) {
		this.board = board;
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1250, 750);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(51, 153, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
		
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
		
		this.actionSpaces = new GUIActionSpaces(this, this.board);
		contentPane.add(actionSpaces);
		
		this.playersStatistics = new GUIPlayersStatistics(this);
		contentPane.add(playersStatistics);
		
		this.chatGUI = new GUIChat(this);
		contentPane.add(chatGUI);
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
	
	public Client getClient(){
		return this.client;
	}
	
	public void setClient(Client client){
		this.client = client;
	}
	
	public Board getBoard(){
		return this.board;
	}
	
	public GUIActionSpaces getGUIActionSpaces(){
		return this.actionSpaces;
	}
	
	public GUIPlayersStatistics getPlayersStatistics(){
		return this.playersStatistics;
	}
}
