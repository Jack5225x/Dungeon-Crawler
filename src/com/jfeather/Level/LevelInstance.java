package com.jfeather.Level;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import com.jfeather.Player.Character;
import com.jfeather.Player.PlayerInstance;

public class LevelInstance {

	private int floor;
	private Character character;
	private PlayerInstance player;
	private Image sprite;
	private int x, y, a, dx, dy, da;
	private boolean left, right, down, up, roll, rollReady;
	private int rollCooldown;
	
	public LevelInstance(int floorNumber, PlayerInstance p) {
		floor = floorNumber;
		player = p;
		character = player.getCharacter();
		sprite = (new ImageIcon("Sprites/Level/TestLevel.png")).getImage();
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int newFloor) {
		floor = newFloor;
	}
	
	public Image getSprite() {
		return sprite;
	}
	
	public void setSprite(Image newSprite) {
		sprite = newSprite;
	}
	
	public int getWidth() {
		return sprite.getWidth(null);
	}
	
	public int getHeight() {
		return sprite.getHeight(null);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int newX) {
		x = newX;
	}
	
	public void setY(int newY) {
		y = newY;
	}
	
	public void setCoords(int newX, int newY) {
		x = newX;
		y = newY;
	}
	
    public void keyPressed(KeyEvent e, int speed) {
        int key = e.getKeyCode();
        int d = (int) 3.5 + speed / 10;
        if (key == 65) {
        	// A
            dx = -d;
            left = true;
        }
        if (key == 68) {
        	// D
            dx = d;
            right = true;
        }
        if (key == 87) {
        	// W
            dy = -d;
            up = true;
        }
        if (key == 83) {
        	// S
            dy = d;
            down = true;
        }
        if (key == 81)
        	// Q
        	da = -3;
        if (key == 69)
        	// E
        	da = 3;
        if (key == 32) {
        	// Space
        	roll = true;
        }
    }

    public void keyReleased(KeyEvent e) {    
        int key = e.getKeyCode();
        
        if (key == 65) {
        	// A
            dx = 0;
            left = false;
        }
        if (key == 68) {
        	// D
            dx = 0;
            right = false;
        }
        if (key == 87) {
        	// W
            dy = 0;
            up = false;
        }
        if (key == 83) {
        	// S
            dy = 0;
            down = false;
        }
        if (key == 81)
        	// Q
        	da = 0;
        if (key == 69)
        	// E
        	da = 0;
        if (key == 32) {
        	// Space
        	roll = false;
        }
    }
	
	public void move() {
		x += dx;
		y += dy;
		a += da;
	}
	
    private void roll() {
    	// Clown fiesta time
    	// Determine the direction
    	if (left && !right && !down && !up) {
    		System.out.println("Left");
    	} else {
    		if (!left && right && !down && !up) {
    			System.out.println("Right");
    		} else {
    			if (!left && !right && down && !up) {
    				System.out.println("Down");
    			} else {
    				if (!left && !right && !down && up) {
    					System.out.println("Up");
    				} else {
    					if (left && !right && down && !up) {
    						System.out.println("Down left");
    					} else {
    						if (left && !right && !down && up) {
    							System.out.println("Up left");
    						} else {
    							if (!left && right && down && !up) {
    								System.out.println("Down right");
    							} else {
    								if (!left && right && !down && up) {
    									System.out.println("Up right");
    								} else {
    									System.out.println("Up");
    								}
    							}
    						}
    					}
    				}
    			}
    		}
    	}
    	
    	// Activate the cooldown
    	if (character.getAgility() < 150)
    		rollCooldown = 2500 - character.getAgility() * 10;
    	else
    		rollCooldown = 750;
    	rollReady = false;
    	new Thread() {
    		@Override
    		public void run() {
    			try {
					Thread.sleep(rollCooldown);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			rollReady = true;
    		}
    	}.start();
    	
    }

}
