package com.jfeather.Main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.jfeather.Player.SaveSelect;

public class TitleScreen implements MouseListener {

	public JLabel title, titleGif, startText, startTextHighlighted, exitText, exitTextHighlighted;
	public JPanel dialog;
	public boolean runGif = false;
	
	public TitleScreen(JPanel dialogPanel) {
		dialog = new JPanel();
		dialog = dialogPanel;
		
		// Add the background
		// This will eventually be a looped gif, but for now a png works
		title = new JLabel(gif("Sprites/TitleScreen/NewTitleScreenBackground.png"));
		dialog.add(title);
		title.addMouseListener(this);
		
		// Define the text buttons
		// If these aren't defined, the mouse listener will run into a NullPointerException because it will try and disable them or something, idk
		startText = new JLabel();
		startTextHighlighted = new JLabel();
		
		exitText = new JLabel();
		exitTextHighlighted = new JLabel();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (!runGif) {
			// If the animation hasn't started yet, run that first
			runTitle();
			runGif = true;
		} else {
			int x = (int) (e.getLocationOnScreen().getX() - dialog.getLocationOnScreen().getX());
			int y = (int) (e.getLocationOnScreen().getY() - dialog.getLocationOnScreen().getY());
			//If the button is over the start button
			if ((x > startText.getX() && x < startText.getX() + startText.getWidth()) && (y > startText.getY() && y < startText.getY() + startText.getHeight())) {
				SaveSelect ss = new SaveSelect();
				JOptionPane.showMessageDialog(null, ss.dialog);
			}
			if ((x > exitText.getX() && x < exitText.getX() + exitText.getWidth()) && (y > exitText.getY() && y < exitText.getY() + exitText.getHeight())) {
				// Close the window
				GameWindow gw;
				try {
					gw = new GameWindow();
					gw.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = (int) (e.getLocationOnScreen().getX() - dialog.getLocationOnScreen().getX());
		int y = (int) (e.getLocationOnScreen().getY() - dialog.getLocationOnScreen().getY());
		//If the button is over the start button
		if ((x > startText.getX() && x < startText.getX() + startText.getWidth()) && (y > startText.getY() && y < startText.getY() + startText.getHeight())) {
			startText.setVisible(false);
			startTextHighlighted.setVisible(true);
		}
		if ((x > exitText.getX() && x < exitText.getX() + exitText.getWidth()) && (y > exitText.getY() && y < exitText.getY() + exitText.getHeight())) {
			exitText.setVisible(false);
			exitTextHighlighted.setVisible(true);
		}

	}
	@Override
	public void mouseExited(MouseEvent e) {
		// Set all of the highlighted text to not be visible
		startTextHighlighted.setVisible(false);
		exitTextHighlighted.setVisible(false);
		
		// Enable all of the un-highlighted text labels
		startText.setVisible(true);
		exitText.setVisible(true);
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void runTitle() {
		try {
			// First, remove the mouse listener from the background, so it doesn't conflict later
			title.removeMouseListener(this);
			
			// Add the title buttons
			titleButtons();
			
			// Add the animated picture
			titleGif = new JLabel(gif("Sprites/TitleScreen/NewTitleScreenBackground.gif"));
			dialog.add(titleGif);
			
			// Remove the static picture
			dialog.remove(title);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void titleButtons() {
		// Add the buttons on the title screen using a fancy animation
		// Since the animations don't loop, they can be reused as the non-highlighted buttons
		// This includes adding the mouse listeners to these components, so that the button detection works later

		// Start Button
		startTextHighlighted = new JLabel(gif("Sprites/TitleScreen/TitleScreenStartTextHighlighted.png"));
		dialog.add(startTextHighlighted);
		startTextHighlighted.setBounds(50, 300, 110, 45);
		startTextHighlighted.addMouseListener(this);
		// Set the highlighted button to not be visible
		startTextHighlighted.setVisible(false);
		
		startText = new JLabel(gif("Sprites/TitleScreen/TitleScreenStartTextAnimation.gif"));
		dialog.add(startText);
		startText.setBounds(50, 300, 110, 45);
		startText.addMouseListener(this);
					
		// Exit Button
		exitTextHighlighted = new JLabel(gif("Sprites/TitleScreen/TitleScreenExitTextHighlighted.png"));
		dialog.add(exitTextHighlighted);
		exitTextHighlighted.setBounds(510, 320, 110, 45);
		exitTextHighlighted.addMouseListener(this);
		// Set the highlighted button to not be visible
		exitTextHighlighted.setVisible(false);
		
		exitText = new JLabel(gif("Sprites/TitleScreen/TitleScreenExitTextAnimation.gif"));
		dialog.add(exitText);
		exitText.setBounds(510, 320, 110, 45);
		exitText.addMouseListener(this);

	}
	
	public ImageIcon gif(String path) {
		// This also works for pngs, but it is the best way (i think) to use gifs, pngs can be done other ways but this works for both
		URL url = null;
		try {
			File link = new File(path);
			url = link.toURI().toURL();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return (new ImageIcon(url));

	}
	
}


