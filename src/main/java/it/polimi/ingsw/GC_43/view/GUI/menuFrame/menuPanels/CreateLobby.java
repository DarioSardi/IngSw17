package it.polimi.ingsw.GC_43.view.GUI.menuFrame.menuPanels;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import it.polimi.ingsw.GC_43.controller.Lobby;
import it.polimi.ingsw.GC_43.view.GUI.menuFrame.Game;

public class CreateLobby extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7896831383375367508L;
	private final ButtonGroup typeGameButtonGroup = new ButtonGroup();
	private int lobbyNumber;
	private int maxNumPlayers;
	private int timer;
	/**
	 * Create the panel.
	 */
	public CreateLobby(Game game) {
		setSize(550, 400);
		setLocation((game.getWidth()-this.getWidth())/2, 210);
		setLayout(null);
		
		JLabel createLobbyLabel = new JLabel("CREATE A NEW LOBBY");
		createLobbyLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		createLobbyLabel.setSize(249, 27);
		createLobbyLabel.setLocation((this.getWidth()-createLobbyLabel.getWidth())/2, 26);
		add(createLobbyLabel);
		
		JLabel lblNewLabel = new JLabel("Select lobby number:");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblNewLabel.setBounds(45, 79, 201, 29);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Max number of players:");
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblNewLabel_1.setBounds(45, 149, 225, 29);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Timer:");
		lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblNewLabel_2.setBounds(45, 223, 62, 29);
		add(lblNewLabel_2);
		
		JSpinner lobbyNumberSpinner = new JSpinner();
		lobbyNumberSpinner.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		lobbyNumberSpinner.setBounds(256, 79, 36, 29);
		add(lobbyNumberSpinner);
		
		JSpinner playerSpinner = new JSpinner();
		playerSpinner.setModel(new SpinnerNumberModel(4, 2, 4, 1));
		playerSpinner.setBounds(280, 149, 36, 29);
		add(playerSpinner);
		
		JSpinner timerSpinner = new JSpinner();
		timerSpinner.setModel(new SpinnerNumberModel(3, 1, 4, 1));
		timerSpinner.setBounds(121, 223, 36, 29);
		add(timerSpinner);
		
		JButton createTheLobbyButton = new JButton("Create the lobby");
		createTheLobbyButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		createTheLobbyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lobbyNumber = (Integer)lobbyNumberSpinner.getValue();
				maxNumPlayers = (Integer)playerSpinner.getValue();
				timer = (Integer)timerSpinner.getValue();
				LobbyGUI lobbyGUI = new LobbyGUI(game.getClient(), false, timer, maxNumPlayers);
				
				
//				game.getJoinLobbyPanel().setLobbyIndex(game.getJoinLobbyPanel().getListLobby().size());
//				game.getJoinLobbyPanel().getListLobby().add(lobbyGUI);
//				String s = "Lobby " + game.getJoinLobbyPanel().getListLobby().size();
//				game.getJoinLobbyPanel().getModel().addElement(s);
				game.getJoinLobbyPanel().getJoinLobbyButton().setVisible(false);
				game.getJoinLobbyPanel().getLeaveLobbyButton().setVisible(true);
				game.getJoinLobbyPanel().getList().setSelectedIndex(game.getJoinLobbyPanel().getListLobby().size()-1);
				
				setVisible(false);
				game.getJoinLobbyPanel().setVisible(true);
			}
		});
		createTheLobbyButton.setSize(240, 40);
		createTheLobbyButton.setLocation((this.getWidth()-createTheLobbyButton.getWidth())/2, 300);
		add(createTheLobbyButton);
		
		JButton returnToMenuButton = new JButton("Cancel and return to mainmenu");
		returnToMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				game.getMainMenuPanel().setVisible(true);
			}
		});
		returnToMenuButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		returnToMenuButton.setSize(240, 25);
		returnToMenuButton.setLocation((this.getWidth()-createTheLobbyButton.getWidth())/2, 350);
		add(returnToMenuButton);
	}
}
