package com.jfeather.Player;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;

import javax.swing.ImageIcon;

public class PlayerInstance {
	
	private int dx, dy, x = 100, y = 100, w, h, angle, da;
	private boolean right = false, left = false, up = false, down = false, roll = false;
	private Image sprite;
	private int rollCooldown = 2500;
	private boolean rollReady = true;
	private Character character;
	
	public PlayerInstance(Character c) {
		character = c;
		initialize();
	}
	
	public void initialize() {
		ImageIcon icon = new ImageIcon("Sprites/Character/CharacterUp.png");
		sprite = icon.getImage();
		
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
	
	public void move() {
		x += dx;
		y += dy;
		angle += da;
		updateSprite();
		if (roll && rollReady)
			roll();
	}
	
	public void setSprite(Image newSprite) {
		sprite = newSprite;
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
    
    public void updateSprite() {
    	// Round 2 baby
    	if (left && !right && !down && !up) {
    		sprite = gif("Sprites/Character/CharacterLeft.png");
    	} else {
    		if (!left && right && !down && !up) {
        		sprite = gif("Sprites/Character/CharacterRight.png");
    		} else {
    			if (!left && !right && down && !up) {
    	    		sprite = gif("Sprites/Character/CharacterDown.png");
    			} else {
    				if (!left && !right && !down && up) {
    		    		sprite = gif("Sprites/Character/CharacterUp.png");
    				} else {
    					if (left && !right && down && !up) {
    						//System.out.println("Down left");
    					} else {
    						if (left && !right && !down && up) {
    							//System.out.println("Up left");
    						} else {
    							if (!left && right && down && !up) {
    								//System.out.println("Down right");
    							} else {
    								if (!left && right && !down && up) {
    									//System.out.println("Up right");
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
    }
    
	public Image gif(String path) {
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

}
