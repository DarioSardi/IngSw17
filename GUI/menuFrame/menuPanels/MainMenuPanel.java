package menuFrame.menuPanels;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import menuFrame.Game;
import java.awt.Color;

public class MainMenuPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	public MainMenuPanel(Game game) {
		setSize(450, 360);

		setOpaque(true);
		setLocation((game.getWidth()-this.getWidth())/2, (game.getHeight()-this.getHeight())/2);
		setLayout(null);
		
		JButton newLobbyButton = new JButton("Create a new lobby");
		newLobbyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				game.getCreateLobbyPanel().setVisible(true);
			}
		});
		newLobbyButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		newLobbyButton.setSize(170, 40);
		newLobbyButton.setLocation((this.getWidth()-newLobbyButton.getWidth())/2, 100);
		add(newLobbyButton);
		
		JButton exitGameButton = new JButton("Exit the game");
		exitGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitGameButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		exitGameButton.setSize(170, 40);
		exitGameButton.setLocation((this.getWidth()-exitGameButton.getWidth())/2, 260);
		add(exitGameButton);
		
		JButton joinLobbyButton = new JButton("Join a lobby");
		joinLobbyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				game.getJoinLobbyPanel().setVisible(true);
			}
		});
		joinLobbyButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		joinLobbyButton.setSize(170, 40);
		joinLobbyButton.setLocation((this.getWidth()-joinLobbyButton.getWidth())/2, 180);
		add(joinLobbyButton);
		
		JLabel mainMenuLabel = new JLabel("MAIN MENU");
		mainMenuLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		mainMenuLabel.setSize(72, 16);
		mainMenuLabel.setLocation((this.getWidth()-mainMenuLabel.getWidth())/2, 14);
		add(mainMenuLabel);
	}
}
