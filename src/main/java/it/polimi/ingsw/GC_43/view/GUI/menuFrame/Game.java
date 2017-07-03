package it.polimi.ingsw.GC_43.view.GUI.menuFrame;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import it.polimi.ingsw.GC_43.view.Client;
import it.polimi.ingsw.GC_43.view.GUI.menuFrame.menuPanels.*;

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
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Game extends JFrame {

	private ConnectionPanel panelConnection;
	private MainMenuPanel mainMenuPanel;
	private JoinLobby joinLobbyPanel;
	private CreateLobby createLobbyPanel;
	private Client client;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {			
					Game frame = new Game();
				} catch (Exception e) {
					e.printStackTrace();
			}
		}
	});
	*/

	/**
	 * Create the frame.
	 */
	public Game(Client client) {
		this.client = client;
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(576, 742);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(51, 153, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
		
		
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
		
		panelConnection = new ConnectionPanel(this);
		contentPane.add(panelConnection);
		
		mainMenuPanel = new MainMenuPanel(this);
		mainMenuPanel.setVisible(false);
		contentPane.add(mainMenuPanel);
		
		joinLobbyPanel = new JoinLobby(this);
		joinLobbyPanel.setVisible(false);
		contentPane.add(joinLobbyPanel);
		
		createLobbyPanel = new CreateLobby(this);
		createLobbyPanel.setVisible(false);
		contentPane.add(createLobbyPanel);
		
		JLabel backgroundImage = new JLabel("");
		backgroundImage.setSize(576, 720);
		backgroundImage.setLocation(0, 22);

		contentPane.add(backgroundImage);
		backgroundImage.setAlignmentX(Component.CENTER_ALIGNMENT);
		backgroundImage.setBackground(new Color(0, 51, 0));
		backgroundImage.setIcon(new ImageIcon("src/main/java/it/polimi/ingsw/GC_43/view/images/background.jpg"));
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
	
	public ConnectionPanel getConnectionPanel(){
		return this.panelConnection;
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
}
