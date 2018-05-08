package com.jfeather.Items;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jfeather.Game.Line;
import com.jfeather.Items.DescrWrap;
import com.jfeather.Player.PlayerInstance;
public class Weapon {
	
	public static String SWORD = "sword";
	public static String DAGGER = "dagger";
	public static String WAND = "wand";
	public static String STAFF = "staff";
	public static String BOW = "bow";
	public static String HAMMER = "hammer";
	public static String CROSSBOW = "crossbow";
	
	public static String[] TYPES = {SWORD, DAGGER, WAND, STAFF, BOW, HAMMER, CROSSBOW};
	
	private int damage, strength, intelligence, speed, rarity, agility, luck;
	private double range;
	private ImageIcon sprite, projectile;
	private String name, toolTip, descr;
	private boolean click, shootReady = true;
	private int characterX, characterY;
	private int mouseX, mouseY;
	private JPanel dialog;
	private volatile int[][] arr;
	private volatile ArrayList<int[][]> shotPaths;
	private volatile int shotCount;
	
	public Weapon(String itemName, String itemDescr, int itemRarity, int itemDamage, int itemSpeed, double itemRange, int itemStrength, int itemIntelligence, int itemAgility, int itemLuck, ImageIcon itemSprite, ImageIcon itemProjectile) {
		strength = itemStrength;
		intelligence = itemIntelligence;
		damage = itemDamage;
		speed = itemSpeed;
		range = itemRange;
		agility = itemAgility;
		luck = itemLuck;
		rarity = itemRarity;
		sprite = itemSprite;
		projectile = itemProjectile;
		name = itemName;
		descr = itemDescr;
		String rarityColor = "";
		shotCount = 0;
		shotPaths = new ArrayList<>();
		switch (itemRarity) {
			case 0: rarityColor = "black"; break;
			case 1: rarityColor = "white"; break;
			case 2: rarityColor = "green"; break;
			case 3: rarityColor = "blue"; break;
			case 4: rarityColor = "purple"; break;
			case 5: rarityColor = "yellow"; break;
			case 6: rarityColor = "orange"; break;
			default: System.out.println("Invalid item rarity! ("+name+")"); rarityColor = "white"; break;
		}
		String stats = "";
		if (strength != 0)  
			stats = stats + "Strength +<font color='red'>"+strength+"</font><br>";
		if (intelligence != 0)
			stats = stats + "Intelligence +<font color='red'>"+intelligence+"</font><br>";
		if (agility != 0)
			stats = stats + "Agility +<font color='red'>"+agility+"</font><br>";
		if (luck != 0)
			stats = stats + "Luck +<font color='red'>"+luck+"</font><br>";
		
		toolTip = "<html> <b><font color='"+rarityColor+"'>"+name+"</font><br>Damage: <font color='red'>"+damage+"</font><br>Speed: <font color='red'>"+speed+"</font><br>"+stats+"<i>"+DescrWrap.descrWrap(descr, name)+"</html>";
	}
	
	public void debugWeapon(Weapon sword) {
		System.out.println("Name: "+sword.name);
		System.out.println("Damage: "+sword.damage);
		System.out.println("Speed: "+sword.speed);
		System.out.println("Rarity ID: "+sword.rarity);
		System.out.println("Description (Formatted): "+DescrWrap.descrWrap(sword.descr, name));
	}
	
	public int getDamage() {
		return damage;
	}
	
	public int getStrength() {
		return strength;
	}
	
	public int getIntelligence() {
		return intelligence;
	}
	
	public int getAgility() {
		return agility;
	}
	
	public int getLuck() {
		return luck;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int newSpeed) {
		speed = newSpeed;
	}
	
	public double getRange() {
		return range;
	}
	
	public int getRarity() {
		return rarity;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescr() {
		return descr;
	}
	
	public String getToolTip() {
		return toolTip;
	}
	
	public ImageIcon getSprite() {
		return sprite;
	}
	
	public ImageIcon getProjectile() {
		return projectile;
	}
	
	public void setPlayerCoords(PlayerInstance player) {
		characterX = player.getX() + player.getWidth() / 2;
		characterY = player.getY() + player.getHeight() / 2;
	}
	
	public void shoot(int xo, int yo, int xf, int yf, JPanel dialog) {
		if (name != null) {
			Line path = new Line(xo, yo, xf, yf);
			shotPaths.add(shotCount, path.genPoints(range));
			//path.printMatrix(arr);
			
			Graphics2D g2d = (Graphics2D) dialog.getGraphics();
			double angle = Math.toRadians(path.getAngleFromX());
			
			int index = shotCount;
			shotCount++;
			new Thread() {
				public void run() {
					// Create a label for each position the projectile will be in during its path
					for (int i = 0; i < shotPaths.get(index).length; i++) {
				    	AffineTransform tx = AffineTransform.getRotateInstance(Math.PI / 2  - angle, shotPaths.get(index)[i][0] + sprite.getIconWidth() / 2, shotPaths.get(index)[i][1] + sprite.getIconHeight() / 2);
						g2d.setTransform(tx);
				    	g2d.drawImage(projectile.getImage(), shotPaths.get(index)[i][0], shotPaths.get(index)[i][1], null);
						try {
							Thread.sleep((int) (speed * 1.5));
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						// Pretty sure its just luck that makes this work
						// i.e. you don't have to delete the projectiles because they just get written over
					}
				}
				
			}.start();
		}
	}
	
	public void updateShoot() {
		// A method that will be run continuously that will reference shoot at certain times
		if (click && shootReady) {
			//System.out.println(characterX + " " + characterY);
			shoot(characterX, characterY, mouseX, mouseY, dialog);
			new Thread() {
				public void run() {
					shootReady = false;
					try {
						// TODO: Make this properly scale with speed or something
						// It sorta does this now, but it can certainly be improved
						Thread.sleep(1000 / speed);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// Reset the cooldown
					shootReady = true;
				}
			}.start();
		}
	}
	
	public void mousePressed(MouseEvent e, int mouseLocX, int mouseLocY, JPanel dialog) {
		click = true;
		mouseX = mouseLocX;
		mouseY = mouseLocY;
		this.dialog = dialog;
	}
	
	public void mouseReleased() {
		click = false;
	}
	
	public boolean getClick() {
		return click;
	}
	
	public void setMouseX(int x) {
		mouseX = x;
	}
	
	public void setMouseY(int y) {
		mouseY = y;
	}
	
	public void setArr(int[][] newArr) {
		shotPaths.set(shotCount, newArr);
	}
	
	public int[][] getArr() {
		if (shotPaths.get(shotCount) != null)
			return shotPaths.get(shotCount);
		return new int[1][2];
	}
}
