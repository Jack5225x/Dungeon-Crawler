package com.jfeather.Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.jfeather.Level.LevelInstance;
import com.jfeather.Player.PlayerInstance;
import com.jfeather.Player.Character;


public class GameInstance extends JPanel implements KeyListener, MouseListener, MouseMotionListener {
	
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int frames = 33;
	private Timer timer;
	private PlayerInstance player;
	public final KeyListener KL = this;
	public final MouseListener ML = this;
	public final MouseMotionListener MML = this;
	private Character character;
	private LevelInstance level;
	private int width, height;
	private volatile int mouseX, mouseY;
	
	public GameInstance(Character c) {
		character = c;
		initialize();

		timer = new Timer(frames, new Listener());
		timer.start();
	}
	
	public void initialize() {
		addKeyListener(this);
		// The only reason you need a separate, named dimension is to define width and height below
		Dimension dim = new Dimension(640, 380);
		setPreferredSize(dim);
		setDoubleBuffered(true);
		setBackground(Color.BLACK);
		width = (int)(dim.getWidth());
		height = (int)(dim.getHeight());

		player = new PlayerInstance(character, width / 2, (int)(height * .5));
		
		level = new LevelInstance(1, player, this);
		level.setCoords((width / 2) - level.getWidth() / 2, (int)(height * .5) - level.getHeight() / 2);
	}

	public void setFPS(int newFPS) {
		// This isn't true fps, because it changes the speed of the game as well, but it functions pretty much the same
		// Maybe this will be referenced in some settings menu (if that gets made)
		timer.stop();
		frames = 1000 / newFPS;
		timer = new Timer(frames, new Listener());
		timer.start();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		// Gets called by the super class by using repaint() in the action event below
		super.paintComponent(g);
		draw(g);
		Toolkit.getDefaultToolkit().sync();
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(level.getSprite(), level.getX(), level.getY(), this);
		g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// Won't be used but needs to stay here
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		player.keyPressed(e, character.getAgility());
		level.keyPressed(e, character.getAgility(), getGraphics());
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		player.keyReleased(e);
		level.keyReleased(e);
	}
	
	private class Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// This method is run continuously on the timer made above
			player.updateSprite();
			level.move(getGraphics());
			repaint();
			character.getActiveWeapon().updateShoot();
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Won't be used
	}	

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// Won't be used
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// Won't be used
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Initialize the weapon stuff and shoot after
		mouseX = (int) (e.getLocationOnScreen().getX() - getLocationOnScreen().getX());
		mouseY = (int) (e.getLocationOnScreen().getY() - getLocationOnScreen().getY());
		character.getActiveWeapon().setPlayerCoords(player);
		character.getActiveWeapon().mousePressed(e, mouseX, mouseY, this);
	}
	
	public int getMouseX() {
		return mouseX;
	}
	
	public int getMouseY() {
		return mouseY;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Stop the shooting
		character.getActiveWeapon().mouseReleased();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// Update info for when the mouse is held down
		mouseX = (int) (e.getLocationOnScreen().getX() - getLocationOnScreen().getX());
		mouseY = (int) (e.getLocationOnScreen().getY() - getLocationOnScreen().getY());
		character.getActiveWeapon().setMouseX(mouseX);
		character.getActiveWeapon().setMouseY(mouseY);
		character.getActiveWeapon().setPlayerCoords(player);
		//System.out.println(mouseX + "  " + mouseY);
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// Won't be used
		
	}

}
