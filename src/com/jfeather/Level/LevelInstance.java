package com.jfeather.Level;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

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
	private HashMap<Integer, Image> sprites;
	private HashMap<Integer, ArrayList<Integer>> spriteLocations;
	private int mapCount;
	private int x, y, a, dx, dy, da;
	private boolean left, right, down, up, roll, rollReady = true;
	private int rollCooldown;
	private boolean isOffset = false;
	private boolean moveable = true;
	private Weapon weapon;
	private Robot bot;
	private Color background;
	private Theme theme;
	
	public static final int ROLL_COST = 25;
	
	public LevelInstance(int floorNumber, PlayerInstance p, GameInstance i, Theme newTheme) {
		floor = floorNumber;
		player = p;
		instance = i;
		character = player.getCharacter();
		weapon = character.getActiveWeapon();
		a = 0;
		theme = newTheme;
		left = false;
		right = false;
		up = false;
		down = false;
		character = player.getCharacter();
		sprite = (new ImageIcon("Sprites/Level/TestLevel.png")).getImage();
		mapCount = 0;
		sprites = new HashMap<Integer, Image>();
		spriteLocations = new HashMap<Integer, ArrayList<Integer>>();
		setCoords((player.getX() / 2) - getWidth() / 2, (int)(player.getY() * .5) - getHeight() / 2);
		background = theme.getBackground();
		
		try {
			bot = new Robot();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getFloor() {
		return floor;
	}
	
	public boolean getMoveable() {
		return moveable;
	}
	
	public void setMoveable(boolean newMove) {
		moveable = newMove;
	}
	
	public void setRollReady(boolean toggle) {
		rollReady = toggle;
	}
	
	public boolean getRollReady() {
		return rollReady;
	}
	
	public int getRollCooldown() {
		return rollCooldown;
	}
	
	public PlayerInstance getPlayer() {
		return player;
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
	
	public void setdX(int newdX) {
		dx = newdX;
	}
	
	public void setdY(int newdY) {
		dy = newdY;
	}

	public void setRight(boolean newRight) {
		right = newRight;
		player.setRight(newRight);
	}
	
	public void setLeft(boolean newLeft) {
		left = newLeft;
		player.setLeft(newLeft);
	}
	
	public void setUp(boolean newUp) {
		up = newUp;
		player.setUp(newUp);
	}
	
	public void setDown(boolean newDown) {
		down = newDown;
		player.setDown(newDown);
	}
	
	public boolean getUp() {
		return up;
	}
	
	public boolean getDown() {
		return down;
	}
	
	public boolean getLeft() {
		return left;
	}
	
	public boolean getRight() {
		return right;
	}
		
	public ArrayList<Integer> getCoords() {
		return new ArrayList<Integer> (Arrays.asList(x, y));
	}
	
	public void addImage(Image newSprite, int xCoord, int yCoord) {
		sprites.put(mapCount, newSprite);
		spriteLocations.put(mapCount, new ArrayList<Integer> (Arrays.asList(xCoord, yCoord)));
		mapCount++;
	}
	
	public void addImage(Image newSprite, ArrayList<Integer> list) {
		sprites.put(mapCount, newSprite);
		spriteLocations.put(mapCount, list);
		mapCount++;
	}
	
	public void addImage(Image newSprite) {
		sprites.put(mapCount, newSprite);
		spriteLocations.put(mapCount, getCoords());
		mapCount++;
	}
	
	public int getMapCount() {
		return mapCount;
	}
	
	public HashMap<Integer, Image> getSpriteMap() {
		return sprites;
	}
	
	public void setSpriteMap(HashMap<Integer, Image> map) {
		sprites = map;
	}
	
	public HashMap<Integer, ArrayList<Integer>> getSpriteLocations() {
		return spriteLocations;
	}
	
	public void setSpriteLocationMap(HashMap<Integer, ArrayList<Integer>> map) {
		spriteLocations = map;
	}
	
	public void move(Graphics g) {
		// TODO: make actual bounds
		//if (!isOutOfBounds()) {
			// Old stuff
			x += dx;
			y += dy;
			//moveShots();
			
			addIncrementsToMap(dx, dy);
		//}
	}
	
	public void addIncrementsToMap(int dx, int dy) {
		Iterator it = spriteLocations.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, ArrayList<Integer>> next = (Map.Entry<Integer, ArrayList<Integer>>) it.next();
			next.setValue(new ArrayList<Integer> (Arrays.asList(next.getValue().get(0) + dx, next.getValue().get(1) + dy)));
		}
	}
	
	private void moveShots() {
		weapon = character.getActiveWeapon();
		//if (weapon != null) {
			int[][] arr = weapon.getArr();
			System.out.println(arr.length);
			for (int i = 0; i < arr.length; i++) {
				arr[i][0] += dx;
				arr[i][1] += dy;
			}
			weapon.setArr(arr);
			System.out.println("moved");
		//}
	}
	
	private boolean isOutOfBounds() {
		// TODO: Fix this
		int dir = player.getDirection();
		int x = 0, y = 0;
		switch (dir) {
			case 0:
				// Left
				x = player.getX();
				y = player.getY() + player.getHeight() / 2;
				//System.out.println("Left");
				break;
			case 1:
				// Right
				x = player.getX() + player.getWidth();
				y = player.getY() + player.getHeight() / 2;
				//System.out.println("Right");
				break;
			case 2:
				// Up
				x = player.getX() + player.getWidth() / 2;
				y = player.getY();
				//System.out.println("Up");
				break;
			case 3:
				// Down
				x = player.getX() + player.getWidth() / 2;
				y = player.getY() + player.getHeight();
				//System.out.println("Down");
				break;
		}
		//System.out.println(x + "  " + y);
		//System.out.println(player.getX() + " " + player.getY());
		Color pixel = bot.getPixelColor(x, y);
		//System.out.println(pixel);
		if (pixel.getRed() == background.getRed() && pixel.getBlue() == background.getBlue() && pixel.getGreen() == background.getGreen())
			return true;
		return false;
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
    			character.setInvulnerable(true);
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
    				addIncrementsToMap(k, 0);
    				try {
						Thread.sleep(2);
					} catch (InterruptedException e) {
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
    			while (spriteLocations.get(0).get(1) != finalY && k != 0) {
    				//if (!approaching(finalY, ogY, spriteLocations.get(0).get(1)))
    					//k = -k;
    					//break;
    				addIncrementsToMap(0, k);
    				try {
						Thread.sleep(2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
    			}
    			moveable = true;
    			character.setInvulnerable(false);
    		}
    	}.start();

    	
    	// Activate the cooldown
    	if (character.getAgility() < 100)
    		rollCooldown = 1500 - character.getAgility() * 10;
    	else
    		rollCooldown = 500;
    	rollReady = false;
    	new Thread() {
    		@Override
    		public void run() {
    			try {
					Thread.sleep(rollCooldown);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    			rollReady = true;
    		}
    	}.start();
    	dy = 0;
    	dx = 0; 
    	character.subtractMana(ROLL_COST);
    }
    
    // Deprecated Method (more or less)
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
    
    // Deprecated Method
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
    
    public void repaintLevel(Graphics2D g2d) {
    	Iterator it = sprites.entrySet().iterator();
    	Iterator it2 = spriteLocations.entrySet().iterator();
    	while (it.hasNext()) {
    		Map.Entry<Integer, Image> nextImage = (Map.Entry<Integer, Image>) it.next();
    		Map.Entry<Integer, ArrayList<Integer>> nextPoint = (Map.Entry<Integer, ArrayList<Integer>>) it2.next();
    		int x = nextPoint.getValue().get(0);
    		int y = nextPoint.getValue().get(1);
    		Image image = nextImage.getValue();
    		g2d.drawImage(image, x, y, null);
    	}
    }
    
    
}
