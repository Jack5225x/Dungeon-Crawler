package com.jfeather.Level;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Obstacle extends JLabel {

	private Image sprite;
	private int x, y, w, h;

	private static final long serialVersionUID = 1L;
	
	public Obstacle(ImageIcon image) {
		sprite = image.getImage();
		bound();
	}
	
	public Obstacle(String imagePath) {
		sprite = (new ImageIcon(imagePath).getImage());
		bound();
	}
	
	public int getWidth() {
		return sprite.getWidth(null);
	}
	
	public int getHeight() {
		return sprite.getHeight(null);
	}
	
	public void customBound(int newW, int newH) {
		w = newW;
		h = newH;
	}
	
	public void bound() {
		w = getWidth();
		h = getHeight();
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void addAt(JPanel dialog, int locX, int locY) {
		dialog.add(this);
		this.setBounds(locX, locY, w, h);
		x = locX;
		y = locY;
	}
}
