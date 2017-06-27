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
	private static final long serialVersionUID = 1L;
	private final ButtonGroup typeGameButtonGroup = new ButtonGroup();
	private boolean typeOfGame;
	private int maxNumPlayers;
	private int timer;
	/**
	 * Create the panel.
	 */
	public CreateLobby(Game game) {
		setSize(450, 360);
		setLocation((game.getWidth()-this.getWidth())/2, (game.getHeight()-this.getHeight())/2);
		setLayout(null);
		
		JLabel createLobbyLabel = new JLabel("CREATE A NEW LOBBY");
		createLobbyLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		createLobbyLabel.setSize(139, 16);
		createLobbyLabel.setLocation((this.getWidth()-createLobbyLabel.getWidth())/2, 14);
		add(createLobbyLabel);
		
		JLabel lblNewLabel = new JLabel("Type of game:");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblNewLabel.setBounds(45, 79, 82, 18);
		add(lblNewLabel);
		
		JRadioButton rdbtnBasedRulesButton = new JRadioButton("Basic rules");
		rdbtnBasedRulesButton.setBorder(null);
		rdbtnBasedRulesButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		typeGameButtonGroup.add(rdbtnBasedRulesButton);
		rdbtnBasedRulesButton.setSelected(true);
		rdbtnBasedRulesButton.setBounds(155, 77, 87, 25);
		add(rdbtnBasedRulesButton);
		
		JLabel lblNewLabel_1 = new JLabel("Max number of players:");
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(45, 138, 136, 18);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Timer:");
		lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(45, 197, 36, 18);
		add(lblNewLabel_2);
		
		JRadioButton rdbtnAdvancedRulesButton = new JRadioButton("Advanced rules");
		rdbtnAdvancedRulesButton.setBorder(null);
		rdbtnAdvancedRulesButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		typeGameButtonGroup.add(rdbtnAdvancedRulesButton);
		rdbtnAdvancedRulesButton.setBounds(281, 77, 113, 25);
		add(rdbtnAdvancedRulesButton);
		
		JSpinner playerSpinner = new JSpinner();
		playerSpinner.setModel(new SpinnerNumberModel(4, 2, 5, 1));
		playerSpinner.setBounds(191, 138, 36, 20);
		add(playerSpinner);
		
		JSpinner timerSpinner = new JSpinner();
		timerSpinner.setModel(new SpinnerNumberModel(3, 1, 4, 1));
		timerSpinner.setBounds(91, 197, 36, 20);
		add(timerSpinner);
		
		JButton createTheLobbyButton = new JButton("Create the lobby");
		createTheLobbyButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		createTheLobbyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				typeOfGame = rdbtnBasedRulesButton.isSelected();
				maxNumPlayers = (Integer)playerSpinner.getValue();
				timer = (Integer)timerSpinner.getValue();
				LobbyGUI lobby = new LobbyGUI(game.getConnectionPanel().getUsername(), typeOfGame, timer, maxNumPlayers);
				game.getJoinLobbyPanel().setLobbyIndex(game.getJoinLobbyPanel().getListLobby().size());
				game.getJoinLobbyPanel().getListLobby().add(lobby);
				String s = "Lobby " + game.getJoinLobbyPanel().getListLobby().size();
				game.getJoinLobbyPanel().getModel().addElement(s);
				game.getJoinLobbyPanel().getJoinLobbyButton().setVisible(false);
				game.getJoinLobbyPanel().getLeaveLobbyButton().setVisible(true);
				game.getJoinLobbyPanel().getList().setSelectedIndex(game.getJoinLobbyPanel().getListLobby().size()-1);
				
				setVisible(false);
				game.getJoinLobbyPanel().setVisible(true);
			}
		});
		createTheLobbyButton.setSize(190, 40);
		createTheLobbyButton.setLocation((this.getWidth()-createTheLobbyButton.getWidth())/2, 260);
		add(createTheLobbyButton);
		
		JButton returnToMenuButton = new JButton("Cancel and return to mainmenu");
		returnToMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				game.getMainMenuPanel().setVisible(true);
			}
		});
		returnToMenuButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		returnToMenuButton.setSize(190, 23);
		returnToMenuButton.setLocation((this.getWidth()-returnToMenuButton.getWidth())/2, 310);
		add(returnToMenuButton);
	}
}
