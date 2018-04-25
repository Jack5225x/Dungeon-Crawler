package com.jfeather.Level;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import com.jfeather.Game.GameInstance;
import com.jfeather.Items.Weapon;
import com.jfeather.Player.Character;
import com.jfeather.Player.PlayerInstance;

public class LevelInstance {

	private int floor;
	private Character character;
	private PlayerInstance player;
	private GameInstance instance;
	private Image sprite;
	private int x, y, a, dx, dy, da;
	private boolean left, right, down, up, roll, rollReady = true;
	private int rollCooldown;
	private boolean isOffset = false;
	private boolean moveable = true;
	private Weapon weapon;
	
	public LevelInstance(int floorNumber, PlayerInstance p, GameInstance i) {
		floor = floorNumber;
		player = p;
		instance = i;
		character = player.getCharacter();
		weapon = character.getActiveWeapon();
		a = 0;
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
	
    public void keyPressed(KeyEvent e, int speed, Graphics g) {
        int key = e.getKeyCode();
        if (moveable) {
	        int d = (int) (8 / (1 + Math.pow(2.717, speed / -10)));
	        if (key == 65) {
	        	// A
	            dx = d;
	            left = true;
	        }
	        if (key == 68) {
	        	// D
	            dx = -d;
	            right = true;
	        }
	        if (key == 87) {
	        	// W
	            dy = d;
	            up = true;
	        }
	        if (key == 83) {
	        	// S
	            dy = -d;
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
	        if (key == 88) {
	        	toggleOffset();
	        }
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
	
	public void move(Graphics g) {
		x += dx;
		y += dy;
		//moveShots();
		
		//a += da;
		//rotate(g, sprite, a);
		if (rollReady && roll)
			roll();
	}
	
	private void moveShots() {
		int[][] arr = weapon.getArr();
		for (int i = 0; i < arr.length; i++) {
			arr[i][0] += dx;
			arr[i][1] += dy;
		}
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
    	
    	int finalX = x + dx;
    	int finalY = y + dy;
    	int ogX = player.getX();
    	int ogY = player.getY();
    	
    	//System.out.println(finalX + " " + ogX + " " + dx);
    	//System.out.println(finalY + " " + ogY + " " + dy);
    	
    	new Thread() {
    		public void run() {
    			character.setInvulnerable(true);
    			moveable = false;
    			int k = 0;
    			if (dx < 0)
    				k = -1;
    			else
    				k = 1;
    			while (x != finalX && k != 0) {
    				//if (!approaching(finalX, ogX, x))
    					//k = -k;
    				x += k;
    				try {
						Thread.sleep(2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}
    			moveable = true;
    			character.setInvulnerable(false);
    		}
    	}.start();
    	
    	new Thread() {
    		public void run() {
    			character.setInvulnerable(true);
    			moveable = false;
    			int k = 0;
    			if (dy < 0)
    				k = -1;
    			else
    				k = 1;
    			while (y != finalY) {
    				//if (!approaching(finalY, ogY, y))
    					//k = -k;
    				y += k;
    				try {
						Thread.sleep(2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}
    			moveable = true;
    			character.setInvulnerable(false);
    		}
    	}.start();

    	
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
    	dy = 0;
    	dx = 0;
    }
    
    public void toggleOffset() {
    	isOffset = !isOffset;
    	if (isOffset) {
    		player.setY((int) (instance.getHeight() * .50));
    		setY((int) (instance.getHeight() * .50));
    	} else {
    		player.setY((int) (instance.getHeight() * .75));
    		setY((int) (instance.getHeight() * .75));
    	}
    }
    
    public void rotate(Graphics g, Image image, int angle) {
    	Graphics2D g2d = (Graphics2D) g.create();
    	g2d.rotate(Math.toRadians(angle), player.getX(), player.getY());
    	g2d.drawImage(image, getX(), getY(), instance);
    	g2d.dispose();
    }
    
    public boolean approaching(int finalX, int ogX, int newX) {
    	int d1 = Math.abs(finalX - ogX);
    	int d2 = Math.abs(finalX - newX);
    	if (d1 > d2)
    		return true;
    	return false;
    }
    
}
