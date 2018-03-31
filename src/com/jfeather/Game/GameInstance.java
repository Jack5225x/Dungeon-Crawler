package com.jfeather.Game;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import com.jfeather.Main.GameWindow;

public class GameInstance implements KeyListener{

	public JPanel dialog;
	
	public GameInstance(GameWindow frame) {
		dialog = new JPanel();
		dialog.setPreferredSize(new Dimension(640, 330));
		frame.add(dialog);
	}

	@Override
	public void keyPressed(KeyEvent k) {
		// TODO Auto-generated method stub
		System.out.println("Key Pressed: " +k);
	}

	@Override
	public void keyReleased(KeyEvent k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent k) {
		// TODO Auto-generated method stub
		
	}
}
