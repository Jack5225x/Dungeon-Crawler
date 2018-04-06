package com.jfeather.Main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitleText {

	public JPanel dialog;
	public JLabel[] labels;
	public int width, height, x, y, size;
	public String color, text;
	private JLabel listener;
	
	public TitleText(JPanel newDialog, String str, int startX, int startY, int newSize, String newColor) {
		dialog = newDialog;
		x = startX;
		y = startY;
		size = newSize;
		color = newColor;
		text = str;
		display(text, x, y, size, color);
	}
	
	public TitleText(JPanel newDialog) {
		dialog = newDialog;
		labels = new JLabel[0];
	}
	
	private String[] separateChars(String str) {
		String[] arr = new String[str.length()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = str.substring(i, i+1);
		}
		return arr;
	}
	
	private ImageIcon[] getLetters(String str, int size, String color) {
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
		ImageIcon[] images = getLetters(str, size, color);
		for (int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel(images[i]);
			dialog.add(labels[i]);
			labels[i].setBounds(startX, startY, images[i].getIconWidth(), images[i].getIconHeight());
			startX += images[i].getIconWidth() + size / 5;
		}
		width = startX - x;
		height = size;
	}
	
	private ImageIcon scale(ImageIcon image, int newHeight) {
		int newWidth = (int)((newHeight / 12) + .5) * image.getIconWidth();
		if (newWidth != 0) {
			Image img = image.getImage();
			Image scaled = img.getScaledInstance((int)(newWidth * 1.2), newHeight, Image.SCALE_SMOOTH);
			return new ImageIcon(scaled);
		}
		return null;
	}
	
	public void setVisible(boolean state) {
		if (labels.length != 0) {
			for (int i = 0; i < labels.length; i++)
				labels[i].setVisible(state);
		} else {
			//System.out.println("Invalid array length! (Not initialized)");
		}
	}
	
	public void addTo(JPanel dialog) {
		for (int i = 0; i < labels.length; i++) {
			dialog.add(labels[i]);
		}
	}
	
	public void addMouseListener(MouseListener ml) {
		listener = new JLabel();
		listener.setBounds(x, y, width, height);
		listener.addMouseListener(ml);
		dialog.add(listener);
	}
	
	public void removeMouseListener(MouseListener ml) {
		listener.removeMouseListener(ml);
		dialog.remove(listener);
	}
	
	public boolean isWithin(MouseEvent e) {
		int locX = (int) (e.getLocationOnScreen().getX() - dialog.getLocationOnScreen().getX());
		int locY = (int) (e.getLocationOnScreen().getY() - dialog.getLocationOnScreen().getY());
		if (locX > x && locX < x + width && locY > y && locY < y + height)
			return true;
		return false;
	}
	
	public void remove() {
		if (labels.length != 0) {
			for (int i = 0; i < labels.length; i++) {
				dialog.remove(labels[i]);
			}
		} else
			System.out.println("Invalid array length! (Not initialized)");
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setSize(int newSize) {
		size = newSize;
		remove();
		display(text, x, y, size, color);
	}
	
	public void setColor(String newColor) {
		color = newColor;
		remove();
		display(text, x, y, size, color);
	}
	
	public String getText() {
		return text;
	}
	
}
