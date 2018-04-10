package com.jfeather.Player;

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class PlayerInstance {
	
	private int dx, dy, x = 100, y = 100, w, h, angle, da;
	private Image sprite;
	
	public PlayerInstance() {
		initialize();
	}
	
	public void initialize() {
		ImageIcon icon = new ImageIcon("Sprites/Items/Weapons/Swords/GreenSword.png");
		sprite = icon.getImage();
		
		w = sprite.getWidth(null);
		h = sprite.getHeight(null);
		
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void addX(int increment) {
		x += increment;
	}
	
	public void addY(int increment) {
		y += increment;
	}
	
	public void addA(int increment) {
		angle += increment;
	}
	
	public int getWidth() {
		return w;
	}
	
	public int getHeight() {
		return h;
	}
	
	public int getAngle() {
		return angle;
	}
	
	public Image getImage() {
		return sprite;
	}
	
	public void move() {
		x += dx;
		y += dy;
		angle += da;
	}
	
	public void setSprite(Image newSprite) {
		sprite = newSprite;
	}
	
	public void setSpritePath(String imagePath) {
		ImageIcon icon = new ImageIcon(imagePath);
		sprite = icon.getImage();
	}
	
	// https://help.adobe.com/en_US/AS2LCR/Flash_10.0/help.html?content=00000520.html
	// A list of key codes
	
    public void keyPressed(KeyEvent e, int speed) {
        int key = e.getKeyCode();
        int d = (int) 3.5 + speed / 10;
        if (key == 65)
        	// A
            dx = -d;
        if (key == 68)
        	// D
            dx = d;
        if (key == 87)
        	// W
            dy = -d;
        if (key == 83)
        	// S
            dy = d;
        if (key == 81)
        	// Q
        	da = -3;
        if (key == 69)
        	// E
        	da = 3;
    }

    public void keyReleased(KeyEvent e) {    
        int key = e.getKeyCode();
        
        if (key == 65)
        	// A
            dx = 0;
        if (key == 68)
        	// D
            dx = 0;
        if (key == 87)
        	// W
            dy = 0;
        if (key == 83)
        	// S
            dy = 0;
        if (key == 81)
        	// Q
        	da = 0;
        if (key == 69)
        	// E
        	da = 0;
    }
}
