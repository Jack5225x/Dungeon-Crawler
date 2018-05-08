package com.jfeather.Player;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PlayerInstance {
	
	private int dx, dy, x, y, w, h, angle, da;
	private boolean right = false, left = false, up = false, down = false, roll = false;
	private Image sprite;
	private int rollCooldown = 1500;
	private boolean rollReady = true;
	private Character character;
	private int facing;
	
	public static int DIR_LEFT = 0;
	public static int DIR_RIGHT = 1;
	public static int DIR_UP = 2;
	public static int DIR_DOWN = 3;
	
	public PlayerInstance(Character c, int startX, int startY) {
		character = c;
		initialize();
		x = startX - sprite.getWidth(null) / 2;
		y = startY - sprite.getHeight(null) / 2;
		sprite = createImage("Sprites/Character/Character.png");
	}
	
	public void initialize() {
		sprite = createImage("Sprites/Character/CharacterUp.png");
		
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
	
	public void setDirection(int newDirection) {
		facing = newDirection;
	}
	
	public void move() {
		x += dx;
		y += dy;
		angle += da;
		if (roll && rollReady)
			roll();
	}
	
	public void setSprite(Image newSprite) {
		sprite = newSprite;
	}
	
	public void setSprite(String path) {
		sprite = createImage(path);
	}
	
	public void setRollCooldown(int  newCooldown) {
		rollCooldown = newCooldown;
	}
	
	public int getRollCooldown() {
		return rollCooldown;
	}
	
	public void setSpritePath(String imagePath) {
		ImageIcon icon = new ImageIcon(imagePath);
		sprite = icon.getImage();
	}
	
	public void setLeft(boolean newLeft) {
		left = newLeft;
	}
	
	public void setRight(boolean newRight) {
		right = newRight;
	}
	
	public void setUp(boolean newUp) {
		up = newUp;
	}
	
	public void setDown(boolean newDown) {
		down = newDown;
	}
	
	// https://help.adobe.com/en_US/AS2LCR/Flash_10.0/help.html?content=00000520.html
	// A list of key codes
	
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
        
    public void updateSprite(Graphics g, JPanel dialog) {
    	
    	Graphics2D g2d = (Graphics2D) g;
    	double angle = 0;
    	/*
    	switch (facing) {
    	case 0:
    		// Left
    		angle = Math.toRadians(-90);
    		break;
    	case 1:
    		// Right
    		angle = Math.toRadians(90);
    		break;
    	case 2:
    		// Up
    		angle = Math.toRadians(0);
    		break;
    	case 3:
    		// Down
    		angle = Math.toRadians(180);
    		break;
    	} */
    	//System.out.println(left + " " + right + " " + up + " " + down);
    	if (left && !right && !down && !up) {
    		facing = DIR_LEFT;
    		angle = Math.toRadians(-90);
    	} else {
    		if (!left && right && !down && !up) {
        		facing = DIR_RIGHT;
        		angle = Math.toRadians(90);
    		} else {
    			if (!left && !right && down && !up) {
    	    		facing = DIR_DOWN;
    	    		angle = Math.toRadians(180);
    			} else {
    				if (!left && !right && !down && up) {
    		    		facing = DIR_UP;
    		    		angle = Math.toRadians(0);
    				} else {
    					if (left && !right && down && !up) {
    						//System.out.println("Down left");
    						angle = Math.toRadians(-135);
    					} else {
    						if (left && !right && !down && up) {
    							//System.out.println("Up left");
    							angle = Math.toRadians(-45);
    						} else {
    							if (!left && right && down && !up) {
    								//System.out.println("Down right");
    								angle = Math.toRadians(135);
    							} else {
    								if (!left && right && !down && up) {
    									//System.out.println("Up right");
    									angle = Math.toRadians(45);
    								} else {
    									// Do nothing here, because this runs whenever nothing else changes
    								}
    							}
    						}
    					}
    				}
    			}
    		}
    	}

    	
    	
    	AffineTransform tx = AffineTransform.getRotateInstance(angle, x + sprite.getWidth(null) / 2, y + sprite.getHeight(null) / 2);
    	
    	g2d.setTransform(tx);
    	g2d.drawImage(sprite, x, y, null);

    }
    
	public Image createImage(String path) {
		// This also works for pngs, but it is the best way (i think) to use gifs, pngs can be done other ways but this works for both
		URL url = null;
		try {
			File link = new File(path);
			url = link.toURI().toURL();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return (new ImageIcon(url)).getImage();
	}
	
	public Character getCharacter() {
		return character;
	}
	
	public int getDirection() {
		return facing;
	}
	
	public void setX(int newX) {
		x = newX;
	}
	
	public void setY(int newY) {
		y = newY;
	}

}
