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

import javax.swing.InputMap;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import com.jfeather.Exceptions.UnsupportedThemeException;
import com.jfeather.Generation.LevelGen;
import com.jfeather.Level.LevelInstance;
import com.jfeather.Level.Obstacle;
import com.jfeather.Level.Theme;
import com.jfeather.Player.PlayerInstance;
import com.jfeather.Player.Character;


public class GameInstance extends JPanel implements MouseListener, MouseMotionListener {
	
		
	private static final long serialVersionUID = 1L;
	
	// These are defined so they can referenced in the GameWindow class
	public final MouseListener ML = this;
	public final MouseMotionListener MML = this;
	//public final KeyListener KL = this;
	
	public static final String MOVE_LEFT = "left";
	public static final String MOVE_RIGHT = "right";
	public static final String MOVE_UP = "up";
	public static final String MOVE_DOWN = "down";
	public static final String ROLL = "roll";
	
	public static final String RELEASE_UP = "r_up";
	public static final String RELEASE_DOWN = "r_down";
	public static final String RELEASE_LEFT = "r_left";
	public static final String RELEASE_RIGHT = "r_right";
	
	private int frames = 33;
	private Timer timer;
	private PlayerInstance player;
	private Character character;
	private LevelInstance level;
	private int width, height;
	private volatile int mouseX, mouseY;
	private int floor;
	
	public GameInstance(Character c) {
		character = c;
		initialize();
		
		// Start the overarching game loop that refreshes the screen
		// Listener is defined below to repaint and do other various things
		timer = new Timer(frames, new Listener());
		timer.start();
		//Obstacle rockTest = new Obstacle("Sprites/Level/Rock.png");
		//rockTest.addAt(this, 50, 50);
	}
	
	public void initialize() {
		//addKeyListener(this);
		
		// The only reason you need a separate, named dimension is to define width and height below
		Dimension dim = new Dimension(640, 340);
		setPreferredSize(dim);
		setDoubleBuffered(true);
		width = (int)(dim.getWidth());
		height = (int)(dim.getHeight());
		requestFocus();
		player = new PlayerInstance(character, width / 2, (int)(height * .5));

		try {
			level = LevelGen.genHalls(floor = 1, player, this, new Theme(Theme.OCEAN));
		} catch (UnsupportedThemeException e) {
			e.printStackTrace();
		}
		setupKeyBindings();

		//level.setCoords((width / 2) - level.getWidth() / 2, (int)(height * .5) - level.getHeight() / 2);
		//level.setCoords((player.getX() / 2) - level.getWidth() / 2, (int)(player.getY() * .5) - level.getHeight() / 2);
	}

	public void setupKeyBindings() {
		// Lord have mercy
		
		// The press actions
		getInputMap().put(KeyStroke.getKeyStroke("pressed W"), MOVE_UP);	
		getActionMap().put(MOVE_UP, new Movement(level, PlayerInstance.DIR_UP));
		
		getInputMap().put(KeyStroke.getKeyStroke("pressed S"), MOVE_DOWN);	
		getActionMap().put(MOVE_DOWN, new Movement(level, PlayerInstance.DIR_DOWN));

		getInputMap().put(KeyStroke.getKeyStroke("pressed A"), MOVE_LEFT);	
		getActionMap().put(MOVE_LEFT, new Movement(level, PlayerInstance.DIR_LEFT));

		getInputMap().put(KeyStroke.getKeyStroke("pressed D"), MOVE_RIGHT);	
		getActionMap().put(MOVE_RIGHT, new Movement(level, PlayerInstance.DIR_RIGHT));

		// The release actions
		getInputMap().put(KeyStroke.getKeyStroke("released W"), RELEASE_UP);	
		getActionMap().put(RELEASE_UP, new Movement(level, Movement.DIR_UP_RELEASE));
		
		getInputMap().put(KeyStroke.getKeyStroke("released S"), RELEASE_DOWN);	
		getActionMap().put(RELEASE_DOWN, new Movement(level, Movement.DIR_DOWN_RELEASE));

		getInputMap().put(KeyStroke.getKeyStroke("released A"), RELEASE_LEFT);	
		getActionMap().put(RELEASE_LEFT, new Movement(level, Movement.DIR_LEFT_RELEASE));

		getInputMap().put(KeyStroke.getKeyStroke("released D"), RELEASE_RIGHT);	
		getActionMap().put(RELEASE_RIGHT, new Movement(level, Movement.DIR_RIGHT_RELEASE));
		
		// Roll action
		getInputMap().put(KeyStroke.getKeyStroke("SPACE"), ROLL);
		getActionMap().put(ROLL, new RollAction(level));

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
		level.repaintLevel(g2d);
		g2d.drawImage(level.getSprite(), level.getX(), level.getY(), this);
		//g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);
		player.updateSprite(g, this);
	}
	
	
	private class Listener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// This method is run continuously on the timer made above
			//player.updateSprite();
			level.move(getGraphics());
			repaint();
			try {
				character.getActiveWeapon().updateShoot();
				// Ignore the error
			} catch (NullPointerException ex) {
			}
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
		try {
			character.getActiveWeapon().setPlayerCoords(player);
			character.getActiveWeapon().mousePressed(e, mouseX, mouseY, this);
		} catch (NullPointerException ex) {
			// Don't really care about exception handling here
		}
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
		try {
			character.getActiveWeapon().mouseReleased();
		} catch (NullPointerException ex) {
			// Don't care about exceptions
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// Update info for when the mouse is held down
		mouseX = (int) (e.getLocationOnScreen().getX() - getLocationOnScreen().getX());
		mouseY = (int) (e.getLocationOnScreen().getY() - getLocationOnScreen().getY());
		try {
			character.getActiveWeapon().setMouseX(mouseX);
			character.getActiveWeapon().setMouseY(mouseY);
			character.getActiveWeapon().setPlayerCoords(player);
		} catch (NullPointerException ex) {
			// Don't care
		}
		//System.out.println(mouseX + "  " + mouseY);
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// Won't be used
		
	}
	
}
