package com.jfeather.Game;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.jfeather.Level.LevelInstance;
import com.jfeather.Main.GameWindow;
import com.jfeather.Main.Inventory;
import com.jfeather.Main.TitleText;
import com.jfeather.Player.PlayerInstance;
import com.jfeather.Player.Character;


public class GameInstance extends JPanel implements KeyListener {
	
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int frames = 33;
	private Timer timer;
	private PlayerInstance player;
	public final KeyListener KL = this;
	private Character character;
	private LevelInstance level;
	
	public GameInstance(Character c) {
		character = c;
		initialize();
		
		timer = new Timer(frames, new Listener());
		timer.start();
	}
	
	public void initialize() {
		addKeyListener(this);
		Dimension dim = new Dimension(640, 380);
		setPreferredSize(dim);
		setDoubleBuffered(true);
		player = new PlayerInstance(character, (int)(dim.getWidth() / 2), (int)(dim.getHeight() / 2));
		level = new LevelInstance(1, character);
	}

	public void setFPS(int newFPS) {
		timer.stop();
		frames = 1000 / newFPS;
		timer = new Timer(frames, new Listener());
		timer.start();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
		Toolkit.getDefaultToolkit().sync();
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// Won't be used but needs to stay here
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Find out why this isn't detecting
		player.keyPressed(e, character.getAgility());
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Same as previous
		player.keyReleased(e);
	}
	
	private class Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			player.move();
			repaint();
		}
		
	}

}
