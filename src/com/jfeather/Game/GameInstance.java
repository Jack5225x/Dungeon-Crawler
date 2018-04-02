package com.jfeather.Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.ActionMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.jfeather.Main.GameWindow;

public class GameInstance implements KeyListener {
	
	// https://help.adobe.com/en_US/AS2LCR/Flash_10.0/help.html?content=00000520.html
	// A list of key codes
	
	/*
	// Old stuff
	private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
	public static final String MOVE_UP = "up";
	public static final String MOVE_DOWN = "down";
	public static final String MOVE_RIGHT = "right";
	public static final String MOVE_LEFT = "left";
	public static final String ROTATE_RIGHT = "rright";
	public static final String ROTATE_LEFT = "rleft";
	*/
	
	public boolean mUp, mDown, mRight, mLeft, rRight, rLeft;
	public JLabel player;
	public JPanel dialog;
	
	public GameInstance() {
		initialize();
	}
	
	public void initialize() {
		dialog = new JPanel();
		dialog.setPreferredSize(new Dimension(640, 330));
		dialog.setLayout(new BorderLayout());
		dialog.addKeyListener(this);
		
		player = new JLabel("Player");
		dialog.add(player, BorderLayout.CENTER);
		//player.setBounds(320, 165, 50, 25);
		
		/*
		// Old stuff that's kinda funky
		dialog.getInputMap(IFW).put(KeyStroke.getKeyStroke("W"), MOVE_UP);
		dialog.getActionMap().put(MOVE_UP, new Movement(MOVE_UP, player));
		
		dialog.getInputMap(IFW).put(KeyStroke.getKeyStroke("S"), MOVE_DOWN);
		dialog.getActionMap().put(MOVE_DOWN, new Movement(MOVE_DOWN, player));

		dialog.getInputMap(IFW).put(KeyStroke.getKeyStroke("D"), MOVE_RIGHT);
		dialog.getActionMap().put(MOVE_RIGHT, new Movement(MOVE_RIGHT, player));

		dialog.getInputMap(IFW).put(KeyStroke.getKeyStroke("A"), MOVE_LEFT);
		dialog.getActionMap().put(MOVE_LEFT, new Movement(MOVE_LEFT, player));

		dialog.getInputMap(IFW).put(KeyStroke.getKeyStroke("E"), ROTATE_RIGHT);
		dialog.getActionMap().put(ROTATE_RIGHT, new Movement(ROTATE_RIGHT, player));
		
		dialog.getInputMap(IFW).put(KeyStroke.getKeyStroke("Q"), ROTATE_LEFT);
		dialog.getActionMap().put(ROTATE_LEFT, new Movement(ROTATE_LEFT, player));
		 */
		
		new Thread() {
			@Override
			public void run() {
				// Run below the rest of the program
				Thread.currentThread().setPriority(MIN_PRIORITY);
				
				// Constantly looking for new inputs
				while (true) {
					Movement mv = new Movement(player, mUp, mDown, mRight, mLeft, rRight, rLeft);
					if (mUp)
						mv.moveUp();
					if (mDown)
						mv.moveDown();
					if (mRight)
						mv.moveRight();
					if (mLeft)
						mv.moveLeft();
					if (rRight)
						mv.rotateRight();
					if (rLeft)
						mv.rotateLeft();
				}
			}
		}.start();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
		switch (e.getKeyCode()) {
			case 87:
				// W
				mUp = true;
				break;
			case 83:
				// S
				mDown = true;
				break;
			case 65:
				// A
				mLeft = true;
				break;
			case 68:
				// D
				mRight = true;
				break;
			case 81:
				// Q
				rLeft = true;
				break;
			case 69:
				// E
				rRight = true;
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case 87:
				// W
				mUp = false;
				break;
			case 83:
				// S
				mDown = false;
				break;
			case 65:
				// A
				mLeft = false;
				break;
			case 68:
				// D
				mRight = false;
				break;
			case 81:
				// Q
				rLeft = false;
				break;
			case 69:
				// E
				rRight = false;
				break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// This won't be used
	}
	
}
