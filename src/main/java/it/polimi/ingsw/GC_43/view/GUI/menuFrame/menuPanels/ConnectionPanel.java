package it.polimi.ingsw.GC_43.view.GUI.menuFrame.menuPanels;

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

import it.polimi.ingsw.GC_43.view.Client;
import it.polimi.ingsw.GC_43.view.GUI.menuFrame.Game;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class ConnectionPanel extends JPanel {
	private boolean GUI;
	private String server;
	private int port;
	private String username;
	private Client client;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	public ConnectionPanel(Game game) {
		client = game.getClient();
		setSize(360, 360);
		setLocation((game.getWidth()-this.getWidth())/2, (game.getHeight()-this.getHeight())/2);
		setLayout(null);
		
		JLabel serverLabel = new JLabel("IP address:");
		serverLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		serverLabel.setEnabled(false);
		serverLabel.setBounds(54, 148, 70, 14);
		add(serverLabel);
		
		JLabel connectionLabel = new JLabel("CONNECTION CONFIGURATION");
		connectionLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		connectionLabel.setSize(190, 16);
		connectionLabel.setLocation((this.getWidth()-connectionLabel.getWidth())/2, 14);
		add(connectionLabel);
		
		JTextField serverTextField = new JTextField();
		serverTextField.setEnabled(false);
		serverTextField.setBounds(154, 145, 138, 20);
		add(serverTextField);
		serverTextField.setColumns(10);
		
		JLabel portLabel = new JLabel("Port:");
		portLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		portLabel.setEnabled(false);
		portLabel.setBounds(54, 189, 70, 14);
		add(portLabel);
		
		JTextField portTextField;
		portTextField = new JTextField();
		portTextField.setEnabled(false);
		portTextField.setBounds(154, 186, 138, 20);
		add(portTextField);
		portTextField.setColumns(10);
		
		JRadioButton autoPanelConnectionRadio = new JRadioButton("  Auto");
		autoPanelConnectionRadio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				serverLabel.setEnabled(false);
				portLabel.setEnabled(false);
				serverTextField.setEnabled(false);
				portTextField.setEnabled(false);
			}
		});
		autoPanelConnectionRadio.setSelected(true);
		autoPanelConnectionRadio.setSize(80, 23);
		autoPanelConnectionRadio.setLocation(100, 98);
		add(autoPanelConnectionRadio);
		
		JRadioButton manualPanelConnectionRadio = new JRadioButton("  Manual");
		manualPanelConnectionRadio.setForeground(Color.BLACK);
		manualPanelConnectionRadio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				serverLabel.setEnabled(true);
				portLabel.setEnabled(true);
				serverTextField.setEnabled(true);
				portTextField.setEnabled(true);
			}
		});
		manualPanelConnectionRadio.setSize(80, 23);
		manualPanelConnectionRadio.setLocation(189, 98);
		add(manualPanelConnectionRadio);
		ButtonGroup group1 = new ButtonGroup();
		group1.add(autoPanelConnectionRadio);
		group1.add(manualPanelConnectionRadio);
		
		JLabel lblNewLabel_5 = new JLabel("Username:");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setBounds(54, 231, 70, 14);
		add(lblNewLabel_5);
		
		JTextField usernameField;
		usernameField = new JTextField();
		usernameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
		        if (usernameField.getText().length() >= 12 )
		            e.consume(); 
			}
		});
		usernameField.setBounds(154, 228, 138, 20);
		add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Username not available");
		lblNewLabel_6.setForeground(new Color(204, 0, 0));
		lblNewLabel_6.setVisible(false);
		lblNewLabel_6.setBounds(123, 264, 112, 14);
		add(lblNewLabel_6);
		
		JRadioButton CLIButton = new JRadioButton("  CLI");
		CLIButton.setBounds(189, 64, 70, 23);
		add(CLIButton);
		
		JRadioButton GUIButton = new JRadioButton("  GUI");
		GUIButton.setSelected(true);
		GUIButton.setBounds(100, 64, 70, 23);
		add(GUIButton);
		
		ButtonGroup group2 = new ButtonGroup();
		group2.add(CLIButton);
		group2.add(GUIButton);
		
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				if(autoPanelConnectionRadio.isSelected()){
					server = "127.0.0.1";
					port = 7777;
				}
				else{
					server = serverTextField.getText();
					port = Integer.parseInt(portTextField.getText());
				}
				GUI = GUIButton.isSelected();
				username = usernameField.getText();
								
				setVisible(false);
				game.getMainMenuPanel().setVisible(true);
			}
		});
		btnNewButton.setSize(89, 23);
		btnNewButton.setLocation((this.getWidth()-btnNewButton.getWidth())/2, 300);
		add(btnNewButton);
	}
	public String getServer() {
		return server;
	}
	public int getPort() {
		return port;
	}
	public String getUsername() {
		return username;
	}
}
