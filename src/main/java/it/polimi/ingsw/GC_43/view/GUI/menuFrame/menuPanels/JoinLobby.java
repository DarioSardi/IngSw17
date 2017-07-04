package it.polimi.ingsw.GC_43.view.GUI.menuFrame.menuPanels;

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

import it.polimi.ingsw.GC_43.view.GUI.menuFrame.Game;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.Choice;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
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

public class JoinLobby extends JPanel {
	private ArrayList<LobbyGUI> listLobby;
	private String[] listValues;
	private DefaultListModel<Object> model;
	private JButton startGameButton;
	private JButton joinLobbyButton;
	private JButton leaveLobbyButton;
	private JList<Object> list;
	private int lobbyIndex;
	private JLabel adminNameLabel;
	private JLabel gameTypeName;
	private JLabel numMaxPlayers;
	private JLabel timeoutValue;
	private JLabel playerInLobbyLabel;
	private JLabel lobbyFullLabel;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	public JoinLobby(Game game) {
		listLobby = new ArrayList<>();
		listValues = new String[]{};
		model = new DefaultListModel<>();
		setSize(450, 360);
		setLocation((game.getWidth()-this.getWidth())/2, (game.getHeight()-this.getHeight())/2);
		setLayout(null);
		
		JLabel joinLobbyLabel = new JLabel("JOIN A LOBBY");
		joinLobbyLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		joinLobbyLabel.setSize(87, 16);
		joinLobbyLabel.setLocation((this.getWidth()-joinLobbyLabel.getWidth())/2, 14);
		add(joinLobbyLabel);
		
		
		joinLobbyButton = new JButton("Join the lobby");
		joinLobbyButton.setEnabled(false);
		joinLobbyButton.setFont(new Font("Tahoma", Font.PLAIN, 15));

		joinLobbyButton.setSize(190, 40);
		joinLobbyButton.setLocation(50, 260);
		add(joinLobbyButton);
		
		leaveLobbyButton = new JButton("Leave the lobby");
		leaveLobbyButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		leaveLobbyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				game.getJoinLobbyPanel().setVisible(true);
			}
		});
		leaveLobbyButton.setSize(190, 40);
		leaveLobbyButton.setLocation(50, 260);
		add(leaveLobbyButton);
		
		JButton returnToMenuButton = new JButton("Return to mainmenu");
		returnToMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lobbyIndex>=0){
		//			listLobby.get(lobbyIndex).removePlayer(game.getConnectionPanel().getUsername());;
					if(listLobby.get(lobbyIndex).getPlayers().size()==0){
						listLobby.remove(lobbyIndex);
						game.getJoinLobbyPanel().getModel().removeElementAt(lobbyIndex);
						lobbyIndex = -1;
					}
				}
				
				joinLobbyButton.setVisible(true);
				leaveLobbyButton.setVisible(false);
				
				if (list.isSelectionEmpty())
				joinLobbyButton.setEnabled(false);
				
				setVisible(false);
				game.getMainMenuPanel().setVisible(true);
			}
		});
		returnToMenuButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		returnToMenuButton.setSize(190, 23);
		returnToMenuButton.setLocation(50, 310);
		add(returnToMenuButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(50, 71, 92, 155);
		add(scrollPane);
		
		list = new JList<>();

		DefaultListCellRenderer renderer =  (DefaultListCellRenderer)list.getCellRenderer();  
		renderer.setHorizontalAlignment(JLabel.CENTER);  
		list.setVisibleRowCount(9);
		list.setFont(new Font("Tahoma", Font.PLAIN, 13));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(model);
		scrollPane.setViewportView(list);
		
		JLabel lobbyNameLabel = new JLabel("Lobby");
		lobbyNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lobbyNameLabel.setBounds(181, 86, 46, 14);
		add(lobbyNameLabel);
		
		JLabel adminLabel = new JLabel("Admin:");
		adminLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		adminLabel.setBounds(181, 111, 56, 14);
		add(adminLabel);
		
		adminNameLabel = new JLabel("");
		adminNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		adminNameLabel.setBounds(236, 111, 46, 14);
		add(adminNameLabel);
		
		JLabel gameType = new JLabel("Game type:");
		gameType.setFont(new Font("Tahoma", Font.PLAIN, 13));
		gameType.setBounds(181, 137, 72, 14);
		add(gameType);
		
		gameTypeName = new JLabel("");
		gameTypeName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		gameTypeName.setBounds(263, 137, 112, 14);
		add(gameTypeName);
		
		JLabel maxNumPlayerLabel = new JLabel("Max number of players:");
		maxNumPlayerLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		maxNumPlayerLabel.setBounds(181, 162, 151, 14);
		add(maxNumPlayerLabel);
		
		numMaxPlayers = new JLabel("");
		numMaxPlayers.setFont(new Font("Tahoma", Font.PLAIN, 13));
		numMaxPlayers.setBounds(329, 162, 29, 14);
		add(numMaxPlayers);
		
		JLabel timeoutLabel = new JLabel("Timer:");
		timeoutLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		timeoutLabel.setBounds(181, 187, 61, 14);
		add(timeoutLabel);
		
		timeoutValue = new JLabel("");
		timeoutValue.setFont(new Font("Tahoma", Font.PLAIN, 13));
		timeoutValue.setBounds(236, 187, 18, 14);
		add(timeoutValue);
		
		JLabel lblNewLabel = new JLabel("min");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(246, 187, 35, 14);
		add(lblNewLabel);
		
		playerInLobbyLabel = new JLabel("");
		playerInLobbyLabel.setBounds(356, 199, 31, 14);
		add(playerInLobbyLabel);
		
		JLabel lobbyDataContainerLabel = new JLabel("");
		lobbyDataContainerLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		lobbyDataContainerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		lobbyDataContainerLabel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), null, null, null));
		lobbyDataContainerLabel.setOpaque(true);
		lobbyDataContainerLabel.setBackground(new Color(255, 255, 255));
		lobbyDataContainerLabel.setBounds(152, 71, 243, 155);
		add(lobbyDataContainerLabel);
		
		leaveLobbyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	//			listLobby.get(lobbyIndex).removePlayer(game.getConnectionPanel().getUsername());;
				if(listLobby.get(lobbyIndex).getPlayers().size()==0){
					listLobby.remove(lobbyIndex);
					game.getJoinLobbyPanel().getModel().removeElementAt(lobbyIndex);
				}
				lobbyIndex = -1;
				joinLobbyButton.setVisible(true);
				leaveLobbyButton.setVisible(false);
				
				if (list.isSelectionEmpty())
				joinLobbyButton.setEnabled(false);
			}
		});
		
		joinLobbyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listLobby.get(list.getSelectedIndex()).getPlayers().size() < listLobby.get(list.getSelectedIndex()).getMaxNumPlayers()){
	//				listLobby.get(list.getSelectedIndex()).addPlayer(game.getConnectionPanel().getUsername());
					lobbyIndex = list.getSelectedIndex();
				}
				else lobbyFullLabel.setVisible(true);
				refreshLobby();
				joinLobbyButton.setVisible(false);
				leaveLobbyButton.setVisible(true);
			}
		});
		
		startGameButton = new JButton("START GAME");
		startGameButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		startGameButton.setEnabled(false);
		startGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		startGameButton.setBounds(263, 260, 132, 73);
		add(startGameButton);
		
		lobbyFullLabel = new JLabel("This lobby is full");
		lobbyFullLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lobbyFullLabel.setVisible(false);
		lobbyFullLabel.setForeground(Color.RED);
		lobbyFullLabel.setBounds(229, 233, 103, 16);
		add(lobbyFullLabel);
		

		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				lobbyFullLabel.setVisible(false);
				if(list.isSelectionEmpty()){
					adminNameLabel.setText("");
					gameTypeName.setText("");	
					numMaxPlayers.setText("");
					timeoutValue.setText("N");
					playerInLobbyLabel.setText("");
					joinLobbyButton.setEnabled(false);
				}
				else{
					refreshLobby();
				}
			}
		});
	}
	
	public ArrayList<LobbyGUI> getListLobby(){
		return this.listLobby;
	}
	
	public String[] getListValues(){
		return this.listValues;
	}
	
	public DefaultListModel<Object> getModel(){
		return this.model;
	}

	public JButton getStartGameButton() {
		return startGameButton;
	}

	public JButton getJoinLobbyButton() {
		return joinLobbyButton;
	}

	public JButton getLeaveLobbyButton() {
		return leaveLobbyButton;
	}

	public JList<Object> getList() {
		return list;
	}
	
	public void setLobbyIndex(int index){
		this.lobbyIndex = index;
	} 
	
	private void refreshLobby(){
		int i = list.getSelectedIndex();
			adminNameLabel.setText(listLobby.get(i).getPlayers().get(0));
		if(listLobby.get(i).getTypeOfGame())
			gameTypeName.setText("Basic rules");				
		else
			gameTypeName.setText("Advanced rules");
		numMaxPlayers.setText(""+listLobby.get(i).getMaxNumPlayers());
		timeoutValue.setText(""+listLobby.get(i).getTimer());
		playerInLobbyLabel.setText(""+listLobby.get(i).getPlayers().size() + " / " + listLobby.get(i).getMaxNumPlayers());
		joinLobbyButton.setEnabled(true);
	}
}
