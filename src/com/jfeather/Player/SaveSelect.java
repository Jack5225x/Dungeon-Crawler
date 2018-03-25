package com.jfeather.Player;

import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SaveSelect extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPanel dialog;
	public JButton save1, save2, save3;
	public String saveName1, saveName2, saveName3;
	
	public SaveSelect() {
		dialog = new JPanel();
		dialog.setPreferredSize(new Dimension(300, 180));
		loadSaves();
	}
	
	public void loadSaves() {
		try {
			// For reading the save files
			FileReader fr1 = new FileReader("Saves/save1.txt");
			BufferedReader br1 = new BufferedReader(fr1);
			FileReader fr2 = new FileReader("Saves/save2.txt");
			BufferedReader br2 = new BufferedReader(fr2);
			FileReader fr3 = new FileReader("Saves/save3.txt");
			BufferedReader br3 = new BufferedReader(fr3);
			
			String authCode = "", line1 = "", line2 = "", line3 = "";
			
			// Fetch the old authentication code
			try {
				// For authenticating the save files
				FileReader fr = new FileReader("src/com/jfeather/Player/auth.txt");
				BufferedReader auth = new BufferedReader(fr);
				authCode = auth.readLine();
				if (authCode == null)
					authCode = "1";
				auth.close();
			} catch (Exception e) {
				authCode = "1";
			}
			
			// Overwrite the old authentication code
			FileWriter fw = new FileWriter("src/com/jfeather/Player/auth.txt", false);
			BufferedWriter authWriter = new BufferedWriter(fw);
			String newAuth = System.currentTimeMillis() + "";
			authWriter.write(newAuth);
			
			try {
				// Read in the first save spot
				line1 = br1.readLine();
				if (!(line1.equals(authCode))) {
					//If the save file is corrupt
					JOptionPane.showMessageDialog(null, "<html>Invalid or tampered save file (slot 1)<br>Overwriting now!", "Save Error", JOptionPane.ERROR_MESSAGE);
					FileWriter fwo = new FileWriter("Saves/save1.txt", false);
					BufferedWriter overwrite = new BufferedWriter(fwo);
					overwrite.write(newAuth);
				} else {
					saveName1 = br1.readLine();
				}
			} catch (NullPointerException ex) {
				JOptionPane.showMessageDialog(null, "<html>Invalid or tampered save file (slot 1)<br>Overwriting now!", "Save Error", JOptionPane.ERROR_MESSAGE);
				FileWriter fwo = new FileWriter("Saves/save1.txt", false);
				BufferedWriter overwrite = new BufferedWriter(fwo);
				overwrite.write(newAuth);
			}

			try {
				// Read in the second save spot
				line2 = br2.readLine();
				if (!(line2.equals(authCode))) {
					//If the save file is corrupt
					JOptionPane.showMessageDialog(null, "<html>Invalid or tampered save file (slot 2)<br>Overwriting now!", "Save Error", JOptionPane.ERROR_MESSAGE);
					FileWriter fwo = new FileWriter("Saves/save2.txt", false);
					BufferedWriter overwrite = new BufferedWriter(fwo);
					overwrite.write(newAuth);
				} else {
					saveName2 = br2.readLine();
				}
			} catch (NullPointerException ex) {
				JOptionPane.showMessageDialog(null, "<html>Invalid or tampered save file (slot 2)<br>Overwriting now!", "Save Error", JOptionPane.ERROR_MESSAGE);
				FileWriter fwo = new FileWriter("Saves/save2.txt", false);
				BufferedWriter overwrite = new BufferedWriter(fwo);
				overwrite.write(newAuth);
			}
			
			try {
				// Read in the third save spot
				line3 = br3.readLine();
				if (!(line3.equals(authCode))) {
					//If the save file is corrupt
					JOptionPane.showMessageDialog(null, "<html>Invalid or tampered save file (slot 3)<br>Overwriting now!", "Save Error", JOptionPane.ERROR_MESSAGE);
					FileWriter fwo = new FileWriter("Saves/save3.txt", false);
					BufferedWriter overwrite = new BufferedWriter(fwo);
					overwrite.write(newAuth);
				} else {
					saveName1 = br3.readLine();
				}
			} catch (NullPointerException ex) {
				JOptionPane.showMessageDialog(null, "<html>Invalid or tampered save file (slot 3)<br>Overwriting now!", "Save Error", JOptionPane.ERROR_MESSAGE);
				FileWriter fwo = new FileWriter("Saves/save3.txt", false);
				BufferedWriter overwrite = new BufferedWriter(fwo);
				overwrite.write(newAuth);
			}

			br1.close();
			br2.close();
			br3.close();
			authWriter.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
