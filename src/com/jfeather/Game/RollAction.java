package com.jfeather.Game;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.AbstractAction;

import com.jfeather.Level.LevelInstance;
import com.jfeather.Player.PlayerInstance;
import com.jfeather.Player.Character;

public class RollAction extends AbstractAction {

	private LevelInstance level;
	private PlayerInstance player;
	private Character character;
	private boolean left, right, up, down, moveable, rollReady;
	private int dx, dy;
	private HashMap<Integer, ArrayList<Integer>> spriteLocations;
	private int rollCooldown;
	
	public RollAction(LevelInstance newLevel) {
		level = newLevel;
		player = level.getPlayer();
		character = player.getCharacter();
		
		left = level.getLeft();
		right = level.getRight();
		up = level.getUp();
		down = level.getDown();
		
		moveable = level.getMoveable();
		rollReady = level.getRollReady();
		
		spriteLocations = level.getSpriteLocations();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("Roll");
		if (rollReady && character.getMana() > LevelInstance.ROLL_COST)
			roll();
	}
	
    private void roll() {
    	// Clown fiesta time
    	
    	int d = 100;
    	double root = Math.sqrt(2);
    	// Determine the direction
    	if (left && !right && !down && !up) {
    		//System.out.println("Left");
    		dx = d;
    		dy = 0;
    	} else {
    		if (!left && right && !down && !up) {
    			//System.out.println("Right");
    			dx = -d;
    			dy = 0;
    		} else {
    			if (!left && !right && down && !up) {
    				//System.out.println("Down");
    				dx = 0;
    				dy = -d;
    			} else {
    				if (!left && !right && !down && up) {
    					//System.out.println("Up");
    					dx = 0;
    					dy = d;
    				} else {
    					if (left && !right && down && !up) {
    						//System.out.println("Down left");
    						dx = (int)(d / root);
    						dy = (int)(-d / root);
    					} else {
    						if (left && !right && !down && up) {
    							//System.out.println("Up left");
        						dx = (int)(d / root);
        						dy = (int)(d / root);
    						} else {
    							if (!left && right && down && !up) {
    								//System.out.println("Down right");
    	    						dx = (int)(-d / root);
    	    						dy = (int)(-d / root);
    							} else {
    								if (!left && right && !down && up) {
    									//System.out.println("Up right");
    		    						dx = (int)(-d / root);
    		    						dy = (int)(d / root);
    								} else {
    									// If the player is standing still, roll in the direction they are looking
    									if (player.getDirection() == PlayerInstance.DIR_RIGHT) {
    										dx = -d;
    										dy = 0;
    									}
    									if (player.getDirection() == PlayerInstance.DIR_LEFT) {
    										dx = d;
    										dy = 0;
    									}
    									if (player.getDirection() == PlayerInstance.DIR_UP) {
    										dx = 0;
    										dy = d;
    									}
    									if (player.getDirection() == PlayerInstance.DIR_DOWN) {
    										dx = 0;
    										dy = -d;
    									}

    								}
    							}
    						}
    					}
    				}
    			}
    		}
    	}
    	
    	int finalX = spriteLocations.get(0).get(0) + dx;
    	int finalY = spriteLocations.get(0).get(1) + dy;
    	int ogX = spriteLocations.get(0).get(0);
    	int ogY = spriteLocations.get(0).get(1);
    	
    	//System.out.println(finalX + " " + ogX + " " + dx);
    	//System.out.println(finalY + " " + ogY + " " + dy);
    	
    	// TODO make this not throw you to infinity sometimes when you roll, idk why?
    	
    	new Thread() {
    		public void run() {
    			moveable = false;
    			int k = 0;
    			if (dx < 0)
    				k = -1;
    			else
    				k = 1;
    			while (spriteLocations.get(0).get(0) != finalX && k != 0) {
    				//if (!approaching(finalX, ogX, spriteLocations.get(0).get(0)))
    					//k = -k;
    					//break;
    				level.addIncrementsToMap(k, 0);
    				try {
						Thread.sleep(2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
    			}
    			moveable = true;
    		}
    	}.start();
    	
    	new Thread() {
    		public void run() {
    			moveable = false;
    			int k = 0;
    			if (dy < 0)
    				k = -1;
    			else
    				k = 1;
    			while (spriteLocations.get(0).get(1) != finalY && k != 0) {
    				//if (!approaching(finalY, ogY, spriteLocations.get(0).get(1)))
    					//k = -k;
    					//break;
    				level.addIncrementsToMap(0, k);
    				try {
						Thread.sleep(2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
    			}
    			moveable = true;
    		}
    	}.start();

    	// Activate the i-frames
    	new Thread() {
    		@Override
    		public void run() {
    			character.setInvulnerable(true);
    			try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    			character.setInvulnerable(false);
    		}
    	}.start();
    	
    	
    	// Activate the cooldown
    	if (character.getAgility() < 100)
    		rollCooldown = 1500 - character.getAgility() * 10;
    	else
    		rollCooldown = 500;
    	level.setRollReady(false);
    	rollReady = false;
    	new Thread() {
    		@Override
    		public void run() {
    			try {
					Thread.sleep(rollCooldown);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    	    	level.setRollReady(true);
    	    	rollReady = true;
    		}
    	}.start();
    	dy = 0;
    	dx = 0; 
    	character.subtractMana(LevelInstance.ROLL_COST);
    }


}
