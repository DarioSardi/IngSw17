package menuFrame.menuPanels;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import menuFrame.Game;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.Choice;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionListener;

import it.polimi.ingsw.GC_43.controller.Lobby;

import javax.swing.event.ListSelectionEvent;
import javax.swing.UIManager;
import java.awt.Cursor;
import java.awt.Component;

public class LobbyGUI{
	private boolean typeOfGame;
	private int maxNumPlayers;
	private int timer;
	private ArrayList<String> players;
	
	LobbyGUI(String admin, boolean typeOfGame, int timer, int maxNumPlayers){
		this.players = new ArrayList<>();
		this.players.add(admin);
		this.typeOfGame = typeOfGame;
		this.timer = timer;
		this.maxNumPlayers = maxNumPlayers;
	}

	public int getMaxNumPlayers() {
		return this.maxNumPlayers;
	}
	
	public boolean getTypeOfGame() {
		return this.typeOfGame;
	}
	
	public int getTimer() {
		return this.timer;
	}

	public ArrayList<String> getPlayers() {
		return this.players;
	}
	
	public String getPlayer(int player) {
		return this.players.get(player);
	}
	
	public void addPlayer(String player){
		this.players.add(player);
	}
	
	public void removePlayer(String player){
		for (int i=0; i < this.players.size(); i++)
			if ((this.players.get(i)).equals(player))
				this.players.remove(i);
	}
}
