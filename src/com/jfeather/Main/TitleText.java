package com.jfeather.Main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitleText {

	public JPanel dialog;
	public JLabel[] labels, spaces;
	
	public TitleText(JPanel newDialog, String str, int startX, int startY, int size, String color) {
		dialog = newDialog;
		display(str, startX, startY, size, color);
	}
	
	public String[] separateChars(String str) {
		String[] arr = new String[str.length()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = str.substring(i, i+1);
		}
		return arr;
	}
	
	public ImageIcon[] getLetters(String str, int size, String color) {
		String[] letters = separateChars(str);
		ImageIcon[] images = new ImageIcon[letters.length];
		for (int i = 0; i < letters.length; i++) {
			String path = "Sprites/TitleScreen/Text/" + letters[i].toUpperCase() + "12" + color.toUpperCase() + ".png";
			images[i] = new ImageIcon(path);
		}
		ImageIcon[] scaledImages = new ImageIcon[letters.length];
		for (int i = 0; i < letters.length; i++) {
			scaledImages[i] = scale(images[i], size);
		}
		return scaledImages;
	}
	
	public void display(String str, int startX, int startY, int size, String color) {
		labels = new JLabel[str.length()];
		spaces = new JLabel[str.length()];
		ImageIcon[] images = getLetters(str, size, color);
		for (int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel(images[i]);
			dialog.add(labels[i]);
			labels[i].setBounds(startX, startY, images[i].getIconWidth(), images[i].getIconHeight());
			spaces[i] = new JLabel();
			dialog.add(spaces[i]);
			spaces[i].setBounds(startX + images[i].getIconWidth(), startY, size / 5, size);
			startX += images[i].getIconWidth() + size / 5;
		}
	}
	
	public ImageIcon scale(ImageIcon image, int newHeight) {
		int newWidth = (int)((newHeight / 12) + .5) * image.getIconWidth();
		if (newWidth != 0) {
			Image img = image.getImage();
			Image scaled = img.getScaledInstance((int)(newWidth * 1.2), newHeight, Image.SCALE_SMOOTH);
			return new ImageIcon(scaled);
		}
		return null;
	}
	
	public void setVisible(boolean state) {
		for (int i = 0; i < labels.length; i++)
			labels[i].setVisible(state);
		for (int i = 0; i < spaces.length; i++)
			spaces[i].setVisible(state);
	}
	
	public void addTo(JPanel dialog) {
		for (int i = 0; i < labels.length; i++) {
			dialog.add(labels[i]);
		}
		for (int i = 0; i < spaces.length; i++) {
			dialog.add(spaces[i]);
		}
	}
	
	public void addMouseListener(MouseListener ml) {
		for (int i = 0; i < labels.length; i++) {
			labels[i].addMouseListener(ml);
		}
		for (int i = 0; i < spaces.length; i++) {
			spaces[i].addMouseListener(ml);
		}

	}
}
