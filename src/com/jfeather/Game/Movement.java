package com.jfeather.Game;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JLabel;

import com.jfeather.Level.LevelInstance;
import com.jfeather.Player.PlayerInstance;

public class Movement extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int DIR_UP_RELEASE = 6;
	public static final int DIR_DOWN_RELEASE = 7;
	public static final int DIR_LEFT_RELEASE = 4;
	public static final int DIR_RIGHT_RELEASE = 5;
	
	private int direction;
	private LevelInstance level;
	private int d;
	private PlayerInstance player;
	
	public Movement(LevelInstance newLevel, int moveDirection) {
		direction = moveDirection;
		level = newLevel;
		player = level.getPlayer();
        //d = (int) 3.5 + level.getPlayer().getCharacter().getAgility() / 10;
		d = 5;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Direction variables are found in PlayerInstance
		if (level.getMoveable()) {
			switch (direction) {
			case 0:
				// Pressed Left
				//System.out.println("Pressed Left");
				level.setdX(d);
				level.setLeft(true);
				player.setDirection(PlayerInstance.DIR_LEFT);
				break;
			case 1:
				// Pressed Right
				//System.out.println("Pressed Right");
				level.setdX(-d);
				level.setRight(true);
				player.setDirection(PlayerInstance.DIR_RIGHT);
				break;
			case 2:
				// Pressed Up
				//System.out.println("Pressed Up");
				level.setdY(d);
				level.setUp(true);
				player.setDirection(PlayerInstance.DIR_UP);
				break;
			case 3:
				// Pressed Down
				//System.out.println("Pressed Down");
				level.setdY(-d);
				level.setDown(true);
				player.setDirection(PlayerInstance.DIR_DOWN);
				break;
			case 4:
				// Released Left
				//System.out.println("Released Left");
				if (!level.getRight())
					level.setdX(0);
				level.setLeft(false);
				break;
			case 5:
				// Released Right
				//System.out.println("Released Right");
				if (!level.getLeft())
					level.setdX(0);
				level.setRight(false);
				break;
			case 6:
				// Released Up
				//System.out.println("Released Up");
				if (!level.getDown())
					level.setdY(0);
				level.setUp(false);
				break;
			case 7:
				// Released Down
				//System.out.println("Released Down");
				//System.out.println(level.getDown());
				if (!level.getUp())
					level.setdY(0);
				level.setDown(false);
				break;
			}
		}
	}
}
